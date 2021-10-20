package com.jarida.server.jaridaserver.users_many_to_many_bidirectional.service;

import com.jarida.server.jaridaserver.exception.ResourceBadRequestException;
import com.jarida.server.jaridaserver.exception.ResourceForbiddenException;
import com.jarida.server.jaridaserver.exception.ResourceNotFoundException;
import com.jarida.server.jaridaserver.exception.ResourceSuccessfulException;
import com.jarida.server.jaridaserver.users_many_to_many_bidirectional.model.User;
import com.jarida.server.jaridaserver.users_many_to_many_bidirectional.repository.RoleRepository;
import com.jarida.server.jaridaserver.users_many_to_many_bidirectional.repository.UserRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserService {
    private UserRepository userRepository;
    private RoleRepository roleRepository;

    public UserService(UserRepository userRepository, RoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
    }

    /** Create a new User */
    public ResponseEntity<Object> createUser(User model) {
        User user = new User();
        if (userRepository.findByEmail(model.getEmail()).isPresent()) {
            throw new ResourceBadRequestException("The Email is already Present, Failed to Create new User");
        } else {
            user.setFirstName(model.getFirstName());
            user.setLastName(model.getLastName());
            user.setMobile(model.getMobile());
            user.setEmail(model.getEmail());
            user.setRoles(model.getRoles());

            User savedUser = userRepository.save(user);
            if (userRepository.findById(savedUser.getId()).isPresent())
                return ResponseEntity.ok().body(savedUser);
            else throw new ResourceForbiddenException("Failed Creating User as Specified");
        }
    }

    /** Update an Existing User */
    @Transactional
    public ResponseEntity<Object> updateUser(User user, Long id) {
        if(userRepository.findById(id).isPresent()) {
            User newUser = userRepository.findById(id).get();
            newUser.setFirstName(user.getFirstName());
            newUser.setLastName(user.getLastName());
            newUser.setMobile(user.getMobile());
            newUser.setEmail(user.getEmail());
            newUser.setRoles(user.getRoles());
            User savedUser = userRepository.save(newUser);
            if(userRepository.findById(savedUser.getId()).isPresent())
                return  ResponseEntity.ok().body(savedUser);
            else throw new ResourceBadRequestException("Failed updating the user specified");
        } else throw new ResourceNotFoundException("Cannot find the user specified");
    }

    /** Delete an User*/
    public ResponseEntity<Object> deleteUser(Long id) {
        if (userRepository.findById(id).isPresent()) {
            userRepository.deleteById(id);
            if (userRepository.findById(id).isPresent())
                throw new ResourceForbiddenException("Failed to Delete the specified User");
            else throw new ResourceSuccessfulException("Successfully deleted the specified user");
        } else throw new ResourceNotFoundException("Cannot find the user specified");
    }

}
