package com.blog.security;

import com.blog.exceptions.ResourceNotFoundException;
import com.blog.models.User;
import com.blog.repositories.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import javax.annotation.Resource;

public class CustomUserDetailsService implements UserDetailsService {

    @Resource
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = this.userRepository.findByEmail(username);
        if(user != null){
            return user;
        }else {
            throw new ResourceNotFoundException("User","Email",Long.parseLong(username));
        }
    }
}
