//package com.BloodDonationApplication.BloodDonationApplication.services;
//
//import com.BloodDonationApplication.BloodDonationApplication.model.User;
//import com.BloodDonationApplication.BloodDonationApplication.repository.UserRepo;
//import org.springframework.security.crypto.password.PasswordEncoder;
//
//public class Authentication {
//    private UserRepo userRepo;
//    private PasswordEncoder passwordEncoder;
//
//    public boolean authenticate(String name, String password){
//        User user = userRepo.findByname(name);
//        if(user!=null){
//            return passwordEncoder.matches(password, user.getPassword());
//        }
//        return false;
//    }
//}
