package com.moondysmell.yanoljaclone.repository;

import com.moondysmell.yanoljaclone.domain.Accommodation;
import java.util.List;
import org.hibernate.mapping.Any;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccomRepository extends JpaRepository<Accommodation, Any> {
    List<Accommodation> findAllByLocationCode(Integer locationCode);
}
