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
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "accommodation_id_seq")
    @SequenceGenerator(name = "accommodation_id_seq", sequenceName = "accommodation_id_seq", allocationSize = 1)
    @Column(name = "id")
    private int id;

    @Column(name = "accom_code")
    private String accomCode;

    @Column(name = "accom_name")
    private String accomName;

    //    @Column
//    private int location_code;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "location_code", referencedColumnName = "code")
//    @JsonIgnore
    private LocationCode locationCode;

    @Column(name = "address")
    private String address;


//    @Enumerated(EnumType.STRING)
//    @Column(name="type", nullable = false)
//    private RoomType room_type;
@Column(name = "type")
private String type;

    @Column(name = "room_name")
    private String roomName;

    @Column(name = "room_cnt")
    private int roomCnt;

    @Column(name = "price")
    private int price;

    @Column(name = "detail")
    private String detail;

    //Accomodation 1 : N Reservation
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "accom", targetEntity = Reservation.class)
    private List<Reservation> reserv = new ArrayList<>();

    public void removeReserv(){
        int restroom_cnt = this.roomCnt - 1;
        if(restroom_cnt == 0){
            //setEnable(false);
            //예약 가능 여부
        }
        if(restroom_cnt < 0){
            //throw new NotEnoughRoomException("예약 가능한 방이 존재하지 않습니다.");
        }
        this.roomCnt = restroom_cnt;
    }

    //상세내용 수정
    public void updateDetail(String detail){
        this.detail = detail;
    }

//    @Builder
//    public Accommodation(String accom_code, String accom_name, int location_code, String address, RoomType room_type, String room_name, int room_cnt, int price, String detail){
//        this.accom_code = accom_code;
//        this.accom_name = accom_name;
//        this.location_code = location_code;
//        this.address = address;
//        this.room_type = room_type;
//        this.room_name = room_name;
//        this.room_cnt = room_cnt;
//        this.price = price;
//    }

}
