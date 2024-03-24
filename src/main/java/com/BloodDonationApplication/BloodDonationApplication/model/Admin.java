package com.BloodDonationApplication.BloodDonationApplication.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.json.simple.JSONObject;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Document
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Admin {
    @Id
    @Getter
    private String id;
    private String name;
    private Map<String,Integer> totalBloodUnits = new HashMap<>();
    {
        totalBloodUnits.put("A+", 0);
        totalBloodUnits.put("B+", 0);
        totalBloodUnits.put("O-", 0);
        totalBloodUnits.put("AB+", 0);
        totalBloodUnits.put("A-", 0);
        totalBloodUnits.put("B-", 0);
        totalBloodUnits.put("AB-", 0);
        totalBloodUnits.put("O+", 0);
    }
    private Integer totalDonations;
    private Integer donationsToday;
    private List<JSONObject> requests = new ArrayList<>();
    private List<JSONObject> donationVolunteers = new ArrayList<>();
    public void setId(String id){
        this.id = id;
    }
    public void setRequests(JSONObject obj){
        requests.add(obj);
    }
    public void setDonationVolunteers(JSONObject obj){donationVolunteers.add(obj);}
}
