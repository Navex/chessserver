package de.itsstuttgart.chessserver.packet.packets;

import de.itsstuttgart.chessserver.clients.ChessClient;
import de.itsstuttgart.chessserver.packet.Packet;
import de.itsstuttgart.chessserver.packet.PacketHeader;
import de.itsstuttgart.chessserver.util.ByteUtils;
import de.itsstuttgart.chessserver.util.DataType;

/**
 * created by paul on 15.01.21 at 19:57
 */
@PacketHeader({0x72, 0x65}) // --
public class RegisterPacket implements Packet {

    @Override
    public void process(byte[] data, ChessClient client) {
        int pointer = 0;
        short usernameLen = ByteUtils.readShort(data, pointer);
        pointer += DataType.getSize(DataType.SHORT);
        String username = ByteUtils.readString(data, pointer, pointer);
        pointer += usernameLen;

        short passwordLen = ByteUtils.readShort(data, pointer);
        pointer += DataType.getSize(DataType.SHORT);
        String password = ByteUtils.readString(data, pointer, pointer);


    }
}
