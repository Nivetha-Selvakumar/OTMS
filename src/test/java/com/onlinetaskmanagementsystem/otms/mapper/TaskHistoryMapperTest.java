package com.onlinetaskmanagementsystem.otms.mapper;

import com.onlinetaskmanagementsystem.otms.DTO.TaskHistoryDTO;
import com.onlinetaskmanagementsystem.otms.entity.OrganisationEntity;
import com.onlinetaskmanagementsystem.otms.entity.TaskEntity;
import com.onlinetaskmanagementsystem.otms.entity.TaskHistoryEntity;
import com.onlinetaskmanagementsystem.otms.entity.UserEntity;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import java.sql.Timestamp;


@ExtendWith(MockitoExtension .class)
class TaskHistoryMapperTest {
    @InjectMocks
    TaskHistoryMapper taskHistoryMapper;


    TaskHistoryEntity taskHistoryEntity = new TaskHistoryEntity();


    TaskEntity taskEntity = new TaskEntity();

    UserEntity userEntity = new UserEntity();


    TaskHistoryDTO taskHistoryDTO= new TaskHistoryDTO();

    OrganisationEntity organisationEntity = new OrganisationEntity();

    @BeforeEach
    void init(){


        userEntity.setEmpName("nive");

        taskEntity.setId(1);
        taskEntity.setCreatedBy(userEntity);
        taskEntity.setCreatedDate(Timestamp.valueOf("2024-01-02 12:30:40"));
        taskEntity.setTaskDesc("Created for testing");

        taskHistoryDTO.setTaskId(taskEntity.getId());
        taskHistoryDTO.setDescription("Created for testing");
        taskHistoryDTO.setCreatedDate(Timestamp.valueOf("2024-01-02 12:30:40"));

        taskHistoryEntity.setTaskId(taskEntity);
        taskHistoryEntity.setDescription("Created for testing");
        taskHistoryEntity.setCreatedDate(Timestamp.valueOf("2024-01-02 12:30:40"));
        taskHistoryEntity.setCreatedBy(taskEntity.getCreatedBy());
        taskHistoryEntity.setId(1);

    }

    @Test
    void taskHistoryModelToEntityTest1(){
        taskHistoryMapper.taskHistoryModelToEntity(taskEntity,organisationEntity, "Created");

    }
    @Test
    void taskHistoryModelToEntityTest(){
        String expected = "Created for testing";
        TaskHistoryEntity taskHistoryEntity = taskHistoryMapper.taskHistoryModelToEntity(taskEntity, organisationEntity, expected);
        Assertions.assertNotNull(taskHistoryEntity);
        Assertions.assertEquals(taskEntity.getCreatedDate(), taskHistoryEntity.getCreatedDate());
        Assertions.assertEquals(expected, taskHistoryEntity.getDescription());

    }


    @Test
    void taskHistoryEntityToModel(){
        TaskHistoryDTO taskHistoryDTO1=taskHistoryMapper.taskHistoryEntityToModel(taskHistoryEntity);
        Assertions.assertEquals(taskHistoryDTO.getTaskId(),taskHistoryDTO1.getTaskId());
        Assertions.assertEquals(taskHistoryDTO.getDescription(),taskHistoryDTO1.getDescription());
        Assertions.assertEquals(taskHistoryDTO.getCreatedDate(),taskHistoryDTO1.getCreatedDate());
    }


}
