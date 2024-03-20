package com.onlinetaskmanagementsystem.otms.service;

import com.onlinetaskmanagementsystem.otms.DTO.SignInDTO;
import com.onlinetaskmanagementsystem.otms.DTO.UserDTO;
import com.onlinetaskmanagementsystem.otms.Exception.CommonException;
import com.onlinetaskmanagementsystem.otms.Response.SignUpResponse;
import jakarta.validation.Valid;
import org.springframework.stereotype.Service;

@Service
public interface UserService {
    SignUpResponse addUser(UserDTO userDTO, String orgRef) throws CommonException;

    UserDTO signInUser(@Valid SignInDTO signInDTO, String orgRef) throws CommonException;

}
