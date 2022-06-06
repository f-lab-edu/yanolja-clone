package com.moondysmell.yanoljaclone.repository;

import com.moondysmell.yanoljaclone.domain.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<Customer, Integer> {
    Optional<Customer> findByNameAndPhoneNum(String name, String phoneNum);
}
