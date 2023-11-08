package com.ecommerceproject.ecommerceproject.controller;

import com.ecommerceproject.ecommerceproject.dto.LoginDTO;
import com.ecommerceproject.ecommerceproject.dto.ResponseDTO;
import com.ecommerceproject.ecommerceproject.dto.SignUpDTO;
import com.ecommerceproject.ecommerceproject.exception.ExpiredTokenException;
import com.ecommerceproject.ecommerceproject.service.IUserService;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {
    @Autowired
    private IUserService userService;

    @PostMapping("/signup")
    public ResponseEntity<?> signUpUser(@RequestBody @Valid SignUpDTO sign){
        userService.addUser(sign);
        ResponseDTO responseDTO = new ResponseDTO(HttpStatus.OK.value(), null,"Sign up successfully");
        return ResponseEntity.ok(responseDTO);
    }

    @PostMapping("/login")
    public ResponseEntity<?> loginUser(@RequestBody @Valid LoginDTO dto){
        String token = userService.userLogIn(dto);
        if(token!=null){
            ResponseDTO responseDTO = new ResponseDTO(HttpStatus.OK.value(), token,"Log in successfully");
            return ResponseEntity.ok(responseDTO);
        }
        ResponseDTO responseDTO = new ResponseDTO(HttpStatus.OK.value(), null,"Wrong email or password");
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseDTO);
    }

    @Transactional
    @PostMapping("/verify")
    public ResponseEntity<?> verifyRegister(@RequestParam String token) throws ExpiredTokenException {
        if(userService.verifyToken(token)){
            ResponseDTO responseDTO = new ResponseDTO(HttpStatus.OK.value(), null,"Verify successfully");
            return ResponseEntity.status(HttpStatus.OK).body(responseDTO);
        }
        ResponseDTO responseDTO = new ResponseDTO(HttpStatus.BAD_REQUEST.value(), null,"Verify fail");
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseDTO);
    }
}
