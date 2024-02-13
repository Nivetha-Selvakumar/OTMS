package com.onlinetaskmanagementsystem.otms.entity;

import com.onlinetaskmanagementsystem.otms.Enum.ActiveStatus;
import com.onlinetaskmanagementsystem.otms.Enum.TaskStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.sql.Timestamp;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Table(name="tbl_task")
public class TaskEntity {
    @Id
    @Column(name = "id", nullable=false)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column(name="user_id", nullable = false)
    private Integer userId;

    @Column(name="task_title", nullable = false, length =50)
    private String taskTitle;

    @Column(name="task_desc", nullable = false, length =1000)
    private String taskDesc;

    @Column(name="priority", nullable = false, length =10)
    private String priority;

    @Column(name = "task_status", nullable = false, length = 10)
    private TaskStatus taskStatus;

    @Column(name = "planned_start_date", nullable = false)
    private Timestamp plannedStartDate;

    @Column(name = "planned_completion_date")
    private Timestamp plannedCompletionDate;

    @Column(name = "actual_start_date")
    private Timestamp actualStartDate;

    @Column(name = "actual_completion_date")
    private Timestamp actualCompletionDate;

    @Enumerated(value = EnumType.STRING)
    @Column(name = "active_status", nullable = false, length = 15)
    private ActiveStatus activeStatus;

    @Column(name = "assignee_id", nullable = false)
    private Integer assigneeId;

    @Column(name = "assigner_id", nullable = false)
    private Integer assignerId;

    @Column(name = "created_by", nullable = false)
    private Integer createdBy;

    @Column(name = "updated_by", nullable = false)
    private Integer updatedBy;

    @Column(name = "created_date", nullable = false)
    @CreationTimestamp
    private Timestamp createdDate;

    @Column(name = "updated_date", nullable = false)
    @UpdateTimestamp
    private Timestamp updatedDate;


}
