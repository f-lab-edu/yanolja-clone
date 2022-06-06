package com.moondysmell.yanoljaclone.common;

import java.util.Map;

import lombok.Data;

@Data
public class CommonResponse<T> {
    private int code;
    private String message;
    private Map<String, T> attribute;

    public CommonResponse(CommonCode commonCode, Map<String, T> attribute) {
        this.code = commonCode.getCode();
        this.message = commonCode.getMessage();
        this.attribute = attribute;
    }

//    public CommonResponse(SuccessCode successCode) {
//        this.code = successCode.getCode();
//        this.message = successCode.getMessage();
//    }

    public CommonResponse(CommonCode commonCode) {
        this.code = commonCode.getCode();
        this.message = commonCode.getMessage();
    }


}
