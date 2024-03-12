package com.onlinetaskmanagementsystem.otms.service;

import com.onlinetaskmanagementsystem.otms.DTO.FilterTask;
import com.onlinetaskmanagementsystem.otms.DTO.TaskDTO;
import com.onlinetaskmanagementsystem.otms.DTO.TaskHistoryDTO;
import com.onlinetaskmanagementsystem.otms.DTO.TaskUpdateDTO;
import com.onlinetaskmanagementsystem.otms.Exception.CommonException;
import com.onlinetaskmanagementsystem.otms.Exception.UserNotFoundException;

import java.util.List;

public interface TaskService {
    Integer addTask(Integer userId, TaskDTO taskDTO) throws CommonException;

    

    TaskDTO viewUpdatedTask(Integer taskId, Integer userId, TaskUpdateDTO taskUpdateDTO) throws CommonException;

    String deleteTask(Integer taskId, Integer userId) throws CommonException;

    List<TaskHistoryDTO> viewHistoryTask(Integer taskId,Integer userId) throws  CommonException;


    List<TaskDTO> viewList(Integer userId, FilterTask filterTask) throws UserNotFoundException;
}
