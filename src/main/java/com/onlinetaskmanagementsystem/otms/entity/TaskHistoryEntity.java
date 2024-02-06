package com.onlinetaskmanagementsystem.otms.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import java.sql.Timestamp;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Table(name="tbl_task_history")
public class TaskHistoryEntity {
    @Id
    @Column(name = "id", nullable=false)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column(name="task_id", nullable = false)
    private Integer taskId;

    @Column(name = "created_by", nullable = false)
    private Integer createdBy;

    @Column(name="desc", nullable = false, length =1000)
    private String desc;

    @Column(name = "created_date", nullable = false)
    @CreationTimestamp
    private Timestamp createdDate;
}
