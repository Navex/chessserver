package de.itsstuttgart.chessserver.util;

/**
 * Created by Paul on 11.02.2017.
 */
public class ByteUtils {

    private static final char[] HEX_ARRAY = "0123456789abcdef".toCharArray();

    /**
     * Writes a simple byte to the destination.
     *
     * @param destination The basic byte array.
     * @param pointer     The part of the destination that should be wrote
     * @param value       The value that should be set
     * @return The pointer increased by 1
     */
    public static int writeBytes(byte[] destination, int pointer, byte value) {
        assert (destination.length > pointer + DataType.getSize(DataType.BYTE));

        destination[pointer++] = value;

        return pointer;
    }

    /**
     * Writes a byte array to the destination.
     * This keeps going trough the array, increases the pointer and
     * sets the value.
     *
     * @param destination The basic byte array.
     * @param pointer     The part of the destination that should be wrote
     * @param value       The value that should be set
     * @return The pointer increased by the length of the array
     */
    public static int writeBytes(byte[] destination, int pointer, byte[] value) {
        assert (destination.length > pointer + value.length);

        for (int i = 0; i < value.length; i++)
            destination[pointer++] = value[i];

        return pointer;
    }

    /**
     * Writes an integer array to the destination.
     * This keeps going trough the array, increases the pointer and
     * sets the value.
     *
     * @param destination The basic byte array.
     * @param pointer     The part of the destination that should be wrote
     * @param data        The value that should be set
     * @return The pointer increased by the length of the array
     */
    public static int writeBytes(byte[] destination, int pointer, int[] data) {
        assert (destination.length > pointer + data.length);

        for (int i = 0; i < data.length; i++)
            pointer = writeBytes(destination, pointer, data[i]);

        return pointer;
    }

    /**
     * Writes an long array to the destination.
     * This keeps going trough the array, increases the pointer and
     * sets the value.
     *
     * @param destination The basic byte array.
     * @param pointer     The part of the destination that should be wrote
     * @param data        The value that should be set
     * @return The pointer increased by the length of the array
     */
    public static int writeBytes(byte[] destination, int pointer, long[] data) {
        assert (destination.length > pointer + data.length);

        for (int i = 0; i < data.length; i++)
            pointer = writeBytes(destination, pointer, data[i]);

        return pointer;
    }

    /**
     * Writes an char array to the destination.
     * This keeps going trough the array, increases the pointer and
     * sets the value.
     *
     * @param destination The basic byte array.
     * @param pointer     The part of the destination that should be wrote
     * @param data        The value that should be set
     * @return The pointer increased by the length of the array
     */
    public static int writeBytes(byte[] destination, int pointer, char[] data) {
        assert (destination.length > pointer + data.length);

        for (int i = 0; i < data.length; i++)
            pointer = writeBytes(destination, pointer, data[i]);

        return pointer;
    }

    /**
     * Writes an short array to the destination.
     * This keeps going trough the array, increases the pointer and
     * sets the value.
     *
     * @param destination The basic byte array.
     * @param pointer     The part of the destination that should be wrote
     * @param data        The value that should be set
     * @return The pointer increased by the length of the array
     */
    public static int writeBytes(byte[] destination, int pointer, short[] data) {
        assert (destination.length > pointer + data.length);

        for (int i = 0; i < data.length; i++)
            pointer = writeBytes(destination, pointer, data[i]);

        return pointer;
    }

    /**
     * Writes an double array to the destination.
     * This keeps going trough the array, increases the pointer and
     * sets the value.
     *
     * @param destination The basic byte array.
     * @param pointer     The part of the destination that should be wrote
     * @param data        The value that should be set
     * @return The pointer increased by the length of the array
     */
    public static int writeBytes(byte[] destination, int pointer, double[] data) {
        assert (destination.length > pointer + data.length);

        for (int i = 0; i < data.length; i++)
            pointer = writeBytes(destination, pointer, data[i]);

        return pointer;
    }

