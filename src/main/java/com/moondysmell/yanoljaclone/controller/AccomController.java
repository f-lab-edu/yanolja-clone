package com.moondysmell.yanoljaclone.controller;

import static java.util.stream.Collectors.toSet;

import com.moondysmell.yanoljaclone.domain.Accommodation;
import com.moondysmell.yanoljaclone.domain.LocationCode;
import com.moondysmell.yanoljaclone.domain.dto.AccomAddDto;
import com.moondysmell.yanoljaclone.domain.dto.RoomAddDto;
import com.moondysmell.yanoljaclone.service.AccomService;
import com.moondysmell.yanoljaclone.service.LocationCodeService;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;

@RequiredArgsConstructor
@RestController
@RequestMapping("/accommodation")
public class AccomController {
    private final AccomService accomService;
    private final LocationCodeService locationCodeService;


    //지역 코드로 숙소 검색
    @GetMapping("/getAll/byLocation")
    public Map<String, Set<Accommodation>> getAccomByLocation(@RequestParam int locationCode) throws Exception {
        List<Accommodation> accomList = accomService.findAllByLocationCode(locationCode);
        return accomList.stream()
                   .collect(Collectors.groupingBy(Accommodation::getAccomCode, toSet()));
    }

    // 지역코드 리스트 받기 (register form을 위해)
    @GetMapping("/getLocationCode")
    public List<LocationCode> getAllLocationCode() {
        return locationCodeService.findAllLocationCode();
    }

    //숙소 추가
    //필수: accomName, locationCode, address, type, roomName, roomCnt, price
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @PostMapping("/add/accom")
    public Accommodation addAccom(@RequestBody @Valid AccomAddDto accom) {
        return accomService.createAccom(accom);
    }

    //방 추가
    //필수: accomCode, roomName, roomCnt, price 선택:detail
    @PostMapping("/add/room")
    public Accommodation addRoom(@RequestBody @Valid RoomAddDto roomAddDto) {
        return accomService.createRoom(roomAddDto);
    }








}
