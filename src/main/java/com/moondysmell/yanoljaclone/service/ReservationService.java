package com.moondysmell.yanoljaclone.service;

import com.moondysmell.yanoljaclone.domain.*;
import com.moondysmell.yanoljaclone.domain.dto.ReservationMakeDto;
import com.moondysmell.yanoljaclone.domain.dto.ReservationResponseDto;
import com.moondysmell.yanoljaclone.repository.AccommodationRepository;
import com.moondysmell.yanoljaclone.repository.UserRepository;
import com.moondysmell.yanoljaclone.repository.reservation.ReservationRepository;
//import com.moondysmell.yanoljaclone.repository.reservation.ReservationRepositoryCustom;
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
    @Autowired
    private final AccommodationRepository accomRepository;
    @Autowired
    private final UserRepository userRepository;
    @Autowired
    private final ReservationRepository reservRepositoty;

//    @Transactional
//    public List<ReservationResponseDto> getReservedResult(String name, int reserv_id, String phone_num) {
//
//        List<ReservationResponseDto> reservList = reservRepositoty.findReservedDetail(name, reserv_id, phone_num);
//
//        if (reservList.isEmpty()) {
//            throw new IllegalArgumentException("예약 내역이 존재하지 않습니다.");
//        }
//
//        return reservList;
//        //return reserv.stream().map(ReservationResponseDto::new).collect(Collectors.toList());
//        //return reservList.stream()
//        //       .map(reserv -> new ReservationResponseDto(reserv))
//        //       .collect(Collectors.toList());
//    }

    @Transactional
    public List<ReservationResponseDto> getReservedResult(String name, int reserv_id, String phone_num) {

        List<ReservationResponseDto> reservList = reservRepositoty.findReservedDetail(name, reserv_id, phone_num);

        if (reservList == null) {
            throw new IllegalArgumentException("예약 내역이 존재하지 않습니다.");
        }

        return reservList;
        //return reserv.stream().map(ReservationResponseDto::new).collect(Collectors.toList());
        //return reservList.stream()
        //       .map(reserv -> new ReservationResponseDto(reserv))
        //       .collect(Collectors.toList());
    }

    @Transactional
    public ReservationResponseDto reserv(ReservationMakeDto reservationMakeDto) {
        Reservation newReserv = reservationMakeDto.toReservationEntity();

        Accommodation accom = accomRepository.findById(reservationMakeDto.getAccomId()).orElseThrow(
                () -> {
                    throw new IllegalArgumentException("해당 숙박업소를 조회할 수 없습니다.");
                }
        );

        //예약내역 입력과 동시에 customerTable에 customer정보 insert
        //입력한 customer객체의 정보를 예약 등록하는 내역에 설정
        Customer customer = Customer.createUser()
                .name(reservationMakeDto.getUserName())
                .phone_num(reservationMakeDto.getPhoneNum())
                .build();
        newReserv.addCustomer(customer);
        newReserv.setAccom(accom);

        newReserv = reservRepositoty.save(newReserv);


        return new ReservationResponseDto(newReserv);
    }

}
