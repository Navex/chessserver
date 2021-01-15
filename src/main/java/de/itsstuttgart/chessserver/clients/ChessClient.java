package de.itsstuttgart.chessserver.clients;

import de.itsstuttgart.chessserver.ChessServer;
import de.itsstuttgart.chessserver.util.ByteUtils;
import de.itsstuttgart.chessserver.util.DataType;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.*;
import java.net.Socket;
import java.net.SocketException;
import java.security.SecureRandom;
import java.util.UUID;

/**
 * created by paul on 15.01.21 at 19:00
 */
public class ChessClient implements Runnable {

    private static final Logger logger = LogManager.getLogger();

    private final UUID clientIdentifier;
    private final ChessServer server;
    private final long connectedAt;
    private final Socket socket;
    private final byte xorSecret;

    private BufferedInputStream inputStream;
    private BufferedOutputStream outputStream;

    public ChessClient(ChessServer server, Socket socket) {
        SecureRandom xorSecretRandom = new SecureRandom();
        this.clientIdentifier = UUID.randomUUID();
        this.server = server;
        this.connectedAt = System.currentTimeMillis();
        this.socket = socket;

        byte[] secret = new byte[1];
        xorSecretRandom.nextBytes(secret);
        this.xorSecret = secret[0];

        try {
            this.socket.setSoTimeout(30 * 1000);
            this.socket.setTcpNoDelay(true);

            this.inputStream = new BufferedInputStream(this.socket.getInputStream());
            this.outputStream = new BufferedOutputStream(this.socket.getOutputStream());

            // send client the xor secret
            this.outputStream.write(xorSecret);
            this.outputStream.flush();
            logger.info("Sent xor secret {}", Integer.toBinaryString(xorSecret & 0xff).replace(' ', '0'));
        } catch (IOException e) {
            this.disconnect();
        }

        new Thread(this, "R " + socket.getInetAddress().getHostAddress()).start();
    }

    @Override
    public void run() {
        try {
            InputStream in = this.inputStream;

            while (this.isConnected()) {
                byte[] lengthBytes = new byte[DataType.getSize(DataType.INTEGER)];
                for (int i = 0; i < 4; i++)
                    lengthBytes[i] = (byte) in.read();
                int length = ByteUtils.readInteger(lengthBytes, 0);
                if (length < 0)
                    break;
                byte[] packet = new byte[length];
                int n = 0;
                while (n < length) {
                    int count = in.read(packet, n, length - n);
                    if (count < 0)
                        throw new EOFException();
                    n += count;
                }
                byte[] xorPacket = new byte[packet.length];
                for (int i = 0; i < packet.length; i++) {
                    xorPacket[i] = (byte) (packet[i] ^ this.xorSecret);
                }

                // Ignore empty packets
                if (packet.length > 0) {
                    this.server.getPacketHandler().processPacket(xorPacket, this);
                }
            }
        } catch (Exception e) {
            // if any actual exception occurs, just disconnect the client, it's fine
            logger.error(e);
        }
        this.disconnect();
    }

    public void send(byte[] data) {
        try {
            byte[] packetSize = new byte[DataType.getSize(DataType.INTEGER)];
            ByteUtils.writeBytes(packetSize, 0, data.length);
            this.outputStream.write(packetSize);
            byte[] xorData = new byte[data.length];
            for (int i = 0; i < data.length; i++) {
                xorData[i] = (byte) (data[i] ^ this.xorSecret);
            }
            this.outputStream.write(xorData);
            this.outputStream.flush();
        } catch (Exception e) {
            logger.error("Failed to send {} byte{} of data.", data.length, data.length == 1 ? "" : "s");
            logger.error("Disconnecting client with reason: {}", e.toString());
            this.disconnect();
        }
    }

    public long getConnectedAt() {
        return connectedAt;
    }

    /**
     * Checks if the socket of this client is still connected to the server
     * @return connection status
     */
    public boolean isConnected() {
        return this.socket.isConnected() && !this.socket.isClosed() && !this.socket.isInputShutdown() && !this.socket.isOutputShutdown();
    }

    private void disconnect() {
        logger.info("Disconnecting {}", this.socket.getInetAddress().getHostAddress());
        server.getConnectedClients().removeIf(c -> c.clientIdentifier.equals(this.clientIdentifier));
        try {
            if (this.isConnected())
                this.socket.close();
        } catch (IOException e) {
            logger.error("Failed disconnecting {}", this.socket.getInetAddress().getHostAddress(), e);
        }
    }

}
