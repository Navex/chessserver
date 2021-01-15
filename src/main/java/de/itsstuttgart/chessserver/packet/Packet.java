package de.itsstuttgart.chessserver.packet;

import de.itsstuttgart.chessserver.clients.ChessClient;

/**
 * created by paul on 01.02.20 at 15:00
 */
public interface Packet {

    void process(byte[] data, ChessClient client);
}
