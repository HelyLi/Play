package com.hhly.lyygame.data.net.protocol.exception;

/**
 * Created by ${HELY} on 17/3/7.
 * 邮箱：heli.lixiong@gmail.com
 */

public class ServerException extends RuntimeException {

    public int code;
    public String message;

    public ServerException(int code, String message) {
        super(message);
        this.code = code;
        this.message = message;
    }
}
