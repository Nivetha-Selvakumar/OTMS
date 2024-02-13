package com.onlinetaskmanagementsystem.otms.mapper;

import com.onlinetaskmanagementsystem.otms.DTO.TaskDTO;
import com.onlinetaskmanagementsystem.otms.DTO.TaskUpdateDTO;
import com.onlinetaskmanagementsystem.otms.Enum.ActiveStatus;
import com.onlinetaskmanagementsystem.otms.Enum.TaskStatus;
import com.onlinetaskmanagementsystem.otms.entity.TaskEntity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import java.sql.Timestamp;

import static org.junit.Assert.assertEquals;

@ExtendWith(MockitoExtension.class)
class TaskMapperTest {
    @InjectMocks
    TaskMapper taskMapper;

    TaskEntity taskEntity = new TaskEntity();

    TaskDTO taskDTO = new TaskDTO();

    TaskUpdateDTO taskUpdateDTO=new TaskUpdateDTO();

    @BeforeEach
    void init(){
        taskEntity.setUserId(1);
        taskEntity.setTaskTitle("Creating for test");
        taskEntity.setTaskDesc("Test desc");
        taskEntity.setPriority("High");
        taskEntity.setTaskStatus(TaskStatus.NOT_YET_STARTED);
        taskEntity.setPlannedStartDate(Timestamp.valueOf("2024-01-02 12:30:40"));
        taskEntity.setPlannedCompletionDate(Timestamp.valueOf("2024-01-02 12:30:40"));
        taskEntity.setActualStartDate(Timestamp.valueOf("2024-01-02 12:30:40"));
        taskEntity.setActualCompletionDate(Timestamp.valueOf("2024-01-02 12:30:40"));
        taskEntity.setActiveStatus(ActiveStatus.valueOf("ACTIVE"));
        taskEntity.setAssigneeId(1);
        taskEntity.setAssignerId(1);
        taskEntity.setCreatedBy(1);
        taskEntity.setUpdatedBy(1);
        taskEntity.setCreatedDate(Timestamp.valueOf("2024-01-02 12:30:40"));
        taskEntity.setUpdatedDate(Timestamp.valueOf("2024-01-02 12:30:40"));


        taskDTO.setUserId(1);
        taskDTO.setTaskTitle("Creating for test");
        taskDTO.setTaskDesc("Test desc");
        taskDTO.setPriority("High");
        taskDTO.setTaskStatus(TaskStatus.NOT_YET_STARTED);
        taskDTO.setPlannedStartDate(Timestamp.valueOf("2024-01-02 12:30:40"));
        taskDTO.setPlannedCompletionDate(Timestamp.valueOf("2024-01-02 12:30:40"));
        taskDTO.setActualStartDate(Timestamp.valueOf("2024-01-02 12:30:40"));
        taskDTO.setActualCompletionDate(Timestamp.valueOf("2024-01-02 12:30:40"));
        taskDTO.setActiveStatus(ActiveStatus.valueOf("ACTIVE"));
        taskDTO.setAssigneeId(1);
        taskDTO.setAssignerId(1);
        taskDTO.setCreatedBy(1);
        taskDTO.setUpdatedBy(1);
        taskDTO.setCreatedDate(Timestamp.valueOf("2024-01-02 12:30:40"));
        taskDTO.setUpdatedDate(Timestamp.valueOf("2024-01-02 12:30:40"));


        taskUpdateDTO.setUserId(1);
        taskUpdateDTO.setTaskTitle("Creating for test");
        taskUpdateDTO.setTaskDesc("Test desc");
        taskUpdateDTO.setPriority("High");
        taskUpdateDTO.setTaskStatus(String.valueOf(TaskStatus.NOT_YET_STARTED));
        taskUpdateDTO.setPlannedStartDate(Timestamp.valueOf("2024-01-02 12:30:40"));
        taskUpdateDTO.setPlannedCompletionDate(Timestamp.valueOf("2024-01-02 12:30:40"));
        taskUpdateDTO.setActualStartDate(Timestamp.valueOf("2024-01-02 12:30:40"));
        taskUpdateDTO.setActualCompletionDate(Timestamp.valueOf("2024-01-02 12:30:40"));
        taskUpdateDTO.setActiveStatus(ActiveStatus.valueOf("ACTIVE"));
        taskUpdateDTO.setAssigneeId(1);
        taskUpdateDTO.setAssignerId(1);
        taskUpdateDTO.setCreatedBy(1);
        taskUpdateDTO.setUpdatedBy(1);

    }

