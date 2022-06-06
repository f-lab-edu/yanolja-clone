package com.moondysmell.yanoljaclone.domain;

import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;

@Entity
@NoArgsConstructor
@ToString
@Getter
@Table(name="reservation")
public class Reservation  {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer reserv_id;

    //Resionvation : Member = N : 1
    @ManyToOne(targetEntity= Customer.class, cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id")
    private Customer customer;

    //Resionvation : Accommodation = N : 1
    @ManyToOne(targetEntity=Accommodation.class)
    @JoinColumn(name= "id")
    private Accommodation accom;

    @Temporal(TemporalType.DATE)
    @Column(nullable = false)
    @DateTimeFormat(pattern = "YYYY-MM-DD")
    private Date checkin;

    @Temporal(TemporalType.DATE)
    @Column(nullable = false)
    @DateTimeFormat(pattern = "YYYY-MM-DD")
    private Date checkout;

    @Column(nullable = false)
    private int room_cnt;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private PaymentType payment_type;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private TransType trans_type;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private ReservStatus reserv_status;

    @Builder(builderMethodName = "createReservation")
    private Reservation(Customer customer, Accommodation accom, Date checkin, Date checkout,int room_cnt, PaymentType payment_type, TransType trans_type, ReservStatus reserv_status) {
        this.customer = customer;
        this.accom = accom;
        this.checkin = checkin;
        this.checkout = checkout;
        this.room_cnt = room_cnt;
        this.payment_type = payment_type;
        this.trans_type = trans_type;
        this.reserv_status = reserv_status;
    }

    //연관관계 메소드 정의
    public void addCustomer(Customer customer){
        this.customer = customer;
        customer.getReserv().add(this);

    }

    public void setAccom(Accommodation accom){
        this.accom = accom;
        accom.getReserv().add(this);
    }




    ///비즈니스 로직 -> 데이터를 가지고 있는 곳에서 비지니스 로직을 만드는 것이 좋음
    //객체 지향적 성격 항샹과 응집력 향상

    //예약 날짜 및 예약 시간 update
    public void updateDateAndTime(Date reservedCheckin,Date reservedCheckout){
        this.checkin = reservedCheckin;
        this.checkout =reservedCheckout;
    }

    //예약) 방 예약 시 Accom 총 방개수 감소

    //
//    public void removeRoomCnt(){
//        int restRoomCnt = this.roomCnt - 1;
//        if(restRoomCnt < 0){
//            throw new RuntimeException("out of stock");
//        }
//        this.roomCnt = restRoomCnt;
//    }




}
