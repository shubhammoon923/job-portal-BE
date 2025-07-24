package com.jobportal.jobportal.repository;

import com.jobportal.jobportal.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepo extends JpaRepository<User,Integer> {

    User findByUserName(String userName);
}
