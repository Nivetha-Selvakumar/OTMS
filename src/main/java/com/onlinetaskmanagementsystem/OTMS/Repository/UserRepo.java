package com.onlinetaskmanagementsystem.OTMS.Repository;

import com.onlinetaskmanagementsystem.OTMS.Entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

@EnableJpaRepositories
@Repository
public interface UserRepo extends JpaRepository<UserEntity,Integer> {

}
