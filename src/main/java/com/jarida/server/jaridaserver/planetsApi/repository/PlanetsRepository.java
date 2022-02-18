package com.jarida.server.jaridaserver.planetsApi.repository;

import com.jarida.server.jaridaserver.planetsApi.model.Planets;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PlanetsRepository extends JpaRepository<Planets, Long> {
    List<Planets> findByPlanetNameContaining(String planetName);
}
