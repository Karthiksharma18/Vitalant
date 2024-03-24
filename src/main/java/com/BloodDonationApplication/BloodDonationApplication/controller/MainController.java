package com.BloodDonationApplication.BloodDonationApplication.controller;

import com.BloodDonationApplication.BloodDonationApplication.services.LoginService;
import com.BloodDonationApplication.BloodDonationApplication.services.MessageSenderService;
import org.json.simple.JSONObject;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;

import java.util.*;

import com.BloodDonationApplication.BloodDonationApplication.model.Admin;
import com.BloodDonationApplication.BloodDonationApplication.model.User;
import com.BloodDonationApplication.BloodDonationApplication.repository.AdminRepo;
import com.BloodDonationApplication.BloodDonationApplication.repository.UserRepo;
//import com.BloodDonationApplication.BloodDonationApplication.services.Authentication;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class MainController {

    @GetMapping("/")
    public String index(){
        return "index";
    }
    @GetMapping("/register")
    public String register(){
        return "Registration";
    }



    @GetMapping("/RequestBlood")
    public String RequestBlood(){
        return "RequestBlood";
    }
    @GetMapping("/DonateBlood")
    public String DonateBlood(){
        return "DonateBlood";
    }

    @Autowired
    UserRepo userRepo2;
    @Autowired
    LoginService loginService;
    @PostMapping("/LoginUser")
    public ResponseEntity<String> loginUser(@RequestBody LoginRequest loginRequest){
        System.out.println(loginRequest.getName());
        User user = userRepo2.findByname(loginRequest.getName());
        String sessionToken = loginService.login(loginRequest.getName(),loginRequest.getPassword());
        if(user!=null){
            if(Objects.equals(loginRequest.getPassword(), user.getPassword())){
                return ResponseEntity.ok(sessionToken);
            }
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Login failed");
    }


    @PostMapping("/LogoutUser")
    public ResponseEntity<Void> logout(@RequestHeader("Authorization") String sessionToken){
        loginService.logout(sessionToken);
        return ResponseEntity.ok().build();
    }


    @Autowired
    UserRepo userRepo;
    @PostMapping("/registerUser")
    public ResponseEntity<String> addUser(@RequestBody User user){
        String idnew = UUID.randomUUID().toString().substring(0,16);
        user.setId(idnew);
        /*System.out.println(user.getId());
        System.out.println("user details"+user.getName() + user.getEmail() + user.getPassword());*/
        if(userRepo.existsByname(user.getName())){
            return ResponseEntity.badRequest().body("username is already taken");
        }
        if(userRepo.existsByemail(user.getEmail())){
            return ResponseEntity.badRequest().body("Email is already registered");
        }
        userRepo.save(user);
        return ResponseEntity.ok("User registered successfully");
    }


    @Autowired
    AdminRepo adminRepo;
    @PostMapping("/registerAdmin")
    @ResponseBody
    public void addUser(@RequestBody Admin admin){
        String idnew = UUID.randomUUID().toString().substring(0,12);
        admin.setId(idnew);
        System.out.println("user details"+admin.getRequests());
        adminRepo.save(admin);
    }

    @Autowired
    private MessageSenderService messageSenderService;
    @PutMapping("/bloodRequests")
    @ResponseBody
    public void makeRequest(@RequestBody BloodRequest bloodRequest){
        JSONObject request = new JSONObject();
        request.put("name",bloodRequest.getName());
        request.put("number",bloodRequest.getNumber());
        request.put("bloodGroup",bloodRequest.getBloodGroup());
        request.put("resolved",bloodRequest.isResolved());
        Admin admin = adminRepo.findByName("Vitalant");
        admin.setRequests(request);
        adminRepo.save(admin);
        String message = "\n\nRequest from Vitalant \n"+bloodRequest.getName()+" is in urgent need of " + bloodRequest.getBloodGroup()+" Blood,\nkindly help"
                            +"\nContact this number if you are willing to donate blood  "+bloodRequest.getNumber()+"\nThanks Vitalant\n\n";
        System.out.println(message);
        messageSenderService.sendMessage(message);
        System.out.println(bloodRequest.getName()+bloodRequest.getNumber());
    }

    @GetMapping("/bloodRequests/numberOfRequests")
    @ResponseBody
    public Integer requestsLength(){
        Admin admin = adminRepo.findByName("Vitalant");
        System.out.print(admin.getRequests().size());
        return admin.getRequests().size();
    }

    @GetMapping("/bloodRequests/numberOfDonations")
    @ResponseBody
    public Integer donationsLength(){
        Admin admin = adminRepo.findByName("Vitalant");
        System.out.print(admin.getDonationVolunteers().size());
        return admin.getDonationVolunteers().size();
    }

    @PutMapping("/volunteerDonation")
    @ResponseBody
    public void volunteerDonation(@RequestBody BloodRequest bloodRequest){
        JSONObject request = new JSONObject();
        request.put("name",bloodRequest.getName());
        request.put("number",bloodRequest.getNumber());
        request.put("bloodGroup",bloodRequest.getBloodGroup());
        request.put("resolved",bloodRequest.isResolved());
        Admin admin = adminRepo.findByName("Vitalant");
        admin.setDonationVolunteers(request);
        adminRepo.save(admin);
        String message = "\n\nMessage from Vitalant \n"+bloodRequest.getName()+" is ready to donate " + bloodRequest.getBloodGroup()+" Blood,\nReach out if you are in need"
                +"\nContact this number for further queries\n"+bloodRequest.getNumber()+"\n\nThanks Vitalant\n\n";
        System.out.println(message);
        messageSenderService.sendMessage(message);
        System.out.println(bloodRequest.getName()+bloodRequest.getNumber());
    }

    @GetMapping("/totalBloodUnits")
    @ResponseBody
    public ResponseEntity<Map<String, Integer>> totalBloodUnits(){
        Admin admin = adminRepo.findByName("Vitalant");
        Map<String,Integer> totalbloodUnits = new HashMap<>();
        if(admin!=null){
            totalbloodUnits = admin.getTotalBloodUnits();
            System.out.println(totalbloodUnits);
        }
        return ResponseEntity.ok().body(totalbloodUnits);
    }
}

@Getter
class LoginRequest{
    @Getter
    private String name;
    private String password;


}
@Getter
class BloodRequest{
    private String name;
    private String number;
    private String bloodGroup;
    private boolean resolved;
}

@Getter
class user{
    private String name;
    private String number;
}