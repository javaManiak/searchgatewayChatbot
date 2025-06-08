package com.synergisticit.searchgateway.domain;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Setter
@Getter
@Entity
@Table(name="Users")
public class User {
    
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private long userId;

    private String userName;
    
    private String userPassword;
    
    private String email;
    
    public User() {
        super();
    }
    
    @JsonManagedReference
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name="user_role")
    private Set<Role> roles = new HashSet<>();


}
