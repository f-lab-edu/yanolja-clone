package com.moondysmell.yanoljaclone.service;

import com.moondysmell.yanoljaclone.domain.Reservation;
import com.moondysmell.yanoljaclone.domain.dto.ReservationResponseDto;
import com.moondysmell.yanoljaclone.repository.reservation.ReservationRepository;
//import com.moondysmell.yanoljaclone.repository.reservation.ReservationRepositoryCustom;
import java.util.Date;
import lombok.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@Transactional(readOnly=true)
@RequiredArgsConstructor
public class ReservationService {
   // @Autowired
   // private final Accommodation accomRepository;
   // @Autowired
   // private final UserRepository userRepository;
    @Autowired
    private final ReservationRepository reservRepositoty;

    @Transactional
    public List<ReservationResponseDto> getReservedResult(String name, int reserv_id, String phone_num){

        List<ReservationResponseDto> reservList = reservRepositoty.findReservedDetail(name, reserv_id, phone_num);

        if(reservList.isEmpty()){
            throw new IllegalArgumentException("예약 내역이 존재하지 않습니다.");
        }

        return reservList;
        //return reserv.stream().map(ReservationResponseDto::new).collect(Collectors.toList());
        //return reservList.stream()
         //       .map(reserv -> new ReservationResponseDto(reserv))
         //       .collect(Collectors.toList());
    }

    public int getReservByAccomIdAndDate(int accomId, Date targetDate) {
        // target date를 포함하고, reserv_status가 reserv_comple인 예약
         return reservRepositoty.roomCntByAccomIdAndDate(accomId, targetDate);

//         return reservations.size();
    }

    //public int makeReservation()
}
