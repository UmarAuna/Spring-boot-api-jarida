package com.jarida.server.jaridaserver.quranlyfeAPI.repository;

import com.jarida.server.jaridaserver.quranlyfeAPI.model.CountDownQuranLyfe;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CountDownQuranLyfeRepository extends JpaRepository<CountDownQuranLyfe, Long> {
}
