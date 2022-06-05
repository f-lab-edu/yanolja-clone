package com.moondysmell.yanoljaclone.domain.dto;

import com.moondysmell.yanoljaclone.domain.Customer;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserCreateRequestDto {

    //@NotBlank(message="NAME_IS_MANDATORY")
    private String name;
   // @NotBlank(message="PASSWORD_IS_MANDATORY")
    private String phone_num;

    public Customer toEntity(){
        return Customer.builder()
                .name(name)
                .phone_num(phone_num)
                .build();
    }
}
