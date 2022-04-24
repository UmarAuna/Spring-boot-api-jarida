package com.jarida.server.jaridaserver.quranlyfeAPI.service;

import com.jarida.server.jaridaserver.quranlyfeAPI.model.AppUpdateQuranLyfe;
import com.jarida.server.jaridaserver.quranlyfeAPI.model.CountDownQuranLyfe;
import com.jarida.server.jaridaserver.quranlyfeAPI.model.QuranLyfe;
import com.jarida.server.jaridaserver.quranlyfeAPI.repository.AppUpdateQuranLyfeRepository;
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

    AppUpdateQuranLyfeRepository appUpdateQuranLyfeRepository;

    public QuranLyfeService(QuranLyfeRepository quranLyfeRepository, CountDownQuranLyfeRepository countDownQuranLyfeRepository, AppUpdateQuranLyfeRepository appUpdateQuranLyfeRepository) {
        this.quranLyfeRepository = quranLyfeRepository;
        this.countDownQuranLyfeRepository = countDownQuranLyfeRepository;
        this.appUpdateQuranLyfeRepository = appUpdateQuranLyfeRepository;
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

    public Optional<AppUpdateQuranLyfe> updateAppVersion(AppUpdateQuranLyfe appUpdateQuranLyfe) {
        return appUpdateQuranLyfeRepository.findById(3L).map(version ->{
            version.setVersionCode(appUpdateQuranLyfe.getVersionCode());

            return appUpdateQuranLyfeRepository.save(version);
        });
    }

    public Optional<CountDownQuranLyfe> getCountDown() {
        return countDownQuranLyfeRepository.findById(3L);
    }

    public Optional<AppUpdateQuranLyfe> getAppVersion() {
        return appUpdateQuranLyfeRepository.findById(3L);
    }


}
