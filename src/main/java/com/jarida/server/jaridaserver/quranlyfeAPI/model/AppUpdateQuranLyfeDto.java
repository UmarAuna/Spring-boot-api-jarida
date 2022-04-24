package com.jarida.server.jaridaserver.quranlyfeAPI.model;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

@Data
public class AppUpdateQuranLyfeDto implements Serializable {

    @ApiModelProperty(value = "versionCode")
    private String versionCode;
}
