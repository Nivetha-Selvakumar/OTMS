package com.onlinetaskmanagementsystem.otms.mapper;

import com.onlinetaskmanagementsystem.otms.DTO.TaskDTO;
import com.onlinetaskmanagementsystem.otms.DTO.TaskUpdateDTO;
import com.onlinetaskmanagementsystem.otms.Enum.ActiveStatus;
import com.onlinetaskmanagementsystem.otms.Enum.Priority;
import com.onlinetaskmanagementsystem.otms.Enum.TaskStatus;
import com.onlinetaskmanagementsystem.otms.entity.TaskEntity;
import org.springframework.stereotype.Component;

@Component
public class SubTaskMapper {
    public TaskEntity subTaskModelToEntity(TaskDTO taskDTO, TaskEntity taskEntity){

        taskEntity.setTaskTitle(taskDTO.getTaskTitle());
        taskEntity.setTaskDesc(taskDTO.getTaskDesc());
        taskEntity.setPriority(Priority.HIGH);
        taskEntity.setTaskStatus(TaskStatus.NOT_YET_STARTED);
        taskEntity.setPlannedStartDate(taskDTO.getPlannedStartDate());
        taskEntity.setPlannedCompletionDate(taskDTO.getPlannedCompletionDate());
        taskEntity.setActualStartDate(taskDTO.getActualStartDate());
        taskEntity.setActualCompletionDate(taskDTO.getActualCompletionDate());
        taskEntity.setActiveStatus(ActiveStatus.ACTIVE);
        taskEntity.setCreatedDate(taskDTO.getCreatedDate());
        taskEntity.setUpdatedDate(taskDTO.getUpdatedDate());

        return taskEntity;

    }

    public TaskDTO subTaskEntityToModel(TaskEntity taskEntity) {
        TaskDTO taskDTO=new TaskDTO();
        taskDTO.setUserId(taskEntity.getUserId().getId());
        taskDTO.setTaskTitle(taskEntity.getTaskTitle());
        taskDTO.setTaskDesc(taskEntity.getTaskDesc());
        taskDTO.setPriority(taskEntity.getPriority());
        taskDTO.setTaskStatus(taskEntity.getTaskStatus());
        taskDTO.setPlannedStartDate(taskEntity.getPlannedStartDate());
        taskDTO.setPlannedCompletionDate(taskEntity.getPlannedCompletionDate());
        taskDTO.setActualStartDate(taskEntity.getActualStartDate());
        taskDTO.setActualCompletionDate(taskEntity.getActualCompletionDate());
        taskDTO.setActiveStatus(taskEntity.getActiveStatus());
        taskDTO.setAssigneeId(taskEntity.getAssigneeId().getId());
        taskDTO.setAssignerId(taskEntity.getAssignerId().getId());
        taskDTO.setCreatedBy(taskEntity.getCreatedBy().getId());
        taskDTO.setUpdatedBy(taskEntity.getUpdatedBy().getId());
        taskDTO.setCreatedDate(taskEntity.getCreatedDate());
        taskDTO.setUpdatedDate(taskEntity.getUpdatedDate());
        taskDTO.setParentTaskId(taskEntity.getParentTaskId()!=null? taskEntity.getParentTaskId().getId() : null);
        taskDTO.setChildCount(taskEntity.getChildCount());

        return taskDTO;
    }

    public TaskEntity subTaskUpdateModelToEntity(TaskUpdateDTO taskUpdateDTO, TaskEntity taskEntity) {
        taskEntity.setTaskTitle(taskUpdateDTO.getTaskTitle());
        taskEntity.setTaskDesc(taskUpdateDTO.getTaskDesc());
        taskEntity.setPriority(Priority.valueOf(taskUpdateDTO.getPriority()));
        taskEntity.setTaskStatus(TaskStatus.valueOf(taskUpdateDTO.getTaskStatus()));
        taskEntity.setPlannedStartDate(taskUpdateDTO.getPlannedStartDate());
        taskEntity.setPlannedCompletionDate(taskUpdateDTO.getPlannedCompletionDate());
        taskEntity.setActualStartDate(taskUpdateDTO.getActualStartDate());
        taskEntity.setActualCompletionDate(taskUpdateDTO.getActualCompletionDate());
        taskEntity.setActiveStatus(taskUpdateDTO.getActiveStatus()!=null?taskUpdateDTO.getActiveStatus():taskEntity.getActiveStatus());

        return  taskEntity;
    }
}
