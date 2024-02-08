package com.onlinetaskmanagementsystem.otms.service;

import com.onlinetaskmanagementsystem.otms.DTO.SignInDTO;
import com.onlinetaskmanagementsystem.otms.DTO.UserDTO;
import com.onlinetaskmanagementsystem.otms.Enum.Status;
import com.onlinetaskmanagementsystem.otms.Exception.CommonException;
import com.onlinetaskmanagementsystem.otms.Response.SignUpResponse;
import com.onlinetaskmanagementsystem.otms.entity.UserEntity;
import com.onlinetaskmanagementsystem.otms.mapper.UserMapper;
import com.onlinetaskmanagementsystem.otms.repository.UserRepo;
import com.onlinetaskmanagementsystem.otms.service.impl.UserImpl;
import com.onlinetaskmanagementsystem.otms.validation.Validation;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.sql.Timestamp;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;
import static org.mockito.Mockito.*;

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

    @BeforeEach
    void init(){
        userDTO.setOrgId(1);
        userDTO.setRoleId(1);
        userDTO.setEmpCode("111");
        userDTO.setEmpName("nive");
        userDTO.setUsername("nive23");
        userDTO.setEmail("n@gmail.com");
        userDTO.setPassword("nivetha");
        userDTO.setRegistrationDate(Timestamp.valueOf("2024-02-02 12:30:40"));
        userDTO.setUserStatus(Status.valueOf("ACTIVE"));
        userDTO.setCreatedBy(1);
        userDTO.setCreatedDate(Timestamp.valueOf("2024-01-02 12:30:40"));
        userDTO.setUpdatedBy(1);
        userDTO.setUpdatedDate(Timestamp.valueOf("2024-01-30 12:30:40"));

        userEntity.setId(1);
        userEntity.setEmail("n@gmail.com");
        userEntity.setUserStatus(Status.valueOf("ACTIVE"));
        userEntity.setEmpCode("111");
        userEntity.setEmpName("nive");
        userEntity.setRoleId(1);
        userEntity.setCreatedDate(Timestamp.valueOf("2024-01-02 12:30:40"));
        userEntity.setRegistrationDate(Timestamp.valueOf("2024-01-02 12:30:40"));
        userEntity.setCreatedBy(1);
        userEntity.setUpdatedDate(Timestamp.valueOf("2024-01-02 12:30:40"));
        userEntity.setUpdatedBy(1);
        userEntity.setOrgId(1);
        userEntity.setUsername("nive");
        userEntity.setPassword("nivetha");

        signInDTO.setPassword("nivetha");
        signInDTO.setEmail("n@gmail.com");
    }

    @Test
    void addUserTest1() throws CommonException {
        when(userMapper.userModelToEntity(userDTO)).thenReturn(userEntity);
        when(validation.checkExistEmail(userDTO.getEmail())).thenReturn(true);
        CommonException commonException=assertThrows(CommonException.class,() -> {
            userImpl.addUser(userDTO);
        });
        String expectedMessage = "User already exist. Try with other email !";
        String actualMessage=commonException.getMessage();
        assertEquals(expectedMessage,actualMessage);
    }

    @Test
    void addUserTest2() throws CommonException{
        when(userMapper.userModelToEntity(userDTO)).thenReturn(userEntity);
        when(validation.checkExistUser(userDTO.getUsername())).thenReturn(Boolean.valueOf("User already exist. Try with other username !"));
        when(userRepo.save(userEntity)).thenReturn(userEntity);
        SignUpResponse id1 = userImpl.addUser(userDTO);
        assertEquals(null,id1);
    }

    @Test
    void  addUserTest3() throws CommonException{
        when(userMapper.userModelToEntity(userDTO)).thenReturn(userEntity);
        when(validation.checkExistUser(userDTO.getUsername())).thenReturn(true);
        CommonException commonException=assertThrows(CommonException.class,() -> {
            userImpl.addUser(userDTO);
        });
        String expectedMessage = "User already exist. Try with other username !";
        String actualMessage=commonException.getMessage();
        assertEquals(expectedMessage,actualMessage);
    }

    @Test
    void signInUserTest1() throws CommonException{
        when(validation.checkExistEmail(signInDTO.getEmail())).thenReturn(true);
        when(userRepo.getUserRecord(signInDTO.getEmail())).thenReturn(userEntity);
        when(userMapper.userEntityToModel(userEntity)).thenReturn(userDTO);
        UserDTO userDTOResponse = userImpl.signInUser(signInDTO);
        assertEquals(userDTO.getEmail(),userDTOResponse.getEmail());
    }

    @Test
    void signInUserTest2() throws CommonException{
        signInDTO.setEmail("n@gmail.com");
        signInDTO.setPassword("Niv");
        when(validation.checkExistEmail(signInDTO.getEmail())).thenReturn(true);
        when(userRepo.getUserRecord(signInDTO.getEmail())).thenReturn(userEntity);
        CommonException commonException=assertThrows(CommonException.class,() -> {
            userImpl.signInUser(signInDTO);
        });
        String response = "Password Mismatched";
        assertEquals("Password Mismatched",response);
    }

    @Test
    void signInUserTest3() throws CommonException{
        when(validation.checkExistEmail(signInDTO.getEmail())).thenReturn(false);
        CommonException commonException=assertThrows(CommonException.class,() -> {
            userImpl.signInUser(signInDTO);
        });
        String response = "Given email not exists";
        assertEquals("Given email not exists",response);
    }



}
