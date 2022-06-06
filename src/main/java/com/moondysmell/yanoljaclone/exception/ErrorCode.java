package com.moondysmell.yanoljaclone.exception;

import lombok.Getter;

@Getter
public enum ErrorCode {

    //common
    INVALID_CODE(400, "C001", "Invalid Code"),
    NOT_ENOUGH_ROOM(401,"C002","No Room Anymore");

    private final String code;
    private final String message;
    private int status;

    ErrorCode(final int status, final String code, final String message) {
        this.status = status;
        this.message = message;
        this.code = code;
    }
}
