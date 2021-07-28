package com.jarida.server.jaridaserver.users_many_to_many_bidirectional.repository;

import com.jarida.server.jaridaserver.users_many_to_many_bidirectional.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findByName(String name);
}
