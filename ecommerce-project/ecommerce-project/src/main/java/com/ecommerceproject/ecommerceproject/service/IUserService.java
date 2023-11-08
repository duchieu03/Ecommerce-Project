package com.ecommerceproject.ecommerceproject.service;

import com.ecommerceproject.ecommerceproject.dto.LoginDTO;
import com.ecommerceproject.ecommerceproject.dto.SignUpDTO;
import com.ecommerceproject.ecommerceproject.exception.ExpiredTokenException;
import com.ecommerceproject.ecommerceproject.model.VerificationToken;

public interface IUserService {
    public void addUser(SignUpDTO user);
    public String userLogIn(LoginDTO dto);
    public boolean verifyToken(String verificationToken) throws ExpiredTokenException;
    public boolean verifyResetToken(String verificationToken) throws ExpiredTokenException;
    public void resetPassword(String email);

}
