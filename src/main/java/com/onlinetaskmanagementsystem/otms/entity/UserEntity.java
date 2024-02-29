package com.onlinetaskmanagementsystem.otms.entity;


import com.onlinetaskmanagementsystem.otms.Enum.ActiveStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import java.sql.Timestamp;
import java.util.List;


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

    @Enumerated(EnumType.STRING)
    @Column(name = "user_status", nullable = false, length = 10)
    private ActiveStatus userStatus;


    @Column(name = "created_date", nullable = false)
    @CreationTimestamp
    private Timestamp createdDate;


    @OneToMany(mappedBy = "userId")
    List<TaskEntity> taskEntityList;

}
