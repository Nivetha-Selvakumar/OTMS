package com.onlinetaskmanagementsystem.otms.repository;

import com.onlinetaskmanagementsystem.otms.DTO.TaskHistoryDTO;
import com.onlinetaskmanagementsystem.otms.entity.TaskHistoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@EnableJpaRepositories
@Repository
public interface TaskHistoryRepo extends JpaRepository<TaskHistoryEntity, Integer> {



//    TaskHistoryEntity findByAllTaskId(Integer taskId);
//
//    List<TaskHistoryDTO> findByTaskId(Integer taskId);
}
