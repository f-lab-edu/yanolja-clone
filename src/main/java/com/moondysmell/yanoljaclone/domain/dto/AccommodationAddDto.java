package com.moondysmell.yanoljaclone.domain.dto;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
public class AccommodationAddDto {
    @Id
    @NotBlank(message = "숙소 이름은 필수입니다")
    private String accomName;

    @NotNull
    private int locationCode;

    @NotBlank
    private String address;

    @NotBlank
    private String type;

    @NotBlank
    private String roomName;

    @Min(value = 0)
    private int roomCnt;

    @Min(value = 0)
    private int price;

    private String detail;

}
