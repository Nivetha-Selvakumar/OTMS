package com.onlinetaskmanagementsystem.otms.mapper;

import com.onlinetaskmanagementsystem.otms.Response.SignUpResponse;
import com.onlinetaskmanagementsystem.otms.DTO.UserDTO;
import com.onlinetaskmanagementsystem.otms.entity.OrganisationEntity;
import com.onlinetaskmanagementsystem.otms.entity.RoleEntity;
import com.onlinetaskmanagementsystem.otms.entity.UserEntity;
import com.onlinetaskmanagementsystem.otms.Enum.ActiveStatus;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {


    public UserEntity userModelToEntity(UserDTO userDTO, OrganisationEntity organisationEntity, RoleEntity roleEntity){
        UserEntity userEntity = new UserEntity();
        userEntity.setEmpCode(userDTO.getEmpCode());
        userEntity.setEmpName(userDTO.getEmpName());
        userEntity.setUsername(userDTO.getUsername());
        userEntity.setEmail(userDTO.getEmail());
        userEntity.setPassword(userDTO.getPassword());
        userEntity.setRegistrationDate(userDTO.getRegistrationDate());
        userEntity.setUserStatus(ActiveStatus.ACTIVE);
        userEntity.setOrgId(organisationEntity);
        userEntity.setRoleId(roleEntity);
        return userEntity;
    }

    public UserDTO userEntityToModel(UserEntity userEntity){
        UserDTO userDTO = new UserDTO();
        userDTO.setId(userEntity.getId());
        userDTO.setEmpCode(userEntity.getEmpCode());
        userDTO.setEmpName(userEntity.getEmpName());
        userDTO.setUsername(userEntity.getUsername());
        userDTO.setEmail(userEntity.getEmail());
        userDTO.setPassword(userEntity.getPassword());
        userDTO.setRegistrationDate(userEntity.getRegistrationDate());
        userDTO.setUserStatus(userEntity.getUserStatus());

        return userDTO;

    }

    public SignUpResponse mapToSignUpModel(UserEntity userEntity) {
        SignUpResponse signUpResponse = new SignUpResponse();
        signUpResponse.setMessage("Successfully Registered!");
        signUpResponse.setUserId("Your UserId: "+userEntity.getId());
        return signUpResponse;
    }
}
