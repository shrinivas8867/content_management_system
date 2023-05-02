package com.preproject.UserRegistration.services;

import com.preproject.UserRegistration.exception.UserAlreadyExists;
import com.preproject.UserRegistration.exception.UserNotFound;
import com.preproject.UserRegistration.model.Users;
import com.preproject.UserRegistration.repository.UserRepos;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService{

    @Autowired
    private UserRepos userRepository;

    @Override
    public Users addUser(Users users) throws UserAlreadyExists {
        if(userRepository.findById(users.getEmail()).isPresent()){
            throw new UserAlreadyExists();

        }else {
            return userRepository.save(users);
        }

    }

    @Override
    public Users getstatus(String email) throws UserNotFound {
        return userRepository.findById(email).get();
    }

//    @Override
//    public Users findByemailandpassword(String email, String password) throws UserNotFound {
//        Users users =userRepository.findByEmailAndPassword(email,password);
//        if(users== null){
//            throw new UserNotFound();
//        }
//        return users;
//    }

    @Override
    public Users loginCheck(Users users) {
        if(userRepository.findById(users.getEmail()).isPresent()){
            Users users1=userRepository.findById(users.getEmail()).get();
            if(users1.getPassword().equals(users.getPassword())){
                return users1;
            }else
                return null;
        }else
            return null;

    }

    @Override
    public List<Users> getAlldata() {
        return userRepository.findAll();
    }

    @Override
    public Users statusUpdate(Users users, String email)throws UserNotFound {
        if(userRepository.findById(email).isPresent()){
            Users users1=userRepository.findById(email).get();
            if(users1.getPassword()!=null){
                users1.setPassword(users.getPassword());
            }if(users1.getStatus()!=null){
                users1.setStatus(users.getStatus());
            }
            return userRepository.save(users1);

        }else throw new UserNotFound();

    }


}
