package de.itsstuttgart.chessserver.packet.packets;

import de.itsstuttgart.chessserver.clients.ChessClient;
import de.itsstuttgart.chessserver.packet.Packet;
import de.itsstuttgart.chessserver.packet.PacketHeader;
import de.itsstuttgart.chessserver.util.ByteUtils;
import de.itsstuttgart.chessserver.util.DataType;
import de.itsstuttgart.chessserver.util.model.HistoryGame;
import de.itsstuttgart.chessserver.util.model.UserModel;

import java.util.List;
import java.util.stream.Collectors;

/**
 * created by paul on 17.01.21 at 21:09
 */
@PacketHeader({0x40, 0x64})
public class DashboardRequestPacket implements Packet {

    @Override
    public void process(byte[] data, ChessClient client) {
        UserModel model = client.getUserModel();
        int size = 0;
        size += DataType.getSize(DataType.BYTE) * 2;
        size += DataType.getSize(DataType.INTEGER) * 3;
        List<HistoryGame> pastGames = model.getPastGames().stream().sorted((h1, h2) -> Long.compare(h2.getGameEnd(), h1.getGameEnd())).collect(Collectors.toList());
        size += DataType.getSize(DataType.BYTE);
        for (int i = 0; i < Math.min(3, pastGames.size()); i++) {
            HistoryGame past = pastGames.get(i);
            size += DataType.getSize(DataType.LONG) * 2; // identifier
            size += DataType.getSize(DataType.SHORT); // fen
            size += past.getFen().length();
            size += DataType.getSize(DataType.LONG); // date
            size += DataType.getSize(DataType.BYTE) * 3; // win + reason + playedwhite
        }

        byte[] packet = new byte[size];
        int pointer = 0;
        pointer = ByteUtils.writeBytes(packet, pointer, (byte)0x40);
        pointer = ByteUtils.writeBytes(packet, pointer, (byte)0x64);
        pointer = ByteUtils.writeBytes(packet, pointer, model.getWins());
        pointer = ByteUtils.writeBytes(packet, pointer, model.getLooses());
        pointer = ByteUtils.writeBytes(packet, pointer, model.getDraws());
        pointer = ByteUtils.writeBytes(packet, pointer, (byte) Math.min(3, pastGames.size()));
        for (int i = 0; i < Math.min(3, pastGames.size()); i++) {
            HistoryGame past = pastGames.get(i);
            pointer = ByteUtils.writeBytes(packet, pointer, past.getMatchIdentifier().getMostSignificantBits());
            pointer = ByteUtils.writeBytes(packet, pointer, past.getMatchIdentifier().getLeastSignificantBits());
            pointer = ByteUtils.writeBytes(packet, pointer, past.getFen());
            pointer = ByteUtils.writeBytes(packet, pointer, past.getGameEnd());
            pointer = ByteUtils.writeBytes(packet, pointer, past.isWin());
            pointer = ByteUtils.writeBytes(packet, pointer, past.getFinishReason());
            pointer = ByteUtils.writeBytes(packet, pointer, past.hasPlayedWhite());
        }
        client.send(packet);
    }
}
