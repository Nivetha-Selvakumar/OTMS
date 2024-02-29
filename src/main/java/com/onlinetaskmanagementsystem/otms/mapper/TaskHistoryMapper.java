package com.onlinetaskmanagementsystem.otms.mapper;

import com.onlinetaskmanagementsystem.otms.DTO.TaskHistoryDTO;
import com.onlinetaskmanagementsystem.otms.entity.TaskEntity;
import com.onlinetaskmanagementsystem.otms.entity.TaskHistoryEntity;
import org.springframework.stereotype.Component;

@Component
public class TaskHistoryMapper {
    public TaskHistoryEntity taskHistoryModelToEntity(TaskEntity taskEntity, String desc){
        TaskHistoryEntity taskHistoryEntity= new TaskHistoryEntity();
        taskHistoryEntity.setTaskId(taskEntity);
        taskHistoryEntity.setCreatedDate(taskEntity.getCreatedDate());
        taskHistoryEntity.setDescription(desc);
        taskHistoryEntity.setCreatedBy(taskEntity.getCreatedBy());
        return taskHistoryEntity;
    }

    public TaskHistoryDTO taskHistoryEntityToModel(TaskHistoryEntity taskHistoryEntity){
        TaskHistoryDTO taskHistoryDTO = new TaskHistoryDTO();
        taskHistoryDTO.setTaskId(taskHistoryEntity.getTaskId().getId());
        taskHistoryDTO.setDescription(taskHistoryEntity.getDescription());
        taskHistoryDTO.setCreatedBy(taskHistoryEntity.getCreatedBy().getId());
        taskHistoryDTO.setCreatedDate(taskHistoryEntity.getCreatedDate());

        return  taskHistoryDTO;
    }
}
