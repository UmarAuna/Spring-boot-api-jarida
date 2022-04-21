package com.jarida.server.jaridaserver.quranlyfeAPI.service;

import com.jarida.server.jaridaserver.quranlyfeAPI.model.CountDownQuranLyfe;
import com.jarida.server.jaridaserver.quranlyfeAPI.model.QuranLyfe;
import com.jarida.server.jaridaserver.quranlyfeAPI.repository.CountDownQuranLyfeRepository;
import com.jarida.server.jaridaserver.quranlyfeAPI.repository.QuranLyfeRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class QuranLyfeService {

    QuranLyfeRepository quranLyfeRepository;

    CountDownQuranLyfeRepository countDownQuranLyfeRepository;

    public QuranLyfeService(QuranLyfeRepository quranLyfeRepository, CountDownQuranLyfeRepository countDownQuranLyfeRepository) {
        this.quranLyfeRepository = quranLyfeRepository;
        this.countDownQuranLyfeRepository = countDownQuranLyfeRepository;
    }

    public List<QuranLyfe> getAllDuas() {
        return new ArrayList<>(quranLyfeRepository.findAll());
    }

    public Optional<CountDownQuranLyfe> updateCountDown(CountDownQuranLyfe countDownQuranLyfe) {
        return countDownQuranLyfeRepository.findById(3L).map(countDown ->{
            countDown.setEndDateTime(countDownQuranLyfe.getEndDateTime());
            countDown.setShowCard(countDownQuranLyfe.getShowCard());
            countDown.setSuccessMessage(countDownQuranLyfe.getSuccessMessage());
            countDown.setCountDownMessage(countDownQuranLyfe.getCountDownMessage());

            return countDownQuranLyfeRepository.save(countDown);
        });
    }

    public Optional<CountDownQuranLyfe> getCountDown() {
        return countDownQuranLyfeRepository.findById(3L);
    }


}
