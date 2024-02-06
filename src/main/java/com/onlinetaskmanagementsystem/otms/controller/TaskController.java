package com.onlinetaskmanagementsystem.otms.controller;

import com.onlinetaskmanagementsystem.otms.DTO.TaskDTO;
import com.onlinetaskmanagementsystem.otms.DTO.TaskHistoryDTO;
import com.onlinetaskmanagementsystem.otms.DTO.TaskUpdateDTO;
import com.onlinetaskmanagementsystem.otms.Exception.CommonException;
import com.onlinetaskmanagementsystem.otms.Exception.TaskNotFoundException;
import com.onlinetaskmanagementsystem.otms.entity.TaskHistoryEntity;
import com.onlinetaskmanagementsystem.otms.service.Taskservice;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/api/task")
public class TaskController {
    @Autowired
    private Taskservice taskService;

    @GetMapping(path="/list")
    public ResponseEntity<List<TaskDTO>> listTask(@RequestParam Integer userId) throws CommonException{
        return new ResponseEntity<>(taskService.viewList(userId),HttpStatus.OK);
    }

    @PostMapping(path="/create")
    public ResponseEntity<String> createTask(@RequestBody @Valid TaskDTO taskDTO)throws CommonException {
        Integer id= taskService.addTask(taskDTO);
        String responseMsg="Successfully Registered!\nYour TaskId: "+id;
        return new ResponseEntity<>(responseMsg, HttpStatus.CREATED);
    }

    @PutMapping(path="/update/{taskId}")
    public ResponseEntity<TaskDTO> updateTask(@PathVariable("taskId") Integer taskId, @RequestBody @Valid TaskUpdateDTO taskUpdateDTO) throws  CommonException{
        return new ResponseEntity<>(taskService.viewUpdatedTask(taskId, taskUpdateDTO),HttpStatus.OK);
    }

    @DeleteMapping(path="/delete/{taskId}")
    public ResponseEntity<String> deleteTask(@PathVariable Integer taskId, @RequestParam Integer userId) throws CommonException {
        return  new ResponseEntity<>(taskService.deleteTask(taskId,userId),HttpStatus.OK);
    }

//
//    @GetMapping(path="/history/{taskId}")
//    public ResponseEntity<List<TaskHistoryEntity>> historyTask(@PathVariable Integer taskId) throws CommonException{
//        List<TaskHistoryDTO> taskHistories = taskService.viewHistoryTask(taskId);
//        return ResponseEntity.ok(taskHistories);
//    }
}