    @Test
    void taskModeltoEntityTest(){
        TaskEntity taskEntity1 = taskMapper.taskModeltoEntity(taskDTO);
        assertEquals(taskEntity.getId(),taskEntity1.getId());
        assertEquals(taskEntity.getTaskTitle(),taskEntity1.getTaskTitle());
        assertEquals(taskEntity.getTaskDesc(),taskEntity1.getTaskDesc());
        assertEquals(taskEntity.getPriority(),taskEntity1.getPriority());
        assertEquals(taskEntity.getTaskStatus(),taskEntity1.getTaskStatus());
        assertEquals(taskEntity.getPlannedStartDate(),taskEntity1.getPlannedStartDate());
        assertEquals(taskEntity.getPlannedCompletionDate(),taskEntity1.getPlannedCompletionDate());
        assertEquals(taskEntity.getActualStartDate(),taskEntity1.getActualStartDate());
        assertEquals(taskEntity.getActualCompletionDate(),taskEntity1.getActualCompletionDate());
        assertEquals(taskEntity.getActiveStatus(),taskEntity1.getActiveStatus());
        assertEquals(taskEntity.getAssigneeId(),taskEntity1.getAssigneeId());
        assertEquals(taskEntity.getAssignerId(),taskEntity1.getAssignerId());
        assertEquals(taskEntity.getCreatedBy(),taskEntity1.getCreatedBy());
        assertEquals(taskEntity.getUpdatedBy(),taskEntity1.getUpdatedBy());
        assertEquals(taskEntity.getCreatedDate(),taskEntity1.getCreatedDate());
        assertEquals(taskEntity.getUpdatedDate(),taskEntity1.getUpdatedDate());

    }

    @Test
    void taskEntityToModel(){
        TaskDTO taskDTO1 = taskMapper.taskEntityToModel(taskEntity);
        assertEquals(taskDTO.getTaskTitle(),taskDTO1.getTaskTitle());
        assertEquals(taskDTO.getTaskDesc(),taskDTO1.getTaskDesc());
        assertEquals(taskDTO.getPriority(),taskDTO1.getPriority());
        assertEquals(taskDTO.getTaskStatus(),taskDTO1.getTaskStatus());
        assertEquals(taskDTO.getPlannedStartDate(),taskDTO1.getPlannedStartDate());
        assertEquals(taskDTO.getPlannedCompletionDate(),taskDTO1.getPlannedCompletionDate());
        assertEquals(taskDTO.getActualStartDate(),taskDTO1.getActualStartDate());
        assertEquals(taskDTO.getActualCompletionDate(),taskDTO1.getActualCompletionDate());
        assertEquals(taskDTO.getActiveStatus(),taskDTO1.getActiveStatus());
        assertEquals(taskDTO.getAssigneeId(),taskDTO1.getAssigneeId());
        assertEquals(taskDTO.getAssignerId(),taskDTO1.getAssignerId());
        assertEquals(taskDTO.getCreatedBy(),taskDTO1.getCreatedBy());
        assertEquals(taskDTO.getUpdatedBy(),taskDTO1.getUpdatedBy());
        assertEquals(taskDTO.getCreatedDate(),taskDTO1.getCreatedDate());
        assertEquals(taskDTO.getUpdatedDate(),taskDTO1.getUpdatedDate());

    }

    @Test
    void taskUpdateModeltoEntityTest(){
        TaskEntity taskEntity1 = taskMapper.taskUpdateModelToEntity(taskUpdateDTO,taskEntity);
        assertEquals(taskUpdateDTO.getTaskTitle(),taskEntity1.getTaskTitle());
    }

}
