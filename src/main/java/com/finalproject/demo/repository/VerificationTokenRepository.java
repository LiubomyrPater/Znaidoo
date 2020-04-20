package com.finalproject.demo.repository;

import com.finalproject.demo.entity.VerificationToken;
import org.springframework.data.repository.CrudRepository;


public interface VerificationTokenRepository extends CrudRepository<VerificationToken, Long> {

    VerificationToken findByToken(String token);

}
