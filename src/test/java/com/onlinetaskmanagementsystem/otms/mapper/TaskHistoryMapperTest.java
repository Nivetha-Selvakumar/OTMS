package com.onlinetaskmanagementsystem.otms.mapper;

import com.onlinetaskmanagementsystem.otms.DTO.TaskHistoryDTO;
import com.onlinetaskmanagementsystem.otms.entity.TaskEntity;
import com.onlinetaskmanagementsystem.otms.entity.TaskHistoryEntity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.sql.Timestamp;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@ExtendWith(MockitoExtension .class)
class TaskHistoryMapperTest {
    @InjectMocks
    TaskHistoryMapper taskHistoryMapper;
    TaskHistoryEntity taskHistoryEntity = new TaskHistoryEntity();

    TaskEntity taskEntity = new TaskEntity();

    TaskHistoryDTO taskHistoryDTO= new TaskHistoryDTO();

    @BeforeEach
    void init(){
        taskHistoryEntity.setTaskId(1);
        taskHistoryEntity.setDescription("Created for testing");
        taskHistoryEntity.setCreatedDate(Timestamp.valueOf("2024-01-02 12:30:40"));
        taskHistoryEntity.setCreatedBy(1);
        taskHistoryEntity.setId(1);

        taskEntity.setId(1);
        taskEntity.setCreatedBy(1);
        taskEntity.setCreatedDate(Timestamp.valueOf("2024-01-02 12:30:40"));
        taskEntity.setTaskDesc("Created for testing");

        taskHistoryDTO.setTaskId(1);
        taskHistoryDTO.setDescription("Created for testing");
        taskHistoryDTO.setCreatedBy(1);
        taskHistoryDTO.setCreatedDate(Timestamp.valueOf("2024-01-02 12:30:40"));

    }

    @Test
    void taskHistoryModelToEntityTest1(){
        taskHistoryMapper.taskHistoryModelToEntity(taskEntity,"Created");
    }
    @Test
    void taskHistoryModelToEntityTest(){
        String expected = "Created for testing";
        TaskHistoryEntity taskHistoryEntity = taskHistoryMapper.taskHistoryModelToEntity(taskEntity, expected);
        assertNotNull(taskHistoryEntity);
        assertEquals(taskEntity.getId(), taskHistoryEntity.getTaskId());
        assertEquals(taskEntity.getCreatedBy(), taskHistoryEntity.getCreatedBy());
        assertEquals(taskEntity.getCreatedDate(), taskHistoryEntity.getCreatedDate());
        assertEquals(expected, taskHistoryEntity.getDescription());

    }

    @Test
    void taskHistoryEntityToModel(){
        TaskHistoryDTO taskHistoryDTO1=taskHistoryMapper.taskHistoryEntityToModel(taskHistoryEntity);
        assertEquals(taskHistoryDTO.getTaskId(),taskHistoryDTO1.getTaskId());
        assertEquals(taskHistoryDTO.getDescription(),taskHistoryDTO1.getDescription());
        assertEquals(taskHistoryDTO.getCreatedDate(),taskHistoryDTO1.getCreatedDate());
        assertEquals(taskHistoryDTO.getCreatedBy(),taskHistoryDTO1.getCreatedBy());
    }


}
