package com.jarida.server.jaridaserver.users_many_to_many_unidirectional.model;

import javax.persistence.*;
import java.util.List;

@Entity
public class UserUni {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String firstName;

    private String lastName;

    private String mobile;

    @Column(unique = true)
    private String email;

    @ManyToMany(targetEntity = RoleUni.class,cascade = CascadeType.ALL)
    private List<RoleUni> rolesUni;
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<RoleUni> getRolesUni() {
        return rolesUni;
    }

    public void setRolesUni(List<RoleUni> rolesUni) {
        this.rolesUni = rolesUni;
    }
}
