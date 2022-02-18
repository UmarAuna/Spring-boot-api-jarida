package com.jarida.server.jaridaserver.planetsApi.controller;


import com.jarida.server.jaridaserver.exception.ResourceNotFoundException;
import com.jarida.server.jaridaserver.planetsApi.model.Planets;
import com.jarida.server.jaridaserver.planetsApi.repository.PlanetsRepository;
import com.jarida.server.jaridaserver.planetsApi.service.PlanetsService;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/v1")
@Validated
@Api(tags = "REST API for getting list of planets")
@SwaggerDefinition(tags = {
        @Tag(name = "Planet", description = "This is for getting  Planet Api")
})
@CrossOrigin
public class PlanetsController {

    PlanetsService planetService;

    PlanetsRepository planetsRepository;

    @Autowired
    public PlanetsController(PlanetsService planetService, PlanetsRepository planetsRepository) {
        this.planetService = planetService;
        this.planetsRepository = planetsRepository;
    }

    @GetMapping("/planets")
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "This is for getting list of planets")
    @ApiResponses(value = {
            @io.swagger.annotations.ApiResponse(code = 200, message = "Successful"),
            @io.swagger.annotations.ApiResponse(code = 400, message = "Invalid request"),
            @io.swagger.annotations.ApiResponse(code = 401, message = "Unauthorized: token has expired or is not valid"),
            @io.swagger.annotations.ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
            @io.swagger.annotations.ApiResponse(code = 404, message = "The resource you were trying to reach is not found"),
            @io.swagger.annotations.ApiResponse(code = 409, message = "The resource you were trying to reach exist/conflict"),
            @io.swagger.annotations.ApiResponse(code = 415, message = "Unsupported Media Type"),
            @io.swagger.annotations.ApiResponse(code = 500, message = "Internal server error")
    })
    public List<Planets> getAllPlanets() {
        return  planetService.getPlants();
    }

    @GetMapping("/planets/search")
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "This is for getting list of planets")
    @ApiResponses(value = {
            @io.swagger.annotations.ApiResponse(code = 200, message = "Successful"),
            @io.swagger.annotations.ApiResponse(code = 400, message = "Invalid request"),
            @io.swagger.annotations.ApiResponse(code = 401, message = "Unauthorized: token has expired or is not valid"),
            @io.swagger.annotations.ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
            @io.swagger.annotations.ApiResponse(code = 404, message = "The resource you were trying to reach is not found"),
            @io.swagger.annotations.ApiResponse(code = 409, message = "The resource you were trying to reach exist/conflict"),
            @io.swagger.annotations.ApiResponse(code = 415, message = "Unsupported Media Type"),
            @io.swagger.annotations.ApiResponse(code = 500, message = "Internal server error")
    })
    public ResponseEntity<List<Planets>> getQueryPlanets(@RequestParam(required = true) String planetName) {

        List<Planets> planets = new ArrayList<>(planetsRepository.findByPlanetNameContaining(planetName));

        if(planets.isEmpty()) {
            throw new ResourceNotFoundException(planetName + " is not a Planet name");
        }

        return new ResponseEntity<>(planets, HttpStatus.OK);
    }


}
