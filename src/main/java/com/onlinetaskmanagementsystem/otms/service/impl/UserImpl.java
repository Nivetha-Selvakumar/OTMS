package com.onlinetaskmanagementsystem.otms.service.impl;


import com.onlinetaskmanagementsystem.otms.DTO.SignInDTO;
import com.onlinetaskmanagementsystem.otms.Response.SignUpResponse;
import com.onlinetaskmanagementsystem.otms.DTO.UserDTO;
import com.onlinetaskmanagementsystem.otms.Exception.CommonException;
import com.onlinetaskmanagementsystem.otms.Exception.UserCredentialException;
import com.onlinetaskmanagementsystem.otms.Exception.UserNotFoundException;
import com.onlinetaskmanagementsystem.otms.entity.UserEntity;
import com.onlinetaskmanagementsystem.otms.Exception.UserCreationException;
import com.onlinetaskmanagementsystem.otms.mapper.UserMapper;
import com.onlinetaskmanagementsystem.otms.repository.UserRepo;
import com.onlinetaskmanagementsystem.otms.service.UserService;
import com.onlinetaskmanagementsystem.otms.validation.Validation;
import jakarta.validation.Valid;
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

    @Autowired
    UserMapper userMapper;

    @Override
    public SignUpResponse addUser(UserDTO userDTO) throws UserCreationException{
        UserEntity userEntity = userMapper.userModelToEntity(userDTO);
        if (validation.checkExistEmail(userDTO.getEmail())) {
            throw new UserCreationException("User already exist. Try with other email !");
        }
        else if(validation.checkExistUser(userDTO.getUsername().trim())){
            throw new UserCreationException("User already exist. Try with other username !");
        }else {
            return userMapper.mapToSignUpModel(userRepo.save(userEntity));
        }

    }

    @Override
    public UserDTO signInUser(@Valid SignInDTO signInDTO) throws CommonException {

//        Verifying the user credentials to signin
            if (validation.checkExistEmail(signInDTO.getEmail())) {
                UserEntity userEntity = userRepo.getUserRecord(signInDTO.getEmail());
                if (userEntity.getPassword().equals(signInDTO.getPassword())) {
                     return userMapper.userEntityToModel(userEntity);
                } else {
                    throw new UserCredentialException("Password Mismatched");
                }
            } else {
                throw new UserNotFoundException("Given email not exists");
            }
    }
}
