package com.onlinetaskmanagementsystem.otms.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("/api/task")
public class TaskController {
    @GetMapping(path="/list")
    public ResponseEntity<String> listTask(){
        return  ResponseEntity.ok("Success");
    }

    @PostMapping(path="/create")
    public ResponseEntity<String> createTask(){
        return  ResponseEntity.ok("Success");
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
