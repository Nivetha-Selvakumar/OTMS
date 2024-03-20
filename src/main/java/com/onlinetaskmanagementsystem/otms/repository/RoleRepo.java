package com.onlinetaskmanagementsystem.otms.repository;

import com.onlinetaskmanagementsystem.otms.Enum.ActiveStatus;
import com.onlinetaskmanagementsystem.otms.entity.RoleEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@EnableJpaRepositories
@Repository
public interface RoleRepo extends JpaRepository<RoleEntity, Integer> {

    Optional<RoleEntity> findByIdAndActiveStatus(Integer roleId, ActiveStatus activeStatus);

    Optional<RoleEntity> findByRoleAndActiveStatus(String roleId, ActiveStatus activeStatus);
}
