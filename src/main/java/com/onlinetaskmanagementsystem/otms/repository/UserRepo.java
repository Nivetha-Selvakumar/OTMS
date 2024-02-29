package com.onlinetaskmanagementsystem.otms.repository;



import com.onlinetaskmanagementsystem.otms.Enum.ActiveStatus;
import com.onlinetaskmanagementsystem.otms.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@EnableJpaRepositories
@Repository
public interface UserRepo extends JpaRepository<UserEntity,Integer> {
    @Query("select tbl_user.id from UserEntity tbl_user where tbl_user.email=:email")
   Integer checkUser(String email);

    @Query("select tbl_user.username from UserEntity tbl_user where tbl_user.username=:username")
    String checkUserName(String username);

    @Query("select u from UserEntity u where u.email=:email")
    UserEntity getUserRecord(String email);

    Optional<UserEntity> findByIdAndUserStatus(Integer userId, ActiveStatus status);

}
