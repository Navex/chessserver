package de.itsstuttgart.chessserver.packet;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * created by paul on 01.02.20 at 15:05
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface PacketHeader {

    byte[] value();
}
