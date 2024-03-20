package com.onlinetaskmanagementsystem.otms.service;


import com.onlinetaskmanagementsystem.otms.DTO.TaskDTO;
import com.onlinetaskmanagementsystem.otms.DTO.TaskHistoryDTO;
import com.onlinetaskmanagementsystem.otms.DTO.TaskUpdateDTO;
import com.onlinetaskmanagementsystem.otms.Exception.CommonException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface SubTaskService {
    List<TaskDTO> viewSubList(Integer taskId, Integer userId,String orgRef) throws CommonException;

    Integer addSubTask(TaskDTO taskDTO, Integer taskId, String orgRef) throws CommonException;

    TaskDTO viewUpdatedSubTask(Integer taskId, Integer subTaskId, TaskUpdateDTO taskUpdateDTO, String orgRef) throws CommonException;

    String deleteSubTask(Integer taskId, Integer userId, Integer subTaskId, String orgRef) throws CommonException;

    List<TaskHistoryDTO> viewHistorySubTask(Integer taskId, Integer subTaskId, Integer userId, String orgRef) throws CommonException;


}
