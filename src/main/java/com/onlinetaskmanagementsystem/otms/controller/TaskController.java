package com.onlinetaskmanagementsystem.otms.controller;

import com.onlinetaskmanagementsystem.otms.DTO.FilterTask;
import com.onlinetaskmanagementsystem.otms.DTO.TaskDTO;
import com.onlinetaskmanagementsystem.otms.DTO.TaskHistoryDTO;
import com.onlinetaskmanagementsystem.otms.DTO.TaskUpdateDTO;
import com.onlinetaskmanagementsystem.otms.Exception.CommonException;
import com.onlinetaskmanagementsystem.otms.service.TaskService;
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
    private TaskService taskService;

    @GetMapping(path="/list/{userId}")
    public ResponseEntity<List<TaskDTO>> listTask(@PathVariable("userId") Integer userId , FilterTask filterTask,@RequestHeader String orgRef) throws CommonException{
        return new ResponseEntity<>(taskService.viewList(userId, filterTask, orgRef),HttpStatus.OK);
    }

    @PostMapping(path="/create")
    public ResponseEntity<String> createTask(@RequestParam Integer userId, @RequestBody @Valid TaskDTO taskDTO, @RequestHeader String orgRef)throws CommonException {
        Integer id= taskService.addTask(userId, taskDTO,orgRef);
        String responseMsg="Successfully Registered!\nYour TaskId: "+id;
        return new ResponseEntity<>(responseMsg, HttpStatus.CREATED);
    }

    @PutMapping(path="/update/{taskId}")
    public ResponseEntity<TaskDTO> updateTask(@PathVariable("taskId") Integer taskId,@RequestParam Integer userId, @RequestBody @Valid TaskUpdateDTO taskUpdateDTO,@RequestHeader String orgRef ) throws  CommonException{
        return new ResponseEntity<>(taskService.viewUpdatedTask(taskId, userId, taskUpdateDTO, orgRef),HttpStatus.OK);
    }

    @DeleteMapping(path="/delete/{taskId}")
    public ResponseEntity<String> deleteTask(@PathVariable Integer taskId, @RequestParam Integer userId, @RequestHeader String orgRef) throws CommonException {
        return  new ResponseEntity<>(taskService.deleteTask(taskId,userId,orgRef),HttpStatus.OK);
    }


    @GetMapping(path="/history/{taskId}")
    public ResponseEntity<List<TaskHistoryDTO>> historyTask(@PathVariable Integer taskId,@RequestParam Integer userId, @RequestHeader String orgRef) throws CommonException{
        return new ResponseEntity<>(taskService.viewHistoryTask(taskId,userId, orgRef),HttpStatus.OK);

    }
}
