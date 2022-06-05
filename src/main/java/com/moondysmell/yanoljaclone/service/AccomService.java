package com.moondysmell.yanoljaclone.service;
import com.moondysmell.yanoljaclone.domain.Accommodation;
import com.moondysmell.yanoljaclone.domain.LocationCode;
import com.moondysmell.yanoljaclone.domain.RoomType;
import com.moondysmell.yanoljaclone.domain.dto.AccomAddDto;
import com.moondysmell.yanoljaclone.domain.dto.RoomAddDto;
import com.moondysmell.yanoljaclone.repository.AccomRepository;
import com.moondysmell.yanoljaclone.repository.LocationCodeRepository;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;
import javax.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Transactional
@Slf4j
public class AccomService {
    private final AccomRepository accomRepository;
    private final LocationCodeRepository locationCodeRepository;
    private final ReservationService reservationService;

    @Autowired
    public AccomService(AccomRepository accomRepository,
                        LocationCodeRepository locationCodeRepository,
                        ReservationService reservationService) {
        this.accomRepository = accomRepository;
        this.locationCodeRepository = locationCodeRepository;
        this.reservationService = reservationService;
    }


    public List<Accommodation> findAllByLocationCode(int locationCode) {
        Optional<LocationCode> location= locationCodeRepository.findById(locationCode);
        if (location.isEmpty())
            throw new RuntimeException("Location Code가 존재하지 않습니다.");
        return accomRepository.findAllByLocationCode(location.get().getCode());
    }

    public List<Accommodation> findAllByTypeAndLocationCode(String type, int locationCode) {
        Optional<LocationCode> location= locationCodeRepository.findById(locationCode);
        if (location.isEmpty())
            throw new RuntimeException("Location Code가 존재하지 않습니다.");

        try{
            RoomType typevalid = RoomType.valueOf(type);
        }catch (IllegalArgumentException e) {
            log.error(">>> " + e.getMessage());
            throw new RuntimeException("type이 존재하지 않습니다.");
        }

        return accomRepository.findAllByLocationCodeAndType(locationCode, type);
    }

//    public List<Accommodation> findAllByDate(String from, String to) {
//        //to - from => 최대 7일. 7일보다 길어질 경우 숙소에 직접 문의
//        // from은 현재보다 더 과거로 갈 수 없음
//        // to는 최대 한달 후? 다음달 까지 가능
//        // from 부터 to까지 모든 날짜를 다 List로 만들어 놓고 for문 돌려야함
//        int reservedCnt = reservationService.getReservByAccomIdAndDate();
//
//    }

    //reservationService 에서 예약 가능한지 확인할 때 사용하는 함수
    public int countEmptyRoomsByAccomId(int accomId, LocalDate from, LocalDate to) {
        Optional<Accommodation> targetAccom = accomRepository.findById(accomId);
        if (targetAccom.isEmpty())
            throw new RuntimeException("accomId 가 존재하지 않습니다. 다시 확인해주세요");

        int emptyRoomResult = 9999;
        int totalRoomCnt = targetAccom.get().getRoomCnt();

        List<LocalDate> listOfDates = from.datesUntil(to)
                                          .collect(Collectors.toList());
        log.info("from과 to의 날짜 차이: " + listOfDates.size());

        for (LocalDate ld : listOfDates) {
            Date targetDate = java.sql.Date.valueOf(ld);
            int reservedCnt = reservationService.getReservByAccomIdAndDate(accomId, targetDate);
            int emptyCnt = totalRoomCnt - reservedCnt;
            emptyRoomResult = (emptyRoomResult > emptyCnt) ? emptyCnt : emptyRoomResult;
        }

        return emptyRoomResult;
    }







    public Accommodation createAccom(AccomAddDto accom) {
        Accommodation newAccom = new Accommodation();
        String accomCode = createAccomCode();
        String roomType =  RoomType.valueOf(accom.getType()).toString();

        Optional<LocationCode> locationCode= locationCodeRepository.findById(accom.getLocationCode());
        if (locationCode.isEmpty())
            throw new RuntimeException("Location Code가 존재하지 않습니다.");

        newAccom.setAccomCode(accomCode);
        newAccom.setAccomName(accom.getAccomName());
//        newAccom.setLocationCode(locationCode.get());
        newAccom.setLocationCode(accom.getLocationCode());
        newAccom.setAddress(accom.getAddress());
        newAccom.setType(roomType);
        newAccom.setRoomName(accom.getRoomName());
        newAccom.setRoomCnt(accom.getRoomCnt());
        newAccom.setPrice(accom.getPrice());

        return accomRepository.save(newAccom);

    }

    public String createAccomCode() {
        return UUID.randomUUID().toString().substring(0,8);
    }



    public Accommodation createRoom(RoomAddDto roomAddDto) {
        List<Accommodation> accomList = accomRepository.findAllByAccomCode(roomAddDto.getAccomCode());

        if (accomList.size() == 0)
            throw new RuntimeException("숙소가 존재하지 않습니다.");

        Accommodation newRoomAccom = accomList.get(0).copy();
        newRoomAccom.setId(null);
        newRoomAccom.setRoomName(roomAddDto.getRoomName());
        newRoomAccom.setRoomCnt(roomAddDto.getRoomCnt());
        newRoomAccom.setPrice(roomAddDto.getPrice());

        try {
            newRoomAccom =accomRepository.save(newRoomAccom);
        } catch (Exception e) {
            log.info(">>> " + e);
        }
        return newRoomAccom;
    }
}
