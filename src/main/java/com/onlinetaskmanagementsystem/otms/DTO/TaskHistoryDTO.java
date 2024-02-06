package com.onlinetaskmanagementsystem.otms.DTO;

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
public class TaskHistoryDTO {
    @NotNull(message = "Enter User id")
    private Integer taskId;

    @NotBlank(message = "Enter Task update description")
    private String description;

    @NotNull(message = "Enter Created by User Id")
    private Integer createdBy;

    private Timestamp createdDate;

}
