package com.moondysmell.yanoljaclone.controller;

import static java.time.temporal.ChronoUnit.DAYS;
import static java.util.stream.Collectors.toSet;
import com.moondysmell.yanoljaclone.domain.Accommodation;
import com.moondysmell.yanoljaclone.domain.LocationCode;
import com.moondysmell.yanoljaclone.domain.dto.AccomAddDto;
import com.moondysmell.yanoljaclone.domain.dto.EmptyRoomDto;
import com.moondysmell.yanoljaclone.domain.dto.RoomAddDto;
import com.moondysmell.yanoljaclone.service.AccomService;
import com.moondysmell.yanoljaclone.service.LocationCodeService;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
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
    @GetMapping("/accom/getByLocation")
    public Map<String, Set<Accommodation>> getAccomByLocation(@RequestParam int locationCode) throws Exception {
        List<Accommodation> accomList = accomService.findAllByLocationCode(locationCode);
        return accomList.stream()
                   .collect(Collectors.groupingBy(Accommodation::getAccomCode, toSet()));
    }

    //지역 코드와 숙소 타입으로 숙소 검색
    @GetMapping("/accom/getByTypeLocation")
    public Map<String, Set<Accommodation>> getAccomByTypeLocation(@RequestParam int locationCode, @RequestParam String locationType) throws Exception {
        List<Accommodation> accomList = accomService.findAllByTypeAndLocationCode(locationType, locationCode);
        return accomList.stream()
                   .collect(Collectors.groupingBy(Accommodation::getAccomCode, toSet()));
    }

    // 예약가능한 방 검색(숙소 코드와 날짜를 기준으로 검색)
    @GetMapping("/room/getByDate")
    public List<EmptyRoomDto> getRoomByDate(@RequestParam("from") String from, @RequestParam("to") String to, @RequestParam("accomCode") String accomCode) {
        LocalDate fromDate = LocalDate.parse(from, DateTimeFormatter.ISO_DATE);
        LocalDate toDate = LocalDate.parse(to, DateTimeFormatter.ISO_DATE);

        // from은 현재보다 더 과거로 갈 수 없음
        //to - from => 최대 7일. 7일보다 길어질 경우 숙소에 직접 문의
        // to는 최대 한달 후까지 가능
        if (fromDate.isAfter(toDate))
            throw new RuntimeException("체크아웃 날짜는 체크인 날짜보다 미래여야 합니다");

        if (fromDate.isBefore(LocalDate.now()))
            throw new RuntimeException("체크인 날짜는 과거가 될 수 없습니다.");

        if (DAYS.between(fromDate,toDate) > 7)
            throw new RuntimeException("체크인 날짜와 체크아웃 날짜의 차이는 7일 이하여야 합니다.");

        if (toDate.isAfter(LocalDate.now().plusMonths(1)) && fromDate.isAfter(LocalDate.now().plusMonths(1)))
            throw new RuntimeException("한달 이내의 날짜만 예약 가능합니다. 체크인, 체크아웃 날짜를 확인해주세요.");

        //대략적인 예약수만 확인 (from 날짜 하루만 예약수 확인)
        return accomService.findAllRoomByCodeAndDate(accomCode, fromDate);
    }


    // 지역코드 리스트 받기 (register form을 위해)
    @GetMapping("/getLocationCode")
    public List<LocationCode> getAllLocationCode() {
        return locationCodeService.findAllLocationCode();
    }

    //숙소 추가
    //필수: accomName, locationCode, address, type, roomName, roomCnt, price
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @PostMapping("/accom/add")
    public Accommodation addAccom(@RequestBody @Valid AccomAddDto accom) {
        return accomService.createAccom(accom);
    }

    //방 추가
    //필수: accomCode, roomName, roomCnt, price
    @PostMapping("/room/add")
    public Accommodation addRoom(@RequestBody @Valid RoomAddDto roomAddDto) {
        return accomService.createRoom(roomAddDto);
    }


}
