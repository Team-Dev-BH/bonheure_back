package com.bonheure.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.bonheure.domain.User;
import com.bonheure.repository.UserRepository;

 

@Service
public class MyUserDetails implements UserDetailsService {

  @Autowired
  private UserRepository userRepository;

  @Override
  public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
    final User user = userRepository.findByEmail(email);

    if (user == null) {
      throw new UsernameNotFoundException("User '" + email + "' not found");
    }

    return org.springframework.security.core.userdetails.User//
            .withUsername(email)//
            .password(user.getPassword())//
            .authorities(user.getRole())//
            .accountExpired(false)//
            .accountLocked(false)//
            .credentialsExpired(false)//
            .disabled(false)//
            .build();
    
 
  }

}
