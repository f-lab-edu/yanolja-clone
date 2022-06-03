package com.moondysmell.yanoljaclone.domain;

import lombok.*;
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
    private int reserv_id;

    //Resionvation : Member = N : 1
    @ManyToOne(targetEntity= Customer.class)
    @JoinColumn(name = "user_id")
    private Customer customer;

    //Resionvation : Accommodation = N : 1
    @ManyToOne(targetEntity=Accommodation.class)
    @JoinColumn(name= "id")
    private Accommodation accom;

    @Temporal(TemporalType.DATE)
    @Column(nullable = false)
    private Date checkin;

    @Temporal(TemporalType.DATE)
    @Column(nullable = false)
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

    @Builder
    private Reservation(final int reserv_id, Customer user_id, Accommodation id, Date checkin, Date checkout, PaymentType payment_type, TransType trans_type, ReservStatus reserv_status) {
        this.reserv_id = reserv_id;
        this.customer = user_id;
        this.accom = id;
        this.checkin = checkout;
        this.payment_type = payment_type;
        this.trans_type = trans_type;
        this.reserv_status = reserv_status;
    }

}
