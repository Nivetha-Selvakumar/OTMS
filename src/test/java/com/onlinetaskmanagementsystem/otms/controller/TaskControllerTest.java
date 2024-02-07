package com.onlinetaskmanagementsystem.otms.controller;


import com.onlinetaskmanagementsystem.otms.DTO.TaskDTO;
import com.onlinetaskmanagementsystem.otms.DTO.TaskHistoryDTO;
import com.onlinetaskmanagementsystem.otms.Exception.CommonException;
import com.onlinetaskmanagementsystem.otms.service.TaskService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
class TaskControllerTest {
    @InjectMocks
    private TaskController taskController;

    @Mock
    private TaskService taskService;

    TaskHistoryDTO taskHistoryDTO=new TaskHistoryDTO();


    TaskDTO taskDTO = new TaskDTO();


    @Test
    void taskHistoryTest() throws CommonException {
        TaskHistoryDTO taskHistoryDTO1 = new TaskHistoryDTO();
        TaskHistoryDTO taskHistoryDTO2 = new TaskHistoryDTO();
        List<TaskHistoryDTO> taskHistoryDTOList = Arrays.asList(taskHistoryDTO1,taskHistoryDTO2);
        when(taskService.viewHistoryTask(taskHistoryDTO.getTaskId(),taskDTO.getUserId())).thenReturn(taskHistoryDTOList);
        List<TaskHistoryDTO> responseEntity = taskController.historyTask(taskHistoryDTO.getTaskId(),taskDTO.getUserId()).getBody();
        assertEquals(taskHistoryDTOList,responseEntity);

    }

}
