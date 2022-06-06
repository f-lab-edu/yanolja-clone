package com.moondysmell.yanoljaclone.controller;

import static java.time.temporal.ChronoUnit.DAYS;
import static java.util.stream.Collectors.toSet;

import com.moondysmell.yanoljaclone.common.CommonResponse;
import com.moondysmell.yanoljaclone.domain.Accommodation;
import com.moondysmell.yanoljaclone.domain.LocationCode;
import com.moondysmell.yanoljaclone.domain.dto.AccomAddDto;
import com.moondysmell.yanoljaclone.domain.dto.EmptyRoomDto;
import com.moondysmell.yanoljaclone.domain.dto.RoomAddDto;
import com.moondysmell.yanoljaclone.common.CustomException;
import com.moondysmell.yanoljaclone.common.CommonCode;
import com.moondysmell.yanoljaclone.service.AccomService;
import com.moondysmell.yanoljaclone.service.LocationCodeService;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<CommonResponse<Set<Accommodation>>> getAccomByLocation(@RequestParam int locationCode) throws Exception {
        List<Accommodation> accomList = accomService.findAllByLocationCode(locationCode);
        Map<String, Set<Accommodation>> result = accomList.stream()
                   .collect(Collectors.groupingBy(Accommodation::getAccomCode, toSet()));

        return ResponseEntity.ok(new CommonResponse(CommonCode.SUCCESS, result));
    }

    //지역 코드와 숙소 타입으로 숙소 검색
    @GetMapping("/accom/getByTypeLocation")
    public ResponseEntity<CommonResponse<Set<Accommodation>>> getAccomByTypeLocation(@RequestParam int locationCode, @RequestParam String locationType) throws CustomException {
        List<Accommodation> accomList = accomService.findAllByTypeAndLocationCode(locationType, locationCode);
        Map<String, Set<Accommodation>> result = accomList.stream()
                                                     .collect(Collectors.groupingBy(Accommodation::getAccomCode, toSet()));

        return ResponseEntity.ok(new CommonResponse(CommonCode.SUCCESS, result));
    }

    // 예약가능한 방 검색(숙소 코드와 날짜를 기준으로 검색)
    @GetMapping("/room/getByDate")
    public ResponseEntity<CommonResponse> getRoomByDate(@RequestParam("from") String from, @RequestParam("to") String to, @RequestParam("accomCode") String accomCode) {
        LocalDate fromDate = LocalDate.parse(from, DateTimeFormatter.ISO_DATE);
        LocalDate toDate = LocalDate.parse(to, DateTimeFormatter.ISO_DATE);

        // from은 현재보다 더 과거로 갈 수 없음
        //to - from => 최대 7일. 7일보다 길어질 경우 숙소에 직접 문의
        // to는 최대 한달 후까지 가능
        if (fromDate.isAfter(toDate))
            throw new CustomException(CommonCode.CHECKIN_CHECKOUT_REVERSE_ERROR);

        if (fromDate.isBefore(LocalDate.now()))
            throw new CustomException(CommonCode.CHECKIN_IS_PAST_ERROR);

        if (DAYS.between(fromDate,toDate) > 7)
            throw new CustomException(CommonCode.CHECKIN_CHECKOUT_TOO_FAR_ERROR);

        if (toDate.isAfter(LocalDate.now().plusMonths(1)) && fromDate.isAfter(LocalDate.now().plusMonths(1)))
            throw new CustomException(CommonCode.CHECKIN_CHECKOUT_OUT_OF_RANGE_ERROR);

        //대략적인 예약수만 확인 (from 날짜 하루만 예약수 확인)
        List<EmptyRoomDto> result = accomService.findAllRoomByCodeAndDate(accomCode, fromDate);
        return ResponseEntity.ok(new CommonResponse(CommonCode.SUCCESS, Map.of("result", result)));
    }


    // 지역코드 리스트 받기 (register form을 위해)
    @GetMapping("/getLocationCode")
    public ResponseEntity<CommonResponse> getAllLocationCode() {
        List<LocationCode> result = locationCodeService.findAllLocationCode();
        return ResponseEntity.ok(new CommonResponse(CommonCode.SUCCESS, Map.of("result", result)));
    }

    //숙소 추가
    //필수: accomName, locationCode, address, type, roomName, roomCnt, price
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @PostMapping("/accom/add")
    public ResponseEntity<CommonResponse> addAccom(@RequestBody @Valid AccomAddDto accom) {
        Accommodation createdAccom = accomService.createAccom(accom);
        return ResponseEntity.ok(new CommonResponse(CommonCode.SUCCESS, Map.of("createdAccom", createdAccom)));
    }

    //방 추가
    //필수: accomCode, roomName, roomCnt, price
    @PostMapping("/room/add")
    public ResponseEntity<CommonResponse> addRoom(@RequestBody @Valid RoomAddDto roomAddDto) {
        Accommodation createdRoom = accomService.createRoom(roomAddDto);
        return ResponseEntity.ok(new CommonResponse(CommonCode.SUCCESS, Map.of("createdRoom", createdRoom)));
    }

//    //CustomException 쓰는 방법 테스트
//    @GetMapping("/errorTest")
//    public ResponseEntity<CommonResponse<String>> errorTest(@RequestParam("b") boolean b) {
//        if (b) {
//            return ResponseEntity.ok(new CommonResponse(CommonCode.SUCCESS));
//        } else
//            throw new CustomException(CommonCode.FAIL);
//    }

}
