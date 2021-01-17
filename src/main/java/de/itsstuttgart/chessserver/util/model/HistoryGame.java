package de.itsstuttgart.chessserver.util.model;

import java.util.UUID;

/**
 * created by paul on 17.01.21 at 21:29
 */
public class HistoryGame {

    private UUID matchIdentifier;
    private String fen;
    private long gameEnd;
    private boolean win;
    private byte finishReason;
    private boolean playedWhite;

    public HistoryGame() {
    }

    public HistoryGame(UUID matchIdentifier, String fen, boolean win, byte finishReason, boolean playedWhite) {
        this.matchIdentifier = matchIdentifier;
        this.fen = fen;
        this.gameEnd = System.currentTimeMillis();
        this.win = win;
        this.finishReason = finishReason;
        this.playedWhite = playedWhite;
    }

    public UUID getMatchIdentifier() {
        return matchIdentifier;
    }

    public void setMatchIdentifier(UUID matchIdentifier) {
        this.matchIdentifier = matchIdentifier;
    }

    public String getFen() {
        return fen;
    }

    public void setFen(String fen) {
        this.fen = fen;
    }

    public long getGameEnd() {
        return gameEnd;
    }

    public void setGameEnd(long gameEnd) {
        this.gameEnd = gameEnd;
    }

    public boolean isWin() {
        return win;
    }

    public void setWin(boolean win) {
        this.win = win;
    }

    public byte getFinishReason() {
        return finishReason;
    }

    public void setFinishReason(byte finishReason) {
        this.finishReason = finishReason;
    }

    public boolean hasPlayedWhite() {
        return playedWhite;
    }

    public void setPlayedWhite(boolean playedWhite) {
        this.playedWhite = playedWhite;
    }
}
