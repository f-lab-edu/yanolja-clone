package com.moondysmell.yanoljaclone.service;

import com.moondysmell.yanoljaclone.domain.LocationCode;
import com.moondysmell.yanoljaclone.repository.LocationCodeRepository;
import java.util.List;
import javax.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Transactional
@Slf4j
public class LocationCodeService {
    private final LocationCodeRepository locationCodeRepository;
    @Autowired
    public LocationCodeService(LocationCodeRepository locationCodeRepository) {
        this.locationCodeRepository = locationCodeRepository;
    }

    public List<LocationCode> findAllLocationCode() {
        return locationCodeRepository.findAll();
    }


}
