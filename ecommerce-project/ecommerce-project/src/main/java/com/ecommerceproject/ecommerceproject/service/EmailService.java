package com.ecommerceproject.ecommerceproject.service;

import com.ecommerceproject.ecommerceproject.exception.EmailFailException;
import com.ecommerceproject.ecommerceproject.model.VerificationToken;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.stereotype.Service;

@Service
public class EmailService {
    @Value("${email.from}")
    private String from;
    @Value("${app.frontend.url}")
    private String url;
    private final JavaMailSender javaMailSender;
    public EmailService(JavaMailSender javaMailSender) {
        this.javaMailSender = javaMailSender;
    }
    private SimpleMailMessage generateMessage(){
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setFrom(from);
        return mailMessage;
    }

    public void sendVerifyToken(VerificationToken verificationToken) throws EmailFailException {
        SimpleMailMessage mailMessage = generateMessage();
        mailMessage.setTo(verificationToken.getUser().getEmail());
        mailMessage.setSubject("Verify your email to active your account");
        mailMessage.setText("Please follow the link below to verify your email to active your account.\n" +
                url + "signup/verify?token=" + verificationToken.getToken());
        try{
            javaMailSender.send(mailMessage);
        }catch (MailException ex){
            throw new EmailFailException();
        }
    }

    public void sendResetPasswordEmail(VerificationToken verificationToken) throws EmailFailException {
        SimpleMailMessage mailMessage = generateMessage();
        mailMessage.setTo(verificationToken.getUser().getEmail());
        mailMessage.setSubject("Verify your email to reset password of your account");
        mailMessage.setText("Please follow the link below to verify your email to reset password of your account.\n" +
                url + "reset/verify?token=" + verificationToken.getToken());
        try{
            javaMailSender.send(mailMessage);
        }
        catch(MailException e){
            throw new EmailFailException();
        }
    }

    public void sendResetPassword(String password , String email) throws EmailFailException {
        SimpleMailMessage mailMessage = generateMessage();
        mailMessage.setTo(email);
        mailMessage.setSubject("New password");
        mailMessage.setText("You can use your new password to sign in: "+ password);
        try{
            javaMailSender.send(mailMessage);
        }
        catch(MailException e){
            throw new EmailFailException();
        }
    }
}
