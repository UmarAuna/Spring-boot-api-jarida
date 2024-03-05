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
@Table(name = "count_down_quran_life")
@AllArgsConstructor
@Builder
public class CountDownQuranLyfe implements Serializable {

    @Id
    @JsonIgnore
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    //@Column(updatable = false, nullable = false)
    @ApiModelProperty(hidden = true) // for swagger hidding not to show
    Long id;

    @Column(name = "end_date_time")
    String endDateTime;

    @Column(name = "show_card")
    String showCard;

    @Column(name = "success_message")
    String successMessage;

    @Column(name = "count_down_message")
    String countDownMessage;

}
