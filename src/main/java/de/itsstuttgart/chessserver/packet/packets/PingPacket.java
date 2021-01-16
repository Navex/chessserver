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
            client.sendListPing();
        } else {
            client.send(new byte[] {0x2d, 0x2d});
        }
    }
}
