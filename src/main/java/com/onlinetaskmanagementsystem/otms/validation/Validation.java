package com.onlinetaskmanagementsystem.otms.validation;

import com.onlinetaskmanagementsystem.otms.Enum.Status;
import com.onlinetaskmanagementsystem.otms.Exception.TaskNotFoundException;
import com.onlinetaskmanagementsystem.otms.Exception.UserNotFoundException;
import com.onlinetaskmanagementsystem.otms.entity.TaskEntity;
import com.onlinetaskmanagementsystem.otms.entity.TaskHistoryEntity;
import com.onlinetaskmanagementsystem.otms.entity.UserEntity;
import com.onlinetaskmanagementsystem.otms.repository.TaskHistoryRepo;
import com.onlinetaskmanagementsystem.otms.repository.TaskRepo;
import com.onlinetaskmanagementsystem.otms.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
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
        TaskEntity task = taskRepo.findByTaskTitle(taskTitle);

        if (task != null && task.getTaskTitle().equals(taskTitle)) {
            taskFlag1 = true;
        }
        return taskFlag1;

    }

    public boolean taskUserValidation(Integer userId) {
        boolean taskFlag2 = false;
        List<TaskEntity> taskEntity = taskRepo.findByUserId(userId);
        if (!taskEntity.isEmpty() && taskEntity.get(0).getUserId().equals(userId)) {
            taskFlag2 = true;
        }
        return taskFlag2;
    }


    public void taskViewValidation(Integer userId) throws UserNotFoundException {
        if (userId != null) {
            Optional<UserEntity> userEntity = userRepo.findByIdAndUserStatus(userId, Status.ACTIVE);
            if (userEntity.isEmpty())
                throw new UserNotFoundException("This user is not found");
        }
    }

    public TaskEntity taskExistValidation(Integer taskId) throws TaskNotFoundException {
        if (taskId != null) {
            Optional<TaskEntity> taskEntity = taskRepo.findByIdAndActiveStatus(taskId, Status.ACTIVE);
            if (taskEntity.isEmpty()) {
                throw new TaskNotFoundException("The task is not found");
            } else {
                return taskEntity.get();
            }
        }
        return null;
    }

    public TaskEntity taskExistValidationByUserIdAndTaskId(Integer taskId, Integer userId) throws TaskNotFoundException {
        if (taskId != null) {
            Optional<TaskEntity> taskEntity = taskRepo.findByUserIdAndIdAndActiveStatus(userId, taskId, Status.ACTIVE);
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
            List<TaskHistoryEntity> taskHistoryEntity = taskHistoryRepo.findByTaskId(taskId);
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
}
