package com.onlinetaskmanagementsystem.otms.repository;

import com.onlinetaskmanagementsystem.otms.Enum.ActiveStatus;
import com.onlinetaskmanagementsystem.otms.entity.TaskEntity;
import com.onlinetaskmanagementsystem.otms.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@EnableJpaRepositories
@Repository
public interface TaskRepo extends JpaRepository<TaskEntity,Integer> {


    List<TaskEntity> findByUserId(UserEntity userId);

    Optional<TaskEntity> findByTaskTitle(String taskTitle);

    List<TaskEntity> findAllByUserId(UserEntity userId);

    Optional<TaskEntity> findByIdAndActiveStatus(Integer taskId, ActiveStatus status);

    Optional<TaskEntity> findByUserIdAndIdAndActiveStatus(UserEntity userId, TaskEntity taskId, ActiveStatus status);

    Optional<TaskEntity> findByTaskTitleAndUserIdAndActiveStatus(String taskTitle, UserEntity userEntity, ActiveStatus activeStatus);
}
