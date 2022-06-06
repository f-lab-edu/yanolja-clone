package com.moondysmell.yanoljaclone.domain.dto;

import com.moondysmell.yanoljaclone.domain.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class ReservationMakeDto {

    private Integer accomId;

    private String userName;

    private String phoneNum;

    private Date checkin;

    private Date checkout;

    private TransType transType;

    private PaymentType paymentType;

    private Integer roomCnt;

    private ReservStatus reservStatus;



    //룸에 대한 잔여수량(key : roomName, value : roomCnt)
    private Map<String, Integer> roomCntInfoMap = new HashMap<>();

    public Reservation toReservationEntity() {
        return Reservation.createReservation()
                .checkin(this.checkin)
                .checkout(this.checkout)
                .payment_type(this.paymentType)
                .trans_type(this.transType)
                .room_cnt(this.roomCnt)
                .reserv_status(this.reservStatus)
                .build();
    }

    @Builder
    public ReservationMakeDto(int accomId, String userName, String phoneNum,
                              Date checkin, Date checkout,
                              TransType transType, PaymentType paymentType, int roomCnt) {
        this.accomId = accomId;
        this.userName = userName;
        this.phoneNum = phoneNum;
        this.checkin = checkin;
        this.checkout = checkout;
        this.transType = transType;
        this.paymentType = paymentType;
        this.roomCnt = roomCnt;

    }


}
