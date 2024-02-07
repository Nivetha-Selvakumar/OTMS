package com.onlinetaskmanagementsystem.otms.repository;

import com.onlinetaskmanagementsystem.otms.entity.TaskHistoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

import java.util.List;


@EnableJpaRepositories
@Repository
public interface TaskHistoryRepo extends JpaRepository<TaskHistoryEntity, Integer> {

    List<TaskHistoryEntity> findByTaskId(Integer taskId);


    List<TaskHistoryEntity> findAllByTaskId(Integer taskId);
}
