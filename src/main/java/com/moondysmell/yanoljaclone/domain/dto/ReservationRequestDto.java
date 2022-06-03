package com.moondysmell.yanoljaclone.domain.dto;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class ReservationRequestDto {
    private String name;
    private int reserv_id;
    private String phone_num;
}
