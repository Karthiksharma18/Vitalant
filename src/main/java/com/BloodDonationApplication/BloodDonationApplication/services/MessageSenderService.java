package com.BloodDonationApplication.BloodDonationApplication.services;

import com.BloodDonationApplication.BloodDonationApplication.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MessageSenderService {
    @Autowired
    private UserService userService;
    @Autowired
    private TwilioServices twilioServices;

    public void sendMessage(String message){
        System.out.println("in messageing");
        List<User> users = userService.getAllusers();
        for(User user : users){
            String number = user.getNumber();
            System.out.println(number);
            twilioServices.sendSms(number,message);
        }
    }
}
