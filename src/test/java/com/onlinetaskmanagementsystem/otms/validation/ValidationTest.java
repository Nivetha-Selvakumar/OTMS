package com.onlinetaskmanagementsystem.otms.validation;

import com.onlinetaskmanagementsystem.otms.DTO.TaskDTO;
import com.onlinetaskmanagementsystem.otms.DTO.TaskHistoryDTO;
import com.onlinetaskmanagementsystem.otms.DTO.UserDTO;
import com.onlinetaskmanagementsystem.otms.Enum.ActiveStatus;
import com.onlinetaskmanagementsystem.otms.Enum.TaskIds;
import com.onlinetaskmanagementsystem.otms.Exception.CommonException;
import com.onlinetaskmanagementsystem.otms.Exception.TaskNotFoundException;
import com.onlinetaskmanagementsystem.otms.entity.TaskEntity;
import com.onlinetaskmanagementsystem.otms.entity.TaskHistoryEntity;
import com.onlinetaskmanagementsystem.otms.entity.UserEntity;
import com.onlinetaskmanagementsystem.otms.repository.TaskHistoryRepo;
import com.onlinetaskmanagementsystem.otms.repository.TaskRepo;
import com.onlinetaskmanagementsystem.otms.repository.UserRepo;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.*;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ValidationTest {
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
        userEntity.setId(1);
        taskEntity.setId(1);
        taskEntity.setUserId(userEntity);
        userDTO.setEmail("n@gmail.com");
        taskDTO.setTaskTitle("New task");
        taskEntity.setTaskTitle("New task");
        taskEntity.setUserId(userEntity);
        taskDTO.setUserId(1);
        taskEntityList.add(taskEntity);
        taskDTO.setActiveStatus(ActiveStatus.valueOf("ACTIVE"));
        userDTO.setUserStatus(ActiveStatus.valueOf("ACTIVE"));
        taskHistoryDTO.setTaskId(1);
        taskHistoryEntity.setTaskId(taskEntity);
        taskHistoryEntityList.add(taskHistoryEntity);

    }

    @Test
    void checkExistEmailValidationTest(){
        when(userRepo.checkUser(userDTO.getEmail())).thenReturn(1);
        boolean result = validation.checkExistEmail(userDTO.getEmail());
        Assertions.assertTrue(result);
    }

    @Test
    void checkExistUserValidationTest(){
        when(userRepo.checkUserName(userDTO.getUsername())).thenReturn(String.valueOf(true));
        boolean result= validation.checkExistUser(userDTO.getUsername());
        Assertions.assertTrue(result);
    }

    @Test
    void taskTitleValidationTest(){
        when(taskRepo.findByTaskTitle(taskDTO.getTaskTitle())).thenReturn(Optional.of(taskEntity));
        boolean result= validation.taskTitleValidation(taskDTO.getTaskTitle());
        Assertions.assertTrue(result);
    }

    @Test
    void  taskUserValidationTest(){
        when(userRepo.findById(userEntity.getId())).thenReturn(Optional.ofNullable(userEntity));
        when(taskRepo.findByUserId(userEntity)).thenReturn(taskEntityList);
        boolean result= validation.taskUserValidation(1);
        Assertions.assertTrue(result);
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
        when(userRepo.findById(userEntity.getId())).thenReturn(Optional.ofNullable(userEntity));
        when(taskRepo.findById(taskEntity.getId())).thenReturn(Optional.ofNullable(taskEntity));
        when(taskRepo.findByUserIdAndIdAndActiveStatus(userEntity,taskEntity.getId(), taskDTO.getActiveStatus())).thenReturn(Optional.ofNullable(taskEntity));
        validation.taskExistValidationByUserIdAndTaskId(1,1);
    }

    @Test
    void  taskExistValidationByUserIdAndTaskIdValidationTest2() {
        when(userRepo.findById(userEntity.getId())).thenReturn(Optional.ofNullable(userEntity));
        when(taskRepo.findById(taskEntity.getId())).thenReturn(Optional.ofNullable(taskEntity));
        when(taskRepo.findByUserIdAndIdAndActiveStatus(userEntity,taskEntity.getId(), taskDTO.getActiveStatus())).thenReturn(Optional.empty());
        assertThrows(CommonException.class,() -> {
            validation.taskExistValidationByUserIdAndTaskId(1,1);
        });
    }

    @Test
    void taskExistValidationByUserIdAndTaskIdValidationTest3() throws TaskNotFoundException {
        TaskEntity taskEntity1 = validation.taskExistValidationByUserIdAndTaskId(null,null);
        Assertions.assertNull(taskEntity1);
    }
    @Test
    void taskHistoryValidationTest2(){
        when(taskRepo.findById(taskEntity.getId())).thenReturn(Optional.ofNullable(taskEntity));
        when(taskHistoryRepo.findByTaskId(taskEntity)).thenReturn(taskHistoryEntityList);
        boolean result= validation.taskHistoryValidation(1);
        Assertions.assertTrue(result);
    }

    @Test
    void taskHistoryUserValidationTest1(){
        when(userRepo.findById(taskDTO.getUserId())).thenReturn(Optional.ofNullable(userEntity));
        boolean result = validation.taskHistoryUserValidation(1);
        Assertions.assertTrue(result);
    }

    @Test
    void taskUserIdAndTaskTitleValidationTest(){
        when(userRepo.findById(taskDTO.getUserId())).thenReturn(Optional.ofNullable(userEntity));
        when(taskRepo.findByTaskTitleAndUserIdAndActiveStatus(taskDTO.getTaskTitle(),userEntity,taskDTO.getActiveStatus())).thenReturn(Optional.ofNullable(taskEntity));
        boolean result = validation.taskUserIdAndTaskTitleValidation(1,taskDTO.getTaskTitle());
        Assertions.assertTrue(result);
    }

    @Test
    void taskUserValidationAndStatusTest(){
        when(userRepo.findByIdAndUserStatus(userEntity.getId(),ActiveStatus.ACTIVE)).thenReturn(Optional.ofNullable(userEntity));
        boolean result = validation.taskUserValidationAndStatus(taskDTO.getUserId());
        Assertions.assertTrue(result);
    }

    @Test
    void taskUserIdValidationTest1(){
        when(userRepo.findById(userEntity.getId())).thenReturn(Optional.of(userEntity));
        boolean result = validation.taskUserIdValidation(taskDTO.getUserId());
        Assertions.assertTrue(result);
    }

    @Test
    void taskUserIdValidationTest2(){
        when(userRepo.findById(userEntity.getId())).thenReturn(Optional.empty());
        boolean result = validation.taskUserIdValidation(taskDTO.getUserId());
        assertFalse(result);
    }

    @Test
    void validateUserIdsTest1() {
        Map<String, Integer> ids = new HashMap<>();
        ids.put(TaskIds.ASSIGNEEID.toString(), 1);
        ids.put(TaskIds.ASSIGNERID.toString(), 2);
        assertThrows(CommonException.class,() -> {
            validation.validatedUserIds(ids);
        });
    }

    @Test
    void validateUserIdsTest2() {
        Map<String, Integer> ids = new HashMap<>();
        ids.put(TaskIds.ASSIGNEEID.toString(), 1);
        ids.put(TaskIds.ASSIGNERID.toString(), 2);
        when(userRepo.findById(1)).thenReturn(Optional.empty());
        when(userRepo.findById(2)).thenReturn(Optional.of(new UserEntity()));
        assertThrows(CommonException.class,() -> {
            validation.validatedUserIds(ids);
        });
    }
    @Test
    void validateUserIdsTest3() {
        Map<String, Integer> ids = new HashMap<>();
        ids.put(TaskIds.ASSIGNEEID.toString(), 1);
        when(userRepo.findById(taskDTO.getUserId())).thenReturn(Optional.ofNullable(userEntity));
        ids.put(TaskIds.ASSIGNERID.toString(), 2);
        assertThrows(CommonException.class,() -> {
            validation.validatedUserIds(ids);
        });
    }

    @Test
    void validateUserIdTest4() throws CommonException {
        Map<String, Integer> ids = new HashMap<>();
        ids.put(TaskIds.ASSIGNEEID.toString(), 1);
        ids.put(TaskIds.ASSIGNERID.toString(), 2);

        when(userRepo.findById(1)).thenReturn(Optional.of(new UserEntity()));
        when(userRepo.findById(2)).thenReturn(Optional.of(new UserEntity()));

        UserEntity result = validation.validatedUserIds(ids);

        assertNull(result);

    }

}
