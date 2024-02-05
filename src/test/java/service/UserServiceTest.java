package service;

import com.onlinetaskmanagementsystem.otms.DTO.UserDTO;
import com.onlinetaskmanagementsystem.otms.Enum.Status;
import com.onlinetaskmanagementsystem.otms.entity.UserEntity;
import com.onlinetaskmanagementsystem.otms.mapper.UserMapper;
import com.onlinetaskmanagementsystem.otms.repository.UserRepo;
import com.onlinetaskmanagementsystem.otms.service.impl.UserImpl;
import com.onlinetaskmanagementsystem.otms.validation.Validation;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.sql.Timestamp;

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

        userEntity.setId(Integer.valueOf(1));
    }
//    @Test
//    void addUserTest() throws UserCreationException {
//        when(userMapper.userModelToEntity(userDTO)).thenReturn(userEntity);
//        when(userRepo.save(userEntity)).thenReturn(userEntity);
//        Integer id = userImpl.addUser(userDTO);
//        assertEquals(Integer.valueOf(1),id);
//    }
}
