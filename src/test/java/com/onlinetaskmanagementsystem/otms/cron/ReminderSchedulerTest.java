package com.onlinetaskmanagementsystem.otms.cron;

import com.onlinetaskmanagementsystem.otms.Enum.ActiveStatus;
import com.onlinetaskmanagementsystem.otms.entity.TaskEntity;
import com.onlinetaskmanagementsystem.otms.entity.UserEntity;
import com.onlinetaskmanagementsystem.otms.repository.TaskRepo;
import com.onlinetaskmanagementsystem.otms.repository.UserRepo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ReminderSchedulerTest {
    @InjectMocks
    ReminderScheduler reminderScheduler;

    @Mock
    TaskRepo taskRepo;

    @Mock
    UserRepo userRepo;

    @Mock
    JavaMailSender javaMailSender;

    UserEntity userEntity = new UserEntity();

    TaskEntity taskEntity = new TaskEntity();

    List<UserEntity> userEntityList= new ArrayList<>();

    List<TaskEntity> taskEntityList = new ArrayList<>();


    @BeforeEach
    void init(){

        userEntity.setId(1);
        userEntity.setEmail("n@gmail.com");
        userEntity.setUserStatus(ActiveStatus.ACTIVE);
        userEntity.setEmpCode("111");
        userEntity.setEmpName("nive");
        userEntity.setCreatedDate(Timestamp.valueOf("2024-01-02 12:30:40"));
        userEntity.setRegistrationDate(Timestamp.valueOf("2024-01-02 12:30:40"));
        userEntity.setUsername("nive");
        userEntity.setPassword("nivetha");

        taskEntity.setId(1);
        taskEntity.setUserId(userEntity);
        taskEntity.setTaskTitle("Testing task");
        taskEntity.setPlannedStartDate(Timestamp.valueOf("2024-01-02 12:30:40"));
        taskEntity.setPlannedCompletionDate(Timestamp.valueOf("2024-03-11 00:00:00"));
        taskEntity.setActiveStatus(ActiveStatus.ACTIVE);

        userEntityList.add(userEntity);
        userEntityList.add(userEntity);

        taskEntityList.add(taskEntity);
        taskEntityList.add(taskEntity);

    }
    @Test
    void sendReminderTaskTest(){
        when(userRepo.findAllByUserStatus(ActiveStatus.ACTIVE)).thenReturn(userEntityList);
        when(taskRepo.findAllByAssigneeIdAndPlannedCompletionDateAndActiveStatus(Mockito.any(), Mockito.any(), Mockito.any())).thenReturn(taskEntityList);
        reminderScheduler.sendReminderTask();
        //        verify(reminderScheduler, times(2)).sendReminderEmail(taskEntity);
    }

//    @Test
//    void sendReminderEmailTest(){
//        reminderScheduler.sendReminderEmail(taskEntity);
//        verify(javaMailSender, times(1)).send(any(SimpleMailMessage.class));
//    }
}
