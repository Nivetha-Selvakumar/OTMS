package com.onlinetaskmanagementsystem.otms.service.impl;

import com.onlinetaskmanagementsystem.otms.DTO.TaskDTO;
import com.onlinetaskmanagementsystem.otms.DTO.TaskHistoryDTO;
import com.onlinetaskmanagementsystem.otms.DTO.TaskUpdateDTO;
import com.onlinetaskmanagementsystem.otms.Enum.Status;
import com.onlinetaskmanagementsystem.otms.Exception.CommonException;
import com.onlinetaskmanagementsystem.otms.Exception.TaskCreationException;
import com.onlinetaskmanagementsystem.otms.Exception.TaskNotFoundException;
import com.onlinetaskmanagementsystem.otms.Exception.UserNotFoundException;
import com.onlinetaskmanagementsystem.otms.entity.TaskEntity;
import com.onlinetaskmanagementsystem.otms.entity.TaskHistoryEntity;
import com.onlinetaskmanagementsystem.otms.mapper.TaskHistoryMapper;
import com.onlinetaskmanagementsystem.otms.mapper.TaskMapper;
import com.onlinetaskmanagementsystem.otms.repository.TaskHistoryRepo;
import com.onlinetaskmanagementsystem.otms.repository.TaskRepo;
import com.onlinetaskmanagementsystem.otms.service.TaskService;
import com.onlinetaskmanagementsystem.otms.validation.Validation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class TaskImpl implements TaskService {
    @Autowired
    TaskMapper taskMapper;

    @Autowired
    Validation validation;

    @Autowired
    TaskRepo taskRepo;

    @Autowired
    TaskHistoryMapper taskHistoryMapper;

    @Autowired
    TaskHistoryRepo taskHistoryRepo;

    @Override
    public Integer addTask(TaskDTO taskDTO) throws CommonException {
        TaskEntity taskEntity = taskMapper.taskModeltoEntity(taskDTO);
        if(validation.taskTitleValidation(taskDTO.getTaskTitle())&& validation.taskUserValidation(taskDTO.getUserId())){
            throw new TaskCreationException("This task is already present for this user");
        }else{
            taskEntity=taskRepo.save(taskEntity);
            TaskHistoryEntity taskHistoryEntity = taskHistoryMapper.taskHistoryModelToEntity(taskEntity, "Created");
            taskHistoryRepo.save(taskHistoryEntity);
            return taskEntity.getId();
        }
    }
    @Override
    public List<TaskDTO> viewList(Integer userId) throws CommonException{
        validation.taskViewValidation(userId);
        List<TaskEntity> taskEntities= taskRepo.findAllByUserId(userId);
        List<TaskDTO> taskDTOList = new ArrayList<>();
            for (TaskEntity taskEntity : taskEntities) {
                taskDTOList.add(taskMapper.taskEntityToModel(taskEntity));
            }
            return taskDTOList;
    }

    @Override
    public TaskDTO viewUpdatedTask(Integer taskId, TaskUpdateDTO taskUpdateDTO) throws CommonException {
        TaskEntity taskEntity = validation.taskExistValidation(taskId);
        taskEntity=taskRepo.save(taskMapper.taskUpdateModelToEntity(taskUpdateDTO,taskEntity));
        TaskHistoryEntity taskHistoryEntity = taskHistoryMapper.taskHistoryModelToEntity(taskEntity, taskUpdateDTO.getUpdatedField());
        taskHistoryRepo.save(taskHistoryEntity);
        return taskMapper.taskEntityToModel(taskEntity);
    }

    @Override
    public String deleteTask(Integer taskId, Integer userId) throws TaskNotFoundException {
        if(validation.taskUserValidation(userId)){
            TaskEntity taskEntity = validation.taskExistValidationByUserIdAndTaskId(taskId, userId);
            taskEntity.setActiveStatus(Status.INACTIVE);
            taskRepo.save(taskEntity);
            return "Successfully Inactive!";
       }
        return "No Active task is for particular User";
    }

    @Override
    public List<TaskHistoryDTO> viewHistoryTask(Integer taskId,Integer userId) throws CommonException {
        List<TaskHistoryDTO> taskHistoryDTOList = new ArrayList<>();
        if (validation.taskHistoryUserValidation(userId)) {
            if (validation.taskHistoryValidation(taskId)) {
                List<TaskHistoryEntity> taskHistoryEntities = taskHistoryRepo.findAllByTaskId(taskId);
                for (TaskHistoryEntity taskHistoryEntity : taskHistoryEntities) {
                    taskHistoryDTOList.add(taskHistoryMapper.taskHistoryEntityToModel(taskHistoryEntity));
                }
            }
            return taskHistoryDTOList;
        } else throw new UserNotFoundException("The user is not found");
    }
}
