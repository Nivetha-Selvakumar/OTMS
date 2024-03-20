package com.onlinetaskmanagementsystem.otms.service;

import com.onlinetaskmanagementsystem.otms.DTO.SignInDTO;
import com.onlinetaskmanagementsystem.otms.DTO.UserDTO;
import com.onlinetaskmanagementsystem.otms.Enum.ActiveStatus;
import com.onlinetaskmanagementsystem.otms.Exception.CommonException;
import com.onlinetaskmanagementsystem.otms.Response.SignUpResponse;
import com.onlinetaskmanagementsystem.otms.entity.OrganisationEntity;
import com.onlinetaskmanagementsystem.otms.entity.RoleEntity;
import com.onlinetaskmanagementsystem.otms.entity.UserEntity;
import com.onlinetaskmanagementsystem.otms.mapper.UserMapper;
import com.onlinetaskmanagementsystem.otms.repository.UserRepo;
import com.onlinetaskmanagementsystem.otms.service.impl.UserImpl;
import com.onlinetaskmanagementsystem.otms.validation.Validation;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.sql.Timestamp;

import static org.junit.Assert.assertThrows;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {
    @InjectMocks
    UserImpl userImpl;

    @Mock
    UserMapper userMapper;
    @Mock
    UserRepo userRepo;
    @Mock
    Validation validation;

    UserEntity userEntity = new UserEntity();
    UserDTO userDTO = new UserDTO();

    SignInDTO signInDTO = new SignInDTO();

    OrganisationEntity organisationEntity = new OrganisationEntity();

    RoleEntity roleEntity = new RoleEntity();
    @BeforeEach
    void init(){

        organisationEntity.setOrgRef("ORG001");

//        userDTO.setOrgId(1);
//        userDTO.setRoleId(1);
        userDTO.setEmpCode("111");
        userDTO.setEmpName("nive");
        userDTO.setUsername("nive23");
        userDTO.setEmail("n@gmail.com");
        userDTO.setPassword("nivetha");
        userDTO.setRegistrationDate(Timestamp.valueOf("2024-02-02 12:30:40"));
        userDTO.setUserStatus(ActiveStatus.valueOf("ACTIVE"));
        userDTO.setRoleId(1);


        userEntity.setId(1);
        userEntity.setEmail("n@gmail.com");
        userEntity.setUserStatus(ActiveStatus.valueOf("ACTIVE"));
        userEntity.setEmpCode("111");
        userEntity.setEmpName("nive");
        userEntity.setCreatedDate(Timestamp.valueOf("2024-01-02 12:30:40"));
        userEntity.setRegistrationDate(Timestamp.valueOf("2024-01-02 12:30:40"));
        userEntity.setUsername("nive");
        userEntity.setPassword("nivetha");

        signInDTO.setPassword("nivetha");
        signInDTO.setEmail("n@gmail.com");
    }

    @Test
    void addUserTest1() throws CommonException {
        when(validation.orgRefValidation(organisationEntity.getOrgRef())).thenReturn(organisationEntity);
//        when(userMapper.userModelToEntity(userDTO, organisationEntity, roleEntity)).thenReturn(userEntity);
        when(validation.checkExistEmail(userDTO.getEmail())).thenReturn(true);
        CommonException commonException=assertThrows(CommonException.class,() -> {
            userImpl.addUser(userDTO, "ORG001");
        });
        String expectedMessage = "User already exist. Try with other email !";
        String actualMessage=commonException.getMessage();
        Assertions.assertEquals(expectedMessage,actualMessage);
    }

    @Test
    void addUserTest2() throws CommonException{
        when(validation.orgRefValidation(organisationEntity.getOrgRef())).thenReturn(organisationEntity);
//        when(userMapper.userModelToEntity(userDTO, organisationEntity,roleEntity)).thenReturn(userEntity);
        when(validation.checkExistUser(userDTO.getUsername())).thenReturn(true);
        CommonException commonException=assertThrows(CommonException.class,() -> {
            userImpl.addUser(userDTO, "ORG001");
        });
        String expectedMessage = "User already exist. Try with other username !";
        String actualMessage=commonException.getMessage();
        Assertions.assertEquals(expectedMessage,actualMessage);
    }

    @Test
    void  addUserTest3() throws CommonException {
        when(validation.orgRefValidation(organisationEntity.getOrgRef())).thenReturn(organisationEntity);
        when(validation.checkExistUser(userDTO.getUsername())).thenReturn(false);
        when(validation.checkExistUser(userDTO.getUsername())).thenReturn(false);
        when(validation.userRoleValidation(userDTO.getRoleId())).thenReturn(roleEntity);
        when(userMapper.userModelToEntity(userDTO, organisationEntity,roleEntity)).thenReturn(userEntity);
        when(userRepo.save(userEntity)).thenReturn(userEntity);
        SignUpResponse id1 = userImpl.addUser(userDTO , "ORG001");
        Assertions.assertNull(id1);
    }

    @Test
    void signInUserTest1() throws CommonException{
        when(validation.orgRefValidation(organisationEntity.getOrgRef())).thenReturn(organisationEntity);
        when(validation.checkExistEmail(signInDTO.getEmail())).thenReturn(true);
        when(validation.checkPassword(signInDTO.getEmail(), signInDTO.getPassword())).thenReturn(true);
        when(userRepo.findByEmailAndPasswordAndOrgId(signInDTO.getEmail(), signInDTO.getPassword(),organisationEntity)).thenReturn(userEntity);
        when(userMapper.userEntityToModel(userEntity)).thenReturn(userDTO);
        UserDTO userDTOResponse = userImpl.signInUser(signInDTO, "ORG001");
        Assertions.assertEquals(userDTO.getEmail(),userDTOResponse.getEmail());
    }

    @Test
    void signInUserTest2(){
        signInDTO.setEmail("n@gmail.com");
        signInDTO.setPassword("Niv");
        when(validation.checkExistEmail(signInDTO.getEmail())).thenReturn(true);
        when(validation.checkPassword(signInDTO.getEmail(), signInDTO.getPassword())).thenReturn(false);
        CommonException commonException=assertThrows(CommonException.class,() -> {
            userImpl.signInUser(signInDTO, "ORG001");
        });
        String response = commonException.getMessage();
        Assertions.assertEquals("Password Mismatched",response);
    }

    @Test
    void signInUserTest3(){
        when(validation.checkExistEmail(signInDTO.getEmail())).thenReturn(false);
        CommonException commonException=assertThrows(CommonException.class,() -> {
            userImpl.signInUser(signInDTO, "ORG001");
        });
        String response = commonException.getMessage();
        Assertions.assertEquals("Given email not exists",response);
    }



}
