package com.preproject.UserRegistration.repository;

import com.preproject.UserRegistration.model.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepos extends JpaRepository<Users, String> {
    public Users findByEmailAndPassword(String email, String password);

}
