package com.onlinetaskmanagementsystem.otms.validation;

import com.onlinetaskmanagementsystem.otms.DTO.SignInDTO;
import com.onlinetaskmanagementsystem.otms.Exception.CommonException;
import com.onlinetaskmanagementsystem.otms.Exception.UserCredentialException;
import com.onlinetaskmanagementsystem.otms.Exception.UserNotFoundException;
import com.onlinetaskmanagementsystem.otms.entity.UserEntity;
import com.onlinetaskmanagementsystem.otms.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Validation {

    @Autowired
    UserRepo userRepo;

    //check exist email If email exists return true and send error message
    public boolean checkExistEmail(String email) {
        boolean userFlag = false;

        Integer id = userRepo.checkUser(email);
        if (id != null && id > 0) {
            userFlag = true;
        }
        return userFlag;
    }

    public boolean checkExistUser(String username) {
        boolean usernameFlag = false;

        String user = userRepo.checkUserName(username);
        if (user != null) {
            usernameFlag = true;
        }
        return usernameFlag;
    }

    public boolean signinValidation(SignInDTO signInDTO) throws CommonException {

        if (checkExistEmail(signInDTO.getEmail())) {
            UserEntity userEntity = userRepo.getUserRecord(signInDTO.getEmail());
            if (userEntity.getPassword().equals(signInDTO.getPassword())) {
                return true;
            } else {
                throw new UserCredentialException("Password Mismatched");
            }

        } else {
            throw new UserNotFoundException("Given email not exists");

        }

    }


}
