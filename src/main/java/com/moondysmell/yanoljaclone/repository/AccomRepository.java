package com.moondysmell.yanoljaclone.repository;

import com.moondysmell.yanoljaclone.domain.Accommodation;
import com.moondysmell.yanoljaclone.domain.LocationCode;
import java.util.List;
import org.hibernate.mapping.Any;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface AccomRepository extends JpaRepository<Accommodation, Any> {
    List<Accommodation> findAllByLocationCode(LocationCode locationCode);

    @Query("SELECT a FROM Accommodation a JOIN FETCH a.locationCode")
    List<Accommodation> findAllDetail();
}
