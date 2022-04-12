package com.jarida.server.jaridaserver.quranlyfeAPI.controller;


import com.jarida.server.jaridaserver.exception.ResourceNotFoundException;
import com.jarida.server.jaridaserver.quranlyfeAPI.model.NamesOfAllah;
import com.jarida.server.jaridaserver.quranlyfeAPI.repository.NamesOfAllahRepository;
import com.jarida.server.jaridaserver.quranlyfeAPI.service.NamesOfAllahService;
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
@Api(tags = "REST API for getting 99 Names of Allah")
@SwaggerDefinition(tags = {
        @Tag(name = "QuranLyfe", description = "This is for getting 99 Names of Allah")
})
@CrossOrigin
public class NamesOfAllahController {
    NamesOfAllahService namesOfAllahService;

    NamesOfAllahRepository namesOfAllahRepository;

    @Autowired
    public NamesOfAllahController(NamesOfAllahService namesOfAllahService, NamesOfAllahRepository namesOfAllahRepository) {
        this.namesOfAllahService = namesOfAllahService;
        this.namesOfAllahRepository = namesOfAllahRepository;
    }

    @GetMapping("/namesofAllah")
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "This is for getting list of 99 Names of Allah")
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
    public List<NamesOfAllah> getNamesofAllah() {
        return namesOfAllahService.getNamesOfAllah();
    }

    @GetMapping("/namesofAllah/search")
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "This is for getting list of 99 Names of Allah")
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
    public ResponseEntity<List<NamesOfAllah>> getQueryNamesofAllah(
            @RequestParam String transliterationName
    ) {
        List<NamesOfAllah> namesOfAllah = new ArrayList<>(namesOfAllahRepository.findByTransliterationNameContaining(transliterationName));

        if(namesOfAllah.isEmpty()) {
            throw new ResourceNotFoundException("No Name Found");
        }

        return new ResponseEntity<>(namesOfAllah, HttpStatus.OK);
    }


}
