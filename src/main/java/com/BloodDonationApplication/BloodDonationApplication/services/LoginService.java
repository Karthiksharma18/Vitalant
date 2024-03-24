package com.BloodDonationApplication.BloodDonationApplication.services;

import com.BloodDonationApplication.BloodDonationApplication.model.Session;
import com.BloodDonationApplication.BloodDonationApplication.model.User;
import com.BloodDonationApplication.BloodDonationApplication.repository.SessionRepo;
import com.BloodDonationApplication.BloodDonationApplication.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.Instant;
import java.util.UUID;

@Service
@Configuration
public class LoginService {
    @Autowired
    private UserRepo userRepo;
    @Autowired
    private SessionRepo sessionRepo;
    public String login(String name,String password){
        User user = userRepo.findByname(name);
        if(user!=null && user.getPassword().equals(password)){
            String sessionToken = UUID.randomUUID().toString();
            Instant expirationTime = Instant.now().plus(Duration.ofHours(1));
            Session session = new Session(user.getId(),sessionToken,expirationTime);
            sessionRepo.save(session);
            return sessionToken;
        }
        return null;
    }

    public void logout(String sessionToken){
        sessionRepo.deleteById(sessionToken);
    }
}
