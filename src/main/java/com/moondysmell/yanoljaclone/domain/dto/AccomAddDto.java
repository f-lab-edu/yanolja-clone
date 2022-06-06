package com.moondysmell.yanoljaclone.domain.dto;

import javax.persistence.Id;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class AccomAddDto {
    @Id
    @NotBlank
    private String accomCode;

    @NotBlank
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


}
