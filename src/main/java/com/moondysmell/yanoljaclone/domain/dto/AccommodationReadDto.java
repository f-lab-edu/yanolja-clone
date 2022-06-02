package com.moondysmell.yanoljaclone.domain.dto;

import com.moondysmell.yanoljaclone.domain.Accommodation;
import com.moondysmell.yanoljaclone.domain.LocationCode;
import com.moondysmell.yanoljaclone.domain.RoomType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import lombok.Data;
import lombok.Getter;


@Getter
public class AccommodationReadDto {
    @Id
    private Long id;

    private String accomCode;

    private String accomName;

    private int locationCode;

    private String locationName;

    private String address;

    private String type;

    private String roomName;

    private int roomCnt;

    private int price;

    private String detail;

    public AccommodationReadDto(Accommodation accommodation) {
        id = accommodation.getId();
        accomCode = accommodation.getAccomCode();
        accomName = accommodation.getAccomName();
        locationCode = accommodation.getLocationCode().getCode();
        locationName = accommodation.getLocationCode().getLocation();
        address = accommodation.getAddress();
        type = accommodation.getType();
        roomName = accommodation.getRoomName();
        roomCnt = accommodation.getRoomCnt();
        price = accommodation.getPrice();
        detail = accommodation.getDetail();
    }
}
