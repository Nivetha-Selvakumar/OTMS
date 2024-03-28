package com.onlinetaskmanagementsystem.otms.DTO;

import com.onlinetaskmanagementsystem.otms.Enum.ActiveStatus;
import com.onlinetaskmanagementsystem.otms.Enum.Priority;
import com.onlinetaskmanagementsystem.otms.Enum.TaskStatus;
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

    private  Integer id;

    private Integer userId;

    @NotBlank(message = "Enter Task title")
    private String taskTitle;

    @NotBlank(message = "Enter Task description")
    private String taskDesc;

    private Priority priority;

    private TaskStatus taskStatus;

    @NotNull(message = "Enter the Planned start date")

    private Timestamp plannedStartDate;

    private Timestamp plannedCompletionDate;

    private Timestamp actualStartDate;

    private Timestamp actualCompletionDate;

    private ActiveStatus activeStatus;

    @NotNull(message = "Enter the Assignee name")
    private Integer assigneeId;

    @NotNull(message = "Enter the Assigner name")
    private Integer assignerId;

    private Integer createdBy;

    private Integer updatedBy;

    private Timestamp createdDate;

    private Timestamp updatedDate;

    private Integer childCount;

    private Integer parentTaskId;

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getTaskTitle() {
        return taskTitle;
    }

    public void setTaskTitle(String taskTitle) {
        this.taskTitle = taskTitle;
    }

    public String getTaskDesc() {
        return taskDesc;
    }

    public void setTaskDesc(String taskDesc) {
        this.taskDesc = taskDesc;
    }

    public Priority getPriority() {
        return priority;
    }

    public void setPriority(Priority priority) {this.priority = priority;}

    public TaskStatus getTaskStatus() {
        return taskStatus;
    }

    public void setTaskStatus(TaskStatus taskStatus) {
        this.taskStatus = taskStatus;
    }

    public Timestamp getPlannedStartDate() {
        return plannedStartDate;
    }

    public void setPlannedStartDate(Timestamp plannedStartDate) {
        this.plannedStartDate = plannedStartDate;
    }

    public Timestamp getPlannedCompletionDate() {
        return plannedCompletionDate;
    }

    public void setPlannedCompletionDate(Timestamp plannedCompletionDate) {
        this.plannedCompletionDate = plannedCompletionDate;
    }

    public Timestamp getActualStartDate() {
        return actualStartDate;
    }

    public void setActualStartDate(Timestamp actualStartDate) {
        this.actualStartDate = actualStartDate;
    }

    public Timestamp getActualCompletionDate() {
        return actualCompletionDate;
    }

    public void setActualCompletionDate(Timestamp actualCompletionDate) {
        this.actualCompletionDate = actualCompletionDate;
    }

    public ActiveStatus getActiveStatus() {
        return activeStatus;
    }

    public void setActiveStatus(ActiveStatus activeStatus) {
        this.activeStatus = activeStatus;
    }

    public Integer getAssigneeId() {
        return assigneeId;
    }

    public void setAssigneeId(Integer assigneeId) {
        this.assigneeId = assigneeId;
    }

    public Integer getAssignerId() {
        return assignerId;
    }

    public void setAssignerId(Integer assignerId) {
        this.assignerId = assignerId;
    }

    public Integer getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(Integer createdBy) {
        this.createdBy = createdBy;
    }

    public Integer getUpdatedBy() {
        return updatedBy;
    }

    public void setUpdatedBy(Integer updatedBy) {
        this.updatedBy = updatedBy;
    }

    public Timestamp getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Timestamp createdDate) {
        this.createdDate = createdDate;
    }

    public Timestamp getUpdatedDate() {
        return updatedDate;
    }

    public void setUpdatedDate(Timestamp updatedDate) {
        this.updatedDate = updatedDate;
    }

    public Integer getChildCount() {
        return childCount;
    }
    public void setChildCount(Integer childCount) {
        this.childCount = childCount;
    }

    public Integer getParentTaskId() {
        return parentTaskId;
    }
    public void setParentTaskId(Integer parentTaskId) {
        this.parentTaskId = parentTaskId;
    }
}
