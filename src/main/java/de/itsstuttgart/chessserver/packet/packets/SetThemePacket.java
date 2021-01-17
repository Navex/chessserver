package de.itsstuttgart.chessserver.packet.packets;

import de.itsstuttgart.chessserver.clients.ChessClient;
import de.itsstuttgart.chessserver.packet.Packet;
import de.itsstuttgart.chessserver.packet.PacketHeader;
import de.itsstuttgart.chessserver.util.ByteUtils;

/**
 * created by paul on 17.01.21 at 20:54
 */
@PacketHeader({0x75, 0x74})
public class SetThemePacket implements Packet {

    @Override
    public void process(byte[] data, ChessClient client) {
        String themeName = ByteUtils.readString(data, 2, ByteUtils.readShort(data, 0));
        client.getUserModel().setTheme(themeName);
        client.getServer().getUserRepository().save(client.getUserModel());
    }
}
