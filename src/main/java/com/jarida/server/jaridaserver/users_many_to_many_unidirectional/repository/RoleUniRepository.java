package com.jarida.server.jaridaserver.users_many_to_many_unidirectional.repository;

import com.jarida.server.jaridaserver.users_many_to_many_unidirectional.model.RoleUni;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleUniRepository extends JpaRepository<RoleUni, Long> {
    Optional<RoleUni> findByName(String name);
}
