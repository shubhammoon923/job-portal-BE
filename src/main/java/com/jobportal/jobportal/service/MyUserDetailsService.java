package com.jobportal.jobportal.service;

import com.jobportal.jobportal.Entity.User;
import com.jobportal.jobportal.Entity.UserPrincipal;
import com.jobportal.jobportal.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;  // **Updated this annotation to @Service**

@Service  // **Change @Component to @Service**
public class MyUserDetailsService implements UserDetailsService {  // **Fixed the class name typo (Sevice -> Service)**

    @Autowired
    private UserRepo userRepo;

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        // Retrieve the user by username from the database
        User user = userRepo.findByUserName(userName);

        // If the user is not found, throw an exception
        if (user == null) {
            throw new UsernameNotFoundException("User Not Found");
        }

        // Return a new UserPrincipal with the User object
        return new UserPrincipal(user);  // **Updated: Passing the User entity to UserPrincipal constructor**
    }
}
