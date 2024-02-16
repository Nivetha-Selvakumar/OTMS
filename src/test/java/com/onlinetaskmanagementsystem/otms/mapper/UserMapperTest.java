package com.onlinetaskmanagementsystem.otms.mapper;

import com.onlinetaskmanagementsystem.otms.DTO.UserDTO;
import com.onlinetaskmanagementsystem.otms.Enum.ActiveStatus;
import com.onlinetaskmanagementsystem.otms.Response.SignUpResponse;
import com.onlinetaskmanagementsystem.otms.entity.UserEntity;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import java.sql.Timestamp;

@ExtendWith(MockitoExtension.class)
class UserMapperTest {
    @InjectMocks
    UserMapper userMapper;

    UserEntity userEntity = new UserEntity();

    UserDTO userDTO = new UserDTO();

    SignUpResponse signUpResponse = new SignUpResponse();

    @BeforeEach
    void init(){

        userEntity.setOrgId(1);
        userEntity.setRoleId(1);
        userEntity.setEmpCode("111");
        userEntity.setEmpName("nivetha");
        userEntity.setUsername("nive");
        userEntity.setEmail("n@gmail.com");
        userEntity.setPassword("nive");
        userEntity.setRegistrationDate(Timestamp.valueOf("2024-01-02 12:30:40"));
        userEntity.setCreatedBy(1);
        userEntity.setCreatedDate(Timestamp.valueOf("2024-01-02 12:30:40"));
        userEntity.setUpdatedBy(1);
        userEntity.setUpdatedDate(Timestamp.valueOf("2024-01-02 12:30:40"));


        userDTO.setOrgId(1);
        userDTO.setRoleId(1);
        userDTO.setEmpCode("111");
        userDTO.setEmpName("nivetha");
        userDTO.setUsername("nive");
        userDTO.setEmail("n@gmail.com");
        userDTO.setPassword("nive");
        userDTO.setRegistrationDate(Timestamp.valueOf("2024-01-02 12:30:40"));
        userDTO.setUserStatus(ActiveStatus.valueOf("ACTIVE"));
        userDTO.setCreatedBy(1);
        userDTO.setCreatedDate(Timestamp.valueOf("2024-01-02 12:30:40"));
        userDTO.setUpdatedBy(1);
        userDTO.setUpdatedDate(Timestamp.valueOf("2024-01-02 12:30:40"));

        signUpResponse.setMessage("Successfully Registered!");
        signUpResponse.setUserId("Your UserId: "+userEntity.getId());
    }


    @Test
    void userModelToEntityTest(){
        UserEntity userEntity1 = userMapper.userModelToEntity(userDTO);
        Assertions.assertEquals(userEntity.getOrgId(), userEntity1.getOrgId());
        Assertions.assertEquals(userEntity.getRoleId(),userEntity1.getRoleId());
        Assertions.assertEquals(userEntity.getEmpCode(),userEntity1.getEmpCode());
        Assertions.assertEquals(userEntity.getEmpName(),userEntity1.getEmpName());
        Assertions.assertEquals(userEntity.getUsername(),userEntity1.getUsername());
        Assertions.assertEquals(userEntity.getEmail(),userEntity1.getEmail());
        Assertions.assertEquals(userEntity.getPassword(),userEntity1.getPassword());
        Assertions.assertEquals(userEntity.getRegistrationDate(),userEntity1.getRegistrationDate());
        Assertions.assertEquals(userEntity.getCreatedBy(),userEntity1.getCreatedBy());
        Assertions.assertEquals(userEntity.getCreatedDate(),userEntity1.getCreatedDate());
        Assertions.assertEquals(userEntity.getUpdatedBy(),userEntity1.getUpdatedBy());
        Assertions.assertEquals(userEntity.getUpdatedDate(),userEntity1.getUpdatedDate());

    }

    @Test
    void userEntityToModelTest(){
        UserDTO userDTO1 = userMapper.userEntityToModel(userEntity);
        Assertions.assertEquals(userDTO.getOrgId(),userDTO1.getOrgId());
        Assertions.assertEquals(userDTO.getRoleId(),userDTO1.getRoleId());
        Assertions.assertEquals(userDTO.getEmpCode(),userDTO1.getEmpCode());
        Assertions.assertEquals(userDTO.getEmpName(),userDTO1.getEmpName());
        Assertions.assertEquals(userDTO.getUsername(),userDTO1.getUsername());
        Assertions.assertEquals(userDTO.getEmail(),userDTO1.getEmail());
        Assertions.assertEquals(userDTO.getPassword(),userDTO1.getPassword());
        Assertions.assertEquals(userDTO.getRegistrationDate(),userDTO1.getRegistrationDate());
        Assertions.assertEquals(userDTO.getCreatedBy(),userDTO1.getCreatedBy());
        Assertions.assertEquals(userDTO.getCreatedDate(),userDTO1.getCreatedDate());
        Assertions.assertEquals(userDTO.getUpdatedBy(),userDTO1.getUpdatedBy());
        Assertions.assertEquals(userDTO.getUpdatedDate(),userDTO1.getUpdatedDate());
    }

    @Test
    void mapToSignUpModelTest(){
        SignUpResponse signUpResponse1= userMapper.mapToSignUpModel(userEntity);
        Assertions.assertEquals(signUpResponse.getUserId(),signUpResponse1.getUserId());
        Assertions.assertEquals(signUpResponse.getMessage(),signUpResponse1.getMessage());
    }


}
