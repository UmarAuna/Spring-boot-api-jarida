package com.jarida.server.jaridaserver.quranlyfeAPI.service;

import com.jarida.server.jaridaserver.quranlyfeAPI.model.NamesOfAllah;
import com.jarida.server.jaridaserver.quranlyfeAPI.repository.NamesOfAllahRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class NamesOfAllahService {

    NamesOfAllahRepository namesOfAllahRepository;

    public NamesOfAllahService(NamesOfAllahRepository namesOfAllahRepository) {
        this.namesOfAllahRepository = namesOfAllahRepository;
    }

    public List<NamesOfAllah> getNamesOfAllah() {
        return new ArrayList<>(namesOfAllahRepository.findAll());
    }
}