    /**
     * Writes an float array to the destination.
     * This keeps going trough the array, increases the pointer and
     * sets the value.
     *
     * @param destination The basic byte array.
     * @param pointer     The part of the destination that should be wrote
     * @param data        The value that should be set
     * @return The pointer increased by the length of the array
     */
    public static int writeBytes(byte[] destination, int pointer, float[] data) {
        assert (destination.length > pointer + data.length);

        for (int i = 0; i < data.length; i++)
            pointer = writeBytes(destination, pointer, data[i]);

        return pointer;
    }

    /**
     * Writes an boolean array to the destination.
     * This keeps going trough the array, increases the pointer and
     * sets the value.
     *
     * @param destination The basic byte array.
     * @param pointer     The part of the destination that should be wrote
     * @param data        The value that should be set
     * @return The pointer increased by the length of the array
     */
    public static int writeBytes(byte[] destination, int pointer, boolean[] data) {
        assert (destination.length > pointer + data.length);

        for (int i = 0; i < data.length; i++)
            pointer = writeBytes(destination, pointer, data[i]);

        return pointer;
    }

    /**
     * Writes an short value to the destination.
     *
     * @param destination The basic byte array.
     * @param pointer     The part of the destination that should be wrote
     * @param value       The value that should be set
     * @return The pointer increased by 2
     */
    public static int writeBytes(byte[] destination, int pointer, short value) {
        assert (destination.length > pointer + DataType.getSize(DataType.SHORT));

        destination[pointer++] = (byte) ((value >> 8) & 0xFF);
        destination[pointer++] = (byte) ((value >> 0) & 0xFF);

        return pointer;
    }

    /**
     * Writes an char value to the destination.
     * (It is exact the same as {@link ByteUtils#writeBytes(byte[], int, short)})
     *
     * @param destination The basic byte array.
     * @param pointer     The part of the destination that should be wrote
     * @param value       The value that should be set
     * @return The pointer increased by 2
     */
    public static int writeBytes(byte[] destination, int pointer, char value) {
        assert (destination.length > pointer + DataType.getSize(DataType.CHAR));

        destination[pointer++] = (byte) ((value >> 8) & 0xFF);
        destination[pointer++] = (byte) ((value >> 0) & 0xFF);

        return pointer;
    }

    /**
     * Writes an integer value to the destination.
     *
     * @param destination The basic byte array.
     * @param pointer     The part of the destination that should be wrote
     * @param value       The value that should be set
     * @return The pointer increased by 4
     */
    public static int writeBytes(byte[] destination, int pointer, int value) {
        assert (destination.length > pointer + DataType.getSize(DataType.INTEGER));

        destination[pointer++] = (byte) ((value >> 24) & 0xFF);
        destination[pointer++] = (byte) ((value >> 16) & 0xFF);
        destination[pointer++] = (byte) ((value >> 8) & 0xFF);
        destination[pointer++] = (byte) ((value >> 0) & 0xFF);

        return pointer;
    }

    /**
     * Writes an long value to the destination.
     *
     * @param destination The basic byte array.
     * @param pointer     The part of the destination that should be wrote
     * @param value       The value that should be set
     * @return The pointer increased by 8
     */
    public static int writeBytes(byte[] destination, int pointer, long value) {
        assert (destination.length > pointer + DataType.getSize(DataType.LONG));

        destination[pointer++] = (byte) ((value >> 56) & 0xFF);
        destination[pointer++] = (byte) ((value >> 48) & 0xFF);
        destination[pointer++] = (byte) ((value >> 40) & 0xFF);
        destination[pointer++] = (byte) ((value >> 32) & 0xFF);
        destination[pointer++] = (byte) ((value >> 24) & 0xFF);
        destination[pointer++] = (byte) ((value >> 16) & 0xFF);
        destination[pointer++] = (byte) ((value >> 8) & 0xFF);
        destination[pointer++] = (byte) ((value >> 0) & 0xFF);

        return pointer;
    }

