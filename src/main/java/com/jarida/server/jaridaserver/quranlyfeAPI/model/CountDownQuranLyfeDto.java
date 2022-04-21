package com.jarida.server.jaridaserver.quranlyfeAPI.model;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Data
public class CountDownQuranLyfeDto implements Serializable {

    @ApiModelProperty(value = "EndDateTime")
    @NotEmpty(message = "End Date Time should not be null or empty")
    @NotNull(message = "End Date Time should not be null or empty")
    private String endDateTime;

    @ApiModelProperty(value = "ShowCard")
    @NotEmpty(message = "Show Card should not be null or empty")
    @NotNull(message = "Show Card should not be null or empty")
    private ShowCard showCard;

    @ApiModelProperty(value = "SuccessMessage")
    @NotEmpty(message = "Success Message should not be null or empty")
    @NotNull(message = "Success Message should not be null or empty")
    private String successMessage;

    @ApiModelProperty(value = "CountDownMessage")
    @NotEmpty(message = "Count Down Message should not be null or empty")
    @NotNull(message = "Count Down Message should not be null or empty")
    private String countDownMessage;
}
