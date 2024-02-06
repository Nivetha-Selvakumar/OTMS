package com.onlinetaskmanagementsystem.otms.service;

import com.onlinetaskmanagementsystem.otms.DTO.TaskDTO;
import com.onlinetaskmanagementsystem.otms.Enum.Status;
import com.onlinetaskmanagementsystem.otms.Exception.CommonException;

import java.util.List;

public interface Taskservice {
    Integer addTask(TaskDTO taskDTO) throws CommonException;

    List<TaskDTO> viewList(Integer id) throws CommonException;


    TaskDTO viewUpdatedList(Integer taskId, TaskDTO taskDTO) throws CommonException;

    String deleteTask(Integer taskId, Integer userId) throws CommonException;
}
