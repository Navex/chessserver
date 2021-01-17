package de.itsstuttgart.chessserver.packet.packets.board;

import de.itsstuttgart.chessserver.clients.ChessClient;
import de.itsstuttgart.chessserver.clients.ChessMatch;
import de.itsstuttgart.chessserver.packet.Packet;
import de.itsstuttgart.chessserver.packet.PacketHeader;
import de.itsstuttgart.chessserver.util.ByteUtils;

import java.util.UUID;

/**
 * created by paul on 17.01.21 at 18:42
 */
@PacketHeader({0x2a, 0x66})
public class BoardFinishPacket implements Packet {

    @Override
    public void process(byte[] data, ChessClient client) {
        long[] uuid = new long[2];
        ByteUtils.readLongs(data, 0, uuid);
        UUID matchIdentifier = new UUID(uuid[0], uuid[1]);

        ChessMatch match = client.getServer().findMatchByIdentifier(matchIdentifier);
        byte reason = data[16];
        String fen = ByteUtils.readString(data, 19, ByteUtils.readShort(data, 17));
        System.out.println(fen);
        if (match != null) {
            switch (reason) {
                case 0x0: // resignation
                case 0x1: // checkmate
                    match.finish(match.getOpponent(client), reason, fen);
                    break;
                case 0x2: // stalemate
                case 0x3: // accepted draw offer
                    match.finish(null, reason, fen);
                    break;
            }
        }
    }
}
