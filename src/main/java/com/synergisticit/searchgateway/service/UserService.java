package com.synergisticit.searchgateway.service;


import com.synergisticit.searchgateway.domain.Role;
import com.synergisticit.searchgateway.domain.User;
import com.synergisticit.searchgateway.repository.RoleRepository;
import com.synergisticit.searchgateway.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    @Autowired
    UserRepository userRepository;
    @Autowired
    RoleRepository roleRepository;

    public List<User> findAll() {
        return userRepository.findAll();
    }
    
    public User save(User u) {
        HashSet<Role> roleSet = new HashSet<>();
        Role userRole = roleRepository.findById(1).orElse(null);
        roleSet.add(userRole);
        u.setRoles(roleSet);
        // hard code for test
        u.setEmail("your-email@gmail.com");
        User user = userRepository.save(u);
        return user;
    }
    
    public User findByUserId(long uId) {
        Optional<User> u = userRepository.findById(uId);
        if(u.isPresent()) {
            return u.get();
        }else
            return null;
    }
    
    public void deleteUserById(long uId) {
        userRepository.deleteById(uId);
    }
    
    public Optional<User> findByUserName(String userName) {
        return userRepository.findByUserName(userName);
    }
}