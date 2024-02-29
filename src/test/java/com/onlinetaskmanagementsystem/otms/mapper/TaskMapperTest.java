package com.onlinetaskmanagementsystem.otms.mapper;

import com.onlinetaskmanagementsystem.otms.DTO.TaskDTO;
import com.onlinetaskmanagementsystem.otms.DTO.TaskUpdateDTO;
import com.onlinetaskmanagementsystem.otms.Enum.ActiveStatus;
import com.onlinetaskmanagementsystem.otms.Enum.Priority;
import com.onlinetaskmanagementsystem.otms.Enum.TaskStatus;
import com.onlinetaskmanagementsystem.otms.entity.TaskEntity;
import com.onlinetaskmanagementsystem.otms.entity.UserEntity;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import java.sql.Timestamp;

@ExtendWith(MockitoExtension.class)
class TaskMapperTest {
    @InjectMocks
    TaskMapper taskMapper;

    TaskEntity taskEntity = new TaskEntity();

    UserEntity userEntity = new UserEntity();

    TaskDTO taskDTO = new TaskDTO();

    TaskUpdateDTO taskUpdateDTO=new TaskUpdateDTO();


    @BeforeEach
    void init(){
        userEntity.setId(1);
        userEntity.setEmpName("nive");

        taskEntity.setUserId(userEntity);
        taskEntity.setTaskTitle("Creating for test");
        taskEntity.setTaskDesc("Test desc");
        taskEntity.setPriority(Priority.HIGH);
        taskEntity.setTaskStatus(TaskStatus.NOT_YET_STARTED);
        taskEntity.setPlannedStartDate(Timestamp.valueOf("2024-01-02 12:30:40"));
        taskEntity.setPlannedCompletionDate(Timestamp.valueOf("2024-01-02 12:30:40"));
        taskEntity.setActualStartDate(Timestamp.valueOf("2024-01-02 12:30:40"));
        taskEntity.setActualCompletionDate(Timestamp.valueOf("2024-01-02 12:30:40"));
        taskEntity.setActiveStatus(ActiveStatus.valueOf("ACTIVE"));
        taskEntity.setCreatedDate(Timestamp.valueOf("2024-01-02 12:30:40"));
        taskEntity.setUpdatedDate(Timestamp.valueOf("2024-01-02 12:30:40"));
        taskEntity.setCreatedBy(userEntity);
        taskEntity.setAssignerId(userEntity);
        taskEntity.setAssigneeId(userEntity);
        taskEntity.setUpdatedBy(userEntity);


        taskDTO.setUserId(1);
        taskDTO.setTaskTitle("Creating for test");
        taskDTO.setTaskDesc("Test desc");
        taskDTO.setPriority(Priority.HIGH);
        taskDTO.setTaskStatus(TaskStatus.NOT_YET_STARTED);
        taskDTO.setPlannedStartDate(Timestamp.valueOf("2024-01-02 12:30:40"));
        taskDTO.setPlannedCompletionDate(Timestamp.valueOf("2024-01-02 12:30:40"));
        taskDTO.setActualStartDate(Timestamp.valueOf("2024-01-02 12:30:40"));
        taskDTO.setActualCompletionDate(Timestamp.valueOf("2024-01-02 12:30:40"));
        taskDTO.setActiveStatus(ActiveStatus.valueOf("ACTIVE"));
        taskDTO.setCreatedDate(Timestamp.valueOf("2024-01-02 12:30:40"));
        taskDTO.setUpdatedDate(Timestamp.valueOf("2024-01-02 12:30:40"));


        taskUpdateDTO.setUserId(1);
        taskUpdateDTO.setTaskTitle("Creating for test");
        taskUpdateDTO.setTaskDesc("Test desc");
        taskUpdateDTO.setPriority(String.valueOf(Priority.HIGH));
        taskUpdateDTO.setTaskStatus(String.valueOf(TaskStatus.NOT_YET_STARTED));
        taskUpdateDTO.setPlannedStartDate(Timestamp.valueOf("2024-01-02 12:30:40"));
        taskUpdateDTO.setPlannedCompletionDate(Timestamp.valueOf("2024-01-02 12:30:40"));
        taskUpdateDTO.setActualStartDate(Timestamp.valueOf("2024-01-02 12:30:40"));
        taskUpdateDTO.setActualCompletionDate(Timestamp.valueOf("2024-01-02 12:30:40"));
        taskUpdateDTO.setActiveStatus(ActiveStatus.valueOf("ACTIVE"));

    }

    @Test
    void taskModelToEntityTest(){
        TaskEntity taskEntity1 = taskMapper.taskModelToEntity(taskDTO,taskEntity);
        Assertions.assertEquals(taskEntity.getId(),taskEntity1.getId());
        Assertions.assertEquals(taskEntity.getTaskTitle(),taskEntity1.getTaskTitle());
        Assertions.assertEquals(taskEntity.getTaskDesc(),taskEntity1.getTaskDesc());
        Assertions.assertEquals(taskEntity.getPriority(),taskEntity1.getPriority());
        Assertions.assertEquals(taskEntity.getTaskStatus(),taskEntity1.getTaskStatus());
        Assertions.assertEquals(taskEntity.getPlannedStartDate(),taskEntity1.getPlannedStartDate());
        Assertions.assertEquals(taskEntity.getPlannedCompletionDate(),taskEntity1.getPlannedCompletionDate());
        Assertions.assertEquals(taskEntity.getActualStartDate(),taskEntity1.getActualStartDate());
        Assertions.assertEquals(taskEntity.getActualCompletionDate(),taskEntity1.getActualCompletionDate());
        Assertions.assertEquals(taskEntity.getActiveStatus(),taskEntity1.getActiveStatus());
        Assertions.assertEquals(taskEntity.getCreatedDate(),taskEntity1.getCreatedDate());
        Assertions.assertEquals(taskEntity.getUpdatedDate(),taskEntity1.getUpdatedDate());

    }

    @Test
    void taskEntityToModel(){
        TaskDTO taskDTO1 = taskMapper.taskEntityToModel(taskEntity);

        Assertions.assertEquals(taskDTO.getTaskTitle(),taskDTO1.getTaskTitle());
        Assertions.assertEquals(taskDTO.getTaskDesc(),taskDTO1.getTaskDesc());
        Assertions.assertEquals(taskDTO.getPriority(),taskDTO1.getPriority());
        Assertions.assertEquals(taskDTO.getTaskStatus(),taskDTO1.getTaskStatus());
        Assertions.assertEquals(taskDTO.getPlannedStartDate(),taskDTO1.getPlannedStartDate());
        Assertions.assertEquals(taskDTO.getPlannedCompletionDate(),taskDTO1.getPlannedCompletionDate());
        Assertions.assertEquals(taskDTO.getActualStartDate(),taskDTO1.getActualStartDate());
        Assertions.assertEquals(taskDTO.getActualCompletionDate(),taskDTO1.getActualCompletionDate());
        Assertions.assertEquals(taskDTO.getActiveStatus(),taskDTO1.getActiveStatus());
        Assertions.assertEquals(taskDTO.getCreatedDate(),taskDTO1.getCreatedDate());
        Assertions.assertEquals(taskDTO.getUpdatedDate(),taskDTO1.getUpdatedDate());

    }

    @Test
    void taskUpdateModeltoEntityTest(){
        TaskEntity taskEntity1 = taskMapper.taskUpdateModelToEntity(taskUpdateDTO,taskEntity);
        Assertions.assertEquals(taskUpdateDTO.getTaskTitle(),taskEntity1.getTaskTitle());
    }

}
