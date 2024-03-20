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
import com.onlinetaskmanagementsystem.otms.entity.OrganisationEntity;
import com.onlinetaskmanagementsystem.otms.entity.TaskEntity;
import com.onlinetaskmanagementsystem.otms.entity.TaskHistoryEntity;
import com.onlinetaskmanagementsystem.otms.entity.UserEntity;
import com.onlinetaskmanagementsystem.otms.mapper.SubTaskMapper;
import com.onlinetaskmanagementsystem.otms.mapper.TaskHistoryMapper;
import com.onlinetaskmanagementsystem.otms.mapper.TaskMapper;
import com.onlinetaskmanagementsystem.otms.repository.TaskHistoryRepo;
import com.onlinetaskmanagementsystem.otms.repository.TaskRepo;
import com.onlinetaskmanagementsystem.otms.repository.UserRepo;
import com.onlinetaskmanagementsystem.otms.service.SubTaskService;
import com.onlinetaskmanagementsystem.otms.validation.Validation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class SubTaskImpl implements SubTaskService {
    @Autowired
    SubTaskMapper subTaskMapper;

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
    public List<TaskDTO> viewSubList(Integer taskId, Integer userId, String orgRef) throws CommonException {
        validation.orgRefValidation(orgRef);
        validation.taskViewValidation(userId);
        validation.subTaskViewValidation(taskId);
        List<TaskEntity> taskEntities= taskRepo.findAllByUserIdAndParentTaskIdAndActiveStatus(userRepo.findById(userId).get(),taskRepo.findById(taskId).get(),ActiveStatus.ACTIVE);
        List<TaskDTO> taskDTOList = new ArrayList<>();
        for (TaskEntity taskEntity : taskEntities) {
            taskDTOList.add(subTaskMapper.subTaskEntityToModel(taskEntity));
        }
        return taskDTOList;
    }

    @Override
    public Integer addSubTask(TaskDTO taskDTO,Integer taskId, String orgRef) throws CommonException {

        Map<String, Integer> ids = new HashMap<>();

        ids.put(TaskIds.ASSIGNEEID.toString(), taskDTO.getAssigneeId());
        ids.put(TaskIds.ASSIGNERID.toString(), taskDTO.getAssignerId());

        OrganisationEntity organisationEntity = validation.orgRefValidation(orgRef);
        if(validation.taskUserIdValidation(taskDTO.getUserId())){
            if(validation.taskUserValidationAndStatus(taskDTO.getUserId())){
                if(validation.taskTaskIdAndUserValidationAndStatus(taskId,taskDTO.getUserId())){
                    if (validation.taskTitleValidation(taskDTO.getTaskTitle())&& validation.taskUserIdAndTaskTitleValidation(taskDTO.getUserId(), taskDTO.getTaskTitle())) {
                        throw new TaskCreationException("This task is already present for this user");
                    } else {
                        // Setting child count to the parent task

                        TaskEntity parentTaskEntity = taskRepo.findById(taskId).orElseThrow(() -> new TaskNotFoundException("Task Not found"));
                        if(parentTaskEntity.getChildCount()==null){
                            parentTaskEntity.setChildCount(1);
                        } else{
                            parentTaskEntity.setChildCount(parentTaskEntity.getChildCount()+1);
                        }

                        // Validating the user id
                        TaskEntity taskEntity = new TaskEntity();
                        taskEntity.setUserId(userRepo.findById(taskDTO.getUserId()).get());

                        taskEntity.setOrgId(organisationEntity);
                        // Validating Created by and updated by
                        UserEntity userEntity = userRepo.findById(taskDTO.getUserId()).orElseThrow(()->new UserNotFoundException("User not found with id"));
                        taskEntity.setCreatedBy(userEntity);
                        taskEntity.setUpdatedBy(userEntity);

                        // Validating Assignee
                        userEntity = userRepo.findById(taskDTO.getAssigneeId()).orElse(validation.validatedUserIds(ids)) ;
                        taskEntity.setAssigneeId(userEntity);

                        // Validating Assigner
                        userEntity = userRepo.findById(taskDTO.getAssignerId()).orElse(validation.validatedUserIds(ids));
                        taskEntity.setAssignerId(userEntity);

                        // Setting Parent task id to child task
                        taskEntity.setParentTaskId(parentTaskEntity);

                        // Mapping all the elements
                        taskEntity = subTaskMapper.subTaskModelToEntity(taskDTO,taskEntity);
                        taskEntity = taskRepo.save(taskEntity);
                        TaskHistoryEntity taskHistoryEntity = taskHistoryMapper.taskHistoryModelToEntity(taskEntity, organisationEntity, "Created");
                        taskHistoryRepo.save(taskHistoryEntity);
                        return taskEntity.getId();
                    }
                }else{
                    throw  new TaskNotFoundException("Task not found or inactive task");
                }
            }else{
                throw new UserNotFoundException("User is not active");
            }
        }else{
            throw new UserNotFoundException("No User is Found");
        }
    }

    @Override
    public TaskDTO viewUpdatedSubTask(Integer taskId, Integer subTaskId, TaskUpdateDTO taskUpdateDTO, String orgRef) throws CommonException{
        Map<String, Integer> ids = new HashMap<>();

        ids.put(TaskIds.ASSIGNEEID.toString(), taskUpdateDTO.getAssigneeId());
        ids.put(TaskIds.ASSIGNERID.toString(), taskUpdateDTO.getAssignerId());

        OrganisationEntity organisationEntity = validation.orgRefValidation(orgRef);
        TaskEntity taskEntity = validation.subTaskExistValidation(taskId,subTaskId);
        UserEntity userEntity = userRepo.findById(taskUpdateDTO.getUserId()).orElseThrow(()->new UserNotFoundException("User not found with id"));
        taskEntity.setUpdatedBy(userEntity);

        userEntity = userRepo.findById(taskUpdateDTO.getAssigneeId()).orElse(validation.validatedUserIds(ids)) ;
        taskEntity.setAssigneeId(userEntity);

        userEntity = userRepo.findById(taskUpdateDTO.getAssignerId()).orElse(validation.validatedUserIds(ids));
        taskEntity.setAssignerId(userEntity);
        taskEntity=taskRepo.save(subTaskMapper.subTaskUpdateModelToEntity(taskUpdateDTO,taskEntity));
        TaskHistoryEntity taskHistoryEntity = taskHistoryMapper.taskHistoryModelToEntity(taskEntity,organisationEntity, taskUpdateDTO.getUpdatedField());
        taskHistoryRepo.save(taskHistoryEntity);
        return subTaskMapper.subTaskEntityToModel(taskEntity);

    }

    @Override
    public String deleteSubTask(Integer taskId, Integer userId, Integer subTaskId,String orgRef)throws CommonException {
        validation.orgRefValidation(orgRef);
        if(validation.taskUserValidation(userId)){
            TaskEntity taskEntity = validation.subTaskExistValidationByUserIdAndTaskIdAndId(taskId,userId,subTaskId);
            taskEntity.setActiveStatus(ActiveStatus.INACTIVE);
            validation.subTaskReductionChildCount(taskId);
            taskRepo.save(taskEntity);
            return "Successfully Inactive!";
        }
        return "No Active task is for particular User";
    }

    @Override
    public List<TaskHistoryDTO> viewHistorySubTask(Integer taskId, Integer subTaskId, Integer userId,String orgRef) throws CommonException {
        validation.orgRefValidation(orgRef);
        List<TaskHistoryDTO> taskHistoryDTOList = new ArrayList<>();
        if (validation.taskHistoryUserValidation(userId) && validation.taskHistoryValidation(taskId)) {
            if (validation.subTaskHistoryValidation(subTaskId)) {
                List<TaskHistoryEntity> taskHistoryEntities = taskHistoryRepo.findAllByTaskId(taskRepo.findById(subTaskId).get());
                for (TaskHistoryEntity taskHistoryEntity : taskHistoryEntities) {
                    taskHistoryDTOList.add(taskHistoryMapper.taskHistoryEntityToModel(taskHistoryEntity));
                }
            }
            return taskHistoryDTOList;
        } else throw new UserNotFoundException("The user is not found");
    }
}
