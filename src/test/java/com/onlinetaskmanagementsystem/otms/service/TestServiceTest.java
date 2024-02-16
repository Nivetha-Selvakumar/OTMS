package com.onlinetaskmanagementsystem.otms.service;

import com.onlinetaskmanagementsystem.otms.DTO.TaskDTO;
import com.onlinetaskmanagementsystem.otms.DTO.TaskHistoryDTO;
import com.onlinetaskmanagementsystem.otms.DTO.TaskUpdateDTO;
import com.onlinetaskmanagementsystem.otms.Enum.ActiveStatus;
import com.onlinetaskmanagementsystem.otms.Enum.TaskStatus;
import com.onlinetaskmanagementsystem.otms.Exception.CommonException;
import com.onlinetaskmanagementsystem.otms.entity.TaskEntity;
import com.onlinetaskmanagementsystem.otms.entity.TaskHistoryEntity;
import com.onlinetaskmanagementsystem.otms.mapper.TaskHistoryMapper;
import com.onlinetaskmanagementsystem.otms.mapper.TaskMapper;
import com.onlinetaskmanagementsystem.otms.repository.TaskHistoryRepo;
import com.onlinetaskmanagementsystem.otms.repository.TaskRepo;
import com.onlinetaskmanagementsystem.otms.service.impl.TaskImpl;
import com.onlinetaskmanagementsystem.otms.validation.Validation;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertThrows;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class TestServiceTest {
    @InjectMocks
    TaskImpl taskImpl;

    @Mock
    TaskMapper taskMapper;

    @Mock
    TaskHistoryMapper taskHistoryMapper;

    @Mock
    TaskHistoryRepo taskHistoryRepo;

    @Mock
    TaskRepo taskRepo;
    @Mock
    Validation validation;

    TaskDTO taskDTO= new TaskDTO();
    TaskEntity taskEntity = new TaskEntity();

    TaskHistoryDTO taskHistoryDTO= new TaskHistoryDTO();

    TaskHistoryEntity taskHistoryEntity = new TaskHistoryEntity();

    List<TaskEntity> taskEntityList = new ArrayList<>();

    List<TaskHistoryEntity> taskHistoryEntityList = new ArrayList<>();

    TaskUpdateDTO taskUpdateDTO = new TaskUpdateDTO();
    @BeforeEach
    void init(){
        taskDTO.setTaskTitle("test creation");
        taskDTO.setUserId(1);;
        taskDTO.setCreatedBy(1);
        taskDTO.setCreatedDate(Timestamp.valueOf("2024-01-02 12:30:40"));
        taskDTO.setTaskDesc("created for testing");
        taskDTO.setUpdatedBy(1);
        taskDTO.setUpdatedDate(Timestamp.valueOf("2024-01-02 12:30:40"));
        taskDTO.setActiveStatus(ActiveStatus.valueOf("ACTIVE"));
        taskDTO.setActualCompletionDate(Timestamp.valueOf("2024-01-02 12:30:40"));
        taskDTO.setActualStartDate(Timestamp.valueOf("2024-01-02 12:30:40"));
        taskDTO.setAssigneeId(1);
        taskDTO.setAssignerId(1);
        taskDTO.setPlannedCompletionDate(Timestamp.valueOf("2024-01-02 12:30:40"));
        taskDTO.setPlannedCompletionDate(Timestamp.valueOf("2024-01-02 12:30:40"));
        taskDTO.setPlannedStartDate(Timestamp.valueOf("2024-01-02 12:30:40"));
        taskDTO.setPriority("High");
        taskDTO.setTaskStatus(TaskStatus.NOT_YET_STARTED);
        taskEntity.setId(1);
        taskEntityList.add(taskEntity);
        taskEntityList.add(taskEntity);
        taskHistoryEntityList.add(taskHistoryEntity);
        taskHistoryEntityList.add(taskHistoryEntity);
        taskHistoryDTO.setTaskId(1);
        taskEntity.setActiveStatus(ActiveStatus.valueOf("INACTIVE"));
    }
    @Test
    void addTaskTest1(){
        when(taskMapper.taskModeltoEntity(taskDTO)).thenReturn(taskEntity);
        when(validation.taskUserValidation(taskDTO.getUserId())).thenReturn(true);
        when(validation.taskTitleValidation(taskDTO.getTaskTitle())).thenReturn(true);
        CommonException commonException=assertThrows(CommonException.class,() -> {
            taskImpl.addTask(taskDTO);
        });
        String expectedMessage = "This task is already present for this user";
        String actualMessage=commonException.getMessage();
        Assertions.assertEquals(expectedMessage,actualMessage);
    }
    @Test
    void addTaskTest2() throws CommonException {
        when(taskMapper.taskModeltoEntity(taskDTO)).thenReturn(taskEntity);
        when(validation.taskTitleValidation(taskDTO.getTaskTitle())).thenReturn(false);
        when(taskRepo.save(taskEntity)).thenReturn(taskEntity);
        when(taskHistoryMapper.taskHistoryModelToEntity(taskEntity,"Created")).thenReturn(new TaskHistoryEntity());
        Integer response = taskImpl.addTask(taskDTO);
        Assertions.assertEquals(taskEntity.getId(),response);
    }

    @Test
    void viewTaskTest1() throws  CommonException{
        when(taskRepo.findAllByUserId(taskDTO.getUserId())).thenReturn(taskEntityList);
        when(taskMapper.taskEntityToModel(taskEntity)).thenReturn(taskDTO);
        List<TaskDTO> actual = taskImpl.viewList(taskDTO.getUserId());
        Assertions.assertEquals(taskEntityList.size(),actual.size());

    }

    @Test
    void viewUpdateTask1() throws CommonException{
        when(validation.taskExistValidation(taskDTO.getUserId())).thenReturn(taskEntity);
        when(taskRepo.save(taskMapper.taskUpdateModelToEntity(taskUpdateDTO,taskEntity))).thenReturn(taskEntity);
        when(taskHistoryMapper.taskHistoryModelToEntity(taskEntity, taskUpdateDTO.getUpdatedField())).thenReturn(taskHistoryEntity);
        TaskDTO actual = taskImpl.viewUpdatedTask(taskHistoryDTO.getTaskId(), taskUpdateDTO);
        Assertions.assertEquals(taskMapper.taskEntityToModel(taskEntity),actual);
    }

    @Test
    void deleteTask1() throws CommonException{
        when(validation.taskUserValidation(taskDTO.getUserId())).thenReturn(true);
        when(validation.taskExistValidationByUserIdAndTaskId(taskHistoryDTO.getTaskId(), taskDTO.getUserId())).thenReturn(taskEntity);
        String actual = taskImpl.deleteTask(taskHistoryDTO.getTaskId(), taskDTO.getUserId());
        String expected = "Successfully Inactive!";
        Assertions.assertEquals(expected,actual);
    }

    @Test
    void  deleteTask2() throws  CommonException{
        when(validation.taskUserValidation(taskDTO.getUserId())).thenReturn(false);
        String actual = taskImpl.deleteTask(taskHistoryDTO.getTaskId(), taskDTO.getUserId());
        String expected = "No Active task is for particular User";
        Assertions.assertEquals(expected,actual);
    }

    @Test
    void viewHistoryTaskTest1() throws  CommonException{
        when(validation.taskHistoryUserValidation(taskDTO.getUserId())).thenReturn(true);
        when(validation.taskHistoryValidation(taskHistoryDTO.getTaskId())).thenReturn(true);
        when(taskHistoryRepo.findAllByTaskId(taskHistoryDTO.getTaskId())).thenReturn(taskHistoryEntityList);

        List<TaskHistoryDTO> actual = taskImpl.viewHistoryTask(taskHistoryDTO.getTaskId(), taskDTO.getUserId());
        Assertions.assertEquals(taskHistoryEntityList.size(),actual.size());
    }
    @Test
    void viewHistoryTaskTest2() throws CommonException{
        when(validation.taskHistoryUserValidation(taskDTO.getUserId())).thenReturn(false);
        CommonException commonException=assertThrows(CommonException.class,() -> {
            taskImpl.viewHistoryTask(taskHistoryDTO.getTaskId(),taskDTO.getUserId());
        });
        String expectedMessage = "The user is not found";
        String actualMessage=commonException.getMessage();
        Assertions.assertEquals(expectedMessage,actualMessage);
    }
}
