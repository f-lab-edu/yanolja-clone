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
public interface ReservationRepository extends JpaRepository<Reservation, Any>{
    //@Query(value = "select distinct r from Reservation r join fetch r.accom ")
    //@Query(value = "select com.moondysmell.yanoljaclone.domain.Reservation(r.reserv_id, r.checkout) " +
    //jpaquery, querydsl, tuple, native query
     @Query(value = "select a.accom_name, r.reserv_id, r.checkin, r.checkout , r.trans_type, r.room_cnt, a.price, u.name, u.phone_num, r.payment_type, a.detail  " +
                   // " from Reservation r " +
                   // "join Accommodation a on r.accom.id = a.id " +
                   // "join Customer u on u.user_id = r.customer.user_id " +
                   // "where u.name = :name and r.reserv_id = :reserv_id and u.phone_num = :phone_num ")
                    "from Reservation r " +
                    "join fetch r.accom a" +
                    "join fetch r.customer c " +
                     "where c.name = :name and r.reserv_id = :reserv_id and c.phone_num = :phone_num ")
    List<ReservationResponseDto> findReservedDetail(@Param("name")String name, @Param("reserv_id")int reserv_id, @Param("phone_num")String phone_num);
}
