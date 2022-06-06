package com.moondysmell.yanoljaclone.repository;

import com.moondysmell.yanoljaclone.domain.Accommodation;
import com.moondysmell.yanoljaclone.domain.LocationCode;
import java.util.List;
import java.util.Optional;
import org.hibernate.mapping.Any;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface AccomRepository extends JpaRepository<Accommodation, Integer> {
    List<Accommodation> findAllByLocationCode(int locationCode);
    Optional<Accommodation> findOneByAccomCode(String  code);
    List<Accommodation> findAllByAccomCode(String accomCode);
    List<Accommodation> findAllByLocationCodeAndType(int locationCode, String type);

}
