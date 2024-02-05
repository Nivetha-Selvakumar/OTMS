package com.onlinetaskmanagementsystem.otms.service;

import com.onlinetaskmanagementsystem.otms.DTO.TaskDTO;
import com.onlinetaskmanagementsystem.otms.Exception.CommonException;

import java.util.List;

public interface Taskservice {
    Integer addTask(TaskDTO taskDTO) throws CommonException;

    List<TaskDTO> viewList(Integer id) throws CommonException;
}
