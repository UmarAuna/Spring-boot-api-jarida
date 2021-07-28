package com.jarida.server.jaridaserver.users_many_to_many_unidirectional.controller;

import com.jarida.server.jaridaserver.exception.ResourceNotFoundException;
import com.jarida.server.jaridaserver.users_many_to_many_unidirectional.model.RoleUni;
import com.jarida.server.jaridaserver.users_many_to_many_unidirectional.repository.RoleUniRepository;
import com.jarida.server.jaridaserver.users_many_to_many_unidirectional.service.RoleUniService;
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
@Api(tags = "Role Many to Many Unidirectional API v2")
@SwaggerDefinition(tags = {
        @Tag(name = "Role", description = "This is for getting  Role Unidirectional Api")
})
public class RoleUniController {

    private RoleUniService roleUniService;
    private RoleUniRepository roleUniRepository;

    public RoleUniController(RoleUniService roleUniService, RoleUniRepository roleUniRepository) {
        this.roleUniService = roleUniService;
        this.roleUniRepository = roleUniRepository;
    }

    @GetMapping("/role-uni/all")
    public List<RoleUni> getRoles() {
        return roleUniRepository.findAll();
    }

    @PostMapping("/role-uni/create")
    public ResponseEntity<Object> createRole(@RequestBody RoleUni role) {
        return  roleUniService.addRole(role);
    }

    @DeleteMapping("/role-uni/delete/{id}")
    public ResponseEntity<Object> deleteRole(@PathVariable Long id) {
        return roleUniService.deleteRole(id);
    }

    @GetMapping("/role-uni/details/{id}")
    public RoleUni getRole(@PathVariable Long id) {
        if(roleUniRepository.findById(id).isPresent())
        return roleUniRepository.findById(id).get();
        else throw new ResourceNotFoundException("No Role Found");
    }

    @PutMapping("/role-uni/update/{id}")
    public ResponseEntity<Object> updateRole(@PathVariable Long id, @RequestBody RoleUni role) {
        return roleUniService.updateRole(id, role);
    }














}
