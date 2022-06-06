package com.moondysmell.yanoljaclone.domain.dto;

import com.moondysmell.yanoljaclone.domain.Customer;
import lombok.*;
import org.springframework.beans.BeanUtils;

@Getter
@Setter
@NoArgsConstructor
public class UserResponseDto {
    private int user_id;
    private String name;
    private String phone_num;

    public UserResponseDto(Customer customer) {
        BeanUtils.copyProperties(customer, this);
    }


}
