package de.itsstuttgart.chessserver.clients;

import de.itsstuttgart.chessserver.util.ByteUtils;
import de.itsstuttgart.chessserver.util.DataType;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.UUID;

/**
 * created by paul on 17.01.21 at 11:29
 */
public class ChessMatch {

    private static final Logger logger = LogManager.getLogger();

    private final UUID matchIdentifier;
    private ChessClient white;
    private ChessClient black;

    public ChessMatch(ChessClient white, ChessClient black) {
        this.matchIdentifier = UUID.randomUUID();

        this.white = white;
        this.black = black;

        this.white.setCurrentMatch(this);
        this.black.setCurrentMatch(this);
    }

    public void start() {
        logger.info("Match started: {}, White: {}, Black: {}", this.matchIdentifier, this.white.getUserModel().getUsername(), this.black.getUserModel().getUsername());
        byte[] startGame = new byte[2 + DataType.getSize(DataType.LONG) * 2 + 1 + DataType.getSize(DataType.SHORT) + this.black.getUserModel().getUsername().length()];
        startGame[0] = 0x73;
        startGame[1] = 0x67;
        ByteUtils.writeBytes(startGame, 2, this.matchIdentifier.getMostSignificantBits());
        ByteUtils.writeBytes(startGame, 10, this.matchIdentifier.getLeastSignificantBits());
        startGame[18] = 0x77; // w
        ByteUtils.writeBytes(startGame, 19, this.black.getUserModel().getUsername());
        this.white.send(startGame);

        startGame = new byte[2 + DataType.getSize(DataType.LONG) * 2 + 1 + DataType.getSize(DataType.SHORT) + this.white.getUserModel().getUsername().length()];
        startGame[0] = 0x73;
        startGame[1] = 0x67;
        ByteUtils.writeBytes(startGame, 2, this.matchIdentifier.getMostSignificantBits());
        ByteUtils.writeBytes(startGame, 10, this.matchIdentifier.getLeastSignificantBits());
        startGame[18] = 0x62; // b
        ByteUtils.writeBytes(startGame, 19, this.white.getUserModel().getUsername());
        this.black.send(startGame);
    }

    public void sendBoth(byte[] data) {
        this.white.send(data);
        this.black.send(data);
    }

    public UUID getMatchIdentifier() {
        return matchIdentifier;
    }

    public void setWhite(ChessClient white) {
        this.white = white;
    }

    public void setBlack(ChessClient black) {
        this.black = black;
    }

    public ChessClient getOpponent(ChessClient client) {
        return client.equals(this.white) ? this.black : this.white;
    }

    /**
     * Ends the game with given winner, use null as draw
     *
     * @param winner who to award
     */
    public void finish(ChessClient winner, byte reason) {
        if (winner == null) {
            this.white.getUserModel().setDraws(this.white.getUserModel().getDraws() + 1);
            this.white.getServer().getUserRepository().save(this.white.getUserModel());
            this.black.getUserModel().setDraws(this.black.getUserModel().getDraws() + 1);
            this.black.getServer().getUserRepository().save(this.black.getUserModel());
            this.sendBoth(new byte[] {0x2a, 0x66, 0x2, reason});

            this.white.setCurrentMatch(null);
            this.black.setCurrentMatch(null);
            return;
        }

        ChessClient looser = getOpponent(winner);
        // add the win
        winner.getUserModel().setWins(winner.getUserModel().getWins() + 1);
        winner.getServer().getUserRepository().save(winner.getUserModel());
        winner.send(new byte[] {0x2a, 0x66, 0x0, reason});

        // add the loose
        looser.getUserModel().setLooses(looser.getUserModel().getDraws() + 1);
        looser.getServer().getUserRepository().save(looser.getUserModel());
        looser.send(new byte[] {0x2a, 0x66, 0x1, reason});

        this.white.setCurrentMatch(null);
        this.black.setCurrentMatch(null);
    }
}
