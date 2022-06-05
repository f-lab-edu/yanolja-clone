package com.moondysmell.yanoljaclone.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;


@Data
@Entity
@NoArgsConstructor
@Table(name="accommodation")
public class Accommodation implements Cloneable{
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "accommodation_id_seq")
    @SequenceGenerator(name = "accommodation_id_seq", sequenceName = "accommodation_id_seq", allocationSize = 1)
    @Column(name = "id")
    private Long id;

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



    public Accommodation copy() {
        Accommodation newAccom = new Accommodation();

        try {
            newAccom = (Accommodation) super.clone();
        } catch (CloneNotSupportedException e) {
            throw new RuntimeException(e);
        }

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
}
