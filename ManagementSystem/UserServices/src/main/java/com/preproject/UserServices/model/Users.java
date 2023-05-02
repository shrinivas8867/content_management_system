package com.preproject.UserServices.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Document
public class Users {
    @Id
    private String email;
    private String firstname;
    private String lastname;
    private String password;
    private long pnumber;
    private String role;
    private String gender;
    private int age;
    private List<Content> contentList;

}
