package de.itsstuttgart.chessserver.packet.packets;

import de.itsstuttgart.chessserver.clients.ChessClient;
import de.itsstuttgart.chessserver.packet.Packet;
import de.itsstuttgart.chessserver.packet.PacketHeader;
import de.itsstuttgart.chessserver.util.model.UserModel;

/**
 * created by paul on 17.01.21 at 21:09
 */
@PacketHeader({0x40, 0x64})
public class DashboardRequestPacket implements Packet {

    @Override
    public void process(byte[] data, ChessClient client) {
        UserModel model = client.getUserModel();
    }
}
