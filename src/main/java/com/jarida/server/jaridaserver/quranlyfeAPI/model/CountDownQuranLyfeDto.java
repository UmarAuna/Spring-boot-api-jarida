package com.jarida.server.jaridaserver.quranlyfeAPI.model;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

@Data
public class CountDownQuranLyfeDto implements Serializable {

    @ApiModelProperty(value = "EndDateTime")
    private String endDateTime;

    @ApiModelProperty(value = "ShowCard")
    private String showCard;

    @ApiModelProperty(value = "SuccessMessage")
    private String successMessage;

    @ApiModelProperty(value = "CountDownMessage")
    private String countDownMessage;
}
