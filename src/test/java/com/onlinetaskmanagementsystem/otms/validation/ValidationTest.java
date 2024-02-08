package com.onlinetaskmanagementsystem.otms.validation;

import com.onlinetaskmanagementsystem.otms.DTO.TaskDTO;
import com.onlinetaskmanagementsystem.otms.DTO.TaskHistoryDTO;
import com.onlinetaskmanagementsystem.otms.DTO.UserDTO;
import com.onlinetaskmanagementsystem.otms.Enum.Status;
import com.onlinetaskmanagementsystem.otms.Exception.CommonException;
import com.onlinetaskmanagementsystem.otms.Exception.TaskNotFoundException;
import com.onlinetaskmanagementsystem.otms.entity.TaskEntity;
import com.onlinetaskmanagementsystem.otms.entity.TaskHistoryEntity;
import com.onlinetaskmanagementsystem.otms.entity.UserEntity;
import com.onlinetaskmanagementsystem.otms.repository.TaskHistoryRepo;
import com.onlinetaskmanagementsystem.otms.repository.TaskRepo;
import com.onlinetaskmanagementsystem.otms.repository.UserRepo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static junit.framework.Assert.*;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ValidationTest {
    @InjectMocks
    Validation validation;

    @Mock
    UserRepo userRepo;

    @Mock
    TaskRepo taskRepo;

    @Mock
    TaskHistoryRepo taskHistoryRepo;

    UserDTO userDTO= new UserDTO();

    TaskDTO taskDTO =new TaskDTO();

    TaskHistoryDTO taskHistoryDTO = new TaskHistoryDTO();
    UserEntity userEntity = new UserEntity();

    TaskEntity taskEntity = new TaskEntity();

    List<TaskEntity> taskEntityList = new ArrayList<>();

    List<TaskHistoryEntity> taskHistoryEntityList = new ArrayList<>();
    TaskHistoryEntity taskHistoryEntity = new TaskHistoryEntity();

    @BeforeEach
    void  init(){
        userDTO.setEmail("n@gmail.com");
        taskDTO.setTaskTitle("New task");
        taskEntity.setTaskTitle("New task");
        taskEntity.setUserId(1);;
        taskDTO.setUserId(1);
        taskEntityList.add(taskEntity);
        taskDTO.setActiveStatus(Status.valueOf("ACTIVE"));
        userDTO.setUserStatus(Status.valueOf("ACTIVE"));
        taskHistoryDTO.setTaskId(1);
        taskHistoryEntity.setTaskId(1);
        taskHistoryEntityList.add(taskHistoryEntity);
        userEntity.setId(1);
    }

    @Test
    void checkExistEmailValidationTest(){
        when(userRepo.checkUser(userDTO.getEmail())).thenReturn(1);
        boolean result = validation.checkExistEmail(userDTO.getEmail());
        assertTrue(result);
    }

    @Test
    void checkExistUserValidationTest(){
        when(userRepo.checkUserName(userDTO.getUsername())).thenReturn(String.valueOf(true));
        boolean result= validation.checkExistUser(userDTO.getUsername());
        assertTrue(result);
    }

    @Test
    void taskTitleValidationTest(){
        when(taskRepo.findByTaskTitle(taskDTO.getTaskTitle())).thenReturn(taskEntity);
        boolean result= validation.taskTitleValidation(taskDTO.getTaskTitle());
        assertTrue(result);
    }

    @Test
    void  taskUserValidationTest(){
        when(taskRepo.findByUserId(taskDTO.getUserId())).thenReturn(taskEntityList);
        boolean result= validation.taskUserValidation(taskDTO.getUserId());
        assertTrue(result);
    }

    @Test
    void taskViewValidationTest1()  throws CommonException  {
        when(userRepo.findByIdAndUserStatus(taskDTO.getUserId(),userDTO.getUserStatus())).thenReturn(Optional.ofNullable(userEntity));
        validation.taskViewValidation(taskDTO.getUserId());
    }
    @Test
    void taskViewValidationTest2()  throws CommonException  {
        when(userRepo.findByIdAndUserStatus(taskDTO.getUserId(),userDTO.getUserStatus())).thenReturn(Optional.empty());
        assertThrows(CommonException.class,() -> {
            validation.taskViewValidation(1);
        });
    }

    @Test
    void taskExistValidationTest1() throws CommonException{
        when(taskRepo.findByIdAndActiveStatus(taskHistoryDTO.getTaskId(), taskDTO.getActiveStatus())).thenReturn(Optional.ofNullable(taskEntity));
        validation.taskExistValidation(1);
    }

    @Test
    void  taskExistValidationTest2() {
        when(taskRepo.findByIdAndActiveStatus(taskHistoryDTO.getTaskId(), taskDTO.getActiveStatus())).thenReturn(Optional.empty());
        assertThrows(CommonException.class,() -> {
            validation.taskExistValidation(1);
        });
    }

    @Test
    void taskExistValidationTest3() throws TaskNotFoundException {
        TaskEntity taskEntity1 = validation.taskExistValidation(null);
        assertEquals(null,taskEntity1);
    }

    @Test
    void taskExistValidationByUserIdAndTaskIdValidationTest1() throws CommonException{
        when(taskRepo.findByUserIdAndIdAndActiveStatus(taskDTO.getUserId(),taskHistoryDTO.getTaskId(), taskDTO.getActiveStatus())).thenReturn(Optional.ofNullable(taskEntity));
        validation.taskExistValidationByUserIdAndTaskId(1,1);
    }

    @Test
    void  taskExistValidationByUserIdAndTaskIdValidationTest2() {
        when(taskRepo.findByUserIdAndIdAndActiveStatus(taskDTO.getUserId(),taskHistoryDTO.getTaskId(), taskDTO.getActiveStatus())).thenReturn(Optional.empty());
        assertThrows(CommonException.class,() -> {
            validation.taskExistValidationByUserIdAndTaskId(1,1);
        });
    }

    @Test
    void taskExistValidationByUserIdAndTaskIdValidationTest3() throws TaskNotFoundException {
        TaskEntity taskEntity1 = validation.taskExistValidationByUserIdAndTaskId(null,null);
        assertEquals(null,taskEntity1);
    }
    @Test
    void taskHistoryValidationTest2(){
        when(taskHistoryRepo.findByTaskId(taskHistoryDTO.getTaskId())).thenReturn(taskHistoryEntityList);
        boolean result= validation.taskHistoryValidation(1);
        assertTrue(result);
    }

    @Test
    void taskHistoryUserValidationTest1(){
        when(userRepo.findById(userEntity.getId())).thenReturn(Optional.ofNullable(userEntity));
        validation.taskHistoryUserValidation(1);
    }





}
