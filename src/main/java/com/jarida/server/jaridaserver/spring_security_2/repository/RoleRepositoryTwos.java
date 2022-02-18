package com.jarida.server.jaridaserver.spring_security_2.repository;

import com.jarida.server.jaridaserver.spring_security_2.entity.RoleTwos;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepositoryTwos extends JpaRepository<RoleTwos, Long> {
    Optional<RoleTwos> findByName(String name);
}
