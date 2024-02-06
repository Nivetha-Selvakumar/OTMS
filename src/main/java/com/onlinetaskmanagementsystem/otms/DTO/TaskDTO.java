package com.onlinetaskmanagementsystem.otms.DTO;

import com.onlinetaskmanagementsystem.otms.Enum.Status;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.springframework.stereotype.Component;


import java.sql.Timestamp;
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Data
@Component
public class TaskDTO {
    @NotNull(message = "Enter User id")
    private Integer userId;
    @NotBlank(message = "Enter Task title")
    private String taskTitle;
    @NotBlank(message = "Enter Task description")
    private String taskDesc;
    @NotBlank(message = "Enter task Priority as High,Medium,Low")
    private String priority;
    @NotBlank(message = "Enter the Task status")
    private String taskStatus;
    @NotNull(message = "Enter the Planned start date")
 //   @Pattern(regexp = "\\d{4}-\\d{2}-\\d{2}T\\d{2}:\\d{2}:\\d{2}",message = "Invalid timestamp format. Use the format: yyyy-MM-ddTHH:mm:ss")
    private Timestamp plannedStartDate;
//    @Pattern(regexp = "\\d{4}-\\d{2}-\\d{2}T\\d{2}:\\d{2}:\\d{2}",message = "Invalid timestamp format. Use the format: yyyy-MM-ddTHH:mm:ss")
    private Timestamp plannedCompletionDate;
//    @Pattern(regexp = "\\d{4}-\\d{2}-\\d{2}T\\d{2}:\\d{2}:\\d{2}",message = "Invalid timestamp format. Use the format: yyyy-MM-ddTHH:mm:ss")
    private Timestamp actualStartDate;
//    @Pattern(regexp = "\\d{4}-\\d{2}-\\d{2}T\\d{2}:\\d{2}:\\d{2}",message = "Invalid timestamp format. Use the format: yyyy-MM-ddTHH:mm:ss")
    private Timestamp actualCompletionDate;
    private Status activeStatus;
    @NotNull(message = "Enter the Assignee id")
    private Integer assigneeId;
    @NotNull(message = "Enter the Assigner id")
    private Integer assignerId;
    private Integer createdBy;
    private Integer updatedBy;
    private Timestamp createdDate;
    private Timestamp updatedDate;

}
