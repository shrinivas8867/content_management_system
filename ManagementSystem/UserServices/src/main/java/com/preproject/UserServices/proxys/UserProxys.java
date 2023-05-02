package com.preproject.UserServices.proxys;

import com.preproject.UserServices.model.Users;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@FeignClient(name="UserRegistration",url="http://localhost:8086")
public interface UserProxys {

    @PostMapping("/content/v1/register/admin")
    public ResponseEntity<?> saveAdmin(@RequestBody Users users);
    @PostMapping("/content/v1/register")
    public ResponseEntity<?> saveUser(@RequestBody Users users);

    @DeleteMapping("/content/v1/delete/{{email}}")
    public ResponseEntity<?> deleteUser(@PathVariable String email);
}
