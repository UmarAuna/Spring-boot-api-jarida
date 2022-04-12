package com.jarida.server.jaridaserver.quranlyfeAPI.controller;


import com.jarida.server.jaridaserver.exception.ResourceNotFoundException;
import com.jarida.server.jaridaserver.quranlyfeAPI.model.QuranLyfe;
import com.jarida.server.jaridaserver.quranlyfeAPI.repository.QuranLyfeRepository;
import com.jarida.server.jaridaserver.quranlyfeAPI.service.QuranLyfeService;
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
@Api(tags = "REST API for getting Dua's from the Quran and Hadith")
@SwaggerDefinition(tags = {
        @Tag(name = "QuranLyfe", description = "This is for getting Dua's from the Quran and Hadith")
})
@CrossOrigin
public class QuranLyfeController {

    QuranLyfeService quranLyfeService;

    QuranLyfeRepository quranLyfeRepository;

    @Autowired
    public QuranLyfeController(QuranLyfeService quranLyfeService, QuranLyfeRepository quranLyfeRepository) {
        this.quranLyfeService = quranLyfeService;
        this.quranLyfeRepository = quranLyfeRepository;
    }

    @GetMapping("/quranlyfe")
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "This is for getting list of Dua's from the Quran and Hadith")
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
    public List<QuranLyfe> getAllDuas() {
        return quranLyfeService.getAllDuas();
    }

    @GetMapping("/quranlyfe/search")
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "This is for getting list of Dua's from the Quran and Hadith")
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
    public ResponseEntity<List<QuranLyfe>> getQueryDuas(
            @RequestParam String categories
    ) {
        List<QuranLyfe> quranLyfe = new ArrayList<>(quranLyfeRepository.findByCategoryContaining(categories));

        if(quranLyfe.isEmpty()) {
            throw new ResourceNotFoundException("No category found");
        }
        return new ResponseEntity<>(quranLyfe, HttpStatus.OK);
    }



}
