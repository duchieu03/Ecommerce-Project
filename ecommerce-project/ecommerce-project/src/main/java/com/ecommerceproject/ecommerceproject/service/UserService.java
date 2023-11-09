package com.ecommerceproject.ecommerceproject.service;

import com.ecommerceproject.ecommerceproject.dto.LoginDTO;
import com.ecommerceproject.ecommerceproject.dto.SignUpDTO;
import com.ecommerceproject.ecommerceproject.exception.EmailDoesNotExistException;
import com.ecommerceproject.ecommerceproject.exception.EmailExistedException;
import com.ecommerceproject.ecommerceproject.exception.ExpiredTokenException;
import com.ecommerceproject.ecommerceproject.exception.UserNotVerifiedException;
import com.ecommerceproject.ecommerceproject.model.Role;
import com.ecommerceproject.ecommerceproject.model.User;
import com.ecommerceproject.ecommerceproject.model.VerificationToken;
import com.ecommerceproject.ecommerceproject.repository.RoleRepository;
import com.ecommerceproject.ecommerceproject.repository.UserRepository;
import com.ecommerceproject.ecommerceproject.repository.VerificationTokenRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class UserService implements IUserService{
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private JwtService jwtService;
    @Autowired
    private EmailService emailService;
    @Autowired
    private VerificationTokenRepository verificationTokenRepository;
    @Override

    public void addUser(SignUpDTO user) {
        Optional<User> userOptional= userRepository.findByEmail(user.getEmail());
        if(userOptional.isPresent()) throw new EmailExistedException();
        else{
            Role role = roleRepository.findById(1).get();
            List<Role> list = new ArrayList<>();
            User user1 = new User();
            user1.setEmail(user.getEmail());
            user1.setFirstName(user.getFirstName());
            user1.setLastName(user.getLastName());
            user1.setPassword(EncryptionService.encryptPassword(user.getPassword()));
            list = user1.getAuthorization();
            list.add(role);
            VerificationToken verificationToken= createVerifiedToken(user1);
            emailService.sendVerifyToken(verificationToken);
            userRepository.save(user1);
        }
    }

    @Override
    public String userLogIn(LoginDTO dto) {
        Optional<User> optionalUser = userRepository.findByEmail(dto.getEmail());
        if(optionalUser.isPresent()){
            User user = optionalUser.get();
            if(EncryptionService.verifyPassword(dto.getPassword(),user.getPassword())){
                if(user.isActivated()){
                    return jwtService.generateToken(user);
                }
               else{
                   List<VerificationToken> list = user.getVerificationTokens();
                   Timestamp endToken = list.get(0).getExpiredTime();
                   if(endToken.before(new Timestamp(System.currentTimeMillis()))){
                       VerificationToken verificationToken = createVerifiedToken(user);
                       emailService.sendVerifyToken(verificationToken);
                       verificationTokenRepository.save(verificationToken);
                   }
                    throw new UserNotVerifiedException();
                }
            }
        }
        return null;
    }


    @Override
    @Transactional
    public boolean verifyToken(String token) throws ExpiredTokenException {
        Optional<VerificationToken> verificationTokenOptional =
                verificationTokenRepository.findByToken(token);
        if(verificationTokenOptional.isEmpty()) {
            return false;
        }
        else{
            VerificationToken verificationToken = verificationTokenOptional.get();
            User user = verificationToken.getUser();
            if(verificationToken.getExpiredTime().before(new Timestamp(System.currentTimeMillis()))){
                VerificationToken verificationToken1 = createVerifiedToken(user);
                emailService.sendVerifyToken(verificationToken1);
                verificationTokenRepository.save(verificationToken1);
                throw new ExpiredTokenException();
            }else {
                user.setActivated(true);
                userRepository.save(user);
                verificationTokenRepository.deleteByUser(user);
                System.out.println(user.getVerificationTokens().size());

                return true;
            }
        }

    }
    private VerificationToken createVerifiedToken(User user){
        VerificationToken verificationToken = new VerificationToken();
        verificationToken.setToken(jwtService.generateVerifyToken(user));
        verificationToken.setUser(user);
        verificationToken.setExpiredTime(new Timestamp(System.currentTimeMillis()+(1000*60*30)));
        user.getVerificationTokens().add(verificationToken);
        return verificationToken;
    }

    @Override
    @Transactional
    public boolean verifyResetToken(String token) throws ExpiredTokenException {
        Optional<VerificationToken> verificationTokenOptional =
                verificationTokenRepository.findByToken(token);
        if(verificationTokenOptional.isEmpty()) {
            return false;
        }
        else {
            VerificationToken verificationToken = verificationTokenOptional.get();
            User user = verificationToken.getUser();
            if (verificationToken.getExpiredTime().before(new Timestamp(System.currentTimeMillis()))) {
                VerificationToken verificationToken1 = createVerifiedToken(user);
                emailService.sendVerifyToken(verificationToken1);
                verificationTokenRepository.save(verificationToken1);
                throw new ExpiredTokenException();
            } else {
                String password = UUID.randomUUID().toString();
                user.setPassword(EncryptionService.encryptPassword(password));
                userRepository.save(user);
                verificationTokenRepository.deleteByUser(user);
                emailService.sendResetPassword(password,user.getEmail());
                System.out.println(user.getVerificationTokens().size());
                return true;
            }
        }
    }

    @Override
    public void resetPassword(String email) {
        Optional<User> userOptional = userRepository.findByEmail(email);
        if(userOptional.isPresent()){
            User user = userOptional.get();
            VerificationToken verificationToken = createResetToken(user);
            emailService.sendResetPasswordEmail(verificationToken);
            userRepository.save(user);
        }else {
            throw new EmailDoesNotExistException();
        }
    }



    private VerificationToken createResetToken(User user){
        VerificationToken verificationToken = new VerificationToken();
        verificationToken.setToken(jwtService.generateResetPasswordToken(user));
        verificationToken.setUser(user);
        verificationToken.setExpiredTime(new Timestamp(System.currentTimeMillis()+(1000*60*30)));
        user.getVerificationTokens().add(verificationToken);
        return verificationToken;
    }
}
