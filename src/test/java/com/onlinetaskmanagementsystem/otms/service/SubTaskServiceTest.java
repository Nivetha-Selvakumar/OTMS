package com.onlinetaskmanagementsystem.otms.service;

import com.onlinetaskmanagementsystem.otms.DTO.TaskDTO;
import com.onlinetaskmanagementsystem.otms.DTO.TaskHistoryDTO;
import com.onlinetaskmanagementsystem.otms.DTO.TaskUpdateDTO;
import com.onlinetaskmanagementsystem.otms.Enum.ActiveStatus;
import com.onlinetaskmanagementsystem.otms.Enum.Priority;
import com.onlinetaskmanagementsystem.otms.Enum.TaskStatus;
import com.onlinetaskmanagementsystem.otms.Exception.CommonException;
import com.onlinetaskmanagementsystem.otms.entity.OrganisationEntity;
import com.onlinetaskmanagementsystem.otms.entity.TaskEntity;
import com.onlinetaskmanagementsystem.otms.entity.TaskHistoryEntity;
import com.onlinetaskmanagementsystem.otms.entity.UserEntity;
import com.onlinetaskmanagementsystem.otms.mapper.SubTaskMapper;
import com.onlinetaskmanagementsystem.otms.mapper.TaskHistoryMapper;
import com.onlinetaskmanagementsystem.otms.repository.TaskHistoryRepo;
import com.onlinetaskmanagementsystem.otms.repository.TaskRepo;
import com.onlinetaskmanagementsystem.otms.repository.UserRepo;
import com.onlinetaskmanagementsystem.otms.service.impl.SubTaskImpl;
import com.onlinetaskmanagementsystem.otms.validation.Validation;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class SubTaskServiceTest {
    @InjectMocks
    SubTaskImpl subTaskImpl;

    @Mock
    SubTaskMapper subTaskMapper;

    @Mock
    TaskHistoryMapper taskHistoryMapper;

    @Mock
    Validation validation;

    @Mock
    TaskRepo taskRepo;

    @Mock
    UserRepo userRepo;

    @Mock
    TaskHistoryRepo taskHistoryRepo;

    TaskEntity taskEntity = new TaskEntity();

    List<TaskEntity> taskEntityList = new ArrayList<>();

    UserEntity userEntity = new UserEntity();

    TaskDTO taskDTO = new TaskDTO();

    List<TaskDTO> taskDTOList = new ArrayList<>();

    TaskUpdateDTO taskUpdateDTO = new TaskUpdateDTO();

    List<TaskHistoryEntity> taskHistoryEntityList = new ArrayList<>();

    TaskHistoryDTO taskHistoryDTO = new TaskHistoryDTO();


    TaskHistoryEntity taskHistoryEntity = new TaskHistoryEntity();

    OrganisationEntity organisationEntity = new OrganisationEntity();

    @BeforeEach
    void init(){
        userEntity.setId(1);

        taskDTO.setTaskTitle("test creation");
        taskDTO.setUserId(1);
        taskDTO.setCreatedDate(Timestamp.valueOf("2024-01-02 12:30:40"));
        taskDTO.setTaskDesc("created for testing");
        taskDTO.setUpdatedDate(Timestamp.valueOf("2024-01-02 12:30:40"));
        taskDTO.setActiveStatus(ActiveStatus.valueOf("ACTIVE"));
        taskDTO.setActualCompletionDate(Timestamp.valueOf("2024-01-02 12:30:40"));
        taskDTO.setActualStartDate(Timestamp.valueOf("2024-01-02 12:30:40"));
        taskDTO.setPlannedCompletionDate(Timestamp.valueOf("2024-01-02 12:30:40"));
        taskDTO.setPlannedCompletionDate(Timestamp.valueOf("2024-01-02 12:30:40"));
        taskDTO.setPlannedStartDate(Timestamp.valueOf("2024-01-02 12:30:40"));
        taskDTO.setPriority(Priority.HIGH);
        taskDTO.setTaskStatus(TaskStatus.NOT_YET_STARTED);
        taskDTO.setChildCount(1);
        taskDTO.setParentTaskId(10);
        taskDTO.setCreatedBy(1);
        taskDTO.setUpdatedBy(1);

        taskEntity.setId(1);
        taskEntity.setActiveStatus(ActiveStatus.valueOf("INACTIVE"));
        taskEntity.setUserId(userEntity);
        taskEntity.setAssignerId(userEntity);
        taskEntity.setAssigneeId(userEntity);
        taskEntity.setCreatedBy(userEntity);
        taskEntity.setUpdatedBy(userEntity);
        taskEntity.setParentTaskId(taskEntity);
        taskEntity.setChildCount(1);


        taskEntityList.add(taskEntity);
        taskEntityList.add(taskEntity);

        taskDTOList.add(taskDTO);
        taskDTOList.add(taskDTO);

        taskHistoryDTO.setTaskId(taskEntity.getId());

        taskHistoryEntityList.add(taskHistoryEntity);
        taskHistoryEntityList.add(taskHistoryEntity);



    }

    @Test
    void viewSubTaskTest() throws CommonException {
        when(userRepo.findById(taskDTO.getUserId())).thenReturn(Optional.of(userEntity));
        when(taskRepo.findById(taskEntity.getId())).thenReturn(Optional.of(taskEntity));
        when(taskRepo.findAllByUserIdAndParentTaskIdAndActiveStatus(userEntity,taskEntity,ActiveStatus.ACTIVE)).thenReturn(taskEntityList);
        when(subTaskMapper.subTaskEntityToModel(Mockito.any())).thenReturn(taskDTO);
        List<TaskDTO> actual = subTaskImpl.viewSubList(taskEntity.getId(),taskDTO.getUserId(),"ORG001");
        assertEquals(taskEntityList.size(), actual.size());

    }

    @Test
    void addSubTaskTest1(){
        when(validation.taskUserIdValidation(taskDTO.getUserId())).thenReturn(true);
        when(validation.taskUserValidationAndStatus(taskDTO.getUserId())).thenReturn(true);
        when(validation.taskTaskIdAndUserValidationAndStatus(taskEntity.getId(),taskDTO.getUserId())).thenReturn(true);
        when(validation.taskTitleValidation(taskDTO.getTaskTitle())).thenReturn(true);
        when(validation.taskUserIdAndTaskTitleValidation(taskDTO.getUserId(),taskDTO.getTaskTitle())).thenReturn(true);
        CommonException commonException=assertThrows(CommonException.class,() -> {
            subTaskImpl.addSubTask(taskDTO,taskEntity.getId(),"ORG001");
        });
        String expectedMessage = "This task is already present for this user";
        String actualMessage=commonException.getMessage();
        assertEquals(expectedMessage,actualMessage);
    }

    @Test
    void addSubTaskTest2() throws CommonException {
        when(validation.orgRefValidation("ORG001")).thenReturn(organisationEntity);
        when(validation.taskUserIdValidation(taskDTO.getUserId())).thenReturn(false);
        CommonException commonException=assertThrows(CommonException.class,() -> {
            subTaskImpl.addSubTask(taskDTO,taskEntity.getId(),"ORG001");
        });
        String expectedMessage = "No User is Found";
        String actualMessage=commonException.getMessage();
        assertEquals(expectedMessage,actualMessage);
    }

    @Test
    void addSubTaskTest3() throws CommonException {
        when(validation.orgRefValidation("ORG001")).thenReturn(organisationEntity);
        when(validation.taskUserIdValidation(taskDTO.getUserId())).thenReturn(true);
        when(validation.taskUserValidationAndStatus(taskDTO.getUserId())).thenReturn(false);
        CommonException commonException=assertThrows(CommonException.class,() -> {
            subTaskImpl.addSubTask(taskDTO,taskEntity.getId(),"ORG001");
        });
        String expectedMessage = "User is not active";
        String actualMessage=commonException.getMessage();
        assertEquals(expectedMessage,actualMessage);
    }
//
    @Test
    void addSubTaskTest4() throws CommonException {
        when(validation.orgRefValidation("ORG001")).thenReturn(organisationEntity);
        when(validation.taskUserIdValidation(taskDTO.getUserId())).thenReturn(true);
        when(validation.taskUserValidationAndStatus(taskDTO.getUserId())).thenReturn(true);
        when(validation.taskTaskIdAndUserValidationAndStatus(taskEntity.getId(),taskDTO.getUserId())).thenReturn(false);
        CommonException commonException=assertThrows(CommonException.class,() -> {
            subTaskImpl.addSubTask(taskDTO,taskEntity.getId(),"ORG001");
        });
        String expectedMessage = "Task not found or inactive task";
        String actualMessage=commonException.getMessage();
        assertEquals(expectedMessage,actualMessage);

    }
    @Test
    void addSubTaskTest5() throws CommonException {
        when(validation.orgRefValidation("ORG001")).thenReturn(organisationEntity);
        when(validation.taskUserIdValidation(taskDTO.getUserId())).thenReturn(true);
        when(validation.taskUserValidationAndStatus(taskDTO.getUserId())).thenReturn(true);
        when(validation.taskTaskIdAndUserValidationAndStatus(taskEntity.getId(),taskDTO.getUserId())).thenReturn(true);
        when(validation.taskTitleValidation(taskDTO.getTaskTitle())).thenReturn(false);
//        when(validation.taskUserIdAndTaskTitleValidation(taskDTO.getUserId(),taskDTO.getTaskTitle())).thenReturn(false);

        TaskEntity parentTaskEntity = new TaskEntity();
        when(taskRepo.findById(userEntity.getId())).thenReturn(Optional.of(parentTaskEntity));

        UserEntity userEntity1=new UserEntity();

        when(userRepo.findById(taskDTO.getUserId())).thenReturn(Optional.of(userEntity1));
        when(subTaskMapper.subTaskModelToEntity(Mockito.any(),Mockito.any())).thenReturn(taskEntity);
        when(taskRepo.save(taskEntity)).thenReturn(taskEntity);
        when(taskHistoryMapper.taskHistoryModelToEntity(taskEntity,organisationEntity,"Created")).thenReturn(new TaskHistoryEntity());
        Integer response = subTaskImpl.addSubTask(taskDTO,taskEntity.getId(),"ORG001");
        assertEquals(taskEntity.getId(),response);

    }

    @Test
    void addSubTaskTest6() throws CommonException {
        when(validation.orgRefValidation("ORG001")).thenReturn(organisationEntity);
        when(validation.taskUserIdValidation(taskDTO.getUserId())).thenReturn(true);
        when(validation.taskUserValidationAndStatus(taskDTO.getUserId())).thenReturn(true);
        when(validation.taskTaskIdAndUserValidationAndStatus(taskEntity.getId(),taskDTO.getUserId())).thenReturn(true);
        when(validation.taskTitleValidation(taskDTO.getTaskTitle())).thenReturn(false);

        TaskEntity parentTaskEntity = new TaskEntity();
        when(taskRepo.findById(userEntity.getId())).thenReturn(Optional.of(parentTaskEntity));
        parentTaskEntity.setChildCount(1);
        UserEntity userEntity1=new UserEntity();

        when(userRepo.findById(taskDTO.getUserId())).thenReturn(Optional.of(userEntity1));
        when(subTaskMapper.subTaskModelToEntity(Mockito.any(),Mockito.any())).thenReturn(taskEntity);
        when(taskRepo.save(taskEntity)).thenReturn(taskEntity);
        when(taskHistoryMapper.taskHistoryModelToEntity(taskEntity,organisationEntity,"Created")).thenReturn(new TaskHistoryEntity());
        Integer response = subTaskImpl.addSubTask(taskDTO,taskEntity.getId(),"ORG001");
        assertEquals(taskEntity.getId(),response);
    }
    @Test
    void viewUpdateTask1() throws CommonException{
        when(validation.orgRefValidation("ORG001")).thenReturn(organisationEntity);
        when(validation.subTaskExistValidation(taskDTO.getParentTaskId(), taskEntity.getId())).thenReturn(taskEntity);
        when(userRepo.findById(taskUpdateDTO.getUserId())).thenReturn(Optional.of(userEntity));
        when(taskRepo.save(subTaskMapper.subTaskUpdateModelToEntity(taskUpdateDTO,taskEntity))).thenReturn(taskEntity);
        when(taskHistoryMapper.taskHistoryModelToEntity(taskEntity,organisationEntity, taskUpdateDTO.getUpdatedField())).thenReturn(taskHistoryEntity);
        TaskDTO actual = subTaskImpl.viewUpdatedSubTask(taskDTO.getParentTaskId(), taskEntity.getId(), taskUpdateDTO,"ORG001");
        Assertions.assertEquals(subTaskMapper.subTaskEntityToModel(taskEntity),actual);
    }

    @Test
    void deleteSubTask1() throws CommonException {
        when(validation.taskUserValidation(userEntity.getId())).thenReturn(false);
        String expected = "No Active task is for particular User";
        String actual = subTaskImpl.deleteSubTask(taskDTO.getParentTaskId(), userEntity.getId(),taskEntity.getId(),"ORG001");
        assertEquals(expected,actual);
    }

    @Test
    void deleteSubTask2() throws CommonException {
        when(validation.taskUserValidation(userEntity.getId())).thenReturn(true);
        when(validation.subTaskExistValidationByUserIdAndTaskIdAndId(taskDTO.getParentTaskId(), userEntity.getId(), taskEntity.getId())).thenReturn(taskEntity);
        String expected = "Successfully Inactive!";
        String actual = subTaskImpl.deleteSubTask(taskDTO.getParentTaskId(), userEntity.getId(),taskEntity.getId(),"ORG001");
        assertEquals(expected,actual);
    }

    @Test
    void viewHistorySubTaskTest1() throws CommonException {
        when(validation.taskHistoryUserValidation(taskDTO.getUserId())).thenReturn(true);
        when(validation.taskHistoryValidation(Mockito.anyInt())).thenReturn(true);
        when(validation.subTaskHistoryValidation(taskEntity.getId())).thenReturn(true);
        when(taskHistoryRepo.findAllByTaskId(taskEntity)).thenReturn(taskHistoryEntityList);
        when(taskHistoryMapper.taskHistoryEntityToModel(taskHistoryEntity)).thenReturn(taskHistoryDTO);
        when(taskRepo.findById(taskHistoryDTO.getTaskId())).thenReturn(Optional.ofNullable(taskEntity));
        List<TaskHistoryDTO> actual = subTaskImpl.viewHistorySubTask(taskDTO.getParentTaskId(), userEntity.getId(), taskEntity.getId(),"ORG001");
        Assertions.assertEquals(taskHistoryEntityList.size(),actual.size());
    }

    @Test
    void viewHistorySubTaskTest2() throws CommonException {
        when(validation.taskHistoryUserValidation(taskDTO.getUserId())).thenReturn(true);
        when(validation.taskHistoryValidation(Mockito.anyInt())).thenReturn(false);
        String expected = "The user is not found";
        CommonException commonException=assertThrows(CommonException.class,() -> {
            subTaskImpl.viewHistorySubTask(taskDTO.getParentTaskId(),taskEntity.getId(),taskDTO.getUserId(),"ORG001");
        });
        String actual = commonException.getMessage();
        assertEquals(expected,actual);
    }

}
