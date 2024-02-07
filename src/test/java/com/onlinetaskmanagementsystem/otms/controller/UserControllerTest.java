package com.onlinetaskmanagementsystem.otms.controller;


import com.onlinetaskmanagementsystem.otms.DTO.SignInDTO;
import com.onlinetaskmanagementsystem.otms.DTO.UserDTO;
import com.onlinetaskmanagementsystem.otms.Exception.CommonException;
import com.onlinetaskmanagementsystem.otms.Response.SignUpResponse;
import com.onlinetaskmanagementsystem.otms.entity.UserEntity;
import com.onlinetaskmanagementsystem.otms.mapper.UserMapper;
import com.onlinetaskmanagementsystem.otms.service.UserService;
import jakarta.validation.Valid;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.stubbing.OngoingStubbing;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.http.HttpStatus.*;

@ExtendWith(MockitoExtension.class)
class UserControllerTest {
    @InjectMocks
    UserController userController;

    @Mock
    UserService userService;

    UserDTO userDTO = new UserDTO();
    SignInDTO signInDTO = new SignInDTO();

    SignUpResponse signUpResponse = new SignUpResponse();

    UserMapper userMapper = new UserMapper();


    @Test
    void signupUserTest() throws CommonException {
        when(userService.addUser(userDTO)).thenReturn(signUpResponse);
        ResponseEntity<SignUpResponse> response = userController.signupUser(userDTO);
        assertEquals(signUpResponse, response.getBody());
    }

    @Test
    void  signinUserTest() throws  CommonException{
        userDTO.setUsername("Nive");
        when(userService.signInUser(signInDTO)).thenReturn(userDTO);
        ResponseEntity<UserDTO> response=userController.signinUser(signInDTO);
        assertEquals(userDTO.getUsername(),response.getBody().getUsername());
    }


}
