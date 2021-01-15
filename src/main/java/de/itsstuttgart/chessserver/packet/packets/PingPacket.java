package de.itsstuttgart.chessserver.packet.packets;

import de.itsstuttgart.chessserver.clients.ChessClient;
import de.itsstuttgart.chessserver.packet.Packet;
import de.itsstuttgart.chessserver.packet.PacketHeader;

/**
 * created by paul on 15.01.21 at 19:27
 */
@PacketHeader({0x2b, 0x2b}) // --
public class PingPacket implements Packet {

    @Override
    public void process(byte[] data, ChessClient client) {
        client.send(new byte[] {0x2d, 0x2d});
    }
}
