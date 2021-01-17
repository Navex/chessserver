package de.itsstuttgart.chessserver.util.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * created by paul on 15.01.21 at 20:08
 */
@Document("chessuser")
public class UserModel {

    @Id
    private String id;

    private String username;

    private byte[] salt;
    private byte[] hash;

    private int wins;
    private int looses;
    private int draws;

    private String theme;
    private List<HistoryGame> pastGames = new ArrayList<>();

    public UserModel() {
    }

    public UserModel(String username, String password) {
        this.username = username;

        SecureRandom sr = new SecureRandom();
        this.salt = new byte[16];
        sr.nextBytes(this.salt);

        try {
            KeySpec spec = new PBEKeySpec(password.toCharArray(), this.salt, 65536, 128);
            SecretKeyFactory factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");

            this.hash = factory.generateSecret(spec).getEncoded();
        } catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
            e.printStackTrace();
        }
    }

    public UserModel(String username, byte[] salt, byte[] hash) {
        this.username = username;
        this.salt = salt;
        this.hash = hash;
    }

    public UserModel(String username, byte[] salt, byte[] hash, int wins, int looses, int draws, String theme, List<HistoryGame> pastGames) {
        this.username = username;
        this.salt = salt;
        this.hash = hash;
        this.wins = wins;
        this.looses = looses;
        this.draws = draws;
        this.theme = theme;
        this.pastGames = pastGames;
    }

    public boolean checkPassword(String password) {
        try {
            KeySpec spec = new PBEKeySpec(password.toCharArray(), this.salt, 65536, 128);
            SecretKeyFactory factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");

            if (Arrays.equals(factory.generateSecret(spec).getEncoded(), this.hash)) {
                return true;
            }
        } catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
            e.printStackTrace();
        }
        return false;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public byte[] getSalt() {
        return salt;
    }

    public void setSalt(byte[] salt) {
        this.salt = salt;
    }

    public byte[] getHash() {
        return hash;
    }

    public void setHash(byte[] hash) {
        this.hash = hash;
    }

    public int getWins() {
        return wins;
    }

    public void setWins(int wins) {
        this.wins = wins;
    }

    public int getLooses() {
        return looses;
    }

    public void setLooses(int looses) {
        this.looses = looses;
    }

    public int getDraws() {
        return draws;
    }

    public void setDraws(int draws) {
        this.draws = draws;
    }

    public String getTheme() {
        return theme;
    }

    public void setTheme(String theme) {
        this.theme = theme;
    }

    public List<HistoryGame> getPastGames() {
        return pastGames;
    }

    public void setPastGames(List<HistoryGame> pastGames) {
        this.pastGames = pastGames;
    }
}
