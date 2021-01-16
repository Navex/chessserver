package de.itsstuttgart.chessserver.packet.packets;

import de.itsstuttgart.chessserver.clients.ChessClient;
import de.itsstuttgart.chessserver.packet.Packet;
import de.itsstuttgart.chessserver.packet.PacketHeader;
import de.itsstuttgart.chessserver.util.ByteUtils;
import de.itsstuttgart.chessserver.util.DataType;

/**
 * created by paul on 15.01.21 at 19:27
 */
@PacketHeader({0x2b, 0x2b}) // --
public class PingPacket implements Packet {

    @Override
    public void process(byte[] data, ChessClient client) {
        if (client.isLoggedIn()) {
            int size = 2;
            size += DataType.getSize(DataType.INTEGER);
            for (ChessClient model : client.getServer().getConnectedClients()) {
                if (model.isLoggedIn()) {
                    size += DataType.getSize(DataType.SHORT);
                    size += model.getUserModel().getUsername().length();
                }
            }
            byte[] ping = new byte[size];
            int pointer = 0;
            pointer = ByteUtils.writeBytes(ping, pointer, (byte)0x2d);
            pointer = ByteUtils.writeBytes(ping, pointer, (byte)0x2d);
            pointer = ByteUtils.writeBytes(ping, pointer, (int)client.getServer().getConnectedClients().stream().filter(ChessClient::isLoggedIn).count());
            for (ChessClient model : client.getServer().getConnectedClients()) {
                if (model.isLoggedIn()) {
                    pointer = ByteUtils.writeBytes(ping, pointer, model.getUserModel().getUsername());
                }
            }
            client.send(ping);
        } else {
            client.send(new byte[] {0x2d, 0x2d});
        }
    }
}
