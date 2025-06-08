package com.synergisticit.searchgateway.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;


@Setter
@Getter
@Entity
public class Role {
    
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private int roleId;
    
    private String roleName;
    
    @JsonBackReference // Mark the back side of the relationship to prevent serialization
    @ManyToMany(mappedBy="roles")
    Set<User> user = new HashSet<>();

    public Role() {
        super();
    }
    
}