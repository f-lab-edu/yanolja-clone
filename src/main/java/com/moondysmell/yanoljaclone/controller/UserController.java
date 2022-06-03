package com.moondysmell.yanoljaclone.controller;

import com.moondysmell.yanoljaclone.domain.dto.UserCreateRequestDto;
import com.moondysmell.yanoljaclone.domain.dto.UserResponseDto;
import com.moondysmell.yanoljaclone.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value="/user")
public class UserController {

    private final UserService userService;

    public UserController(final UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public ResponseEntity<?> createUSer(@Validated @RequestBody final UserCreateRequestDto userCreateRequestDto, BindingResult bindingResult){
       /* if (bindingResult.hasErrors()) {
            List<String> errors = bindingResult.getAllErrors().stream().map(e -> e.getDefaultMessage()).collect(Collectors.toList());
            // 200 response with 404 status code
            //return ResponseEntity.ok(new ErrorResponse("404", "Validation failure", errors));
            // 404 request
            return ResponseEntity.badRequest().body(new ErrorResponse("404", "Validation failure", errors));
        }*/

        return new ResponseEntity(
                new UserResponseDto(userService.createUser(userCreateRequestDto.toEntity())),HttpStatus.CREATED
            );
        }


}
