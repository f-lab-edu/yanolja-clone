package com.moondysmell.yanoljaclone.exception;

import com.moondysmell.yanoljaclone.common.CommonResponse;
import java.util.Map;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@Slf4j
public class ErrorControllerAdvice {

//    @ExceptionHandler(value = CustomException.class)
//    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
//    protected ResponseEntity<ErrorResponse> handleCustomException(CustomException e) {
//        ErrorResponse response = ErrorResponse.of(e.getErrorCode());
//        log.error(">>> " + response);
//        log.error(e.getMessage());
//        return new ResponseEntity<>(response, e.getHttpStatus());
//    }

    @ExceptionHandler(value = CustomException.class)
//    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    protected ResponseEntity<CommonResponse<String>> handleCustomException(CustomException e) {
//        ErrorResponse response = ErrorResponse.of(e.getErrorCode());
        CommonResponse<String> response= new CommonResponse(e.getErrorCode());
        //attribute 넣고싶다면 이렇게
        //response.setAttribute(Map.of("result", "실패"));
        log.error(">>> " + response);
        log.error(e.getMessage());
        return new ResponseEntity<>(response, e.getHttpStatus());
    }
}
