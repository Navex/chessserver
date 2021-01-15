package de.itsstuttgart.chessserver;

import de.itsstuttgart.chessserver.clients.ChessClient;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.net.Socket;

/**
 * created by paul on 15.01.21 at 18:52
 */
public class ConnectionListener implements Runnable {

    private static final Logger logger = LogManager.getLogger();

    private final ChessServer server;

    public ConnectionListener(ChessServer server) {
        this.server = server;

        new Thread(this, "Accept " + this.server.getSocket().getLocalPort()).start();
    }

    @Override
    public void run() {
        while (this.server.isRunning()) {
            try {
                Socket client = this.server.getSocket().accept();
                logger.trace("Accepted {}", client.getInetAddress().getHostAddress());
                this.server.getConnectedClients().add(new ChessClient(this.server, client));
            } catch (IOException e) {
                logger.error("Failed accepting client on port {}", this.server.getSocket().getLocalPort(), e);
            }
        }
    }
}
