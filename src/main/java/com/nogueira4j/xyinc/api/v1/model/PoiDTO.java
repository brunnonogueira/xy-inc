package com.nogueira4j.xyinc.api.v1.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PoiDTO {

    @ApiModelProperty(value = "This is the name of poi", required = true)
    @NotNull
    private String name;
    @ApiModelProperty(value = "This is the coordenada X of poi", required = true)
    @NotNull
    @Min(0)
    private int coordenadaX;
    @ApiModelProperty(value = "This is the coordenada Y of poi", required = true)
    @NotNull
    @Min(0)
    private int coordenadaY;

    @JsonProperty("poi_url")
    private String poiUrl;
}
