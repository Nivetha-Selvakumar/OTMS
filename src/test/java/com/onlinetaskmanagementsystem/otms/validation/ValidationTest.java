package com.onlinetaskmanagementsystem.otms.validation;

import com.onlinetaskmanagementsystem.otms.DTO.RoleDTO;
import com.onlinetaskmanagementsystem.otms.DTO.TaskDTO;
import com.onlinetaskmanagementsystem.otms.DTO.TaskHistoryDTO;
import com.onlinetaskmanagementsystem.otms.DTO.UserDTO;
import com.onlinetaskmanagementsystem.otms.Enum.ActiveStatus;
import com.onlinetaskmanagementsystem.otms.Enum.TaskIds;
import com.onlinetaskmanagementsystem.otms.Exception.CommonException;
import com.onlinetaskmanagementsystem.otms.Exception.TaskNotFoundException;
import com.onlinetaskmanagementsystem.otms.entity.*;
import com.onlinetaskmanagementsystem.otms.repository.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
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
    OrganisationRepo organisationRepo;

    @Mock
    RoleRepo roleRepo;

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

    OrganisationEntity organisationEntity = new OrganisationEntity();

    RoleEntity roleEntity= new RoleEntity();

    RoleDTO roleDTO = new RoleDTO();


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
        taskDTO.setParentTaskId(1);
        taskHistoryDTO.setTaskId(1);
        taskHistoryEntity.setTaskId(taskEntity);
        taskHistoryEntityList.add(taskHistoryEntity);
        roleEntity.setId(1);
        roleEntity.setRole("EMPLOYEE");
        roleDTO.setRole("EMPLOYEE");

    }

    @Test
    void checkExistEmailValidationTest(){
        when(userRepo.findByEmail(userDTO.getEmail())).thenReturn(Optional.of(userEntity));
        boolean result = validation.checkExistEmail(userDTO.getEmail());
        Assertions.assertTrue(result);
    }

    @Test
    void checkExistUserValidationTest(){
        when(userRepo.findByUsername(userDTO.getUsername())).thenReturn(Optional.of(userEntity));
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
    void taskViewValidationTest2() {
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
        Assertions.assertNull(taskEntity1);
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
        Assertions.assertFalse(result);
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

        Assertions.assertNull(result);

    }

    @Test
    void taskTaskIdAndUserValidationAndStatusTest(){
        when(userRepo.findById(taskDTO.getUserId())).thenReturn(Optional.of(userEntity));
        when(taskRepo.findByIdAndUserIdAndActiveStatus(taskDTO.getParentTaskId(),userEntity,ActiveStatus.ACTIVE)).thenReturn(Optional.of(taskEntity));
        boolean response = validation.taskTaskIdAndUserValidationAndStatus(taskDTO.getParentTaskId(),taskDTO.getUserId());
        Assertions.assertTrue(response);
    }

    @Test
    void subTaskViewValidationTest1() {
        when(taskRepo.findByIdAndActiveStatus(taskHistoryDTO.getTaskId(),taskDTO.getActiveStatus())).thenReturn(Optional.empty());
        assertThrows(CommonException.class,() -> {
            validation.subTaskViewValidation(taskDTO.getParentTaskId());
        });
    }

    @Test
    void subTaskExistValidationByUserIdAndTaskIdAndIdTest1(){
        when(userRepo.findById(taskDTO.getUserId())).thenReturn(Optional.ofNullable(userEntity));
        when(taskRepo.findById(taskEntity.getId())).thenReturn(Optional.ofNullable(taskEntity));
        when(taskRepo.findByUserIdAndIdAndParentTaskIdAndActiveStatus(userEntity, taskHistoryDTO.getTaskId(), taskEntity,taskDTO.getActiveStatus())).thenReturn(Optional.empty());
        assertThrows(CommonException.class,() -> {
            validation.subTaskExistValidationByUserIdAndTaskIdAndId(taskDTO.getParentTaskId(),taskDTO.getUserId(),1);
        });
    }

    @Test
    void subTaskExistValidationByUserIdAndTaskIdAndIdTest2() throws CommonException {
        when(userRepo.findById(taskDTO.getUserId())).thenReturn(Optional.ofNullable(userEntity));
        when(taskRepo.findById(taskEntity.getId())).thenReturn(Optional.ofNullable(taskEntity));
        when(taskRepo.findByUserIdAndIdAndParentTaskIdAndActiveStatus(userEntity, taskHistoryDTO.getTaskId(), taskEntity,taskDTO.getActiveStatus())).thenReturn(Optional.of(taskEntity));
        validation.subTaskExistValidationByUserIdAndTaskIdAndId(taskDTO.getParentTaskId(),taskDTO.getUserId(),1);
    }

    @Test
    void subTaskExistValidationByUserIdAndTaskIdAndIdTest3() throws CommonException {
        taskEntity.setParentTaskId(null);
        validation.subTaskExistValidationByUserIdAndTaskIdAndId(taskDTO.getParentTaskId(),null,1);
    }

    @Test
    void subTaskExistValidationTest1(){
        when(taskRepo.findById(taskEntity.getId())).thenReturn(Optional.of(taskEntity));
        when(taskRepo.findByIdAndParentTaskIdAndActiveStatus(taskHistoryDTO.getTaskId(), taskEntity,ActiveStatus.ACTIVE)).thenReturn(Optional.empty());
        assertThrows(CommonException.class,() -> {
            validation.subTaskExistValidation(taskDTO.getParentTaskId(),1);
        });
    }
    @Test
    void subTaskExistValidationTest2() throws CommonException {
        when(taskRepo.findById(taskEntity.getId())).thenReturn(Optional.of(taskEntity));
        when(taskRepo.findByIdAndParentTaskIdAndActiveStatus(taskHistoryDTO.getTaskId(), taskEntity,ActiveStatus.ACTIVE)).thenReturn(Optional.of(taskEntity));
        validation.subTaskExistValidation(taskDTO.getParentTaskId(),1);
    }

    @Test
    void subTaskExistValidationTest3() throws CommonException {
        taskEntity.setParentTaskId(null);
        validation.subTaskExistValidation(taskDTO.getParentTaskId(),null);
    }

    @Test
    void subTaskHistoryValidationTest(){
        when(taskRepo.findById(taskEntity.getId())).thenReturn(Optional.ofNullable(taskEntity));
        when(taskHistoryRepo.findByTaskId(taskEntity)).thenReturn(taskHistoryEntityList);
        Assertions.assertTrue(validation.subTaskHistoryValidation(taskHistoryDTO.getTaskId()));
    }

    @Test
    void subTaskReductionChildCount(){
        taskEntity.setChildCount(3);
        when(taskRepo.findById(taskEntity.getId())).thenReturn(Optional.of(taskEntity));
        validation.subTaskReductionChildCount(taskHistoryDTO.getTaskId());
    }

    @Test
    void orgRefValidationTest1(){
        when(organisationRepo.findByOrgRef(Mockito.any())).thenReturn(Optional.empty());
        assertThrows(CommonException.class,() -> {
            validation.orgRefValidation(Mockito.any());
        });
    }

    @Test
    void orgRefValidationTest2() throws CommonException {
        when(organisationRepo.findByOrgRef(Mockito.any())).thenReturn(Optional.of(organisationEntity));
        OrganisationEntity actual = validation.orgRefValidation(Mockito.any());
        assertEquals(organisationEntity,actual);
    }

    @Test
    void userRoleValidationTest1() throws CommonException {
        when(roleRepo.findByIdAndActiveStatus(roleEntity.getId(),ActiveStatus.ACTIVE)).thenReturn(Optional.of(roleEntity));
        assertEquals(roleEntity,validation.userRoleValidation(roleEntity.getId()));
    }
    @Test
    void userRoleValidationTest2(){
        when(roleRepo.findByIdAndActiveStatus(roleEntity.getId(),ActiveStatus.ACTIVE)).thenReturn(Optional.empty());
        assertThrows(CommonException.class,() -> {
            validation.userRoleValidation(roleEntity.getId());
        });
    }

    @Test
    void userRoleValidationTest3() throws CommonException {
        roleEntity.setId(null);
        when(roleRepo.findByRoleAndActiveStatus(roleEntity.getRole(), ActiveStatus.ACTIVE)).thenReturn(Optional.of(roleEntity));
        assertEquals(roleEntity,validation.userRoleValidation(null));
    }

    @Test
    void userRoleValidationTest4(){
        roleEntity.setId(null);
        when(roleRepo.findByRoleAndActiveStatus(roleEntity.getRole(), ActiveStatus.ACTIVE)).thenReturn(Optional.empty());
        assertThrows(CommonException.class,() -> {
            validation.userRoleValidation(roleEntity.getId());
        });
    }



}