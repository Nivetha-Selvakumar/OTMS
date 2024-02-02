package com.onlinetaskmanagementsystem.otms.entity;


import com.onlinetaskmanagementsystem.otms.Enum.Status;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.sql.Timestamp;


@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Table(name="tbl_user")
public class UserEntity {
    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column(name = "org_id", nullable = false)
    private Integer orgId;

    @Column(name = "role_id", nullable = false)
    private Integer roleId;

    @Column(name = "emp_code", nullable = false, length = 50 )
    private String empCode;

    @Column(name = "emp_name", nullable = false, length = 50)
    private String empName;

    @Column(name = "username", nullable = false, length = 50)
    private String username;

    @Column(name = "email", nullable = false, length = 50)
    private String email;

    @Column(name = "password", nullable = false, length = 20)
    private String password;

    @Column(name = "registration_date", nullable = false)
    @CreationTimestamp
    private Timestamp registrationDate;

    @Enumerated(value = EnumType.STRING)
    @Column(name = "user_status", nullable = false, length = 10)
    private Status userStatus;

    @Column(name = "created_by", nullable = false)
    private Integer createdBy;

    @Column(name = "created_date", nullable = false)
    @CreationTimestamp
    private Timestamp createdDate;

    @Column(name = "updated_by", nullable = false)
    private Integer updatedBy;

    @Column(name = "updated_date", nullable = false)
    @UpdateTimestamp
    private Timestamp updatedDate;


}
