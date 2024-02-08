package com.onlinetaskmanagementsystem.otms.controller;


import com.onlinetaskmanagementsystem.otms.DTO.SignInDTO;
import com.onlinetaskmanagementsystem.otms.DTO.UserDTO;
import com.onlinetaskmanagementsystem.otms.Exception.CommonException;
import com.onlinetaskmanagementsystem.otms.Response.SignUpResponse;
import com.onlinetaskmanagementsystem.otms.mapper.UserMapperTest;
import com.onlinetaskmanagementsystem.otms.service.UserService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UserControllerTest {
    @InjectMocks
    UserController userController;

    @Mock
    UserService userService;

    UserDTO userDTO = new UserDTO();
    SignInDTO signInDTO = new SignInDTO();

    SignUpResponse signUpResponse = new SignUpResponse();

    UserMapperTest userMapper = new UserMapperTest();


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
