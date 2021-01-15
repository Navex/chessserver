package de.itsstuttgart.chessserver.util;

public class DataType {

    public static final byte UNKNOWN    = 0x00; // No data type
    public static final byte BYTE       = 0x01; // Byte
    public static final byte SHORT      = 0x02; // Short
    public static final byte CHAR       = 0x03; // Char
    public static final byte INTEGER    = 0x04; // Integer
    public static final byte LONG       = 0x05; // Long
    public static final byte FLOAT      = 0x06; // Float
    public static final byte DOUBLE     = 0x07; // Double
    public static final byte BOOLEAN    = 0x08; // Boolean

    public static int getSize(byte dataType) {
        switch (dataType) {
            case UNKNOWN: assert(false);
            case BYTE:      return 1; // 1 Byte == 1 Byte
            case SHORT:     return 2; // 1 Short == 2 Bytes
            case CHAR:      return 2; // 1 Char == 2 Bytes
            case INTEGER:   return 4; // 1 Int == 4 Bytes
            case LONG:      return 8; // 1 Long == 8 Bytes
            case FLOAT:     return 4; // 1 Float == 4 Bytes
            case DOUBLE:    return 8; // 1 Double == 8 Bytes
            case BOOLEAN:   return 1; // 1 Boolean == 1 Byte (1 bit)
            default:        return 0x00; // Not Found
        }
    }
}