    /**
     * Writes an float to the destination.
     * This uses Java's {@link Float#floatToIntBits(float)} function, witch returns
     * an integer, so it uses {@link ByteUtils#writeBytes(byte[], int, int)} to write
     * the bytes.
     *
     * @param destination The basic byte array.
     * @param pointer     The part of the destination that should be wrote
     * @param value       The value that should be set
     * @return The pointer increased by 4
     */
    public static int writeBytes(byte[] destination, int pointer, float value) {
        assert (destination.length > pointer + DataType.getSize(DataType.FLOAT));

        return writeBytes(destination, pointer, Float.floatToIntBits(value));
    }

    /**
     * Writes an long to the destination.
     * This uses Java's {@link Double#doubleToLongBits(double)} function, witch returns
     * an long, so it uses {@link ByteUtils#writeBytes(byte[], int, long)} to write
     * the bytes.
     *
     * @param destination The basic byte array.
     * @param pointer     The part of the destination that should be wrote
     * @param value       The value that should be set
     * @return The pointer increased by 8
     */
    public static int writeBytes(byte[] destination, int pointer, double value) {
        assert (destination.length > pointer + DataType.getSize(DataType.DOUBLE));

        return writeBytes(destination, pointer, Double.doubleToLongBits(value));
    }

    /**
     * Writes an boolean to the destination.
     *
     * @param destination The basic byte array.
     * @param pointer     The part of the destination that should be wrote
     * @param value       The value that should be set
     * @return The pointer increased by 1
     */
    public static int writeBytes(byte[] destination, int pointer, boolean value) {
        assert (destination.length > pointer + DataType.getSize(DataType.BOOLEAN));

        destination[pointer++] = (byte) (value ? 0x01 : 0x00);

        return pointer;
    }

    /**
     * Writes an string to the destination.
     * This is quite different than the other methods (because string does not have a static length):
     * a few examples:
     * 1. Write the size before the string
     * 2. Null-Termination-Char
     * 3. both
     *
     * @param destination The basic byte array.
     * @param pointer     The part of the destination that should be wrote
     * @param value       The value that should be set
     * @return The pointer increased by 1
     */
    public static int writeBytes(byte[] destination, int pointer, String value) {
        pointer = writeBytes(destination, pointer, (short) value.length());
        pointer = writeBytes(destination, pointer, value.getBytes());

        return pointer;
    }

    /**
     * Reads 1 byte at the source location (might be a bit useless)
     *
     * @param source  the byte array source
     * @param pointer the pointer
     * @return the byte
     */
    public static byte readByte(byte[] source, int pointer) {
        return (byte) (source[pointer]);
    }

    public static void readBytes(byte[] source, int pointer, byte[] dest) {
        for (int i = 0; i < dest.length; i++)
            dest[i] = source[pointer + i];
    }

    public static void readShorts(byte[] source, int pointer, short[] dest) {
        for (int i = 0; i < dest.length; i++) {
            dest[i] = readShort(source, pointer);
            pointer += DataType.getSize(DataType.SHORT);
        }
    }

    public static void readChars(byte[] source, int pointer, char[] dest) {
        for (int i = 0; i < dest.length; i++) {
            dest[i] = readChar(source, pointer);
            pointer += DataType.getSize(DataType.CHAR);
        }
    }

    public static void readIntegers(byte[] source, int pointer, int[] dest) {
        for (int i = 0; i < dest.length; i++) {
            dest[i] = readInteger(source, pointer);
            pointer += DataType.getSize(DataType.INTEGER);
        }
    }

    public static void readLongs(byte[] source, int pointer, long[] dest) {
        for (int i = 0; i < dest.length; i++) {
            dest[i] = readLong(source, pointer);
            pointer += DataType.getSize(DataType.LONG);
        }
    }

    public static void readDoubles(byte[] source, int pointer, double[] dest) {
        for (int i = 0; i < dest.length; i++) {
            dest[i] = readDouble(source, pointer);
            pointer += DataType.getSize(DataType.DOUBLE);
        }
    }

    public static void readFloats(byte[] source, int pointer, float[] dest) {
        for (int i = 0; i < dest.length; i++) {
            dest[i] = readFloat(source, pointer);
            pointer += DataType.getSize(DataType.FLOAT);
        }
    }

