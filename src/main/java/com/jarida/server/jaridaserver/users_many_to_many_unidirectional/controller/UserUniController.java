package com.jarida.server.jaridaserver.users_many_to_many_unidirectional.controller;


import com.jarida.server.jaridaserver.exception.ResourceNotFoundException;
import com.jarida.server.jaridaserver.users_many_to_many_unidirectional.model.UserUni;
import com.jarida.server.jaridaserver.users_many_to_many_unidirectional.repository.UserUniRepository;
import com.jarida.server.jaridaserver.users_many_to_many_unidirectional.service.UserUniService;
import io.swagger.annotations.Api;
import io.swagger.annotations.SwaggerDefinition;
import io.swagger.annotations.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v2")
@Validated
@Api(tags = "User Many to Many Unidirectional API v2")
@SwaggerDefinition(tags = {
        @Tag(name = "User", description = "This is for getting  User Unidirectional Api")
})
public class UserUniController {
    //https://dzone.com/articles/introduction-to-spring-data-jpa-part-7-unidirectio
    //https://github.com/gudpick/jpa-demo/tree/many-to-many-unidirecctional-starter/src/main/java/com/notyfyd/service
    private UserUniService userUniService;
    private UserUniRepository userUniRepository;

    public UserUniController(UserUniService userUniService, UserUniRepository userUniRepository) {
        this.userUniService = userUniService;
        this.userUniRepository = userUniRepository;
    }

    @PostMapping("/user-uni/create")
    public ResponseEntity<Object> createUserUni(@RequestBody UserUni userUni) {
        return userUniService.createUserUni(userUni);
    }

    @GetMapping("/user-uni/details/{id}")
    public UserUni getUser(@PathVariable Long id) {
        if(userUniRepository.findById(id).isPresent())
        return userUniRepository.findById(id).get();
        else throw new ResourceNotFoundException("Cannot find the user specified");
    }

    @GetMapping("/user-uni/all")
    public List<UserUni> getUsers() {
        return userUniRepository.findAll();
    }

    @PutMapping("/user-uni/update/{id}")
    public ResponseEntity<Object> updateUser(@PathVariable Long id, @RequestBody UserUni user) {
        return userUniService.updateUser(user, id);
    }

    @DeleteMapping("user-uni/delete/{id}")
    public ResponseEntity<Object> deleteUser(@PathVariable Long id) {
        return userUniService.deleteUser(id);
    }














}
