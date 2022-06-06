package com.moondysmell.yanoljaclone.exception;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonFormat.Shape;
import lombok.Getter;
import org.springframework.web.bind.annotation.RequestParam;

@Getter
@JsonFormat(shape = Shape.OBJECT)
public enum ErrorCode {

    // COMMON
    UNKNOWN_ERROR(400, -1, "실패. 알 수 없는 오류"),


    //-1000:Accom

    //-2000:Reservation

    //-3000: Customer

    //-4000: LocationCode
    LOCATION_CODE_NOT_EXIST(400, -4000, "Location Code가 존재하지 않습니다.");
    private int status;
    private int code;
    private String message;

    ErrorCode(int status, int code, String message) {
        this.status = status;
        this.message = message;
        this.code = code;
    }

}
