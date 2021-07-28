package com.jarida.server.jaridaserver.users_many_to_many_unidirectional.service;

import com.jarida.server.jaridaserver.exception.ResourceBadRequestException;
import com.jarida.server.jaridaserver.exception.ResourceForbiddenException;
import com.jarida.server.jaridaserver.exception.ResourceNotFoundException;
import com.jarida.server.jaridaserver.exception.ResourceSuccessfulException;
import com.jarida.server.jaridaserver.users_many_to_many_unidirectional.model.RoleUni;
import com.jarida.server.jaridaserver.users_many_to_many_unidirectional.repository.RoleUniRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class RoleUniService {

    private RoleUniRepository roleUniRepository;

    public RoleUniService(RoleUniRepository roleUniRepository) {
        this.roleUniRepository = roleUniRepository;
    }

    /** Create a new role  */
    public ResponseEntity<Object> addRole(RoleUni role)  {

        RoleUni newRole = new RoleUni();
        newRole.setName(role.getName());
        newRole.setDescription(role.getDescription());

        RoleUni savedRole = roleUniRepository.save(newRole);

        if(roleUniRepository.findById(savedRole.getId()).isPresent()) {
            return ResponseEntity.ok().body(savedRole);
        } else throw new ResourceForbiddenException("Failed to Create specified Role");
    }

    /** Delete a specified role given the id */
    public ResponseEntity<Object> deleteRole(Long id) {

        if(roleUniRepository.findById(id).isPresent()){

            roleUniRepository.deleteById(id);
            if(roleUniRepository.findById(id).isPresent()){
                throw new ResourceForbiddenException("Failed to delete the specified record");
            } else throw new ResourceSuccessfulException("Successfully deleted specified record");
        } else throw new ResourceNotFoundException("No Records Found");
    }

    /** Update a Role */
    public ResponseEntity<Object> updateRole(Long id, RoleUni role) {
        if(roleUniRepository.findById(id).isPresent()){
            RoleUni newRole = roleUniRepository.findById(id).get();
            newRole.setName(role.getName());
            newRole.setDescription(role.getDescription());

            RoleUni savedRole = roleUniRepository.save(newRole);

            if(roleUniRepository.findById(savedRole.getId()).isPresent())
            return ResponseEntity.ok().body(savedRole);
            else throw new ResourceBadRequestException("Failed to update Role");
        } else throw new ResourceNotFoundException("Specified Role not found");
    }

}

