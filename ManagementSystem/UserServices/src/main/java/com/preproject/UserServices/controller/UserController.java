package com.preproject.UserServices.controller;

import com.preproject.UserServices.exception.ContentAlreadyExist;
import com.preproject.UserServices.exception.ContentNotFound;
import com.preproject.UserServices.exception.UserAlreadyExist;
import com.preproject.UserServices.exception.UserNotFound;
import com.preproject.UserServices.model.Content;
import com.preproject.UserServices.model.Users;
import com.preproject.UserServices.services.UserProductServices;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;


@RestController
@RequestMapping("/Content/v2/")
public class UserController {
    private ResponseEntity responseEntity;
    @Autowired
    private UserProductServices userProductServices;

    @PostMapping("registration")
    public ResponseEntity<?> registerUser(@RequestBody Users users) throws UserAlreadyExist{
        try{
            users.setContentList(new ArrayList<>());
            responseEntity= new ResponseEntity<>(userProductServices.registerUser(users), HttpStatus.CREATED);
        }catch (UserAlreadyExist e){
            throw new UserAlreadyExist();
        }
        return responseEntity;
    }
    @PostMapping("user/addProduct")
    public ResponseEntity<?> saveContentToList(@RequestBody Content content, HttpServletRequest request) throws UserNotFound, ContentAlreadyExist {
        try{
            System.out.println("header"+request.getHeader("Authorization"));
            String email=(String) request.getAttribute("current user email");
//            String email = claims.getSubject();
            responseEntity=new ResponseEntity(userProductServices.saveUserProductToLIst(content, email),HttpStatus.OK);
        }catch (UserNotFound e){
            System.out.println("User Not Found");
            throw new UserNotFound();
        }
        catch(ContentAlreadyExist e){
            throw new ContentAlreadyExist();
        }
        return responseEntity;
    }
    @PutMapping("user/update/{contentid}")
    public ResponseEntity<?> updateContent(@RequestBody Content content,@PathVariable int contentid, HttpServletRequest request) throws ContentNotFound,UserNotFound{
        try{
            System.out.println("header"+request.getHeader("Authorization"));
            String email=(String) request.getAttribute("current user email");

            System.out.println("email :: "+email);
            System.out.println("-------------------------------------------------------");
            String contentids= Integer.toString(contentid);
            System.out.println("id is in controller   :"+contentids);
            responseEntity= new ResponseEntity(userProductServices.UpdateProductToList(content,email,contentids),HttpStatus.CREATED);
        }catch (ContentNotFound e){
            System.out.println("=====================================");
            throw new ContentNotFound();

        } catch (UserNotFound e) {
            throw new UserNotFound();
        }
        return responseEntity;
    }
    @GetMapping("user/allcontent")
    public ResponseEntity<?> getAllContent(HttpServletRequest request)throws UserNotFound{
        try{
            List<Content> userlist;

            String alpha=(String) request.getAttribute("current user email");
            userlist = userProductServices.getAllUsersProducts(alpha);
            responseEntity=new ResponseEntity(userlist,HttpStatus.OK);
        }catch (UserNotFound e){
            throw new UserNotFound();
        }
        return responseEntity;
    }
    @GetMapping("admin/alldetails")
    public ResponseEntity<?> getAllUsers(HttpServletRequest request)throws UserNotFound{
        try {
            List<Users> adminList;
            String role=(String) request.getAttribute("current_role");
            String email=(String) request.getAttribute("current user email");
            System.out.println("ROle "+role);
            adminList=userProductServices.getAllUsers(role,email);
            responseEntity=new ResponseEntity(adminList,HttpStatus.OK);
        }catch (UserNotFound e){
            throw new UserNotFound();
        }
        return responseEntity;
    }

    @DeleteMapping("user/delete/{contentid}")
    public ResponseEntity<?> deleteContent(@PathVariable int contentid, HttpServletRequest request)throws ContentNotFound {
        try{
            String email=(String) request.getAttribute("current user email");
//            String email=claims.getSubject();
            String contentids= Integer.toString(contentid);
            responseEntity=new ResponseEntity(userProductServices.deleteUserProductFromList(contentids,email),HttpStatus.OK);
        }catch (ContentNotFound e){
            throw new ContentNotFound();
        } catch (UserNotFound e) {
            throw new RuntimeException(e);
        }
        return responseEntity;

    }
    @GetMapping("user/getById/{contentid}")
    public ResponseEntity<?> getContentById(@PathVariable int contentid,HttpServletRequest request)throws ContentNotFound{
        try{
            Content userlist;
            String contentids= Integer.toString(contentid);
            String alpha=(String) request.getAttribute("current user email");
            userlist = userProductServices.getContentById(alpha,contentids);
            responseEntity=new ResponseEntity(userlist,HttpStatus.OK);
        } catch (UserNotFound e) {
            throw new RuntimeException(e);
        }
        return responseEntity;
    }
    @GetMapping("admin/getUser/{emailid}")
    public ResponseEntity<?> getUserdetails(@PathVariable String emailid ,HttpServletRequest request)throws UserNotFound {
        try {

            String alpha = (String) request.getAttribute("current user email");
            responseEntity = new ResponseEntity(userProductServices.getUserByEmail(alpha, emailid), HttpStatus.OK);

        } catch (UserNotFound e) {
            throw new UserNotFound();
        }
        return responseEntity;
    }
}
