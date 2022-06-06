package com.moondysmell.yanoljaclone.common;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonFormat.Shape;
import lombok.Getter;

@Getter
@JsonFormat(shape = Shape.OBJECT)
public enum SuccessCode {
    SUCCESS(200,"성공");

    private int code;
    private String message;

    SuccessCode(int code, String message) {
        this.code = code;
        this.message = message;
    }

}
