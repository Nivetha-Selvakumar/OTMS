package com.onlinetaskmanagementsystem.otms.repository;



import com.onlinetaskmanagementsystem.otms.Enum.ActiveStatus;
import com.onlinetaskmanagementsystem.otms.entity.OrganisationEntity;
import com.onlinetaskmanagementsystem.otms.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@EnableJpaRepositories
@Repository
public interface UserRepo extends JpaRepository<UserEntity,Integer> {
    Optional<UserEntity> findByEmail(String email);

    Optional<UserEntity> findByUsername(String username);

    Optional<UserEntity> findByIdAndUserStatus(Integer userId, ActiveStatus status);

    List<UserEntity> findAllByUserStatus(ActiveStatus activeStatus);

    UserEntity findByEmailAndPassword(String email, String password);

    UserEntity findByEmailAndPasswordAndOrgId(String email, String password, OrganisationEntity organisationEntity);
}
