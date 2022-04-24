package com.jarida.server.jaridaserver.quranlyfeAPI.repository;

import com.jarida.server.jaridaserver.quranlyfeAPI.model.AppUpdateQuranLyfe;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AppUpdateQuranLyfeRepository extends JpaRepository<AppUpdateQuranLyfe, Long> {
}
