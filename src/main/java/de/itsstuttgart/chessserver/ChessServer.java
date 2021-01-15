package de.itsstuttgart.chessserver;

import de.itsstuttgart.chessserver.clients.ChessClient;
import de.itsstuttgart.chessserver.packet.PacketHandler;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.net.ServerSocket;
import java.util.ArrayList;
import java.util.List;

/**
 * created by paul on 15.01.21 at 18:50
 */
public class ChessServer {

    private static final Logger logger = LogManager.getLogger();

    public static void main(String[] args) throws IOException {
        new ChessServer(53729);
    }

    private final ServerSocket socket;
    private final ConnectionListener listener;

    private List<ChessClient> connectedClients;
    private PacketHandler packetHandler;

    private boolean running;

    /**
     * Creates a new chess server on the specified port
     *
     * @param port port to listen on
     * @throws IOException any exceptions (port not available, insufficient permissions...)
     */
    public ChessServer(int port) throws IOException {
        logger.info("Starting chess server...");

        // Mark server as running
        this.running = true;

        this.connectedClients = new ArrayList<>();
        this.packetHandler = new PacketHandler();

        this.socket = new ServerSocket(port);
        this.listener = new ConnectionListener(this);

        logger.info("Now listening on port {}", port);
    }

    public ServerSocket getSocket() {
        return socket;
    }

    public ConnectionListener getListener() {
        return listener;
    }

    /**
     * Checks if the server is currently running
     *
     * @return status
     */
    public boolean isRunning() {
        return this.running && !this.socket.isClosed();
    }

    public List<ChessClient> getConnectedClients() {
        return connectedClients;
    }

    public PacketHandler getPacketHandler() {
        return packetHandler;
    }

    public void shutdown() {
        this.running = false;
        try {
            this.socket.close();
        } catch (IOException e) {
            logger.error("Failed stopping server", e);
        }
    }
}
