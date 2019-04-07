package com.nogueira4j.xyinc.api.v1.model;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PoiListDTO {

    @ApiModelProperty(value = "This is list of pois")
    List<PoiDTO> pois;
}
