package com.moondysmell.yanoljaclone.service;

import com.moondysmell.yanoljaclone.domain.Accommodation;
import com.moondysmell.yanoljaclone.domain.LocationCode;
import com.moondysmell.yanoljaclone.domain.RoomType;
import com.moondysmell.yanoljaclone.domain.dto.AccommodationAddDto;
import com.moondysmell.yanoljaclone.domain.dto.AccommodationReadDto;
import com.moondysmell.yanoljaclone.repository.AccomRepository;
import com.moondysmell.yanoljaclone.repository.LocationCodeRepository;
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
    @Autowired
    public AccomService(AccomRepository accomRepository,
                        LocationCodeRepository locationCodeRepository) {
        this.accomRepository = accomRepository;
        this.locationCodeRepository = locationCodeRepository;
    }

    public List<LocationCode> findAllLocationCode() {
        return locationCodeRepository.findAll();
    }

    public List<Accommodation> findAllDetail() {
        return accomRepository.findAllDetail();
    }
    public List<Accommodation> findAllByLocationCode(int locationCode) {
        Optional<LocationCode> location= locationCodeRepository.findById(locationCode);
        if (location.isEmpty())
            throw new RuntimeException("Location Code가 존재하지 않습니다.");

        return accomRepository.findAllByLocationCode(location.get());
    }


    public Accommodation createAccom(AccommodationAddDto accom) {
        Accommodation newAccom = new Accommodation();
        String accomCode = createAccomCode();
        String roomType =  RoomType.valueOf(accom.getType()).toString();

        Optional<LocationCode> locationCode= locationCodeRepository.findById(accom.getLocationCode());
        if (locationCode.isEmpty())
            throw new RuntimeException("Location Code가 존재하지 않습니다.");

        newAccom.setAccomCode(accomCode);
        newAccom.setAccomName(accom.getAccomName());
        newAccom.setLocationCode(locationCode.get());
//        newAccom.setLocationCode(accom.getLocationCode());
        newAccom.setAddress(accom.getAddress());
        newAccom.setType(roomType);
        newAccom.setRoomName(accom.getRoomName());
        newAccom.setRoomCnt(accom.getRoomCnt());
        newAccom.setPrice(accom.getPrice());
        newAccom.setDetail(accom.getDetail());

        return accomRepository.save(newAccom);

    }

    public String createAccomCode() {
        return UUID.randomUUID().toString().substring(0,8);
    }

    public List<AccommodationReadDto> convertAccomToReadDto(List<Accommodation> accomList) {
        return accomList.stream()
                   .map(accom -> new AccommodationReadDto(accom))
                   .collect(Collectors.toList());
    }


}
