package com.jarida.server.jaridaserver.jarida.repository;

import com.jarida.server.jaridaserver.jarida.model.Jarida;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface JaridaRepository extends JpaRepository<Jarida, Long> {
    List<Jarida> findByTitleContaining(String title);
}
