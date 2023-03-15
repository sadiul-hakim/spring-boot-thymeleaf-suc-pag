package com.smartcontact.smartcontactmanager.config;

import com.smartcontact.smartcontactmanager.entities.User;
import com.smartcontact.smartcontactmanager.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public class UserDetailsServiceImp implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User userByEmail = userRepository.findUserByEmail(username);

        if(userByEmail==null){
            throw new UsernameNotFoundException("User not found");
        }

        return new CustomUserDetail(userByEmail);
    }
}
