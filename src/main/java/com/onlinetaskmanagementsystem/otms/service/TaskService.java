package com.onlinetaskmanagementsystem.otms.service;

import com.onlinetaskmanagementsystem.otms.DTO.FilterTask;
import com.onlinetaskmanagementsystem.otms.DTO.TaskDTO;
import com.onlinetaskmanagementsystem.otms.DTO.TaskHistoryDTO;
import com.onlinetaskmanagementsystem.otms.DTO.TaskUpdateDTO;
import com.onlinetaskmanagementsystem.otms.Exception.CommonException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface TaskService {
    Integer addTask(Integer userId, TaskDTO taskDTO, String orgRef) throws CommonException;

    TaskDTO viewUpdatedTask(Integer taskId, Integer userId, TaskUpdateDTO taskUpdateDTO, String orgRef) throws CommonException;

    String deleteTask(Integer taskId, Integer userId, String orgRef) throws CommonException;

    List<TaskHistoryDTO> viewHistoryTask(Integer taskId,Integer userId,String orgRef) throws  CommonException;


    List<TaskDTO> viewList(Integer userId, FilterTask filterTask, String orgRef) throws CommonException;
}
