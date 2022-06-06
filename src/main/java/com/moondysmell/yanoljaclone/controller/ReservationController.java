package com.moondysmell.yanoljaclone.controller;

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

@RequiredArgsConstructor
@RestController
@RequestMapping("/reservation")
public class ReservationController {

    @Autowired
    private final ReservationService reservService;


    @PostMapping("/make")
    public ResponseEntity<ReservationResponseDto> makeReservarion(@RequestBody ReservationMakeDto reservationMakeDto) {

           String userName = reservationMakeDto.getUserName();
           String phoneNum = reservationMakeDto.getPhoneNum();
           TransType transType = reservationMakeDto.getTransType();
           PaymentType paymentType =  reservationMakeDto.getPaymentType();

           ReservationResponseDto reservedResult = reservService.reserv(reservationMakeDto);

           if(reservedResult != null) return ResponseEntity.ok(reservedResult);
           else return new ResponseEntity(HttpStatus.NOT_ACCEPTABLE);


    }


    //한 유저에 대한 예약 내역
    @GetMapping("/detail")
    public ResponseEntity<List<ReservationResponseDto>> findByDetail(@Valid @RequestBody ReservationRequestDto reservedRequestDto){
        //객체로 받아오기 위해 RequestBody선언
        String user_name = reservedRequestDto.getName();
        int reserv_id = reservedRequestDto.getReserv_id();
        String phone_num = reservedRequestDto.getPhone_num();

        List<ReservationResponseDto> reservedResult = reservService.getReservedResult(user_name, reserv_id, phone_num);

        if(reservedResult.isEmpty()) return new ResponseEntity(HttpStatus.NO_CONTENT);
        else return  new ResponseEntity<>(reservedResult, HttpStatus.OK);

    }

    @PostMapping("/cancle")
    public ResponseEntity<Reservation> cancleReservation(@RequestParam int reserv_id){
           Reservation reservedResult = reservService.cancleReservation(reserv_id);
        return new ResponseEntity<>(reservedResult, HttpStatus.OK);
    }

}
