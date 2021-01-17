package de.itsstuttgart.chessserver.util.model;

/**
 * created by paul on 17.01.21 at 21:29
 */
public class HistoryGame {

    private String fen;
    private long gameEnd;
    private boolean win;
    private byte finishReason;

    public HistoryGame() {
    }

    public HistoryGame(String fen, boolean win, byte finishReason) {
        this.fen = fen;
        this.gameEnd = System.currentTimeMillis();
        this.win = win;
        this.finishReason = finishReason;
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
}
