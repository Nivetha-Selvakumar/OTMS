package com.onlinetaskmanagementsystem.otms.service.impl;


import com.onlinetaskmanagementsystem.otms.DTO.SignInDTO;
import com.onlinetaskmanagementsystem.otms.DTO.UserDTO;
import com.onlinetaskmanagementsystem.otms.Exception.CommonException;
import com.onlinetaskmanagementsystem.otms.entity.UserEntity;
import com.onlinetaskmanagementsystem.otms.Exception.UserCreationException;
import com.onlinetaskmanagementsystem.otms.mapper.UserMapper;
import com.onlinetaskmanagementsystem.otms.repository.UserRepo;
import com.onlinetaskmanagementsystem.otms.service.UserService;
import com.onlinetaskmanagementsystem.otms.validation.Validation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserImpl implements UserService {

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    Validation validation;


    @Override
    public Integer addUser(UserDTO userDTO) throws UserCreationException{

        UserEntity userEntity = UserMapper.userModelToEntity(userDTO);
        if (validation.checkExistEmail(userDTO.getEmail())) {
            throw new UserCreationException("User already exist. Try with other email !");
        }
        else if(validation.checkExistUser(userDTO.getUsername().trim())){
            throw new UserCreationException("User already exist. Try with other username !");
        }else {
            userEntity = userRepo.save(userEntity);
            return userEntity.getId();
        }

    }

    @Override
    public String signInUser(SignInDTO signInDTO) throws CommonException {

//        Verifying the user credentials to signin
        if(validation.signinValidation(signInDTO)){
            return "Login successful";
        }
        return null;
    }

}
