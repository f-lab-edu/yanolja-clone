package com.moondysmell.yanoljaclone.common;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonFormat.Shape;
import lombok.Getter;
import lombok.Setter;

@Getter
@JsonFormat(shape = Shape.OBJECT)
public enum CommonCode {
    // SUCCESS
    SUCCESS(200, 200, "성공"),
    // FAIL
    FAIL(500, -1, "실패. 알 수 없는 오류"),

    //-1000:Accom
    ACCOM_TYPE_NOT_EXIST(400,-1000, "존재하지 않는 Type입니다."),
    ACCOMCODE_NOT_EXIST(400, -1001, "accomCode에 해당하는 숙소가 없습니다."),
    ACCOM_ID_NOT_EXIST(400, -1002, "accomId가 존재하지 않습니다. "),
    CHECKIN_CHECKOUT_REVERSE_ERROR(400, -1003, "체크아웃 날짜는 체크인 날짜보다 미래여야 합니다"),
    CHECKIN_IS_PAST_ERROR(400, -1004, "체크인 날짜는 과거가 될 수 없습니다."),
    CHECKIN_CHECKOUT_TOO_FAR_ERROR(400, -1005, "체크인 날짜와 체크아웃 날짜의 차이는 7일 이하여야 합니다."),
    CHECKIN_CHECKOUT_OUT_OF_RANGE_ERROR(400, -1006, "한 달 이내의 날짜만 예약 가능합니다. 체크인, 체크아웃 날짜를 확인해주세요."),
    //-2000:Reservation

    //-3000: Customer

    //-4000: LocationCode
    LOCATION_CODE_NOT_EXIST(400, -4000, "Location Code가 존재하지 않습니다.");


    private int status;
    private int code;
    private String message;

    CommonCode(int status, int code, String message) {
        this.status = status;
        this.message = message;
        this.code = code;
    }

}
