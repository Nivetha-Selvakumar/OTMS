package com.onlinetaskmanagementsystem.otms.service.impl;

import com.onlinetaskmanagementsystem.otms.DTO.TaskDTO;
import com.onlinetaskmanagementsystem.otms.DTO.TaskHistoryDTO;
import com.onlinetaskmanagementsystem.otms.DTO.TaskUpdateDTO;
import com.onlinetaskmanagementsystem.otms.Enum.ActiveStatus;
import com.onlinetaskmanagementsystem.otms.Enum.TaskIds;
import com.onlinetaskmanagementsystem.otms.Exception.CommonException;
import com.onlinetaskmanagementsystem.otms.Exception.TaskCreationException;
import com.onlinetaskmanagementsystem.otms.Exception.TaskNotFoundException;
import com.onlinetaskmanagementsystem.otms.Exception.UserNotFoundException;
import com.onlinetaskmanagementsystem.otms.entity.TaskEntity;
import com.onlinetaskmanagementsystem.otms.entity.TaskHistoryEntity;
import com.onlinetaskmanagementsystem.otms.entity.UserEntity;
import com.onlinetaskmanagementsystem.otms.mapper.TaskHistoryMapper;
import com.onlinetaskmanagementsystem.otms.mapper.TaskMapper;
import com.onlinetaskmanagementsystem.otms.repository.TaskHistoryRepo;
import com.onlinetaskmanagementsystem.otms.repository.TaskRepo;
import com.onlinetaskmanagementsystem.otms.repository.UserRepo;
import com.onlinetaskmanagementsystem.otms.service.TaskService;
import com.onlinetaskmanagementsystem.otms.validation.Validation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

//@Component
@Service
public class TaskImpl implements TaskService {
    @Autowired
    TaskMapper taskMapper;

    @Autowired
    Validation validation;

    @Autowired
    TaskRepo taskRepo;

    @Autowired
    UserRepo userRepo;

    @Autowired
    TaskHistoryMapper taskHistoryMapper;

    @Autowired
    TaskHistoryRepo taskHistoryRepo;



    @Override
    public Integer addTask(TaskDTO taskDTO) throws CommonException {
        //validate user and get user entity
        //optional entity is present okay else throw
        //Then Validate User, Task Title and Task Status same is present okay else throw
        //Then map the entity and save the entity Then flow will continue
//        #### Validating the users

        Map<String, Integer> ids = new HashMap<>();

        ids.put(TaskIds.ASSIGNEEID.toString(), taskDTO.getAssigneeId());
        ids.put(TaskIds.ASSIGNERID.toString(), taskDTO.getAssignerId());

        if(validation.taskUserIdValidation(taskDTO.getUserId())){
            if(validation.taskUserValidationAndStatus(taskDTO.getUserId())){
                if (validation.taskTitleValidation(taskDTO.getTaskTitle())&& validation.taskUserIdAndTaskTitleValidation(taskDTO.getUserId(), taskDTO.getTaskTitle())) {
                    throw new TaskCreationException("This task is already present for this user");
                } else {
                    //                    Check if all the given user Ids present in the user table or not

                    TaskEntity taskEntity = new TaskEntity();

                    taskEntity.setUserId(userRepo.findById(taskDTO.getUserId()).get());

                    UserEntity userEntity = userRepo.findById(taskDTO.getUserId()).orElseThrow(()->new UserNotFoundException("User not found with id"));
                    taskEntity.setCreatedBy(userEntity);
                    taskEntity.setUpdatedBy(userEntity);

                    userEntity = userRepo.findById(taskDTO.getAssigneeId()).orElse(validation.validatedUserIds(ids)) ;
                    taskEntity.setAssigneeId(userEntity);

                    userEntity = userRepo.findById(taskDTO.getAssignerId()).orElse(validation.validatedUserIds(ids));
                    taskEntity.setAssignerId(userEntity);
                    taskEntity = taskMapper.taskModelToEntity(taskDTO,taskEntity);
                    taskEntity = taskRepo.save(taskEntity);
                    TaskHistoryEntity taskHistoryEntity = taskHistoryMapper.taskHistoryModelToEntity(taskEntity, "Created");
                    taskHistoryRepo.save(taskHistoryEntity);
                    return taskEntity.getId();
                }
            }else{
                throw  new UserNotFoundException("User is not active");
            }
        }else{
            throw new UserNotFoundException("No User is Found");
        }
    }


    @Override
    public List<TaskDTO> viewList(Integer userId) throws CommonException{

        validation.taskViewValidation(userId);

        List<TaskEntity> taskEntities= taskRepo.findAllByUserId(userRepo.findById(userId).get());
        List<TaskDTO> taskDTOList = new ArrayList<>();
            for (TaskEntity taskEntity : taskEntities) {
                taskDTOList.add(taskMapper.taskEntityToModel(taskEntity));
            }
            return taskDTOList;
    }

    @Override
    public TaskDTO viewUpdatedTask(Integer taskId, TaskUpdateDTO taskUpdateDTO) throws CommonException {
        Map<String, Integer> ids = new HashMap<>();

        ids.put(TaskIds.ASSIGNEEID.toString(), taskUpdateDTO.getAssigneeId());
        ids.put(TaskIds.ASSIGNERID.toString(), taskUpdateDTO.getAssignerId());
        TaskEntity taskEntity = validation.taskExistValidation(taskId);
        UserEntity userEntity = userRepo.findById(taskUpdateDTO.getUserId()).orElseThrow(()->new UserNotFoundException("User not found with id"));
        taskEntity.setUpdatedBy(userEntity);

        userEntity = userRepo.findById(taskUpdateDTO.getAssigneeId()).orElse(validation.validatedUserIds(ids)) ;
        taskEntity.setAssigneeId(userEntity);

        userEntity = userRepo.findById(taskUpdateDTO.getAssignerId()).orElse(validation.validatedUserIds(ids));
        taskEntity.setAssignerId(userEntity);
        taskEntity=taskRepo.save(taskMapper.taskUpdateModelToEntity(taskUpdateDTO,taskEntity));
        TaskHistoryEntity taskHistoryEntity = taskHistoryMapper.taskHistoryModelToEntity(taskEntity, taskUpdateDTO.getUpdatedField());
        taskHistoryRepo.save(taskHistoryEntity);
        return taskMapper.taskEntityToModel(taskEntity);
    }

    @Override
    public String deleteTask(Integer taskId, Integer userId) throws TaskNotFoundException {
        if(validation.taskUserValidation(userId)){
            TaskEntity taskEntity = validation.taskExistValidationByUserIdAndTaskId(taskId, userId);
            taskEntity.setActiveStatus(ActiveStatus.INACTIVE);
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
                List<TaskHistoryEntity> taskHistoryEntities = taskHistoryRepo.findAllByTaskId(taskRepo.findById(taskId).get());
                for (TaskHistoryEntity taskHistoryEntity : taskHistoryEntities) {
                    taskHistoryDTOList.add(taskHistoryMapper.taskHistoryEntityToModel(taskHistoryEntity));
                }
            }
            return taskHistoryDTOList;
        } else throw new UserNotFoundException("The user is not found");
    }
}
