package com.onlinetaskmanagementsystem.otms.controller;


import com.onlinetaskmanagementsystem.otms.cron.ReminderScheduler;
import com.onlinetaskmanagementsystem.otms.service.TaskService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ReminderControllerTest {
    @InjectMocks
    ReminderController reminderController;

    @Mock
    ReminderScheduler reminderScheduler;

    @Test
    void setReminderTest(){
        reminderScheduler.sendReminderTask();
        String expected = "Reminder set successfully";
        ResponseEntity<String> actual = reminderController.setReminder();
        assertEquals(expected,actual.getBody());

    }



}
