package com.BloodDonationApplication.BloodDonationApplication.repository;

import com.BloodDonationApplication.BloodDonationApplication.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepo extends MongoRepository<User,String> {
    User findByname(String name);

    boolean existsByname(String name);

    boolean existsByemail(String email);
}
