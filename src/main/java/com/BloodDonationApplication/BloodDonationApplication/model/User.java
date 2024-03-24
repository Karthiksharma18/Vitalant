package com.BloodDonationApplication.BloodDonationApplication.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {
    @Id
    private String id;
    private String name;
    private String password;
    private String number;
    private String bloodGroup;
    private String gender;
    private String email;
    public void setId(String id){
        this.id = id;
    }
}
