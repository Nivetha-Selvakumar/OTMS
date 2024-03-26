package com.onlinetaskmanagementsystem.otms.controller;

import com.onlinetaskmanagementsystem.otms.DTO.SignInDTO;
import com.onlinetaskmanagementsystem.otms.DTO.UserDTO;
import com.onlinetaskmanagementsystem.otms.Exception.CommonException;
import com.onlinetaskmanagementsystem.otms.Response.SignUpResponse;
import com.onlinetaskmanagementsystem.otms.service.UserService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;


@RestController
@CrossOrigin
@RequestMapping("/api/user")
@Controller
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping(path = "/signup")
    public ResponseEntity<SignUpResponse> signupUser(@RequestBody @Valid UserDTO userDTO,@RequestHeader String orgRef) throws CommonException {
        return new ResponseEntity<>(userService.addUser(userDTO,orgRef), HttpStatus.CREATED);
    }

    @PostMapping(path="/signin")
    public ResponseEntity<UserDTO> signinUser(@RequestBody @Valid SignInDTO signInDTO,@RequestHeader String orgRef) throws CommonException{
        return new ResponseEntity<>(userService.signInUser(signInDTO, orgRef), HttpStatus.OK);
    }
}