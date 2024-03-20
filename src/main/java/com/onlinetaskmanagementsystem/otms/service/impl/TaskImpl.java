package com.onlinetaskmanagementsystem.otms.service.impl;

import com.onlinetaskmanagementsystem.otms.DTO.FilterTask;
import com.onlinetaskmanagementsystem.otms.DTO.TaskDTO;
import com.onlinetaskmanagementsystem.otms.DTO.TaskHistoryDTO;
import com.onlinetaskmanagementsystem.otms.DTO.TaskUpdateDTO;
import com.onlinetaskmanagementsystem.otms.Enum.ActiveStatus;
import com.onlinetaskmanagementsystem.otms.Enum.TaskIds;
import com.onlinetaskmanagementsystem.otms.Exception.CommonException;
import com.onlinetaskmanagementsystem.otms.Exception.TaskCreationException;
import com.onlinetaskmanagementsystem.otms.Exception.UserNotFoundException;
import com.onlinetaskmanagementsystem.otms.Utils.AppConstant;
import com.onlinetaskmanagementsystem.otms.entity.OrganisationEntity;
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
import java.util.stream.Collectors;

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
    public Integer addTask(Integer userId, TaskDTO taskDTO, String orgRef) throws CommonException {
        //validate user and get user entity
        //optional entity is present okay else throw
        //Then Validate User, Task Title and Task Status same is present okay else throw
        //Then map the entity and save the entity Then flow will continue
//        #### Validating the users

        Map<String, Integer> ids = new HashMap<>();

        ids.put(TaskIds.ASSIGNEEID.toString(), taskDTO.getAssigneeId());
        ids.put(TaskIds.ASSIGNERID.toString(), taskDTO.getAssignerId());
        OrganisationEntity organisationEntity = validation.orgRefValidation(orgRef);
        if(validation.taskUserIdValidation(userId)){
            if(validation.taskUserValidationAndStatus(userId)){
                if (validation.taskTitleValidation(taskDTO.getTaskTitle())&& validation.taskUserIdAndTaskTitleValidation(userId, taskDTO.getTaskTitle())) {
                    throw new TaskCreationException("This task is already present for this user");
                } else {
                    //                    Check if all the given user Ids present in the user table or not

                    TaskEntity taskEntity = new TaskEntity();

                    taskEntity.setOrgId(organisationEntity);

                    taskEntity.setUserId(userRepo.findById(userId).get());

                    UserEntity userEntity = userRepo.findById(userId).orElseThrow(()->new UserNotFoundException("User not found with id"));
                    taskEntity.setCreatedBy(userEntity);
                    taskEntity.setUpdatedBy(userEntity);

                    userEntity = userRepo.findById(taskDTO.getAssigneeId()).orElse(validation.validatedUserIds(ids)) ;
                    taskEntity.setAssigneeId(userEntity);

                    userEntity = userRepo.findById(taskDTO.getAssignerId()).orElse(validation.validatedUserIds(ids));
                    taskEntity.setAssignerId(userEntity);
                    taskEntity = taskMapper.taskModelToEntity(taskDTO,taskEntity);
                    taskEntity = taskRepo.save(taskEntity);
                    TaskHistoryEntity taskHistoryEntity = taskHistoryMapper.taskHistoryModelToEntity(taskEntity, organisationEntity, "Created");
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
    public List<TaskDTO> viewList(Integer userId, FilterTask filterTask, String orgRef) throws CommonException {

        validation.orgRefValidation(orgRef);
        validation.taskViewValidation(userId);
        List<TaskEntity> taskEntities;
        if(!filterTask.getEmpFilterType().equals(AppConstant.ALL)) {

        List<UserEntity> userEntityList = filterTask.getUserId().stream()
                .map(id -> {
                       UserEntity user = new UserEntity();
                        user.setId(id);
                        return user;
                    })
                    .collect(Collectors.toList());

            taskEntities = taskRepo.findAllByUserIdInAndActiveStatus(userEntityList,ActiveStatus.ACTIVE);
        }else{
            taskEntities = taskRepo.findAllByActiveStatus(ActiveStatus.ACTIVE);
        }
        List<TaskDTO> taskDTOList = new ArrayList<>();
        for (TaskEntity taskEntity : taskEntities) {
            taskDTOList.add(taskMapper.taskEntityToModel(taskEntity));
        }
        return taskDTOList;
    }

    @Override
    public TaskDTO viewUpdatedTask(Integer taskId, Integer userId, TaskUpdateDTO taskUpdateDTO,String orgRef) throws CommonException {
        Map<String, Integer> ids = new HashMap<>();

        ids.put(TaskIds.ASSIGNEEID.toString(), taskUpdateDTO.getAssigneeId());
        ids.put(TaskIds.ASSIGNERID.toString(), taskUpdateDTO.getAssignerId());
        OrganisationEntity organisationEntity = validation.orgRefValidation(orgRef);
        TaskEntity taskEntity = validation.taskExistValidation(taskId);
        UserEntity userEntity = userRepo.findById(userId).orElseThrow(()->new UserNotFoundException("User not found with id"));
        taskEntity.setUpdatedBy(userEntity);

        userEntity = userRepo.findById(taskUpdateDTO.getAssigneeId()).orElse(validation.validatedUserIds(ids)) ;
        taskEntity.setAssigneeId(userEntity);

        userEntity = userRepo.findById(taskUpdateDTO.getAssignerId()).orElse(validation.validatedUserIds(ids));
        taskEntity.setAssignerId(userEntity);
        taskEntity=taskRepo.save(taskMapper.taskUpdateModelToEntity(taskUpdateDTO,taskEntity));
        TaskHistoryEntity taskHistoryEntity = taskHistoryMapper.taskHistoryModelToEntity(taskEntity, organisationEntity, taskUpdateDTO.getUpdatedField());
        taskHistoryRepo.save(taskHistoryEntity);
        return taskMapper.taskEntityToModel(taskEntity);
    }

    @Override
    public String deleteTask(Integer taskId, Integer userId,String orgRef) throws CommonException {
        validation.orgRefValidation(orgRef);
        if(validation.taskUserValidation(userId)){
            TaskEntity taskEntity = validation.taskExistValidationByUserIdAndTaskId(taskId, userId);
            taskEntity.setActiveStatus(ActiveStatus.INACTIVE);
            taskRepo.save(taskEntity);
            return "Successfully Inactive!";
       }
        return "No Active task is for particular User";
    }

    @Override
    public List<TaskHistoryDTO> viewHistoryTask(Integer taskId,Integer userId,String orgRef) throws CommonException {
        validation.orgRefValidation(orgRef);
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
