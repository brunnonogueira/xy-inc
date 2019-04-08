package com.nogueira4j.xyinc.controllers.v1;

import com.nogueira4j.xyinc.api.v1.model.PoiDTO;
import com.nogueira4j.xyinc.api.v1.model.PoiDTOValidator;
import com.nogueira4j.xyinc.api.v1.model.PoiListDTO;
import com.nogueira4j.xyinc.services.PoiService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Api(description = "This is my POI Controller")
@RestController
@RequestMapping(PoiController.BASE_URL)
public class PoiController {

    public static final String BASE_URL = "/api/v1/pois";

    private PoiService poiService;

    @InitBinder("poiDTO")
    protected void initBinder(WebDataBinder binder) {
        binder.setValidator(new PoiDTOValidator());
    }

    @Autowired
    public PoiController(PoiService poiService){
        this.poiService = poiService;
    }

    @ApiOperation(value = "This will get a list of pois.", notes = "These are some notes about the API.")
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public PoiListDTO getListOfPois(){
        return new PoiListDTO(poiService.getAllPois());
    }

    @ApiOperation(value = "This will create a new poi.", notes = "These are some notes about the API.")
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public PoiDTO createNewPoi(@RequestBody @Valid PoiDTO customerDTO){
        return poiService.createNewPoi(customerDTO);
    }

    @ApiOperation(value = "This will get a list of nearby clients with the coordinate.", notes = "These are some notes about the API.")
    @GetMapping({"/{coordenadaX}&{coordenadaY}"})
    @ResponseStatus(HttpStatus.OK)
    public PoiListDTO getListOfPoisByProximity(@PathVariable int coordenadaX, @PathVariable int coordenadaY){
        return new PoiListDTO(poiService.getAllPoisByProximity(coordenadaX, coordenadaY));
    }
}
