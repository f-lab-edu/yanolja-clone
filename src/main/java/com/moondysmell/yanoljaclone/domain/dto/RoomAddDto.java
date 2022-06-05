package com.moondysmell.yanoljaclone.domain.dto;

import javax.persistence.Id;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import lombok.Getter;

@Getter
public class RoomAddDto {
    @Id
    @NotBlank
    private String accomCode;

    @NotBlank
    private String roomName;

    @Min(value = 0)
    private int roomCnt;

    @Min(value = 0)
    private int price;

    private String detail;

}
