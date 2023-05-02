package com.preproject.UserRegistration.services;

import com.preproject.UserRegistration.model.Users;

import java.util.Map;

public interface TockenGenerat {
    public Map<String,String> generateTocken(Users users);
}
