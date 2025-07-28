package com.jobportal.jobportal.repository;

import com.jobportal.jobportal.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepo extends JpaRepository<User,Integer> {

    User findByUserName(String userName);

    Optional<User> findByEmail(String email);
}
