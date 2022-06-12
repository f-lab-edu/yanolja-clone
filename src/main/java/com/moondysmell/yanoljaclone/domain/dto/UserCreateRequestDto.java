package com.moondysmell.yanoljaclone.domain.dto;

import com.moondysmell.yanoljaclone.domain.Customer;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserCreateRequestDto {

    @NotBlank(message="NAME_IS_MANDATORY")
    private String name;

    @NotBlank(message="PHONENUM_IS_MANDATORY")
    private String phone_num;

//    public Customer toEntity(){
//        return Customer.createUser()
//                .name(name)
//                .phone_num(phone_num)
//                .build();
//    }

//    @Builder(builderMethodName = "createUser")
//    public UserCreateRequestDto(String name, String phone_num) {
//        this.name = name;
//        this.phone_num = phone_num;
//    }
}
