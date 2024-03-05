package com.jarida.server.jaridaserver.students.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import java.time.LocalDate;
import java.time.Period;
import java.util.UUID;

@Entity
@Table
public class Student {



    @Id
   /* @SequenceGenerator(
            name = "student_sequence",
            sequenceName = "student_sequence",
            allocationSize = 1
    )*/
    /*@GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "student_sequence"
    )*/
    @GeneratedValue(generator = "uuid4")
    @GenericGenerator(name = "UUID", strategy = "uuid4")
    @Type(type = "org.hibernate.type.UUIDCharType")
    //@Column(columnDefinition = "CHAR(36)", unique = true)
    // @ApiModelProperty(hidden = true) // for swagger hidding not to show
    private UUID id = UUID.randomUUID();

    @NotNull(message = "Name cannot be empty")
    @NotBlank(message = "Name cannot be empty")
    private String name;

    @NotNull(message = "Email cannot be empty")
    @NotBlank(message = "Email cannot be empty")
    private String email;

    @Past(message="date of birth must be less than today")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate dob;

    @Transient
    private Integer age;

    /*
     * There’re two kinds of LOB: BLOB and CLOB:
     * BLOB is for storing binary data
     * CLOB is for storing text data
     * */
    //@Lob
    private byte[] data;

    public Student() {
    }

    public Student(UUID id, String name, String email, LocalDate dob, byte[] data) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.dob = dob;
        this.data = data;
    }

    public Student(String name, String email, LocalDate dob) {
        this.name = name;
        this.email = email;
        this.dob = dob;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public LocalDate getDob() {
        return dob;
    }

    public void setDob(LocalDate dob) {
        this.dob = dob;
    }

    public Integer getAge() {
        return Period.between(this.dob, LocalDate.now()).getYears();
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public byte[] getData() {
        return data;
    }

    public void setData(byte[] data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", dob=" + dob +
                ", age=" + age +
                '}';
    }
}
