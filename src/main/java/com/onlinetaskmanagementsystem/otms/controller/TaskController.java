package com.onlinetaskmanagementsystem.otms.controller;

import com.onlinetaskmanagementsystem.otms.DTO.TaskDTO;
import com.onlinetaskmanagementsystem.otms.Exception.CommonException;
import com.onlinetaskmanagementsystem.otms.Exception.TaskNotFoundException;
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
    public ResponseEntity<TaskDTO> updateTask(@PathVariable("taskId") Integer taskId, @RequestBody @Valid TaskDTO taskDTO) throws  CommonException{
        return new ResponseEntity<>(taskService.viewUpdatedList(taskId, taskDTO),HttpStatus.OK);
    }

    @DeleteMapping(path="/delete/{taskId}")
    public ResponseEntity<String> deleteTask(@PathVariable Integer taskId, @RequestParam Integer userId) throws CommonException {
        return  new ResponseEntity<>(taskService.deleteTask(taskId,userId),HttpStatus.OK);
    }

    @PostMapping(path = "")
    @GetMapping(path="/history/{taskId}")
    public ResponseEntity<String> historyTask(@PathVariable String taskId){
        return  ResponseEntity.ok("Success" + taskId);
    }
}
