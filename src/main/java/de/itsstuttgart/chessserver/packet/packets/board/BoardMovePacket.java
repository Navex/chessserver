package de.itsstuttgart.chessserver.packet.packets.board;

import de.itsstuttgart.chessserver.clients.ChessClient;
import de.itsstuttgart.chessserver.clients.ChessMatch;
import de.itsstuttgart.chessserver.packet.Packet;
import de.itsstuttgart.chessserver.packet.PacketHeader;
import de.itsstuttgart.chessserver.util.ByteUtils;

import java.util.UUID;

/**
 * created by paul on 17.01.21 at 15:11
 */
@PacketHeader({0x2a, 0x6d})
public class BoardMovePacket implements Packet {

    @Override
    public void process(byte[] data, ChessClient client) {
        long[] uuid = new long[2];
        ByteUtils.readLongs(data, 0, uuid);
        UUID matchIdentifier = new UUID(uuid[0], uuid[1]);
//        System.out.println(matchIdentifier + ": " + Integer.toHexString(data[16]) + " to " + Integer.toHexString(data[17]));

        ChessMatch match = client.getServer().findMatchByIdentifier(matchIdentifier);
        if (match != null) {
            // forward move to client
            match.getOpponent(client).send(new byte[] {0x2a, 0x6d, data[16], data[17]});
        }
    }
}
