package com.onlinetaskmanagementsystem.otms.repository;

import com.onlinetaskmanagementsystem.otms.Enum.ActiveStatus;
import com.onlinetaskmanagementsystem.otms.entity.TaskEntity;
import com.onlinetaskmanagementsystem.otms.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@EnableJpaRepositories
@Repository
public interface TaskRepo extends JpaRepository<TaskEntity,Integer> {


    List<TaskEntity> findByUserId(UserEntity userId);

    Optional<TaskEntity> findByTaskTitle(String taskTitle);


    //Subtask and task
    Optional<TaskEntity> findByIdAndActiveStatus(Integer taskId, ActiveStatus status);
    

    Optional<TaskEntity> findByTaskTitleAndUserIdAndActiveStatus(String taskTitle, UserEntity userEntity, ActiveStatus activeStatus);

    Optional<TaskEntity> findByUserIdAndIdAndActiveStatus(UserEntity userId, Integer taskId, ActiveStatus activeStatus);


    List<TaskEntity> findAllByAssigneeIdAndPlannedCompletionDateAndActiveStatus(UserEntity user, Date dueDate, ActiveStatus activeStatus);

    // Sub task
    Optional<TaskEntity> findByIdAndUserIdAndActiveStatus(Integer taskId, UserEntity userId, ActiveStatus activeStatus);

    List<TaskEntity> findAllByUserIdAndParentTaskIdAndActiveStatus(UserEntity userEntity, TaskEntity taskEntity, ActiveStatus activeStatus);

    Optional<TaskEntity> findByUserIdAndIdAndParentTaskIdAndActiveStatus(UserEntity userEntity, Integer subTaskId, TaskEntity taskEntity, ActiveStatus activeStatus);


    Optional<TaskEntity> findByIdAndParentTaskIdAndActiveStatus(Integer subTaskId, TaskEntity taskEntity, ActiveStatus activeStatus);


    List<TaskEntity> findAllByUserIdInAndActiveStatus(List<UserEntity> userEntityList, ActiveStatus activeStatus);

    List<TaskEntity> findAllByActiveStatus(ActiveStatus activeStatus);

}
