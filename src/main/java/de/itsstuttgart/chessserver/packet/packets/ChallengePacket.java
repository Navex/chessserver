package de.itsstuttgart.chessserver.packet.packets;

import de.itsstuttgart.chessserver.clients.ChessClient;
import de.itsstuttgart.chessserver.packet.Packet;
import de.itsstuttgart.chessserver.packet.PacketHeader;
import de.itsstuttgart.chessserver.util.ByteUtils;
import de.itsstuttgart.chessserver.util.DataType;

import java.util.Optional;
import java.util.UUID;

/**
 * created by paul on 16.01.21 at 16:30
 */
@PacketHeader({0x63, 0x2b})
public class ChallengePacket implements Packet {

    @Override
    public void process(byte[] data, ChessClient client) {
        long[] uuid = new long[2];
        ByteUtils.readLongs(data, 0, uuid);
        UUID playerIdentifier = new UUID(uuid[0], uuid[1]);

        Optional<ChessClient> playerOptional = client.getServer().getConnectedClients().stream().filter(c -> c.getClientIdentifier().equals(playerIdentifier)).findFirst();
        if (playerOptional.isPresent()) {
            ChessClient player = playerOptional.get();
            byte[] challenge = new byte[2 + DataType.getSize(DataType.LONG) * 2];
            int pointer = 0;
            pointer = ByteUtils.writeBytes(challenge, pointer, (byte) 0x63);
            pointer = ByteUtils.writeBytes(challenge, pointer, (byte) 0x2b);
            pointer = ByteUtils.writeBytes(challenge, pointer, client.getClientIdentifier().getMostSignificantBits());
            ByteUtils.writeBytes(challenge, pointer, client.getClientIdentifier().getLeastSignificantBits());
            player.send(challenge);
        } else {
            client.send(new byte[]{0x61, 0x6c, 0x02});
        }
    }
}
