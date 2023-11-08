package com.ecommerceproject.ecommerceproject.handler;

import com.ecommerceproject.ecommerceproject.dto.ResponseDTO;
import com.ecommerceproject.ecommerceproject.exception.*;
import jakarta.validation.constraints.Email;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class HandleGlobalException {
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ResponseDTO> handleInvalidValueProperty(MethodArgumentNotValidException arg){
        ResponseDTO responseDTO= new ResponseDTO(HttpStatus.BAD_REQUEST.value(),null,"Wrong format");
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseDTO);
    }

    @ExceptionHandler(CategoryNotFoundException.class)
    public ResponseEntity<ResponseDTO> handleCategoryNotFound(CategoryNotFoundException arg){
        ResponseDTO responseDTO= new ResponseDTO(HttpStatus.BAD_REQUEST.value(),null,"Not found category");
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseDTO);
    }

    @ExceptionHandler(ProductNotFoundException.class)
    public ResponseEntity<ResponseDTO> handleProductNotFound(ProductNotFoundException arg){
        ResponseDTO responseDTO= new ResponseDTO(HttpStatus.BAD_REQUEST.value(),null,"Not found product");
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseDTO);
    }

    @ExceptionHandler(EmailExistedException.class)
    public ResponseEntity<ResponseDTO> handleProductNotFound(EmailExistedException arg){
        ResponseDTO responseDTO= new ResponseDTO(HttpStatus.BAD_REQUEST.value(),null,"Email is already existed");
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseDTO);
    }

    @ExceptionHandler(EmailFailException.class)
    public ResponseEntity<ResponseDTO> handleProductNotFound(EmailFailException arg){
        ResponseDTO responseDTO= new ResponseDTO(HttpStatus.INTERNAL_SERVER_ERROR.value(),null,"Email is not send because some problems");
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(responseDTO);
    }

    @ExceptionHandler(UserNotVerifiedException.class)
    public ResponseEntity<ResponseDTO> handleProductNotFound(UserNotVerifiedException arg){
        ResponseDTO responseDTO= new ResponseDTO(HttpStatus.BAD_REQUEST.value(),null,"User is not verify and token is resend");
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseDTO);
    }

    @ExceptionHandler(ExpiredTokenException.class)
    public ResponseEntity<ResponseDTO> handleProductNotFound(ExpiredTokenException arg){
        ResponseDTO responseDTO= new ResponseDTO(HttpStatus.BAD_REQUEST.value(),null,"Token is expired and token is resend");
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseDTO);
    }

    @ExceptionHandler(EmailDoesNotExistException.class)
    public ResponseEntity<ResponseDTO> handleProductNotFound(EmailDoesNotExistException arg){
        ResponseDTO responseDTO= new ResponseDTO(HttpStatus.BAD_REQUEST.value(),null,"Email does not exist in database");
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseDTO);
    }
}
