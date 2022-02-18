package com.jarida.server.jaridaserver.spring_security_2.repository;

import com.jarida.server.jaridaserver.spring_security_2.entity.CommentTwos;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentRepositoryTwos extends JpaRepository<CommentTwos, Long> {
    List<CommentTwos> findByPostTwosId(Long PostTwosId);
}