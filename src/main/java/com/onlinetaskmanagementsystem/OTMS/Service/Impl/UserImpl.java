package com.onlinetaskmanagementsystem.OTMS.Service.Impl;


import com.onlinetaskmanagementsystem.OTMS.DTO.UserDTO;
import com.onlinetaskmanagementsystem.OTMS.Entity.UserEntity;
import com.onlinetaskmanagementsystem.OTMS.Exception.UserCreationException;
import com.onlinetaskmanagementsystem.OTMS.Mapper.UserMapper;
import com.onlinetaskmanagementsystem.OTMS.Repository.UserRepo;
import com.onlinetaskmanagementsystem.OTMS.Service.UserService;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserImpl implements UserService {

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public boolean checkExistEmail(String email) {
        boolean userFlag = false;

        Integer id = userRepo.checkUser(email);
        if (id != null && id>0) {
            userFlag = true;
        }
        return userFlag;
    }


    @Override
    public Integer addUser(UserDTO userDTO) throws UserCreationException{
//        UserEntity userEntity= UserMapper.userModelToEntity(userDTO);
//        userRepo.save(userEntity);
//        return userEntity.getId();

        UserEntity userEntity = UserMapper.userModelToEntity(userDTO);
        if (checkExistEmail(userDTO.getEmail())) {
            throw new UserCreationException("User already exist. Try with other email !");
        } else {
            userEntity = userRepo.save(userEntity);
            return userEntity.getId();
        }
    }
}
