package com.onlinetaskmanagementsystem.otms.entity;

import com.onlinetaskmanagementsystem.otms.Enum.ActiveStatus;
import com.onlinetaskmanagementsystem.otms.Enum.Priority;
import com.onlinetaskmanagementsystem.otms.Enum.TaskStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.sql.Timestamp;
import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Table(name = "tbl_task")
public class TaskEntity {
    @Id
    @Column(name = "id", nullable=false)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "org_id", referencedColumnName = "Id", nullable = false)
    private OrganisationEntity orgId;

    @ManyToOne
    @JoinColumn(name = "user_id",nullable = false)
    private UserEntity userId;

    @Column(name="task_title", nullable = false, length =50)
    private String taskTitle;

    @Column(name="task_desc", nullable = false, length =1000)
    private String taskDesc;

    @Enumerated(EnumType.STRING)
    @Column(name="priority", nullable = false, length =10)
    private Priority priority;

    @Enumerated(EnumType.STRING)
    @Column(name = "task_status",  length = 10, nullable = false)
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

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "assignee_id",nullable = false)
    private UserEntity assigneeId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "assigner_id",nullable = false)
    private UserEntity assignerId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "created_by",nullable = false)
    private UserEntity createdBy;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "updated_by",nullable = false)
    private UserEntity updatedBy;

    @Column(name = "created_date", nullable = false)
    @CreationTimestamp
    private Timestamp createdDate;

    @Column(name = "updated_date", nullable = false)
    @UpdateTimestamp
    private Timestamp updatedDate;

    @Column(name = "child_count")
    private Integer childCount;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "parent_task_id")
    private TaskEntity parentTaskId;

    @OneToMany(mappedBy = "taskId")
    List<TaskHistoryEntity> taskHistoryEntity;

    @OneToMany(mappedBy = "parentTaskId")
    List<TaskEntity> taskEntityList;

}
