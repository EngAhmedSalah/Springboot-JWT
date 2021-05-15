package com.example.springjwtdemo.Controller;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class MyUserDetails implements UserDetailsService
{

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException
    {
        return new User("ahmed" , "root" , new ArrayList<>());
    }
}
