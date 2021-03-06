package com.moondysmell.yanoljaclone.reservation;

import com.moondysmell.yanoljaclone.domain.PaymentType;
import com.moondysmell.yanoljaclone.domain.ReservStatus;
import com.moondysmell.yanoljaclone.domain.Reservation;
import com.moondysmell.yanoljaclone.domain.TransType;
import com.moondysmell.yanoljaclone.domain.dto.ReservationMakeDto;
import com.moondysmell.yanoljaclone.domain.dto.ReservationResponseDto;
import com.moondysmell.yanoljaclone.service.ReservationService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Set;

@SpringBootTest
class ReservationServiceTest {

	@Autowired
	private ReservationService reservService;
	//private ReservationRepository reservRepository;

	@Test
	public void create(){

		Date date = new Date();
		//SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");

		ReservationMakeDto reservationMakeDto = new ReservationMakeDto();


		reservationMakeDto.setAccomId(1);
		reservationMakeDto.setUserName("kkk");
		reservationMakeDto.setPhoneNum("01012223333");
		reservationMakeDto.setCheckin(date);
		reservationMakeDto.setCheckout(date);
		reservationMakeDto.setTransType(TransType.car);
		reservationMakeDto.setPaymentType(PaymentType.card);
		reservationMakeDto.setRoomCnt(1);
		reservationMakeDto.setReservStatus(ReservStatus.reserv_complete);

		reservService.reserv(reservationMakeDto);

}





	@Test
	@Transactional
	public void read(){


		List<ReservationResponseDto> reserv = reservService.getReservedResult("smell",1,"01022223333");

	}

	@Test
	public void cancle(){

		reservService.cancleReservation(8);

	}



}
