package com.moondysmell.yanoljaclone.user;

import com.moondysmell.yanoljaclone.ServerApplicationTests;
import com.moondysmell.yanoljaclone.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;


public class UserServiceTest extends ServerApplicationTests {

	@Autowired
	private UserRepository userRepository;
	//private UserService userService;

    @Test
    public void create() {
        String name = "hyangah";
        int phone_num = 01012345677;

        /*User user = new User().builder()
                .name(name)
                .phone_num(phone_num)
                .build();
        userService.createUser(user);
        */



    }



}
