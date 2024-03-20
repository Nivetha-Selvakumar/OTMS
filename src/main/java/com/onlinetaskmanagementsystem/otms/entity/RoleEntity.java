package com.onlinetaskmanagementsystem.otms.entity;


import com.onlinetaskmanagementsystem.otms.Enum.ActiveStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Table(name="tbl_role")
public class RoleEntity {
    @Id
    @Column(name = "id", nullable=false)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column(name="role", nullable = false, length = 50)
    private String role;

    @Enumerated(value = EnumType.STRING)
    @Column(name = "active_status", nullable = false, length = 15)
    private ActiveStatus activeStatus;

}
