package com.onlinetaskmanagementsystem.otms.service;




import com.onlinetaskmanagementsystem.otms.DTO.SignInDTO;
import com.onlinetaskmanagementsystem.otms.DTO.UserDTO;
import com.onlinetaskmanagementsystem.otms.Exception.CommonException;
import com.onlinetaskmanagementsystem.otms.Exception.UserCreationException;

import org.springframework.stereotype.Service;

@Service
public interface UserService {
    Integer addUser(UserDTO userDTO) throws UserCreationException;

    String signInUser(SignInDTO signInDTO) throws CommonException;
}
