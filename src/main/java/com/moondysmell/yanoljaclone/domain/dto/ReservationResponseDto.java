package com.moondysmell.yanoljaclone.domain.dto;

import com.moondysmell.yanoljaclone.domain.PaymentType;
import com.moondysmell.yanoljaclone.domain.Reservation;
import com.moondysmell.yanoljaclone.domain.TransType;
import lombok.*;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ReservationResponseDto {

    private int reserv_id;
    private String accom_name;
    private Date checkin;
    private Date checkout;
    private TransType trans_type;
    private int room_cnt;
    private int price;
    private String name;
    private String phone_num;
    private PaymentType payment_type;
    private String detail;




    public ReservationResponseDto(Reservation reservation){
        this.reserv_id = reservation.getReserv_id();
        this.accom_name = reservation.getAccom().getAccom_name();
        this.checkin = reservation.getCheckin();
        this.checkout = reservation.getCheckout();
        this.trans_type = reservation.getTrans_type();
        this.room_cnt = reservation.getRoom_cnt();
        this.price = reservation.getAccom().getPrice();
        this.name = reservation.getCustomer().getName();
        this.phone_num = reservation.getCustomer().getPhone_num();
        this.payment_type = reservation.getPayment_type();
        this.detail = reservation.getAccom().getDetail();
    }

}
