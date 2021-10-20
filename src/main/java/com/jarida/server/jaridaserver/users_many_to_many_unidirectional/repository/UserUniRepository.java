package com.jarida.server.jaridaserver.users_many_to_many_unidirectional.repository;

import com.jarida.server.jaridaserver.users_many_to_many_unidirectional.model.UserUni;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserUniRepository extends JpaRepository<UserUni, Long> {
    Optional<UserUni> findByEmail(String email);
}
