package com.preproject.UserRegistration.services;

import com.preproject.UserRegistration.model.Users;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Service
public class TockenImpl implements TockenGenerat{
    @Override
    public Map<String, String> generateTocken(Users users) {
        String token=null;
        Map<String,String> result=new HashMap<>();
        users.setPassword("");
        Map<String,Object> userData=new HashMap<>();
        userData.put("user_role",users.getRole());
        userData.put("user_email", users.getEmail());
        userData.put("user_status", users.getStatus());
        token = Jwts.builder().setClaims(userData)
                .setIssuedAt(new Date())
                .signWith(SignatureAlgorithm.HS256, "code")
                .compact();
        result.put("token",token);
        result.put("Message","Successfully Logged In...");
        result.put("role",users.getRole());
        result.put("status",users.getStatus());
        return result;
    }
}
