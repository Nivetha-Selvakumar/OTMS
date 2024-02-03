package com.onlinetaskmanagementsystem.otms.controller;


import com.onlinetaskmanagementsystem.otms.DTO.SignInDTO;
import com.onlinetaskmanagementsystem.otms.DTO.UserDTO;
import com.onlinetaskmanagementsystem.otms.Exception.CommonException;
import com.onlinetaskmanagementsystem.otms.service.UserService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UserControllerTest {
    @InjectMocks
    UserController userController;

    @Mock
    UserService userService;

    UserDTO userDTO;
    SignInDTO signInDTO;


    @Test
    void signupUserTest() throws CommonException {
        String s = "Successfully Registered!\nYour UserId: 1";
        when(userService.addUser(userDTO)).thenReturn(1);
        ResponseEntity<String> user = userController.signupUser(userDTO);
        assertEquals(s, user.getBody());
    }

    @Test
    void userSigninTest() throws CommonException {

        when(userService.signInUser(signInDTO)).thenReturn("Login successful");
        String stringResponseEntity = userService.signInUser(signInDTO);
        assertEquals("Login successful", stringResponseEntity);
    }
}
