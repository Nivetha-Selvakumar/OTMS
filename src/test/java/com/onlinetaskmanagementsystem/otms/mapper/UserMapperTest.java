package com.onlinetaskmanagementsystem.otms.mapper;

import com.onlinetaskmanagementsystem.otms.DTO.UserDTO;
import com.onlinetaskmanagementsystem.otms.Enum.ActiveStatus;
import com.onlinetaskmanagementsystem.otms.Response.SignUpResponse;
import com.onlinetaskmanagementsystem.otms.entity.OrganisationEntity;
import com.onlinetaskmanagementsystem.otms.entity.RoleEntity;
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

    OrganisationEntity organisationEntity = new OrganisationEntity();

    RoleEntity roleEntity = new RoleEntity();


    @BeforeEach
    void init(){

        userEntity.setEmpCode("111");
        userEntity.setEmpName("nivetha");
        userEntity.setUsername("nive");
        userEntity.setEmail("n@gmail.com");
        userEntity.setPassword("nive");
        userEntity.setRegistrationDate(Timestamp.valueOf("2024-01-02 12:30:40"));
        userEntity.setCreatedDate(Timestamp.valueOf("2024-01-02 12:30:40"));


        userDTO.setEmpCode("111");
        userDTO.setEmpName("nivetha");
        userDTO.setUsername("nive");
        userDTO.setEmail("n@gmail.com");
        userDTO.setPassword("nive");
        userDTO.setRegistrationDate(Timestamp.valueOf("2024-01-02 12:30:40"));
        userDTO.setUserStatus(ActiveStatus.valueOf("ACTIVE"));
        signUpResponse.setMessage("Successfully Registered!");
        signUpResponse.setUserId("Your UserId: "+userEntity.getId());
    }


    @Test
    void userModelToEntityTest(){
        UserEntity userEntity1 = userMapper.userModelToEntity(userDTO, organisationEntity,roleEntity);
        Assertions.assertEquals(userEntity.getEmpCode(),userEntity1.getEmpCode());
        Assertions.assertEquals(userEntity.getEmpName(),userEntity1.getEmpName());
        Assertions.assertEquals(userEntity.getUsername(),userEntity1.getUsername());
        Assertions.assertEquals(userEntity.getEmail(),userEntity1.getEmail());
        Assertions.assertEquals(userEntity.getPassword(),userEntity1.getPassword());
        Assertions.assertEquals(userEntity.getRegistrationDate(),userEntity1.getRegistrationDate());


    }

    @Test
    void userEntityToModelTest(){
        UserDTO userDTO1 = userMapper.userEntityToModel(userEntity);
        Assertions.assertEquals(userDTO.getEmpCode(),userDTO1.getEmpCode());
        Assertions.assertEquals(userDTO.getEmpName(),userDTO1.getEmpName());
        Assertions.assertEquals(userDTO.getUsername(),userDTO1.getUsername());
        Assertions.assertEquals(userDTO.getEmail(),userDTO1.getEmail());
        Assertions.assertEquals(userDTO.getPassword(),userDTO1.getPassword());
        Assertions.assertEquals(userDTO.getRegistrationDate(),userDTO1.getRegistrationDate());

    }

    @Test
    void mapToSignUpModelTest(){
        SignUpResponse signUpResponse1= userMapper.mapToSignUpModel(userEntity);
        Assertions.assertEquals(signUpResponse.getUserId(),signUpResponse1.getUserId());
        Assertions.assertEquals(signUpResponse.getMessage(),signUpResponse1.getMessage());
    }

}
