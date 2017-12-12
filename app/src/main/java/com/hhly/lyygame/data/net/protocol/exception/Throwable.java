package com.hhly.lyygame.data.net.protocol.exception;

/**
 * Created by ${HELY} on 17/3/7.
 * 邮箱：heli.lixiong@gmail.com
 */

public class Throwable extends Exception {

    private int code;
    private String message;

    public Throwable(java.lang.Throwable throwable, int code) {
        super(throwable);
        this.code = code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
