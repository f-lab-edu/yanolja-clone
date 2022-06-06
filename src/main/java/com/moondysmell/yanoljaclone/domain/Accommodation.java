package com.moondysmell.yanoljaclone.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.lang.Nullable;


@Data
@Entity
@NoArgsConstructor
@Table(name="accommodation")
public class Accommodation {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "accommodation_id_seq")
    @SequenceGenerator(name = "accommodation_id_seq", sequenceName = "accommodation_id_seq", allocationSize = 1)
    @Column(name = "id")
    private Integer id;

    @Column(name = "accom_code")
    private String accomCode;

    @Column(name = "accom_name")
    private String accomName;

    @Column(name = "location_code")
    private int locationCode;

    @Column(name = "address")
    private String address;

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
    @JsonIgnore
    private List<Reservation> reserv = new ArrayList<>();

    public Accommodation copy() {
        Accommodation newAccom = new Accommodation();

        newAccom.setId(this.id);
        newAccom.setAccomCode(this.accomCode);
        newAccom.setAccomName(this.accomName);
        newAccom.setLocationCode(this.locationCode);
        newAccom.setAddress(this.address);
        newAccom.setType(this.type);
        newAccom.setRoomName(this.roomName);
        newAccom.setRoomCnt(this.roomCnt);
        newAccom.setPrice(this.price);
        newAccom.setDetail(this.detail);

        return newAccom;
    }

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

}
