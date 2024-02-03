package com.onlinetaskmanagementsystem.otms.service;

import com.onlinetaskmanagementsystem.otms.DTO.TaskDTO;
import com.onlinetaskmanagementsystem.otms.Exception.CommonException;

public interface Taskservice {
    Integer addTask(TaskDTO taskDTO) throws CommonException;
}
