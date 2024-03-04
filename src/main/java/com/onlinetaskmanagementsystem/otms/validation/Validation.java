package com.onlinetaskmanagementsystem.otms.validation;

import com.onlinetaskmanagementsystem.otms.Enum.ActiveStatus;
import com.onlinetaskmanagementsystem.otms.Enum.TaskIds;
import com.onlinetaskmanagementsystem.otms.Exception.CommonException;
import com.onlinetaskmanagementsystem.otms.Exception.TaskNotFoundException;
import com.onlinetaskmanagementsystem.otms.Exception.UserNotFoundException;
import com.onlinetaskmanagementsystem.otms.entity.TaskEntity;
import com.onlinetaskmanagementsystem.otms.entity.TaskHistoryEntity;
import com.onlinetaskmanagementsystem.otms.entity.UserEntity;
import com.onlinetaskmanagementsystem.otms.repository.TaskHistoryRepo;
import com.onlinetaskmanagementsystem.otms.repository.TaskRepo;
import com.onlinetaskmanagementsystem.otms.repository.UserRepo;
import jakarta.persistence.criteria.CriteriaBuilder;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Component
public class Validation {

    @Autowired
    UserRepo userRepo;

    @Autowired
    TaskRepo taskRepo;

    @Autowired
    TaskHistoryRepo taskHistoryRepo;

    //check exist email If email exists return true and send error message
    public boolean checkExistEmail(String email) {
        boolean userFlag = false;
        Integer id = userRepo.checkUser(email);
        if (id != null && id > 0) {
            userFlag = true;
        }
        return userFlag;
    }

    public boolean checkExistUser(String username) {
        boolean usernameFlag = false;

        String user = userRepo.checkUserName(username);
        if (user != null) {
            usernameFlag = true;
        }
        return usernameFlag;
    }


    public boolean taskTitleValidation(String taskTitle) {
        boolean taskFlag1 = false;
        Optional<TaskEntity> task = taskRepo.findByTaskTitle(taskTitle);
        if (task.isPresent() && task.get().getTaskTitle().equals(taskTitle)) {
            taskFlag1 = true;
        }
        return taskFlag1;

    }


    public boolean taskUserValidation(Integer userId) {
        boolean taskFlag2 = false;
        List<TaskEntity> taskEntity = taskRepo.findByUserId(userRepo.findById(userId).get());
        if (!taskEntity.isEmpty() && taskEntity.get(0).getUserId().getId().equals(userId)) {
            taskFlag2 = true;
        }
        return taskFlag2;
    }


    public void taskViewValidation(Integer userId) throws UserNotFoundException {
        if (userId != null) {
            Optional<UserEntity> userEntity = userRepo.findByIdAndUserStatus(userId, ActiveStatus.ACTIVE);
            if (userEntity.isEmpty())
                throw new UserNotFoundException("This user is not found");
        }
    }

    public TaskEntity taskExistValidation(Integer taskId) throws TaskNotFoundException {
        if (taskId != null) {
            Optional<TaskEntity> taskEntity = taskRepo.findByIdAndActiveStatus(taskId, ActiveStatus.ACTIVE);
            if (taskEntity.isEmpty()) {
                throw new TaskNotFoundException("The task is not found");
            } else {
                return taskEntity.get();
            }
        }
        return null;
    }

    public boolean taskIdExistValidation(Integer taskId) {
        boolean flag= false;
        Optional<TaskEntity> taskEntity = taskRepo.findById(taskId);
        if (taskEntity.isPresent()){
            flag=true;
        }
        return flag;
    }

    public TaskEntity taskExistValidationByUserIdAndTaskId(Integer taskId, Integer userId) throws TaskNotFoundException {
        boolean taskId1= taskIdExistValidation(taskId);
        boolean userId1= taskUserIdValidation(userId);
        if (taskId != null && (taskId1 && userId1)){
                Optional<TaskEntity> taskEntity = taskRepo.findByUserIdAndIdAndActiveStatus(userRepo.findById(userId).get(),taskId,ActiveStatus.ACTIVE);
                if (taskEntity.isEmpty()) {
                    throw new TaskNotFoundException("The task is not found");
                } else {
                    return taskEntity.get();
                }

        }
        return null;
    }

    public boolean taskHistoryValidation(Integer taskId) {
        boolean taskHistoryFlag = false;
        if(taskId != null){
            List<TaskHistoryEntity> taskHistoryEntity = taskHistoryRepo.findByTaskId(taskRepo.findById(taskId).get());
            if (!taskHistoryEntity.isEmpty() && (taskHistoryEntity.get(0).getTaskId().equals(taskId))) {
                    taskHistoryFlag = true;
                }
        }
        return taskHistoryFlag;
    }

    public boolean taskHistoryUserValidation(Integer userId) {
        boolean taskHistoryFlag2 = false;
        Optional<UserEntity> userEntity = userRepo.findById(userId);
        if (userEntity.isPresent()) {
            taskHistoryFlag2 = true;
        }
        return taskHistoryFlag2;
    }

    public boolean taskUserIdAndTaskTitleValidation(Integer userId, String taskTitle) {
        boolean flag = false;
        Optional<TaskEntity> taskEntity = taskRepo.findByTaskTitleAndUserIdAndActiveStatus(taskTitle,userRepo.findById(userId).get(),ActiveStatus.ACTIVE);
        if(taskEntity.isPresent()){
            flag=true;
        }
        return flag;
    }

    public boolean taskUserValidationAndStatus(Integer userId) {
        boolean flag=false;
       Optional<UserEntity> userEntity = userRepo.findByIdAndUserStatus(userId, ActiveStatus.ACTIVE);
       if (userEntity.isPresent()){
           flag=true;
       }
       return flag;
    }

    public boolean taskUserIdValidation(Integer userId) {
        boolean flag= false;
        Optional<UserEntity> userEntity = userRepo.findById(userId);
        if (userEntity.isPresent()){
            flag=true;
        }
        return flag;
    }

    public UserEntity validatedUserIds(Map<String, Integer> ids) throws CommonException {
        boolean assigneeIdValid = taskUserIdValidation(ids.get(TaskIds.ASSIGNEEID.toString()));
        boolean assignerIdValid = taskUserIdValidation(ids.get(TaskIds.ASSIGNERID.toString()));

        if(!assigneeIdValid&& !assignerIdValid){
            throw new UserNotFoundException("Assigner and Assignee Ids are not valid");
        }else if (!assigneeIdValid){
            throw new UserNotFoundException("Assignee id is not valid");
        }else if (!assignerIdValid){
            throw new UserNotFoundException("Assigner id is not valid");
        }
        return null;
    }
}
