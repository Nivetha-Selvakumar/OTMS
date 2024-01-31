package com.onlinetaskmanagementsystem.OTMS.Mapper;

import com.onlinetaskmanagementsystem.OTMS.DTO.UserDTO;
import com.onlinetaskmanagementsystem.OTMS.Entity.UserEntity;
import com.onlinetaskmanagementsystem.OTMS.Enum.Status;

public class UserMapper {
    public static UserEntity userModelToEntity(UserDTO userDTO){
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
}
