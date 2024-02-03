package com.onlinetaskmanagementsystem.otms.controller;

import com.onlinetaskmanagementsystem.otms.DTO.TaskDTO;
import com.onlinetaskmanagementsystem.otms.Exception.CommonException;
import com.onlinetaskmanagementsystem.otms.service.Taskservice;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("/api/task")
public class TaskController {
    @Autowired
    private Taskservice taskService;

    @GetMapping(path="/list")
    public ResponseEntity<String> listTask(){
        return  ResponseEntity.ok("Success");
    }

    @PostMapping(path="/create")
    public ResponseEntity<String> createTask(@RequestBody @Valid TaskDTO taskDTO)throws CommonException {
        Integer id= taskService.addTask(taskDTO);
        String responseMsg="Successfully Registered!\nYour TaskId: "+id;
        return new ResponseEntity<>(responseMsg, HttpStatus.CREATED);
    }

    @PutMapping(path="/update/{taskId}")
    public ResponseEntity<String> updateTask(@PathVariable String taskId){
        return  ResponseEntity.ok("Success" + taskId);
    }

    @DeleteMapping(path="/delete/{taskId}")
    public ResponseEntity<String> deleteTask(@PathVariable String taskId){
        return  ResponseEntity.ok("Success" + taskId);
    }

    @GetMapping(path="/history/{taskId}")
    public ResponseEntity<String> historyTask(@PathVariable String taskId){
        return  ResponseEntity.ok("Success" + taskId);
    }
}
