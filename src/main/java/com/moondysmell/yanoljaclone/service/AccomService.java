package com.moondysmell.yanoljaclone.service;

import com.moondysmell.yanoljaclone.domain.Accommodation;
import com.moondysmell.yanoljaclone.domain.RoomType;
import com.moondysmell.yanoljaclone.domain.dto.AccommodationAddDto;
import com.moondysmell.yanoljaclone.repository.AccomRepository;
import java.util.List;
import java.util.UUID;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class AccomService {
    private final AccomRepository accomRepository;
    @Autowired
    public AccomService(AccomRepository accomRepository) {
        this.accomRepository = accomRepository;
    }

    public List<Accommodation> findAllByLocationCode(int locationCode) throws Exception {
        return accomRepository.findAllByLocationCode(locationCode);
    }


    public Accommodation createAccom(AccommodationAddDto accom) {
        Accommodation newAccom = new Accommodation();
        String accomCode = createAccomCode();
        newAccom.setId(null);
        newAccom.setAccomCode(accomCode);
        newAccom.setAccomName(accom.getAccomName());
        newAccom.setLocationCode(accom.getLocationCode());
        newAccom.setAddress(accom.getAddress());
        newAccom.setType(RoomType.valueOf(accom.getType()));
        newAccom.setRoomName(accom.getRoomName());
        newAccom.setRoomCnt(accom.getRoomCnt());
        newAccom.setPrice(accom.getPrice());
        newAccom.setDetail(accom.getDetail());

        return accomRepository.save(newAccom);

    }

    public String createAccomCode() {
        return UUID.randomUUID().toString().substring(0,8);
    }


}
