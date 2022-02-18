package com.jarida.server.jaridaserver.spring_security_2.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name= "role_twos")
public class RoleTwos {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(length =60)
    private String name;

}
