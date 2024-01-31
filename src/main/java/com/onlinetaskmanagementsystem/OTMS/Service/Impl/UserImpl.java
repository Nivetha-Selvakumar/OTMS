package com.onlinetaskmanagementsystem.OTMS.Service.Impl;


import com.onlinetaskmanagementsystem.OTMS.DTO.UserDTO;
import com.onlinetaskmanagementsystem.OTMS.Entity.UserEntity;
import com.onlinetaskmanagementsystem.OTMS.Mapper.UserMapper;
import com.onlinetaskmanagementsystem.OTMS.Repository.UserRepo;
import com.onlinetaskmanagementsystem.OTMS.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserImpl implements UserService {

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public Integer addUser(UserDTO userDTO) {
        UserEntity userEntity= UserMapper.userModelToEntity(userDTO);
        userRepo.save(userEntity);
        return userEntity.getId();
    }


}
