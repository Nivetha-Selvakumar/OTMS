package com.onlinetaskmanagementsystem.otms.service;


import com.onlinetaskmanagementsystem.otms.DTO.TaskDTO;
import com.onlinetaskmanagementsystem.otms.DTO.TaskHistoryDTO;
import com.onlinetaskmanagementsystem.otms.DTO.TaskUpdateDTO;
import com.onlinetaskmanagementsystem.otms.Exception.CommonException;

import java.util.List;

public interface SubTaskService {
    List<TaskDTO> viewSubList(Integer taskId, Integer userId) throws CommonException;

    Integer addSubTask(TaskDTO taskDTO, Integer taskId) throws CommonException;

    TaskDTO viewUpdatedSubTask(Integer taskId, Integer subTaskId, TaskUpdateDTO taskUpdateDTO) throws CommonException;

    String deleteSubTask(Integer taskId, Integer userId, Integer subTaskId) throws CommonException;

    List<TaskHistoryDTO> viewHistorySubTask(Integer taskId, Integer subTaskId, Integer userId) throws CommonException;


}
