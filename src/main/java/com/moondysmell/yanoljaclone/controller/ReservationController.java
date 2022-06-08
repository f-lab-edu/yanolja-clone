package com.moondysmell.yanoljaclone.controller;

import com.moondysmell.yanoljaclone.common.CommonCode;
import com.moondysmell.yanoljaclone.common.CommonResponse;
import com.moondysmell.yanoljaclone.common.CustomException;
import com.moondysmell.yanoljaclone.domain.PaymentType;
import com.moondysmell.yanoljaclone.domain.Reservation;
import com.moondysmell.yanoljaclone.domain.TransType;
import com.moondysmell.yanoljaclone.domain.dto.ReservationMakeDto;
import com.moondysmell.yanoljaclone.domain.dto.ReservationRequestDto;
import com.moondysmell.yanoljaclone.domain.dto.ReservationResponseDto;
import com.moondysmell.yanoljaclone.service.ReservationService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;

@RequiredArgsConstructor
@RestController
@RequestMapping("/reservation")
public class ReservationController {

    @Autowired
    private final ReservationService reservService;


    @PostMapping("/make")
    public ResponseEntity<CommonResponse> makeReservarion(@Valid @RequestBody ReservationMakeDto reservationMakeDto) {

           String userName = reservationMakeDto.getUserName();
           String phoneNum = reservationMakeDto.getPhoneNum();
           TransType transType = reservationMakeDto.getTransType();
           PaymentType paymentType =  reservationMakeDto.getPaymentType();

           ReservationResponseDto reservedResult = reservService.reserv(reservationMakeDto);

        return ResponseEntity.ok(new CommonResponse(CommonCode.SUCCESS, Map.of("reservedResult", reservedResult)));


    }


    //한 유저에 대한 예약 내역
    @GetMapping("/detail")
    public ResponseEntity<CommonResponse<List<ReservationResponseDto>>> findByDetail(@Valid @RequestBody ReservationRequestDto reservedRequestDto){
        //객체로 받아오기 위해 RequestBody선언
        String user_name = reservedRequestDto.getName();
        int reserv_id = reservedRequestDto.getReserv_id();
        String phone_num = reservedRequestDto.getPhone_num();

        List<ReservationResponseDto> reservedResult = reservService.getReservedResult(user_name, reserv_id, phone_num);

        return ResponseEntity.ok(new CommonResponse(CommonCode.SUCCESS, Map.of("reservedResult", reservedResult)));

    }

    @PostMapping("/cancle")
    public ResponseEntity<CommonResponse> cancleReservation(@RequestParam int reserv_id){
           Reservation reservedResult = reservService.cancleReservation(reserv_id);
        return ResponseEntity.ok(new CommonResponse(CommonCode.SUCCESS, Map.of("reservedResult", reservedResult)));
    }

}
