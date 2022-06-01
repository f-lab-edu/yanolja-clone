package com.moondysmell.yanoljaclone.domain.dto;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class AccommodationAddDto {
    @Id
    @NotBlank(message = "숙소 이름은 필수입니다")
    private String accomName;

    @NotBlank
    private int locationCode;

    @NotBlank
    private String address;

    @NotBlank
    private String type;

    @NotBlank
    private String roomName;

    @NotBlank
    private int roomCnt;

    @NotBlank
    private int price;

    private String detail;

}
