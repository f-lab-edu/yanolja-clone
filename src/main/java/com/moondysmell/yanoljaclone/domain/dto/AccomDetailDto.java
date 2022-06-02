package com.moondysmell.yanoljaclone.domain.dto;

import com.moondysmell.yanoljaclone.domain.Accommodation;
import javax.persistence.Id;
import lombok.Getter;


@Getter
public class AccomDetailDto {
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

    public AccomDetailDto(Accommodation accommodation) {
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
