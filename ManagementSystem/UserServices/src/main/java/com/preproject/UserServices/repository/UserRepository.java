package com.preproject.UserServices.repository;

import com.preproject.UserServices.model.Users;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends MongoRepository<Users,String> {



}
