package com.onlinetaskmanagementsystem.otms.cron;

import com.onlinetaskmanagementsystem.otms.Enum.ActiveStatus;
import com.onlinetaskmanagementsystem.otms.entity.TaskEntity;
import com.onlinetaskmanagementsystem.otms.entity.UserEntity;
import com.onlinetaskmanagementsystem.otms.repository.TaskRepo;
import com.onlinetaskmanagementsystem.otms.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.sql.Struct;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Component
public class ReminderScheduler {
    @Autowired
    private TaskRepo taskRepo;

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private JavaMailSender javaMailSender;

    @Value("${spring.mail.username}")
    String fromMail;

    @Scheduled(cron= "${spring.mail.cron}")
    public void sendReminderTask(){
        Date today = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String dateString = dateFormat.format(today);
        String timeStampString = dateString + " 00:00:00";
        List<UserEntity> userEntityList = userRepo.findAllByUserStatus(ActiveStatus.ACTIVE);
        for (UserEntity user: userEntityList){
            List<TaskEntity> taskEntityList = taskRepo.findAllByAssigneeIdAndPlannedCompletionDateAndActiveStatus(user,Timestamp.valueOf(timeStampString), ActiveStatus.ACTIVE);
            for (TaskEntity task: taskEntityList){
                sendReminderEmail(task);
            }
        }
    }
    public void sendReminderEmail(TaskEntity task){
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(fromMail);
        message.setTo(task.getUserId().getEmail());
        message.setSubject("Reminder: Task Due Today - "+task.getTaskTitle());
        message.setText("Dear "+ task.getUserId().getEmpName()+",\n\n"+ "This is a reminder that your task \"" + task.getTaskTitle() + "\" is due today.\n\n"+ "Thank you,\nYour Online Task Management System" );
        javaMailSender.send(message);
    }
}
