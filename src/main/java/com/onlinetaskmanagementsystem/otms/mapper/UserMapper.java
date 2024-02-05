package com.onlinetaskmanagementsystem.otms.mapper;

import com.onlinetaskmanagementsystem.otms.Response.SignUpResponse;
import com.onlinetaskmanagementsystem.otms.DTO.UserDTO;
import com.onlinetaskmanagementsystem.otms.entity.UserEntity;
import com.onlinetaskmanagementsystem.otms.Enum.Status;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {
    public UserEntity userModelToEntity(UserDTO userDTO){
        UserEntity userEntity = new UserEntity();
        userEntity.setOrgId(userDTO.getOrgId());
        userEntity.setRoleId(userDTO.getRoleId());
        userEntity.setEmpCode(userDTO.getEmpCode());
        userEntity.setEmpName(userDTO.getEmpName());
        userEntity.setUsername(userDTO.getUsername());
        userEntity.setEmail(userDTO.getEmail());
        userEntity.setPassword(userDTO.getPassword());
        userEntity.setRegistrationDate(userDTO.getRegistrationDate());
        userEntity.setUserStatus(Status.ACTIVE);
        userEntity.setCreatedBy(userDTO.getCreatedBy());
        userEntity.setCreatedDate(userDTO.getCreatedDate());
        userEntity.setUpdatedBy(userDTO.getUpdatedBy());
        userEntity.setUpdatedDate(userDTO.getUpdatedDate());

        return userEntity;

    }

    public UserDTO userEntityToModel(UserEntity userEntity){
        UserDTO userDTO = new UserDTO();
        userDTO.setOrgId(userEntity.getOrgId());
        userDTO.setRoleId(userEntity.getRoleId());
        userDTO.setEmpCode(userEntity.getEmpCode());
        userDTO.setEmpName(userEntity.getEmpName());
        userDTO.setUsername(userEntity.getUsername());
        userDTO.setEmail(userEntity.getEmail());
        userDTO.setPassword(userEntity.getPassword());
        userDTO.setRegistrationDate(userEntity.getRegistrationDate());
        userDTO.setUserStatus(userEntity.getUserStatus());
        userDTO.setCreatedBy(userEntity.getCreatedBy());
        userDTO.setCreatedDate(userEntity.getCreatedDate());
        userDTO.setUpdatedBy(userEntity.getUpdatedBy());
        userDTO.setUpdatedDate(userEntity.getUpdatedDate());

        return userDTO;

    }

    public SignUpResponse mapToSignUpModel(UserEntity userEntity) {
        SignUpResponse signUpResponse = new SignUpResponse();
        signUpResponse.setMessage("Successfully Registered!");
        signUpResponse.setUserId("Your UserId: "+userEntity.getId());
        return signUpResponse;
    }
}
