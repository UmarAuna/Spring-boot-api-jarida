package com.jarida.server.jaridaserver.quranlyfeAPI.service;

import com.jarida.server.jaridaserver.quranlyfeAPI.model.QuranLyfe;
import com.jarida.server.jaridaserver.quranlyfeAPI.repository.QuranLyfeRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class QuranLyfeService {

    QuranLyfeRepository quranLyfeRepository;

    public QuranLyfeService(QuranLyfeRepository quranLyfeRepository) {
        this.quranLyfeRepository = quranLyfeRepository;
    }

    public List<QuranLyfe> getAllDuas() {
        return new ArrayList<>(quranLyfeRepository.findAll());
    }
}
