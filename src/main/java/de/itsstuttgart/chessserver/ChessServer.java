package de.itsstuttgart.chessserver;

import de.itsstuttgart.chessserver.clients.ChessClient;
import de.itsstuttgart.chessserver.clients.ChessMatch;
import de.itsstuttgart.chessserver.packet.PacketHandler;
import de.itsstuttgart.chessserver.util.repositories.UserRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.ServerSocket;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * created by paul on 15.01.21 at 18:50
 */
@Service
public class ChessServer {

    private static final Logger logger = LogManager.getLogger();

    private final ServerSocket socket;

    private final List<ChessClient> connectedClients;
    private final PacketHandler packetHandler;

    private boolean running;
    private final UserRepository userRepository;

    /**
     * Creates a new chess server on the specified port
     *
     * @throws IOException any exceptions (port not available, insufficient permissions...)
     */
    public ChessServer(UserRepository userRepository) throws IOException {
        logger.info("Starting chess server...");

        // Mark server as running
        this.running = true;

        this.connectedClients = new ArrayList<>();
        this.packetHandler = new PacketHandler();
        this.userRepository = userRepository;

        this.socket = new ServerSocket(53729);
        new ConnectionListener(this);

        logger.info("Now listening on port {}", 53729);
    }

    public ServerSocket getSocket() {
        return socket;
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

    public UserRepository getUserRepository() {
        return userRepository;
    }

    public ChessMatch findMatchByIdentifier(UUID matchIdentifier) {
        return this.getConnectedClients().stream()
                .filter(c -> c.isInGame()
                        && c.getCurrentMatch().getMatchIdentifier().equals(matchIdentifier))
                .map(ChessClient::getCurrentMatch)
                .findFirst().orElse(null);
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
