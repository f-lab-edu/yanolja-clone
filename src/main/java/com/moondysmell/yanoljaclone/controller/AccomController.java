package com.moondysmell.yanoljaclone.controller;

import com.moondysmell.yanoljaclone.domain.Accommodation;
import com.moondysmell.yanoljaclone.domain.LocationCode;
import com.moondysmell.yanoljaclone.domain.dto.AccommodationAddDto;
import com.moondysmell.yanoljaclone.domain.dto.AccommodationReadDto;
import com.moondysmell.yanoljaclone.service.AccomService;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import javax.validation.Valid;

@RequiredArgsConstructor
@RestController
@RequestMapping("/accommodation")
public class AccomController {
    private final AccomService accomService;


    //지역 코드로 숙소 검색
    @GetMapping("/getAll/location")
    public List<Accommodation> getAllLocation(@RequestParam int locationCode) throws Exception {
        List<Accommodation> result = accomService.findAllByLocationCode(locationCode);
        return result;
    }

    // 모든 숙소 검색 지역 정보까지 읽기 (join 연습용)
    @GetMapping("/getAllDetail")
    public List<AccommodationReadDto> getAllDetail() {
        List<Accommodation> accomList = accomService.findAllDetail();
        return accomList.stream()
            .map(accom -> new AccommodationReadDto(accom))
            .collect(Collectors.toList());
    }

    // 지역코드 리스트 받기 (register form을 위해)
    @GetMapping("getLocationCode")
    public List<LocationCode> getAllLocationCode() {
        return accomService.findAllLocationCode();
    }

    //숙소 추가
    //필수: accomName, locationCode, address, type, roomName, roomCnt, price
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @PostMapping("/add")
    public Accommodation addAccom(@RequestBody @Valid AccommodationAddDto accom) {
        return accomService.createAccom(accom);
    }



}
