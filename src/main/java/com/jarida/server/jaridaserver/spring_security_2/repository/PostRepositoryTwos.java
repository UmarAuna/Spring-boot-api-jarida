package com.jarida.server.jaridaserver.spring_security_2.repository;

import com.jarida.server.jaridaserver.spring_security_2.entity.PostTwos;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PostRepositoryTwos extends JpaRepository<PostTwos, Long> {
    //@Query("SELECT s FROM UserTwos, PostTwos s  WHERE s.id = s.id")
    // Optional<UserTwos> findByUserId(Long userId);

    Page<PostTwos> findByUserTwosId(Long userId, Pageable pageable);
}
