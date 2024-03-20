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
@Table(name="tbl_organisation")
public class OrganisationEntity {
    @Id
    @Column(name = "id", nullable=false)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column(name="org_ref", nullable = false, length =20)
    private String orgRef;

    @Column(name="org_name", nullable = false, length =50)
    private String orgName;

    @Column(name="address", nullable = false, length =100)
    private String address;

    @Enumerated(value = EnumType.STRING)
    @Column(name = "active_status", nullable = false, length = 15)
    private ActiveStatus activeStatus;



}
