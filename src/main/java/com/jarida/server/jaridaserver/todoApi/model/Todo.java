package com.jarida.server.jaridaserver.todoApi.model;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.sql.Timestamp;

@Entity
@Data
@Table
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Todo {
    @Id
    @GeneratedValue
    //@Column(updatable = false, nullable = false)
    Long id;

    @NotNull(message = "Title cannot be empty")
    @NotBlank(message = "Title cannot be empty")
    @Column
    String title;

    @NotNull(message = "Description cannot be empty")
    @NotBlank(message = "Description cannot be empty")
    @Column
    String description;

   /* @NotNull(message = "Status cannot be empty")
    @NotBlank(message = "Status cannot be empty")*/
    @Column
    TodoStatus todoStatus;

    @CreationTimestamp
    @ApiModelProperty(hidden = true) // for swagger hidding not to show
    @Column(updatable = false)
    Timestamp dateCreated;

    @UpdateTimestamp
    @ApiModelProperty(hidden = true) // for swagger hidding not to show
    Timestamp lastModified;
}
