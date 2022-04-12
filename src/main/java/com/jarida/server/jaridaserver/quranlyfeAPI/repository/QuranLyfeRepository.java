package com.jarida.server.jaridaserver.quranlyfeAPI.repository;

import com.jarida.server.jaridaserver.quranlyfeAPI.model.QuranLyfe;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QuranLyfeRepository extends JpaRepository<QuranLyfe, Long> {
    List<QuranLyfe> findByCategoryContaining(String category);
}
