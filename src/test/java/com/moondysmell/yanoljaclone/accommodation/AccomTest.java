package com.moondysmell.yanoljaclone.accommodation;


import static java.util.stream.Collectors.toSet;

import com.moondysmell.yanoljaclone.domain.Accommodation;
import com.moondysmell.yanoljaclone.domain.RoomType;
import com.moondysmell.yanoljaclone.domain.dto.AccomAddDto;
import com.moondysmell.yanoljaclone.domain.dto.RoomAddDto;
import com.moondysmell.yanoljaclone.repository.AccommodationRepository;
import com.moondysmell.yanoljaclone.service.AccomService;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@Slf4j
public class AccomTest {

    @Autowired
    private AccomService accomService;

    @Test
    public void getAllByLocation() {
        List<Accommodation> accomList = accomService.findAllByLocationCode(10);

        Map<String, Set<Accommodation>> groupedAccomList = accomList.stream()
                                                               .collect(
                                                                   Collectors.groupingBy(
                                                                       Accommodation::getAccomCode,
                                                                       toSet()));
        log.info("groupedAccomList keySet: " + groupedAccomList.keySet().toString());
    }

    @Test
    public void getAllByLocationAndType() {
        List<Accommodation> accomList = accomService.findAllByTypeAndLocationCode("Hotel",10);

        Map<String, Set<Accommodation>> groupedAccomList = accomList.stream()
                                                               .collect(
                                                                   Collectors.groupingBy(
                                                                       Accommodation::getAccomCode,
                                                                       toSet()));
        log.info("groupedAccomList keySet: " + groupedAccomList.keySet().toString());
    }

    @Test
    public void addAccom() {
        AccomAddDto newAccom = AccomAddDto.builder()
                                              .accomCode(accomService.createAccomCode())
                                              .accomName("산속의산")
                                              .locationCode(20)
                                              .address("인천 계양구")
                                              .type(RoomType.Pension.toString())
                                              .roomName("스탠다드")
                                              .roomCnt(10)
                                              .price(50000)
                                              .build();
        Accommodation createdAccom = accomService.createAccom(newAccom);
        log.info("created Accom >>> " + createdAccom);
    }

    @Test
    public void addRoom() {
        RoomAddDto newRoom = RoomAddDto.builder()
                                            .accomCode("37c678ac")
                                            .roomName("대형방")
                                            .roomCnt(1)
                                            .price(100000)
                                            .build();

        Accommodation createdRoom = accomService.createRoom(newRoom);
        log.info(createdRoom.toString());
    }


    @Test
    public void calculateEmptyRoomByAccomId() {

        LocalDate from = LocalDate.parse("2022-06-06", DateTimeFormatter.ISO_DATE);
        LocalDate to = LocalDate.parse("2022-06-08", DateTimeFormatter.ISO_DATE);

        int emptyRoomCnt = accomService.countEmptyRoomsByAccomId(1,from, to);
        log.info("result: " + emptyRoomCnt);
    }
}
