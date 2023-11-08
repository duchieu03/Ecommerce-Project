package com.ecommerceproject.ecommerceproject.repository;

import com.ecommerceproject.ecommerceproject.model.User;
import com.ecommerceproject.ecommerceproject.model.VerificationToken;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface VerificationTokenRepository extends JpaRepository<VerificationToken,Integer> {
    public Optional<VerificationToken> findByToken(String token);
    public void deleteByUser(User user);
}
