package com.preproject.UserServices.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.FORBIDDEN, reason = "Content Already Taken..")
public class ContentAlreadyExist extends Exception{
}
