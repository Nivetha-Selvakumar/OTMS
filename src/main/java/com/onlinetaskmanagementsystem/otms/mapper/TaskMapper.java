package com.onlinetaskmanagementsystem.otms.mapper;

import com.onlinetaskmanagementsystem.otms.DTO.TaskDTO;
import com.onlinetaskmanagementsystem.otms.DTO.TaskUpdateDTO;
import com.onlinetaskmanagementsystem.otms.Enum.ActiveStatus;
import com.onlinetaskmanagementsystem.otms.Enum.TaskStatus;
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
        taskEntity.setTaskStatus(TaskStatus.NOT_YET_STARTED);
        taskEntity.setPlannedStartDate(taskDTO.getPlannedStartDate());
        taskEntity.setPlannedCompletionDate(taskDTO.getPlannedCompletionDate());
        taskEntity.setActualStartDate(taskDTO.getActualStartDate());
        taskEntity.setActualCompletionDate(taskDTO.getActualCompletionDate());
        taskEntity.setActiveStatus(ActiveStatus.ACTIVE);
        taskEntity.setAssigneeId(taskDTO.getAssigneeId());
        taskEntity.setAssignerId(taskDTO.getAssignerId());
        taskEntity.setCreatedBy(taskDTO.getAssignerId());
        taskEntity.setUpdatedBy(taskDTO.getUpdatedBy());
        taskEntity.setCreatedDate(taskDTO.getCreatedDate());
        taskEntity.setUpdatedDate(taskDTO.getUpdatedDate());

        return taskEntity;

    }

    public TaskDTO taskEntityToModel(TaskEntity taskEntity) {
        TaskDTO taskDTO=new TaskDTO();
        taskDTO.setUserId(taskEntity.getUserId());
        taskDTO.setTaskTitle(taskEntity.getTaskTitle());
        taskDTO.setTaskDesc(taskEntity.getTaskDesc());
        taskDTO.setPriority(taskEntity.getPriority());
        taskDTO.setTaskStatus(TaskStatus.NOT_YET_STARTED);
        taskDTO.setPlannedStartDate(taskEntity.getPlannedStartDate());
        taskDTO.setPlannedCompletionDate(taskEntity.getPlannedCompletionDate());
        taskDTO.setActualStartDate(taskEntity.getActualStartDate());
        taskDTO.setActualCompletionDate(taskEntity.getActualCompletionDate());
        taskDTO.setActiveStatus(taskEntity.getActiveStatus());
        taskDTO.setAssigneeId(taskEntity.getAssigneeId());
        taskDTO.setAssignerId(taskEntity.getAssignerId());
        taskDTO.setCreatedBy(taskEntity.getAssignerId());
        taskDTO.setUpdatedBy(taskEntity.getUpdatedBy());
        taskDTO.setCreatedDate(taskEntity.getCreatedDate());
        taskDTO.setUpdatedDate(taskEntity.getUpdatedDate());

        return taskDTO;
    }

    public TaskEntity taskUpdateModelToEntity(TaskUpdateDTO taskUpdateDTODTO, TaskEntity taskEntity) {
        taskEntity.setUserId(taskUpdateDTODTO.getUserId());
        taskEntity.setTaskTitle(taskUpdateDTODTO.getTaskTitle());
        taskEntity.setTaskDesc(taskUpdateDTODTO.getTaskDesc());
        taskEntity.setPriority(taskUpdateDTODTO.getPriority());
        taskEntity.setTaskStatus(TaskStatus.valueOf(taskUpdateDTODTO.getTaskStatus()));
        taskEntity.setPlannedStartDate(taskUpdateDTODTO.getPlannedStartDate());
        taskEntity.setPlannedCompletionDate(taskUpdateDTODTO.getPlannedCompletionDate());
        taskEntity.setActualStartDate(taskUpdateDTODTO.getActualStartDate());
        taskEntity.setActualCompletionDate(taskUpdateDTODTO.getActualCompletionDate());
        taskEntity.setActiveStatus(taskUpdateDTODTO.getActiveStatus()!=null?taskUpdateDTODTO.getActiveStatus():taskEntity.getActiveStatus());
        taskEntity.setAssigneeId(taskUpdateDTODTO.getAssigneeId());
        taskEntity.setAssignerId(taskUpdateDTODTO.getAssignerId());
        taskEntity.setCreatedBy(taskUpdateDTODTO.getAssignerId());
        taskEntity.setUpdatedBy(taskUpdateDTODTO.getUpdatedBy());

        return  taskEntity;
    }
}
