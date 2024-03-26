package com.onlinetaskmanagementsystem.otms.controller;

import com.onlinetaskmanagementsystem.otms.cron.ReminderScheduler;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;


@RestController
@CrossOrigin
@RequestMapping("/api/user")
@Controller
public class ReminderController {
    @Autowired
    ReminderScheduler reminderScheduler;

    @GetMapping(path = "/reminder")
    public ResponseEntity<String> setReminder()  {
        reminderScheduler.sendReminderTask();
        return ResponseEntity.status(HttpStatus.CREATED).body("Reminder set successfully");
    }

}
