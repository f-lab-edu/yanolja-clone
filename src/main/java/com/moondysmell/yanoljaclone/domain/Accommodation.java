package com.moondysmell.yanoljaclone.domain;

import lombok.*;
import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;


@Entity
@Getter
@Setter
@NoArgsConstructor
@ToString
@Table(name="accommodation")
public class Accommodation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column
    private String accom_code;

    @Column(nullable = false)
    private String accom_name;

    @Column
    private int location_code;

    @Column(nullable = false)
    private String address;

    @Enumerated(EnumType.STRING)
    @Column(name="type", nullable = false)
    private RoomType room_type;

    @Column(nullable = false)
    private String room_name;

    @Column(nullable = false)
    private int room_cnt;

    @Column(nullable = false)
    private int price;

    @Column
    @Lob
    private String detail;

    //Accomodation 1 : N Reservation
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "accom", targetEntity = Reservation.class)
    private List<Reservation> reserv = new ArrayList<>();

    public void removeReserv(){
        int restroom_cnt = this.room_cnt - 1;
        if(restroom_cnt == 0){
            //setEnable(false);
            //예약 가능 여부
        }
        if(restroom_cnt < 0){
            //throw new NotEnoughRoomException("예약 가능한 방이 존재하지 않습니다.");
        }
        this.room_cnt = restroom_cnt;
    }

    //상세내용 수정
    public void updateDetail(String detail){
        this.detail = detail;
    }

    @Builder
    public Accommodation(String accom_code, String accom_name, int location_code, String address, RoomType room_type, String room_name, int room_cnt, int price, String detail){
        this.accom_code = accom_code;
        this.accom_name = accom_name;
        this.location_code = location_code;
        this.address = address;
        this.room_type = room_type;
        this.room_name = room_name;
        this.room_cnt = room_cnt;
        this.price = price;
    }

}
