package com.moondysmell.yanoljaclone.domain.dto;

import javax.persistence.Id;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Getter
@Builder
@ToString
public class EmptyRoomDto {
    @Id
    private int id;

    private String accomCode;

    private String accomName;

    private String address;

    @NotBlank
    private String roomName;

    private int emptyRoomCnt;

    private int price;

}
