package de.itsstuttgart.chessserver.packet.packets;

import de.itsstuttgart.chessserver.clients.ChessClient;
import de.itsstuttgart.chessserver.packet.Packet;
import de.itsstuttgart.chessserver.packet.PacketHeader;
import de.itsstuttgart.chessserver.util.ByteUtils;
import de.itsstuttgart.chessserver.util.DataType;
import de.itsstuttgart.chessserver.util.model.UserModel;

/**
 * created by paul on 16.01.21 at 12:41
 */
@PacketHeader({0x6c, 0x6f})
public class LoginPacket implements Packet {

    @Override
    public void process(byte[] data, ChessClient client) {
        int pointer = 0;
        short usernameLen = ByteUtils.readShort(data, pointer);
        pointer += DataType.getSize(DataType.SHORT);
        String username = ByteUtils.readString(data, pointer, usernameLen);
        pointer += usernameLen;

        short passwordLen = ByteUtils.readShort(data, pointer);
        pointer += DataType.getSize(DataType.SHORT);
        String password = ByteUtils.readString(data, pointer, passwordLen);

        if (client.getServer().getUserRepository().existsByUsername(username)) {
            UserModel user = client.getServer().getUserRepository().findByUsername(username);
            if (user.checkPassword(password)) {
                client.setLoggedIn(user);
                byte[] ls = new byte[2 + DataType.getSize(DataType.SHORT) + user.getUsername().length()];
                ls[0] = 0x6c;
                ls[1] = 0x73;
                ByteUtils.writeBytes(ls, 2, user.getUsername());
                client.send(ls);
            } else {
                client.send(new byte[]{0x61, 0x6c, 0x01});
            }
        } else {
            client.send(new byte[]{0x61, 0x6c, 0x01});
        }
    }
}
