package com.onlinetaskmanagementsystem.otms.controller;

import com.onlinetaskmanagementsystem.otms.DTO.TaskDTO;
import com.onlinetaskmanagementsystem.otms.DTO.TaskHistoryDTO;
import com.onlinetaskmanagementsystem.otms.DTO.TaskUpdateDTO;
import com.onlinetaskmanagementsystem.otms.Exception.CommonException;
import com.onlinetaskmanagementsystem.otms.service.SubTaskService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class SubTaskControllerTest {
    @Mock
    SubTaskService subTaskService;

    @InjectMocks
    SubTaskController subTaskController;

    TaskUpdateDTO taskUpdateDTO = new TaskUpdateDTO();

    TaskDTO taskDTO = new TaskDTO();

    List<TaskHistoryDTO> taskHistoryDTOList = new ArrayList<>();
    @Test
    void  listSubTaskTest() throws CommonException {
        TaskDTO taskDTO1=new TaskDTO();
        TaskDTO taskDTO2 = new TaskDTO();
        List<TaskDTO> taskDTOList = Arrays.asList(taskDTO1,taskDTO2);
        when(subTaskService.viewSubList(taskDTO.getParentTaskId(), taskDTO.getUserId(), "ORG001")).thenReturn(taskDTOList);
        List<TaskDTO> response = subTaskController.listSubTask(taskDTO.getParentTaskId(),taskDTO.getUserId(),"ORG001").getBody();
        Assertions.assertEquals(taskDTOList,response);
    }

    @Test
    void createSubTaskTest() throws CommonException {
        when(subTaskService.addSubTask(taskDTO,taskDTO.getParentTaskId(), "ORG001")).thenReturn(1);
        String expected = "Successfully Registered!\nYour SubTaskId: 1";
        String actual = subTaskController.createSubTask(taskDTO.getParentTaskId(),taskDTO,"ORG001").getBody();
        Assertions.assertEquals(expected, actual);
    }

    @Test
    void updateSubTaskTest() throws CommonException {
        when(subTaskService.viewUpdatedSubTask(taskDTO.getParentTaskId(), 1,taskUpdateDTO,"ORG001")).thenReturn(taskDTO);
        TaskDTO response = subTaskController.updateSubTask(taskDTO.getParentTaskId(),1,taskUpdateDTO,"ORG001").getBody();
        Assertions.assertEquals(taskDTO, response);
    }
    @Test
    void deleteSubTaskTest() throws CommonException {
        when(subTaskService.deleteSubTask(taskDTO.getParentTaskId(), taskDTO.getUserId(), 1,"ORG001")).thenReturn("Successfully Inactive!");
        String response = subTaskController.deleteSubTask(taskDTO.getParentTaskId(),1, taskDTO.getUserId(),"ORG001").getBody();
        Assertions.assertEquals("Successfully Inactive!", response);
    }

    @Test
    void historySubTaskTest() throws CommonException {
        when(subTaskService.viewHistorySubTask(taskDTO.getParentTaskId(), 1, taskDTO.getUserId(),"ORG001")).thenReturn(taskHistoryDTOList);
        List<TaskHistoryDTO> response = subTaskController.historySubTask(taskDTO.getParentTaskId(),1, taskDTO.getUserId(),"ORG001").getBody();
        Assertions.assertEquals(taskHistoryDTOList, response);
    }

}
