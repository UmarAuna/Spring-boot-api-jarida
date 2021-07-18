package com.jarida.server.jaridaserver.tutor_one_2_many.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Entity
@Table(name = "course")
public class Course extends AuditModel implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id",insertable = false, updatable = false, unique = true)
    private Long id;

    @Column(name = "title")
    @NotNull(message = "Tittle is mandatory")
    @NotEmpty(message = "Title is mandatory" )
    private String title;

    @JsonBackReference
    @ManyToOne(cascade = CascadeType.PERSIST, fetch = FetchType.LAZY)
    @JoinColumn(name = "instructor_id")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Instructor instructor;

    public Course() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Instructor getInstructor() {
        return instructor;
    }

    public void setInstructor(Instructor instructor) {
        this.instructor = instructor;
    }

    @Override
    public String toString() {
        return "Course{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", instructor=" + instructor +
                '}';
    }
}
