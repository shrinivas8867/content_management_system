package com.preproject.UserRegistration.controller;

import com.preproject.UserRegistration.exception.UserAlreadyExists;
import com.preproject.UserRegistration.exception.UserNotFound;
import com.preproject.UserRegistration.model.Users;
import com.preproject.UserRegistration.services.TockenGenerat;
import com.preproject.UserRegistration.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/content/v1/")
public class UserController {
    private ResponseEntity responseEntity;
    private UserService userService;
    private TockenGenerat tockenGenerat;

    @Autowired
    public UserController(UserService userService,TockenGenerat tockenGenerat){
        this.userService=userService;
        this.tockenGenerat=tockenGenerat;

    }

    @PostMapping("register")
    public ResponseEntity<?> userregister(@RequestBody Users users) throws UserAlreadyExists {

        users.setRole("role_user");
        users.setStatus("active");

        return new ResponseEntity(userService.addUser(users), HttpStatus.CREATED);
    }

    @PutMapping("admin/update/{email}")
    public ResponseEntity<?> updatestatus(@RequestBody Users users,@PathVariable String email)throws UserNotFound {
        try{
            responseEntity=new ResponseEntity(userService.statusUpdate(users,email),HttpStatus.OK);
            return responseEntity;

        }catch (UserNotFound e){
            throw new UserNotFound();
        }
    }

    @PostMapping("register/admin")

    public ResponseEntity<?> adminregistery(@RequestBody Users users) throws UserAlreadyExists{
        users.setStatus("active");
        users.setRole("admin_admin");
        return new ResponseEntity(userService.addUser(users), HttpStatus.CREATED);
    }


    @PostMapping("login")
    public ResponseEntity<?> loginCheck(@RequestBody Users users){
        Users result=userService.loginCheck(users);
        if(result!=null){
            return new ResponseEntity<>(tockenGenerat.generateTocken(result),HttpStatus.OK);

        }else
            return new ResponseEntity<>("Authentication Failed",HttpStatus.OK);

    }
    @GetMapping("admin/getall")
    public ResponseEntity<?> getallUsers(){
        return new ResponseEntity<>(userService.getAlldata(),HttpStatus.OK);
    }

    @GetMapping("admin/getstatus/{email}")
    public ResponseEntity<?> getstatus(@PathVariable String email) throws UserNotFound {
        return new ResponseEntity<>(userService.getstatus(email),HttpStatus.OK);
    }



}

