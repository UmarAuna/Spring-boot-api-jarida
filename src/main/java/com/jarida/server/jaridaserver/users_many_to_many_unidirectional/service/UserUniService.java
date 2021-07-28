package com.jarida.server.jaridaserver.users_many_to_many_unidirectional.service;

import com.jarida.server.jaridaserver.exception.ResourceBadRequestException;
import com.jarida.server.jaridaserver.exception.ResourceForbiddenException;
import com.jarida.server.jaridaserver.exception.ResourceNotFoundException;
import com.jarida.server.jaridaserver.exception.ResourceSuccessfulException;
import com.jarida.server.jaridaserver.users_many_to_many_unidirectional.model.UserUni;
import com.jarida.server.jaridaserver.users_many_to_many_unidirectional.repository.RoleUniRepository;
import com.jarida.server.jaridaserver.users_many_to_many_unidirectional.repository.UserUniRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserUniService {

    private UserUniRepository userUniRepository;
    private RoleUniRepository roleUniRepository;

    public UserUniService(UserUniRepository userUniRepository, RoleUniRepository roleUniRepository) {
        this.userUniRepository = userUniRepository;
        this.roleUniRepository = roleUniRepository;
    }

    /** Create a new User */
    public ResponseEntity<Object> createUserUni(UserUni model) {

        UserUni user = new UserUni();

        if (userUniRepository.findByEmail(model.getEmail()).isPresent()) {
            throw new ResourceBadRequestException ("The Email is already Present, Failed to Create new User");

        } else {
            user.setFirstName(model.getFirstName());
            user.setLastName(model.getLastName());
            user.setMobile(model.getMobile());
            user.setEmail(model.getEmail());
            user.setRolesUni(model.getRolesUni());

            UserUni savedUser = userUniRepository.save(user);
            if (userUniRepository.findById(savedUser.getId()).isPresent())
            return ResponseEntity.ok().body(savedUser);
            else throw new ResourceForbiddenException("Failed Creating User as Specified");
        }
    }


    /** Update an Existing User */
    @Transactional
    public ResponseEntity<Object> updateUser(UserUni user, Long id) {

        if(userUniRepository.findById(id).isPresent()) {

            UserUni newUser = userUniRepository.findById(id).get();
            newUser.setFirstName(user.getFirstName());
            newUser.setLastName(user.getLastName());
            newUser.setMobile(user.getMobile());
            newUser.setEmail(user.getEmail());
            newUser.setRolesUni(user.getRolesUni());

            UserUni savedUser = userUniRepository.save(newUser);

            if(userUniRepository.findById(savedUser.getId()).isPresent())
            return  ResponseEntity.ok().body(savedUser);
            else throw new ResourceBadRequestException("Failed updating the user specified");
        } else throw new ResourceNotFoundException("Cannot find the user specified");
    }

    /** Delete an User*/
    public ResponseEntity<Object> deleteUser(Long id) {
        if (userUniRepository.findById(id).isPresent()) {
            userUniRepository.deleteById(id);
            if (userUniRepository.findById(id).isPresent())
                throw new ResourceForbiddenException("Failed to Delete the specified User");
            else throw new ResourceSuccessfulException("Successfully deleted the specified user");
        } else throw new ResourceNotFoundException("Cannot find the user specified");


    }
}
