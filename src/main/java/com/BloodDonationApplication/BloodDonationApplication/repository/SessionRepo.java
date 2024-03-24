package com.BloodDonationApplication.BloodDonationApplication.repository;

import com.BloodDonationApplication.BloodDonationApplication.model.Session;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface SessionRepo extends MongoRepository<Session,String> {
    Session findBySessionToken(String sessionToken);
}
