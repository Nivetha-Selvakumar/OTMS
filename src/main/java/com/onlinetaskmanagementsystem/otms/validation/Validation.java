package com.onlinetaskmanagementsystem.otms.validation;

import com.onlinetaskmanagementsystem.otms.Enum.ActiveStatus;
import com.onlinetaskmanagementsystem.otms.Enum.TaskIds;
import com.onlinetaskmanagementsystem.otms.Exception.*;
import com.onlinetaskmanagementsystem.otms.entity.*;
import com.onlinetaskmanagementsystem.otms.repository.*;
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

    @Autowired
    OrganisationRepo organisationRepo;

    @Autowired
    RoleRepo roleRepo;

    //check exist email If email exists return true and send error message
    public boolean checkExistEmail(String email) {
        boolean userFlag = false;
        Optional<UserEntity> userEntity = userRepo.findByEmail(email);
        if (userEntity.isPresent()) {
            userFlag = true;
        }
        return userFlag;
    }

    public boolean checkExistUser(String username) {
        boolean usernameFlag = false;
        Optional<UserEntity> userEntity = userRepo.findByUsername(username);
        if (userEntity.isPresent()) {
            usernameFlag = true;
        }
        return usernameFlag;
    }

    public boolean checkPassword(String email, String password) {
        boolean flag = false;
        UserEntity user = userRepo.findByEmailAndPassword(email, password);
        if (user != null) {
            flag = true;
        }
        return flag;
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
        boolean flag = false;
        Optional<TaskEntity> taskEntity = taskRepo.findById(taskId);
        if (taskEntity.isPresent()) {
            flag = true;
        }
        return flag;
    }

    public TaskEntity taskExistValidationByUserIdAndTaskId(Integer taskId, Integer userId) throws TaskNotFoundException {
        boolean taskId1 = taskIdExistValidation(taskId);
        boolean userId1 = taskUserIdValidation(userId);
        if (taskId != null && (taskId1 && userId1)) {
            Optional<TaskEntity> taskEntity = taskRepo.findByUserIdAndIdAndActiveStatus(userRepo.findById(userId).get(), taskId, ActiveStatus.ACTIVE);
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
        if (taskId != null) {
            List<TaskHistoryEntity> taskHistoryEntity = taskHistoryRepo.findByTaskId(taskRepo.findById(taskId).get());
            if (!taskHistoryEntity.isEmpty() && (taskHistoryEntity.get(0).getTaskId().getId().equals(taskId))) {
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
        Optional<TaskEntity> taskEntity = taskRepo.findByTaskTitleAndUserIdAndActiveStatus(taskTitle, userRepo.findById(userId).get(), ActiveStatus.ACTIVE);
        if (taskEntity.isPresent()) {
            flag = true;
        }
        return flag;
    }

    public boolean taskUserValidationAndStatus(Integer userId) {
        boolean flag = false;
        Optional<UserEntity> userEntity = userRepo.findByIdAndUserStatus(userId, ActiveStatus.ACTIVE);
        if (userEntity.isPresent()) {
            flag = true;
        }
        return flag;
    }

    public boolean taskUserIdValidation(Integer userId) {
        boolean flag = false;
        Optional<UserEntity> userEntity = userRepo.findById(userId);
        if (userEntity.isPresent()) {
            flag = true;
        }
        return flag;
    }

    public UserEntity validatedUserIds(Map<String, Integer> ids) throws CommonException {
        boolean assigneeIdValid = taskUserIdValidation(ids.get(TaskIds.ASSIGNEEID.toString()));
        boolean assignerIdValid = taskUserIdValidation(ids.get(TaskIds.ASSIGNERID.toString()));

        if (!assigneeIdValid && !assignerIdValid) {
            throw new UserNotFoundException("Assigner and Assignee Ids are not valid");
        } else if (!assigneeIdValid) {
            throw new UserNotFoundException("Assignee id is not valid");
        } else if (!assignerIdValid) {
            throw new UserNotFoundException("Assigner id is not valid");
        }
        return null;
    }

    public boolean taskTaskIdAndUserValidationAndStatus(Integer taskId, Integer userId) {
        boolean flag = false;
        Optional<TaskEntity> taskEntity = taskRepo.findByIdAndUserIdAndActiveStatus(taskId, userRepo.findById(userId).get(), ActiveStatus.ACTIVE);
        if (taskEntity.isPresent()) {
            flag = true;
        }
        return flag;
    }

    public void subTaskViewValidation(Integer taskId) throws UserNotFoundException {
        if (taskId != null) {
            Optional<TaskEntity> taskEntity = taskRepo.findByIdAndActiveStatus(taskId, ActiveStatus.ACTIVE);
            if (taskEntity.isEmpty())
                throw new UserNotFoundException("Invalid task id ");
        }
    }

    public TaskEntity subTaskExistValidationByUserIdAndTaskIdAndId(Integer taskId, Integer userId, Integer subTaskId) throws CommonException {
        boolean taskId1 = taskIdExistValidation(taskId);
        boolean userId1 = taskUserIdValidation(userId);
        boolean subTaskId1 = taskIdExistValidation(subTaskId);
        if (subTaskId != null && taskId != null && (taskId1 && userId1 && subTaskId1)) {
            Optional<TaskEntity> taskEntity = taskRepo.findByUserIdAndIdAndParentTaskIdAndActiveStatus(userRepo.findById(userId).get(), subTaskId, taskRepo.findById(taskId).get(), ActiveStatus.ACTIVE);
            if (taskEntity.isEmpty()) {
                throw new TaskNotFoundException("The Subtask is not found");
            } else {
                return taskEntity.get();
            }
        }
        return null;
    }


    public TaskEntity subTaskExistValidation(Integer taskId, Integer subTaskId) throws CommonException {
        if (taskId != null && subTaskId != null) {
            Optional<TaskEntity> taskEntity = taskRepo.findByIdAndParentTaskIdAndActiveStatus(subTaskId, taskRepo.findById(taskId).get(), ActiveStatus.ACTIVE);
            if (taskEntity.isEmpty()) {
                throw new UserNotFoundException("The Subtask is not found ");
            } else {
                return taskEntity.get();
            }
        }
        return null;
    }

    public boolean subTaskHistoryValidation(Integer subTaskId) {
        boolean taskHistoryFlag = false;
        if (subTaskId != null) {
            List<TaskHistoryEntity> taskHistoryEntity = taskHistoryRepo.findByTaskId(taskRepo.findById(subTaskId).get());
            if (!taskHistoryEntity.isEmpty() && (taskHistoryEntity.get(0).getTaskId().getId().equals(subTaskId))) {
                taskHistoryFlag = true;
            }
        }
        return taskHistoryFlag;
    }


    public void subTaskReductionChildCount(Integer taskId) {
        TaskEntity taskEntity = taskRepo.findById(taskId).get();
        if (taskId != null) {
            taskEntity.setChildCount(taskEntity.getChildCount() - 1);
        }
    }

    public OrganisationEntity orgRefValidation(String orgRef) throws CommonException {
        Optional<OrganisationEntity> organisationEntity = organisationRepo.findByOrgRef(orgRef);
        if (organisationEntity.isEmpty()) {
            throw new OrganisationRefNotFoundException("Organisation Reference is not found.");
        } else {
            return organisationEntity.get();
        }
    }


    public RoleEntity userRoleValidation(Integer roleId) throws CommonException {
        if (roleId != null) {
            Optional<RoleEntity> roleEntity = roleRepo.findByIdAndActiveStatus(roleId, ActiveStatus.ACTIVE);
            if (roleEntity.isPresent()) {
                return roleEntity.get();
            } else {
                throw new RoleNotFoundException("Role not found");
            }
        } else {
            Optional<RoleEntity> roleEntity = roleRepo.findByRoleAndActiveStatus("EMPLOYEE", ActiveStatus.ACTIVE);
            if (roleEntity.isPresent()) {
                return roleEntity.get();
            } else {
                throw new RoleNotFoundException("Role not found");
            }
        }
    }
}
