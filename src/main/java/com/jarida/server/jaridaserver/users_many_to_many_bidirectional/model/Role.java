package com.jarida.server.jaridaserver.users_many_to_many_bidirectional.model;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import javax.persistence.*;
import java.util.List;

@Entity
@JsonIdentityInfo(
        generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "id")
public class    Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String description;

    @ManyToMany(targetEntity = UserInfo.class, mappedBy = "roles", cascade = {CascadeType.PERSIST,
            CascadeType.DETACH,CascadeType.MERGE,CascadeType.REFRESH})
    private List<UserInfo> users;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<UserInfo> getUsers() {
        return users;
    }

    public void setUsers(List<UserInfo> users) {
        this.users = users;
    }
}

