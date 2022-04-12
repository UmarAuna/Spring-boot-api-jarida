package com.jarida.server.jaridaserver.planetsApi.service;

import com.jarida.server.jaridaserver.planetsApi.model.Planets;
import com.jarida.server.jaridaserver.planetsApi.repository.PlanetsRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PlanetsService {
    PlanetsRepository planetRepository;

    //@Autowired
    public PlanetsService(PlanetsRepository planetRepository) {
        this.planetRepository = planetRepository;
    }

    public List<Planets> getPlanets() {
        return new ArrayList<>(planetRepository.findAll());
    }
}
