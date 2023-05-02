package com.preproject.UserServices.services;

import com.preproject.UserServices.exception.ContentNotFound;
import com.preproject.UserServices.exception.UserAlreadyExist;
import com.preproject.UserServices.exception.UserNotFound;
import com.preproject.UserServices.model.Content;
import com.preproject.UserServices.model.Users;
import com.preproject.UserServices.proxys.UserProxys;
import com.preproject.UserServices.repository.ContentRepository;
import com.preproject.UserServices.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserProductServicesImpl implements UserProductServices{

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ContentRepository contentRepository;
    @Autowired
    private UserProxys userProxys;



    @Override
    public Users registerUser(Users users) throws UserAlreadyExist{
        if(userRepository.findById(users.getEmail()).isPresent()){
            throw new UserAlreadyExist();
        }
        Users saved=userRepository.save(users);
        if(!(saved.getEmail().isEmpty())){
            if(users.getRole().equals("admin_admin")){
                ResponseEntity a=userProxys.saveAdmin(users);
                System.out.println(a.getBody());
            }else {
                ResponseEntity r = userProxys.saveUser(users);
                System.out.println(r.getBody());
            }
        }
        return saved;
    }

    @Override
    public Users saveUserProductToLIst(Content content, String email) throws UserNotFound {
        if(userRepository.findById(email).isEmpty()){
            throw new UserNotFound();
        }
        Users users1=userRepository.findById(email).get();
//        List<Content> contentList=users1.getContentList();
//        for(Content ct: contentList){
//            System.out.println("open");
//            if(ct.getContentid()!=content.getContentid()){
//                if(users1.getContentList()==null){
//                    users1.setContentList(Arrays.asList(content));
//                    System.out.println("adder");
//                }else{
//                    List<Content> products= users1.getContentList();
//                    products.add(ct);
//                    users1.setContentList(products);
//                    System.out.println("notadded");
//                }
//                users1=userRepository.save(users1);
//            }
//        }

        users1.getContentList().add(content);
        return userRepository.save(users1);
    }

    @Override
    public Users UpdateProductToList(Content content, String email, String id) throws  UserNotFound,ContentNotFound {
        if(userRepository.findById(email).isPresent()){
            Users userss=userRepository.findById(email).get();
            List<Content> contentList=userss.getContentList();
            List<Users> usersList = null;
            for(Content ct: contentList) {
                if (ct.getContentid().equals(id)) {
                    if (content.getContent() != null) {
                        ct.setContent(content.getContent());
                    }
                    if (content.getDescription() != null) {
                        ct.setDescription(content.getDescription());
                    }
                    if (content.getDate() != null) {
                        ct.setDate(content.getDate());
                    }
                    System.out.println(ct);
                    userss.setContentList(contentList);
                    break;
                }
            }

            return userRepository.save(userss);
        }
        else throw new UserNotFound();

    }

    @Override
    public boolean deleteUserProductFromList(String contentid, String email) throws UserNotFound, ContentNotFound {
        if(userRepository.findById(email).isEmpty()){
            throw new UserNotFound();
        }
        boolean flag=false;
        Users users=userRepository.findById(email).get();
        List<Content> contentList=users.getContentList();
        for(Content ct: contentList){
            if(ct.getContentid().equals(contentid)){
                    contentList.remove(ct);
                    users.setContentList(contentList);
                    userRepository.save(users);
                    flag=true;
                break;
            }
        }
        return flag;
    }





    @Override
    public Content getContentById(String email, String id) throws UserNotFound, ContentNotFound {
        if(userRepository.findById(email).isEmpty()){
            throw new UserNotFound();
        }
        Content content;
        System.out.println(email);
        System.out.println(id);
        Content content1=null;
        List<Content> temp=userRepository.findById(email).get().getContentList();
        System.out.println(temp);

        for(Content list:temp){
            if(list.getContentid().equals(id)){
                content1=list;
                break;
            }
        }
        return content1;
    }

    @Override
    public Users getUserByEmail(String email,String emailid) throws UserNotFound {
        if(userRepository.findById(email).isEmpty()){
            throw new UserNotFound();
        }
        Users users=userRepository.findById(emailid).get();

        return users;
    }


    @Override
    public List<Content> getAllUsersProducts(String email) throws UserNotFound {
        if(userRepository.findById(email).isEmpty()){
            throw new UserNotFound();
        }

        List<Content> content=userRepository.findById(email).get().getContentList();
        System.out.println(content);
        return content;
    }

    @Override
    public List<Users> getAllUsers(String role,String email) throws UserNotFound {
        List<Users> users;
        if(role.equals("admin_admin")){
            users=userRepository.findAll();
            System.out.println(users);
            return users;
        }else{
            throw new UserNotFound();
        }

    }
}
