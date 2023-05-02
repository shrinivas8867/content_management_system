package com.preproject.UserServices.services;

import com.preproject.UserServices.exception.ContentAlreadyExist;
import com.preproject.UserServices.exception.ContentNotFound;
import com.preproject.UserServices.exception.UserAlreadyExist;
import com.preproject.UserServices.exception.UserNotFound;
import com.preproject.UserServices.model.Content;
import com.preproject.UserServices.model.Users;

import java.util.List;

public interface UserProductServices {
    Users registerUser(Users users) throws UserAlreadyExist;
    Users saveUserProductToLIst(Content content , String email)  throws UserNotFound,ContentAlreadyExist;
    Users UpdateProductToList(Content content, String email, String id) throws UserNotFound, ContentNotFound;
    boolean deleteUserProductFromList(String contentid,String email) throws UserNotFound, ContentNotFound;
    List<Users> getAllUsers(String role,String email) throws UserNotFound;
    Content getContentById(String email, String id) throws UserNotFound, ContentNotFound;

    Users getUserByEmail(String email, String emailid)throws UserNotFound;

    List<Content> getAllUsersProducts(String email) throws UserNotFound;
}
