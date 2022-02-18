package com.jarida.server.jaridaserver.spring_security_2.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.NaturalId;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Stream;

@Data
@Entity
@Table(name = "user_twos", uniqueConstraints = {
        @UniqueConstraint(columnNames = {"username"}),
        @UniqueConstraint(columnNames = {"email"}),
        @UniqueConstraint(columnNames = {"user_uuid"})
})
@DynamicUpdate
public class UserTwos extends DateAudit implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    @Column(name = "user_uuid")
    private String userUUID;

    
    @NotBlank
    @Column(name = "first_name")
    @Size(max = 40)
    private String firstName;

    @NotBlank
    @Column(name = "last_name")
    @Size(max = 40)
    private String lastName;

    @NotBlank
    @Column(name = "username")
    @Size(max = 15)
    private String username;

    @NotBlank
    @Column(name = "phone_number")
    @Size(max = 15)
    private String phoneNumber;

    @NotBlank
    @NaturalId
    @Size(max = 40)
    @Column(name = "email")
    @Email(message = "Email not properly formatted")
    private String email;

    @NotBlank
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @Size(max = 100)
    @Column(name = "password")
    private String password;

    //@JsonIgnore
    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(name = "user_role_twos",
            joinColumns =@JoinColumn(name = "user_twos_id", referencedColumnName = "id"),
            inverseJoinColumns =@JoinColumn(name = "role_twos_id", referencedColumnName = "id"))
    private Set<RoleTwos> roleTwos;

    @JsonIgnore
    @JsonManagedReference
    @OneToMany(mappedBy = "userTwos", cascade = CascadeType.ALL, fetch = FetchType.LAZY /*orphanRemoval = true*/)
    private Set<PostTwos> PostTwos;


    public UserTwos() {
    }


    @JsonIgnore
    public List<PostTwos> getPostTwos() {
        return PostTwos == null ? null : new ArrayList<>(PostTwos);
    }

    public int getPostTwosCount() {
        return PostTwos.size();
    }

    public Stream<Integer> getCommentTwosCount() {
        return PostTwos.stream().map( comment -> comment.getCommentTwos().size());
    }

}
