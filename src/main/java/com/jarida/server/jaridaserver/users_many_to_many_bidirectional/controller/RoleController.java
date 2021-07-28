package com.jarida.server.jaridaserver.users_many_to_many_bidirectional.controller;


import com.jarida.server.jaridaserver.exception.ResourceNotFoundException;
import com.jarida.server.jaridaserver.users_many_to_many_bidirectional.model.Role;
import com.jarida.server.jaridaserver.users_many_to_many_bidirectional.repository.RoleRepository;
import com.jarida.server.jaridaserver.users_many_to_many_bidirectional.service.RoleService;
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
@Api(tags = "Role Many to Many Bidirectional API v2")
@SwaggerDefinition(tags = {
        @Tag(name = "Role", description = "This is for getting  Role Bidirectional Api")
})
public class RoleController {

    private RoleService roleService;
    private RoleRepository roleRepository;

    public RoleController(RoleService roleService, RoleRepository roleRepository) {
        this.roleService = roleService;
        this.roleRepository = roleRepository;
    }

    @PostMapping("/role/create")
    public ResponseEntity<Object> createRole(@RequestBody Role role) {
        return  roleService.addRole(role);
    }

    @DeleteMapping("/role/delete/{id}")
    public ResponseEntity<Object> deleteRole(@PathVariable Long id) {
        return roleService.deleteRole(id);
    }

    @GetMapping("/role/details/{id}")
    public Role getRole(@PathVariable Long id) {
        if(roleRepository.findById(id).isPresent())
            return roleRepository.findById(id).get();
        else throw new ResourceNotFoundException("No Role Found");
    }

    @GetMapping("/role/all")
    public List<Role> getRoles() {
        return roleRepository.findAll();
    }

    @PutMapping("/role/update/{id}")
    public ResponseEntity<Object> updateRole(@PathVariable Long id, @RequestBody Role role) {
        return roleService.updateRole(id, role);
    }

}
