package com.onlinetaskmanagementsystem.otms.service.impl;

import com.onlinetaskmanagementsystem.otms.DTO.TaskDTO;
import com.onlinetaskmanagementsystem.otms.Exception.CommonException;
import com.onlinetaskmanagementsystem.otms.Exception.TaskCreationException;
import com.onlinetaskmanagementsystem.otms.entity.TaskEntity;
import com.onlinetaskmanagementsystem.otms.mapper.TaskMapper;
import com.onlinetaskmanagementsystem.otms.repository.TaskRepo;
import com.onlinetaskmanagementsystem.otms.service.Taskservice;
import com.onlinetaskmanagementsystem.otms.validation.Validation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class TaskImpl implements Taskservice {
    @Autowired
    TaskMapper taskMapper;

    @Autowired
    Validation validation;

    @Autowired
    TaskRepo taskRepo;

    @Override
    public Integer addTask(TaskDTO taskDTO) throws CommonException {
        TaskEntity taskEntity = taskMapper.taskModeltoEntity(taskDTO);
        if(validation.taskTitleValidation(taskDTO.getTaskTitle())&& validation.taskUserValidation(taskDTO.getUserId())){
            throw new TaskCreationException("Task already present for this user");
        }else{
            taskEntity=taskRepo.save(taskEntity);
            return taskEntity.getId();
        }
    }
}
