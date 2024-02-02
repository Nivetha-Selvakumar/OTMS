package com.onlinetaskmanagementsystem.otms.controller;


import com.onlinetaskmanagementsystem.otms.DTO.SignInDTO;
import com.onlinetaskmanagementsystem.otms.DTO.UserDTO;
import com.onlinetaskmanagementsystem.otms.Enum.Status;
import com.onlinetaskmanagementsystem.otms.Exception.CommonException;
import com.onlinetaskmanagementsystem.otms.Exception.UserCreationException;
import com.onlinetaskmanagementsystem.otms.service.UserService;
import com.onlinetaskmanagementsystem.otms.service.impl.UserImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.Assert.*;
import static org.junit.Assert.assertThrows;
import static org.mockito.Mockito.when;

//@ExtendWith(MockitoExtension.class)
@SpringBootTest
class UserControllerTest {
    @InjectMocks
    UserController userController;

    @Autowired
    UserService userService;


    UserDTO userDTO;
    @BeforeEach
    public  void init(){
        userService = new UserImpl();
        UserDTO userDTO=new UserDTO();
        userDTO.setEmail("n@gmail.com");
        userDTO.setUserStatus(Status.ACTIVE);
        userDTO.setUsername("Sample");
        userDTO.setPassword("1234yyufytfy");
        userDTO.setEmpCode("ATs123");
        userDTO.setCreatedBy(1);
        userDTO.setUpdatedBy(1);
        userDTO.setOrgId(1);
        userDTO.setRoleId(1);;
        userDTO.setEmpName("nive");
    }

//    @Test
//    void signupUserTest() throws UserCreationException{
//        when(userService.addUser(Mockito.any())).thenReturn(Mockito.anyInt());
////        ResponseEntity<String> user = userController.signupUser(userDTO);
////        assertEquals(HttpStatus.CREATED,user.getStatusCode());
//    }

    @Test
    void userSigninTest(){

        SignInDTO signInDTO = new SignInDTO();
        signInDTO.setEmail("n@gmail.com");
        signInDTO.setPassword("nive");

        try {
            assertEquals("Signin successful", userService.signInUser(signInDTO));
        } catch (CommonException e) {
            throw new RuntimeException(e);
        }
    }



}
