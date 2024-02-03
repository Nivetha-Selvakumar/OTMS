package com.onlinetaskmanagementsystem.otms.mapper;

import com.onlinetaskmanagementsystem.otms.DTO.TaskDTO;
import com.onlinetaskmanagementsystem.otms.Enum.Status;
import com.onlinetaskmanagementsystem.otms.entity.TaskEntity;
import org.springframework.stereotype.Component;

@Component
public class TaskMapper {
    public TaskEntity taskModeltoEntity(TaskDTO taskDTO){
        TaskEntity taskEntity = new TaskEntity();
        taskEntity.setUserId(taskDTO.getUserId());
        taskEntity.setTaskTitle(taskDTO.getTaskTitle());
        taskEntity.setTaskDesc(taskDTO.getTaskDesc());
        taskEntity.setPriority(taskDTO.getPriority());
        taskEntity.setTaskStatus(taskDTO.getTaskStatus());
        taskEntity.setPlannedStartDate(taskDTO.getPlannedStartDate());
        taskEntity.setPlannedCompletionDate(taskDTO.getPlannedCompletionDate());
        taskEntity.setActualStartDate(taskDTO.getActualStartDate());
        taskEntity.setActualCompletionDate(taskDTO.getActualCompletionDate());
        taskEntity.setActiveStatus(Status.ACTIVE);
        taskEntity.setAssigneeId(taskDTO.getAssigneeId());
        taskEntity.setAssignerId(taskDTO.getAssignerId());
        taskEntity.setCreatedBy(taskDTO.getAssignerId());
        taskEntity.setUpdatedBy(taskDTO.getUpdatedBy());
        taskEntity.setCreatedDate(taskDTO.getCreatedDate());
        taskEntity.setUpdatedDate(taskDTO.getUpdatedDate());

        return taskEntity;

    }
}
