package com.moondysmell.yanoljaclone.repository.reservation;

import com.moondysmell.yanoljaclone.domain.Reservation;
import com.moondysmell.yanoljaclone.domain.dto.ReservationRequestDto;
import com.moondysmell.yanoljaclone.domain.dto.ReservationResponseDto;
import org.hibernate.mapping.Any;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReservationRepository extends JpaRepository<Reservation, Integer>{

    //jpaquery, querydsl, tuple, native query
     @Query(value = "select new com.moondysmell.yanoljaclone.domain.dto.ReservationResponseDto( r.reserv_id, a.accomName, r.checkin, r.checkout , r.trans_type, r.room_cnt, a.price, c.name, c.phone_num, r.payment_type, a.detail ) " +
                    "from Reservation r " +
                    "join Accommodation a on r.accom.id = a.id " +
                    "join Customer c on c.user_id = r.customer.user_id " +
                    "where c.name = :name and r.reserv_id = :reserv_id and c.phone_num = :phone_num ")
    //ReservationResponseDto findReservedDetail(@Param("name")String name, @Param("reserv_id")int reserv_id, @Param("phone_num")String phone_num);
    List<ReservationResponseDto> findReservedDetail(@Param("name")String name, @Param("reserv_id")int reserv_id, @Param("phone_num")String phone_num);

}
