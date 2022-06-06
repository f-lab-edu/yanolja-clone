package com.moondysmell.yanoljaclone.service;

import com.moondysmell.yanoljaclone.common.CommonCode;
import com.moondysmell.yanoljaclone.common.CustomException;
import com.moondysmell.yanoljaclone.domain.*;
import com.moondysmell.yanoljaclone.domain.dto.ReservationMakeDto;
import com.moondysmell.yanoljaclone.domain.dto.ReservationResponseDto;
import com.moondysmell.yanoljaclone.repository.AccommodationRepository;
import com.moondysmell.yanoljaclone.repository.UserRepository;
import com.moondysmell.yanoljaclone.repository.reservation.ReservationRepository;
//import com.moondysmell.yanoljaclone.repository.reservation.ReservationRepositoryCustom;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.Optional;
import lombok.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
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

    @Transactional
    public List<ReservationResponseDto> getReservedResult(String name, int reserv_id, String phone_num){

        //회원T에 해당회원정보가 있는지 확인, 없으면 예외처리
        Customer customer = userRepository.findByNameAndPhoneNum(name,phone_num).orElseThrow(
                () -> {
                    throw new CustomException(CommonCode.CUSTOMER_IS_NOT_EXIST);
                }
        );

        //
        reservRepositoty.findById(reserv_id).orElseThrow(() -> {
            throw new CustomException(CommonCode.RRSERV_ID_IS_NOT_EXIST);
        });

        //해당 회원이 있으면 예약 내역 조회
        List<ReservationResponseDto> reservList = reservRepositoty.findReservedDetail(customer.getName(), reserv_id, customer.getPhoneNum());

        if(reservList.isEmpty()){
            throw new CustomException(CommonCode.RESERVATION_IS_NOT_EXIST);
        }

        return reservList;
    }

    @Transactional
    public ReservationResponseDto reserv(ReservationMakeDto reservationMakeDto) {
        Reservation newReserv = reservationMakeDto.toReservationEntity();

        int accomId = reservationMakeDto.getAccomId();
        String userName = reservationMakeDto.getUserName();
        String phoneNum = reservationMakeDto.getPhoneNum();
        int roomCnt = reservationMakeDto.getRoomCnt();
        LocalDate checkin = convertDateToLocalDate(reservationMakeDto.getCheckin());
        LocalDate checkout = convertDateToLocalDate(reservationMakeDto.getCheckout());


        Accommodation targetAccom = accomRepository.findById(accomId).orElseThrow(
                () -> {
                    throw new CustomException(CommonCode.ACCOM_ID_NOT_EXIST);
                }
        );

        //예약가능한 날짜와 방
        int emptyRoomCnt = countEmptyRoomsByAccomIdAndDate(accomId, checkin, checkout);
        if (emptyRoomCnt < roomCnt) throw new CustomException(CommonCode.AVAILABLE_ROOM_IS_NOT_EXIST);

        //예약내역 입력과 동시에 customerTable에 customer정보 insert
        //입력한 customer객체의 정보를 예약 등록하는 내역에 설정
        //customer값이 있으면 그 값받아서 Reservation에 사용, 없으면 새로 생성
        Customer customer = userRepository.findByNameAndPhoneNum(userName,phoneNum).orElse(
                Customer.createUser()
                .name(reservationMakeDto.getUserName())
                .phoneNum(reservationMakeDto.getPhoneNum())
                .build());

        newReserv.addCustomer(customer);
        newReserv.setAccom(targetAccom);

        newReserv = reservRepositoty.save(newReserv);

        return new ReservationResponseDto(newReserv);
    }

    @Transactional
    public void cancleReservation(int reserv_id){
        //예약 취소 시 상태값만 변경 (reserv_complete -> reserve_cancle)
        Reservation reservation = reservRepositoty.findById(reserv_id).get();
        if(reservation.getReserv_status() == ReservStatus.reserv_complete) {
            reservation.setReserv_status(ReservStatus.reserve_cancle);
        }else{
            throw new CustomException(CommonCode.AVAILABLE_ROOM_IS_NOT_EXIST);
        }
        reservRepositoty.save(reservation);

    }

    public int getReservByAccomIdAndDate(int accomId, Date targetDate) {
        // target date를 포함하고, reserv_status가 reserv_comple인 예약
        Optional<Integer> cnt = reservRepositoty.roomCntByAccomIdAndDate(accomId, targetDate);
        return cnt.isPresent() ? cnt.get() : 0;

    }

    public LocalDate convertDateToLocalDate(Date dt) {
        return dt.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
    }

    //reservationService 에서 예약 가능한지 확인할 때 사용하는 함수
    public int countEmptyRoomsByAccomIdAndDate(int accomId, LocalDate from, LocalDate to) {
        Optional<Accommodation> targetAccom = accomRepository.findById(accomId);
        if (targetAccom.isEmpty())
            throw new RuntimeException("accomId 가 존재하지 않습니다. 다시 확인해주세요");

        int emptyRoomResult = 9999;
        int totalRoomCnt = targetAccom.get().getRoomCnt();

        // from 부터 to까지 모든 날짜를 다 List로 만들어 놓고 for문 돌림
        List<LocalDate> listOfDates = from.datesUntil(to)
                .collect(Collectors.toList());
        log.info("from과 to의 날짜 차이: " + listOfDates.size());

        for (LocalDate ld : listOfDates) {
            Date targetDate = java.sql.Date.valueOf(ld);
            int reservedCnt = getReservByAccomIdAndDate(accomId, targetDate);
            int emptyCnt = totalRoomCnt - reservedCnt;
            emptyRoomResult = (emptyRoomResult > emptyCnt) ? emptyCnt : emptyRoomResult;
        }

        return emptyRoomResult;
    }


}
