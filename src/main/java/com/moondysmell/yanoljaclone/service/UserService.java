package com.moondysmell.yanoljaclone.service;

import com.moondysmell.yanoljaclone.domain.Customer;
import com.moondysmell.yanoljaclone.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
@Transactional(readOnly = true)
public class UserService {

    private final UserRepository userRepository;

    @Transactional
    public Customer createUser(Customer customer) {
        return userRepository.save(customer);
    }
}
