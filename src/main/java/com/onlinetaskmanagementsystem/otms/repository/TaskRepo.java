package com.onlinetaskmanagementsystem.otms.repository;

import com.onlinetaskmanagementsystem.otms.Enum.Status;
import com.onlinetaskmanagementsystem.otms.entity.TaskEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@EnableJpaRepositories
@Repository
public interface TaskRepo extends JpaRepository<TaskEntity,Integer> {


    List<TaskEntity> findByUserId(Integer userId);

    TaskEntity findByTaskTitle(String taskTitle);

    List<TaskEntity> findAllByUserId(Integer userId);

    Optional<TaskEntity> findByIdAndActiveStatus(Integer taskId, Status status);

    Optional<TaskEntity> findByUserIdAndIdAndActiveStatus(Integer userId, Integer taskId, Status status);
}
