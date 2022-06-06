package com.moondysmell.yanoljaclone.service;
import com.moondysmell.yanoljaclone.domain.Accommodation;
import com.moondysmell.yanoljaclone.domain.LocationCode;
import com.moondysmell.yanoljaclone.domain.RoomType;
import com.moondysmell.yanoljaclone.domain.dto.AccomAddDto;
import com.moondysmell.yanoljaclone.domain.dto.EmptyRoomDto;
import com.moondysmell.yanoljaclone.domain.dto.RoomAddDto;
import com.moondysmell.yanoljaclone.repository.AccomRepository;
import com.moondysmell.yanoljaclone.repository.LocationCodeRepository;
import java.time.LocalDate;
import java.util.ArrayList;
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

    public List<EmptyRoomDto> findAllRoomByCodeAndDate(String accomCode, LocalDate from) {
        List<Accommodation> accommodationList = accomRepository.findAllByAccomCode(accomCode);
        if (accommodationList.size() == 0)
            throw new RuntimeException("accomCode에 해당하는 숙소가 없습니다.");

        List<EmptyRoomDto> result = new ArrayList<>();
        Date targetDate = java.sql.Date.valueOf(from);

        //대략적인 예약수만 확인 (from 날짜 하루만 예약수 확인). 정확한 빈 방 계산은 예약하는 시점에 실행
        for (Accommodation accom : accommodationList) {
            int totalRoomCnt = accom.getRoomCnt();
            int reservedCnt = reservationService.getReservByAccomIdAndDate(accom.getId(), targetDate);
            EmptyRoomDto room = EmptyRoomDto.builder()
                           .id(accom.getId())
                           .accomCode(accom.getAccomCode())
                           .accomName(accom.getAccomName())
                           .address(accom.getAddress())
                           .roomName(accom.getRoomName())
                           .emptyRoomCnt(totalRoomCnt - reservedCnt)
                           .price(accom.getPrice())
                           .build();
            result.add(room);
        }

        return result;
    }



    public Accommodation createAccom(AccomAddDto accom) {
//        Accommodation newAccom = new Accommodation();
        String accomCode = createAccomCode();
        String roomType =  RoomType.valueOf(accom.getType()).toString();

        Optional<LocationCode> locationCode= locationCodeRepository.findById(accom.getLocationCode());
        if (locationCode.isEmpty())
            throw new RuntimeException("Location Code가 존재하지 않습니다.");

        Accommodation newAccom = Accommodation.builder()
                                     .accomCode(accomCode)
                                     .accomName(accom.getAccomName())
                                     .locationCode(accom.getLocationCode())
                                     .address(accom.getAddress())
                                     .type(roomType)
                                     .roomName(accom.getRoomName())
                                     .roomCnt(accom.getRoomCnt())
                                     .price(accom.getPrice())
                                     .detail(accom.getDetail())
                                     .build();

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
