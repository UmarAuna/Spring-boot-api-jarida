package com.jarida.server.jaridaserver.users_many_to_many_bidirectional.controller;


import com.jarida.server.jaridaserver.exception.ResourceNotFoundException;
import com.jarida.server.jaridaserver.users_many_to_many_bidirectional.model.UserInfo;
import com.jarida.server.jaridaserver.users_many_to_many_bidirectional.repository.UserRepository;
import com.jarida.server.jaridaserver.users_many_to_many_bidirectional.service.UserService;
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
@Api(tags = "User Many to Many Bidirectional API v2")
@SwaggerDefinition(tags = {
        @Tag(name = "User", description = "This is for getting  User Bidirectional Api")
})
public class UserController {
    //https://dzone.com/articles/introduction-to-spring-data-jpa-part-8-many-to-man
    //https://github.com/gudpick/jpa-demo/blob/many-to-many-bidirectional-refactor-delete-cascade/src/main/java/com/notyfyd/controller/RoleController.java
    private UserService userService;
    private UserRepository userRepository;

    public UserController(UserService userService, UserRepository userRepository) {
        this.userService = userService;
        this.userRepository = userRepository;
    }

    @PostMapping("/user/create")
    public ResponseEntity<Object> createUser(@RequestBody UserInfo user) {
        return userService.createUser(user);
    }

    @GetMapping("/user/details/{id}")
    public UserInfo getUser(@PathVariable Long id) {
        if(userRepository.findById(id).isPresent())
            return userRepository.findById(id).get();
        else throw new ResourceNotFoundException("Cannot find the user specified");
    }

    @GetMapping("/user/all")
    public List<UserInfo> getUsers() {
        return userRepository.findAll();
    }

    @PutMapping("/user/update/{id}")
    public ResponseEntity<Object> updateUser(@PathVariable Long id, @RequestBody UserInfo user) {
        return userService.updateUser(user, id);
    }

    @DeleteMapping("user/delete/{id}")
    public ResponseEntity<Object> deleteUser(@PathVariable Long id) {
        return userService.deleteUser(id);
    }
}
