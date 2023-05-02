package com.preproject.UserRegistration.services;

import com.preproject.UserRegistration.exception.UserAlreadyExists;
import com.preproject.UserRegistration.exception.UserNotFound;
import com.preproject.UserRegistration.model.Users;

import java.util.List;

public interface UserService {
    public Users addUser (Users users) throws UserAlreadyExists;


    public Users getstatus(String email) throws UserNotFound;

    public Users loginCheck(Users users);
    public List<Users> getAlldata();

    public Users statusUpdate(Users users, String email) throws UserNotFound;

}
