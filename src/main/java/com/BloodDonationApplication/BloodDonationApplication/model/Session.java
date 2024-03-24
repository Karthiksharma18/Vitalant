package com.BloodDonationApplication.BloodDonationApplication.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.Instant;

@Document
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Session {
    @Id
    private String id;
    private String userId;
    private String sessionToken;
    private Instant expirationTime;

    public Session(String id, String sessionToken, Instant expirationTime) {
        this.userId = id;
        this.sessionToken = sessionToken;
        this.expirationTime =expirationTime;
    }
}
