package com.onlinetaskmanagementsystem.otms.controller;


import com.onlinetaskmanagementsystem.otms.DTO.TaskDTO;
import com.onlinetaskmanagementsystem.otms.DTO.TaskHistoryDTO;
import com.onlinetaskmanagementsystem.otms.DTO.TaskUpdateDTO;
import com.onlinetaskmanagementsystem.otms.Exception.CommonException;
import com.onlinetaskmanagementsystem.otms.service.TaskService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;

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
    void  listTaskTest() throws  CommonException{
        TaskDTO taskDTO1=new TaskDTO();
        TaskDTO taskDTO2 = new TaskDTO();
        List<TaskDTO> taskDTOList = Arrays.asList(taskDTO1,taskDTO2);
        when(taskService.viewList(taskDTO.getUserId())).thenReturn(taskDTOList);
        List<TaskDTO> response = taskController.listTask(taskDTO.getUserId()).getBody();
        assertEquals(taskDTOList,response);
    }

    @Test
    void createTaskTest() throws CommonException{
        when(taskService.addTask(taskDTO)).thenReturn(1);
        ResponseEntity<String> response= taskController.createTask(taskDTO);
        assertEquals("Successfully Registered!\nYour TaskId: 1",response.getBody());
    }

    @Test
    void updateTaskTest() throws CommonException{
        when(taskService.viewUpdatedTask(taskHistoryDTO.getTaskId(), new TaskUpdateDTO())).thenReturn(taskDTO);
        TaskDTO response= taskController.updateTask(taskHistoryDTO.getTaskId(),new TaskUpdateDTO()).getBody();
        assertEquals(taskDTO,response);
    }

    @Test
    void deleteTaskTest() throws CommonException{
        when(taskService.deleteTask(taskHistoryDTO.getTaskId(),taskDTO.getUserId())).thenReturn("Successfully Inactive!");
        String response = taskController.deleteTask(taskHistoryDTO.getTaskId(),taskDTO.getUserId()).getBody();
        Assertions.assertEquals("Successfully Inactive!", response);
    }

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
