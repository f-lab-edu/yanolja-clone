package com.moondysmell.yanoljaclone.repository;

import com.moondysmell.yanoljaclone.domain.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<Customer, Integer> {
}