    public static void readBooleans(byte[] source, int pointer, boolean[] dest) {
        for (int i = 0; i < dest.length; i++) {
            dest[i] = readBoolean(source, pointer);
            pointer += DataType.getSize(DataType.BOOLEAN);
        }
    }

    /**
     * Reads a char out of the byte array
     *
     * @param source  the byte source
     * @param pointer the index
     * @return char
     */
    public static char readChar(byte[] source, int pointer) {
        return (char) ((source[pointer] & 0xFF) << 8 | (source[pointer + 1] & 0xFF));
    }

    /**
     * same as {@link ByteUtils#readChar(byte[], int)}
     */
    public static short readShort(byte[] source, int pointer) {
        return (short) ((source[pointer] & 0xFF) << 8 | (source[pointer + 1] & 0xFF));
    }

    /**
     * Reads a integer out of the byte array
     *
     * @param source  byte array source
     * @param pointer index
     * @return int
     */
    public static int readInteger(byte[] source, int pointer) {
        return (int) ((source[pointer] & 0xFF) << 24 | (source[pointer + 1] & 0xFF) << 16 | (source[pointer + 2] & 0xFF) << 8 | (source[pointer + 3] & 0xFF));
    }

    /**
     * Reads a long out of the byte array
     *
     * @param source  byte array source
     * @param pointer index
     * @return long
     */
    public static long readLong(byte[] source, int pointer) {
        return (long) ((((long) source[pointer] & 0xFF) << 56) | (((long) source[pointer + 1] & 0xFF) << 48) | (((long) source[pointer + 2] & 0xFF) << 40) | (((long) source[pointer + 3] & 0xFF) << 32) | (((long) source[pointer + 4] & 0xFF) << 24) | (((long) source[pointer + 5] & 0xFF) << 16) | (((long) source[pointer + 6] & 0xFF) << 8) | (((long) source[pointer + 7] & 0xFF)));
    }

    /**
     * Uses the {@link Float#intBitsToFloat(int)} function to convert the bytes to a float
     * Firstly it ready the int out of the byte source
     *
     * @param source  byte array source
     * @param pointer index
     * @return float
     */
    public static float readFloat(byte[] source, int pointer) {
        return Float.intBitsToFloat(readInteger(source, pointer));
    }

    /**
     * Uses the {@link Double#longBitsToDouble(long)} function to convert the bytes to a double
     * Firstly it ready the long out of the byte source
     *
     * @param source  byte array source
     * @param pointer index
     * @return double
     */
    public static double readDouble(byte[] source, int pointer) {
        return Double.longBitsToDouble(readLong(source, pointer));
    }

    /**
     * Reads a boolean out of a byte array (checks if the byte is 0x01 or 0x00)
     *
     * @param source  byte array source
     * @param pointer index
     * @return float
     */
    public static boolean readBoolean(byte[] source, int pointer) {
        assert (source[pointer] == 0x00 || source[pointer] == 0x01);
        return source[pointer] != 0x00;
    }

    public static String readString(byte[] source, int pointer, int length) {
        return new String(source, pointer, length);
    }

    /**
     * Converts a byte array size to a human readable string (kb, mb, etc.)
     *
     * @param bytes byte array length
     * @param si
     * @return output
     */
    public static String humanReadableByteCount(long bytes, boolean si) {
        int unit = si ? 1000 : 1024;
        if (bytes < unit) return bytes + " B";
        int exp = (int) (Math.log(bytes) / Math.log(unit));
        String pre = (si ? "kMGTPE" : "KMGTPE").charAt(exp - 1) + (si ? "" : "i");
        return String.format("%.1f %sB", bytes / Math.pow(unit, exp), pre);
    }

    public static String bytesToHex(byte[] bytes) {
        char[] hexChars = new char[bytes.length * 2];
        for (int j = 0; j < bytes.length; j++) {
            int v = bytes[j] & 0xFF;
            hexChars[j * 2] = HEX_ARRAY[v >>> 4];
            hexChars[j * 2 + 1] = HEX_ARRAY[v & 0x0F];
        }
        return new String(hexChars);
    }
}
