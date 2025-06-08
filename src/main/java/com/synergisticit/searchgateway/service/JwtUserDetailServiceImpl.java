package com.synergisticit.searchgateway.service;

import com.synergisticit.searchgateway.domain.Role;
import com.synergisticit.searchgateway.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Service
public class JwtUserDetailServiceImpl implements UserDetailsService {
    
    @Autowired
    UserService userService;
    
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> user = userService.findByUserName(username);
        if(user.isEmpty()) {
            throw new UsernameNotFoundException(username);
        }
        Set<GrantedAuthority> ga = new HashSet<>();
        Set<Role> roles = user.get().getRoles();
        for (Role role : roles) {
//            System.out.println("role.getRoleName()" + role.getRoleName());
            ga.add(new SimpleGrantedAuthority(role.getRoleName()));
        }
        return new org.springframework.security.core.userdetails.User(user.get().getUserName(), user.get().getUserPassword(), ga);
    }
}
