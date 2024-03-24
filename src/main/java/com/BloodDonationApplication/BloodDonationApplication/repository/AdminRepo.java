package com.BloodDonationApplication.BloodDonationApplication.repository;

import com.BloodDonationApplication.BloodDonationApplication.model.Admin;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface AdminRepo extends MongoRepository<Admin,String> {
    Admin findByName(String name);

}
