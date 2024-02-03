package com.onlinetaskmanagementsystem.otms.repository;

import com.onlinetaskmanagementsystem.otms.entity.TaskEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

@EnableJpaRepositories
@Repository
public interface TaskRepo extends JpaRepository<TaskEntity,Integer> {


    TaskEntity findByUserId(Integer userId);

    TaskEntity findByTaskTitle(String taskTitle);
}
