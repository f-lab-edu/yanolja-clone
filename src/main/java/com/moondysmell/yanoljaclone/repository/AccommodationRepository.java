package com.moondysmell.yanoljaclone.repository;

import com.moondysmell.yanoljaclone.domain.Accommodation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccommodationRepository extends JpaRepository<Accommodation, Integer> {

}
