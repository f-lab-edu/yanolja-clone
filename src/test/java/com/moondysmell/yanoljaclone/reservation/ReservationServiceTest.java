package com.moondysmell.yanoljaclone.reservation;

import com.moondysmell.yanoljaclone.domain.Reservation;
import com.moondysmell.yanoljaclone.domain.dto.ReservationResponseDto;
import com.moondysmell.yanoljaclone.service.ReservationService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Set;

@SpringBootTest
class ReservationServiceTest {

	@Autowired
	private ReservationService reservService;
	//private ReservationRepository reservRepository;

	/*@Test
	public void create(){
		Reservation reserv = new Reservation();

		Reservation newReserv = reservRepository.save(reserv);


		Assertions.assertNotNull(newReserv);
		/*Reservation reserv = new Reservation().builder()
				.checkin("2021-05-21")
				.chechout("2021-05-24")

				.email(email)
				.userOauthType(userOauthType)
				.build();
		reservationService.makeReservaion(reserv)

}
		 */




	@Test
	@Transactional
	public void read(){


		List<ReservationResponseDto> reserv = reservService.getReservedResult("smell",1,"01022223333");
	}



}
