package com.jarida.server.jaridaserver.users_many_to_many_bidirectional.service;

import com.jarida.server.jaridaserver.exception.ResourceBadRequestException;
import com.jarida.server.jaridaserver.exception.ResourceForbiddenException;
import com.jarida.server.jaridaserver.exception.ResourceNotFoundException;
import com.jarida.server.jaridaserver.exception.ResourceSuccessfulException;
import com.jarida.server.jaridaserver.users_many_to_many_bidirectional.model.Role;
import com.jarida.server.jaridaserver.users_many_to_many_bidirectional.model.User;
import com.jarida.server.jaridaserver.users_many_to_many_bidirectional.repository.RoleRepository;
import com.jarida.server.jaridaserver.users_many_to_many_bidirectional.repository.UserRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class RoleService {

    private RoleRepository roleRepository;
    private UserRepository userRepository;

    public RoleService(RoleRepository roleRepository, UserRepository userRepository) {
        this.roleRepository = roleRepository;
        this.userRepository = userRepository;
    }

    /** Create a new role  */
    @Transactional
    public ResponseEntity<Object> addRole(Role role)  {

        Role newRole = new Role();
        newRole.setName(role.getName());
        newRole.setDescription(role.getDescription());
        List<Role> roleList = new ArrayList<>();
        roleList.add(newRole);
        for(int i=0; i< role.getUsers().size(); i++){
            if(!userRepository.findByEmail(role.getUsers().get(i).getEmail()).isPresent()) {
                User newUser = role.getUsers().get(i);
                newUser.setRoles(roleList);
                User savedUser = userRepository.save(newUser);
                if(! userRepository.findById(savedUser.getId()).isPresent())
                    throw new ResourceBadRequestException("Role Creation Failed");
            }
            else throw new ResourceBadRequestException("User with email Id is already Present");
        }
        return ResponseEntity.ok().body(newRole);
    }

    /** Delete a specified role given the id */
    public ResponseEntity<Object> deleteRole(Long id) {
        if(roleRepository.findById(id).isPresent()){
            if(roleRepository.getOne(id).getUsers().size() == 0) {
                roleRepository.deleteById(id);
                if (roleRepository.findById(id).isPresent()) {
                    throw new ResourceBadRequestException("Failed to delete the specified record");
                } else throw new ResourceSuccessfulException("Successfully deleted specified record");
            } else throw new ResourceForbiddenException("Failed to delete, Please delete the users associated with this role");
        } else throw new ResourceNotFoundException("No Records Found");
    }



    /** Update a Role */
    public ResponseEntity<Object> updateRole(Long id, Role role) {
        if(roleRepository.findById(id).isPresent()){
            Role newRole = roleRepository.findById(id).get();
            newRole.setName(role.getName());
            newRole.setDescription(role.getDescription());
            Role savedRole = roleRepository.save(newRole);
            if(roleRepository.findById(savedRole.getId()).isPresent())
                return ResponseEntity.ok().body(savedRole);
            else throw new ResourceBadRequestException("Failed to update Role");

        } else throw new ResourceNotFoundException("Specified Role not found");
    }
}
