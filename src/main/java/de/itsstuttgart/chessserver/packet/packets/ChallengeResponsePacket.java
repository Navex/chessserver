package de.itsstuttgart.chessserver.packet.packets;

import de.itsstuttgart.chessserver.clients.ChessClient;
import de.itsstuttgart.chessserver.clients.ChessMatch;
import de.itsstuttgart.chessserver.packet.Packet;
import de.itsstuttgart.chessserver.packet.PacketHeader;
import de.itsstuttgart.chessserver.util.ByteUtils;
import de.itsstuttgart.chessserver.util.DataType;

import java.util.Optional;
import java.util.UUID;

/**
 * created by paul on 17.01.21 at 11:23
 */
@PacketHeader({0x63, 0x63})
public class ChallengeResponsePacket implements Packet {

    @Override
    public void process(byte[] data, ChessClient client) {
        long[] uuid = new long[2];
        ByteUtils.readLongs(data, 0, uuid);
        UUID opponentIdentifier = new UUID(uuid[0], uuid[1]);

        boolean accepted = data[DataType.getSize(DataType.LONG) * 2] == 0x1;

        Optional<ChessClient> opponentOptional = client.getServer().getConnectedClients().stream().filter(c -> c.getClientIdentifier().equals(opponentIdentifier)).findFirst();
        if (opponentOptional.isPresent() && accepted) {
            ChessClient opponent = opponentOptional.get();

            boolean opponentWhite = Math.random() < .5d;
            ChessClient white = opponentWhite ? opponent : client;
            ChessClient black = opponentWhite ? client : opponent;

            ChessMatch match = new ChessMatch(white, black);
            match.start();
        }
    }
}
