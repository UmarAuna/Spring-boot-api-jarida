package com.jarida.server.jaridaserver.quranlyfeAPI.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Getter
@Setter
@ToString
@RequiredArgsConstructor
@Table(name = "app_update_quran_life")
@AllArgsConstructor
@Builder
public class AppUpdateQuranLyfe implements Serializable {

    @Id
    @JsonIgnore
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(updatable = false, nullable = false)
    @ApiModelProperty(hidden = true) // for swagger hidding not to show
    Long id;

    @Column(name = "url")
    String url;

    @Column(name = "version_code")
    String versionCode;


}
