package com.jarida.server.jaridaserver.quranlyfeAPI.repository;

import com.jarida.server.jaridaserver.quranlyfeAPI.model.NamesOfAllah;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NamesOfAllahRepository extends JpaRepository<NamesOfAllah, Long> {
    List<NamesOfAllah> findByTransliterationNameContaining(String transliterationName);
}
