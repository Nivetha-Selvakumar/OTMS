package com.onlinetaskmanagementsystem.OTMS.Controller;

import com.onlinetaskmanagementsystem.OTMS.DTO.UserDTO;
import com.onlinetaskmanagementsystem.OTMS.Service.UserService;
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
    public ResponseEntity<String> signupUser(@RequestBody @Valid UserDTO userDTO){
        Integer id= userService.addUser(userDTO);
        String responseMsg="Successfully Registered!\nYour UserId: "+id;
        return new ResponseEntity<>(responseMsg, HttpStatus.CREATED);
    }
}
