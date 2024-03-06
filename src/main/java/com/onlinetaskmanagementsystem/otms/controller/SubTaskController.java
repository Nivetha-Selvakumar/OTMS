package com.onlinetaskmanagementsystem.otms.controller;

import com.onlinetaskmanagementsystem.otms.DTO.TaskDTO;
import com.onlinetaskmanagementsystem.otms.DTO.TaskHistoryDTO;
import com.onlinetaskmanagementsystem.otms.DTO.TaskUpdateDTO;
import com.onlinetaskmanagementsystem.otms.Exception.CommonException;
import com.onlinetaskmanagementsystem.otms.service.SubTaskService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/api/subtask")
public class SubTaskController {
    @Autowired
    SubTaskService subTaskService;

    @GetMapping(path="/list/{taskId}")
    public ResponseEntity<List<TaskDTO>> listSubTask(@PathVariable("taskId") Integer taskId, @RequestParam Integer userId) throws CommonException {
        return new ResponseEntity<>(subTaskService.viewSubList(taskId,userId), HttpStatus.OK);
    }

    @PostMapping(path="/create/{taskId}")
    public ResponseEntity<String> createSubTask(@PathVariable("taskId") Integer taskId, @RequestBody @Valid TaskDTO taskDTO)throws CommonException {
        Integer id= subTaskService.addSubTask(taskDTO,taskId);
        String responseMsg="Successfully Registered!\nYour SubTaskId: "+id;
        return new ResponseEntity<>(responseMsg, HttpStatus.CREATED);
    }

    @PutMapping(path="/update/{taskId}/{subTaskId}")
    public ResponseEntity<TaskDTO> updateSubTask(@PathVariable("taskId") Integer taskId, @PathVariable("subTaskId") Integer subTaskId, @RequestBody @Valid TaskUpdateDTO taskUpdateDTO) throws  CommonException{
        return new ResponseEntity<>(subTaskService.viewUpdatedSubTask(taskId, subTaskId, taskUpdateDTO),HttpStatus.OK);
    }

    @DeleteMapping(path="/delete/{taskId}/{subTaskId}")
    public ResponseEntity<String> deleteSubTask(@PathVariable("taskId") Integer taskId,@PathVariable("subTaskId") Integer subTaskId, @RequestParam Integer userId) throws CommonException {
        return  new ResponseEntity<>(subTaskService.deleteSubTask(taskId,userId,subTaskId),HttpStatus.OK);
    }


    @GetMapping(path="/history/{taskId}/{subTaskId}")
    public ResponseEntity<List<TaskHistoryDTO>> historySubTask(@PathVariable Integer taskId, @PathVariable("subTaskId") Integer subTaskId,@RequestParam Integer userId) throws CommonException{
        return new ResponseEntity<>(subTaskService.viewHistorySubTask(taskId,subTaskId,userId),HttpStatus.OK);

    }
}
