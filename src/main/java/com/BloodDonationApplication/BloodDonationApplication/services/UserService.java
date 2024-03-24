package com.BloodDonationApplication.BloodDonationApplication.services;

import com.BloodDonationApplication.BloodDonationApplication.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Configuration
public class UserService {
    @Autowired
    private MongoTemplate mongoTemplate;
    public List<User> getAllusers(){
        return mongoTemplate.findAll(User.class,"user");
    }
}
