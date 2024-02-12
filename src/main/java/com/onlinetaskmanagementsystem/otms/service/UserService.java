package com.onlinetaskmanagementsystem.otms.service;

import com.onlinetaskmanagementsystem.otms.DTO.SignInDTO;
import com.onlinetaskmanagementsystem.otms.Response.SignUpResponse;
import com.onlinetaskmanagementsystem.otms.DTO.UserDTO;
import com.onlinetaskmanagementsystem.otms.Exception.CommonException;
import com.onlinetaskmanagementsystem.otms.Exception.UserCreationException;

import jakarta.validation.Valid;
import org.springframework.stereotype.Service;

@Service
public interface UserService {
    SignUpResponse addUser(UserDTO userDTO) throws UserCreationException;

    UserDTO signInUser(@Valid SignInDTO signInDTO) throws CommonException;

}
