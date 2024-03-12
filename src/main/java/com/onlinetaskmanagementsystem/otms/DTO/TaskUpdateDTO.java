package com.onlinetaskmanagementsystem.otms.DTO;

import com.onlinetaskmanagementsystem.otms.Enum.ActiveStatus;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Data
@Component
public class TaskUpdateDTO {

    private Integer userId;

    @Size(min = 10,max = 50,message = "Your TaskTitle must be in 10 to 50 characters")
    private String taskTitle;

    @Size(min = 10,max = 2000,message = "Your Description must be in 10 to 2000 characters")
    private String taskDesc;

    @Size(min = 1,max = 50,message = "Your Priority must be in 1 to 50 characters")
    private String priority;

    @Size(min = 1,max = 50,message = "Your TaskStatus must be in 1 to 50 characters")
    private String taskStatus;

    private Timestamp plannedStartDate;

    private Timestamp plannedCompletionDate;

    private Timestamp actualStartDate;

    private Timestamp actualCompletionDate;

    private ActiveStatus activeStatus;

    private Integer assigneeId;

    private Integer assignerId;

    private Integer updatedBy;

    private Integer childCount;

    private Integer parentTaskId;

    @NotNull
    private String updatedField;

    

}
