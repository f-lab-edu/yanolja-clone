package com.moondysmell.yanoljaclone.accommodation;

import com.moondysmell.yanoljaclone.ServerApplicationTests;
import com.moondysmell.yanoljaclone.domain.Accommodation;
import com.moondysmell.yanoljaclone.domain.RoomType;
import com.moondysmell.yanoljaclone.repository.AccommodationRepository;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;

public class AccomTest extends ServerApplicationTests {

    @Autowired
    private AccommodationRepository repository;
/*
    @Test
    public void create(){
        Accommodation accom = new Accommodation();
        accom.setAccom_name("강화도바다향기");
        accom.setAccom_code("A123");
        accom.setLocation_code(01);
        accom.setAddress("경기도 인천광역시 강화도");
        accom.setRoom_type(RoomType.hotel);
        accom.setRoom_name("바다방");
        accom.setRoom_cnt(3);
        accom.setPrice(70000);
        accom.setDetail("깨끗하고 넓은방");

        Accommodation newAccom = repository.save(accom);
        Assertions.assertNotNull(newAccom);


    }
*/
}
