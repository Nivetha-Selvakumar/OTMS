package com.onlinetaskmanagementsystem.OTMS.Repository;

import com.onlinetaskmanagementsystem.OTMS.Entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

@EnableJpaRepositories
@Repository
public interface UserRepo extends JpaRepository<UserEntity,Integer> {
    @Query("select tbl_user.id from UserEntity tbl_user where tbl_user.email=:email")
    public Integer checkUser(String email);


    @Query("select tbl_user.username from UserEntity tbl_user where tbl_user.username=:username")
    public  String checkUserName(String username);
}
