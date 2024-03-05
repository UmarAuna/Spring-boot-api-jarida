package com.jarida.server.jaridaserver.quranlyfeAPI.model;

import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;

@Entity
@Getter
@Setter
@ToString
@RequiredArgsConstructor
@Table(name = "quran_life_notification")
@AllArgsConstructor
@Builder
public class QuranLyfeNotification implements Serializable {

    @Id
    //@JsonIgnore
    @GeneratedValue(strategy = GenerationType.AUTO)
    //@Column(updatable = false, nullable = false)
    // @ApiModelProperty(hidden = true) // for swagger hidding not to show
    Long id;

    @Column(name = "title")
    String title;


    @Column(name = "message")
    String message;

    @Column(name = "intent")
    String intent;

    @Column(name = "url")
    private String url;


   /* @JsonInclude(JsonInclude.Include.NON_NULL)
    @Transient
    @JsonProperty
    @Nullable
    @Column(name = "format", nullable = true)
    private String format;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @Transient
    @JsonProperty
    @Nullable
    @Column(name = "url", nullable = true)
    private String url;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @Transient
    @JsonProperty
    @Nullable
    @Column(name = "name", nullable = true)
    private String name;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @Transient
    @JsonProperty
    @Nullable
    @Column(name = "secure_url", nullable = true)
    private String secure_url;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @Transient
    @JsonProperty
    @Nullable
    @Column(name = "file_name", nullable = true)
    private String fileName;*/

    @CreationTimestamp
    @ApiModelProperty(hidden = true) // for swagger hidding not to show
    @Column(updatable = false)
    Timestamp dateCreated;

    @UpdateTimestamp
    @ApiModelProperty(hidden = true) // for swagger hidding not to show
    Timestamp lastModified;

}
