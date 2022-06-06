package com.moondysmell.yanoljaclone.exception;

import lombok.Getter;

@Getter
public enum ErrorCode {

    //common
    INVALID_CODE(400, "C001", "Invalid Code"),

    //1000:Accom

    //2000:Reservation
    NOT_ENOUGH_ROOM(2000,"A002","No Room Anymore");

    //3000: Customer

    //4000: LocationCode

    private final String code;
    private final String message;
    private int status;

    ErrorCode(final int status, final String code, final String message) {
        this.status = status;
        this.message = message;
        this.code = code;
    }
}
