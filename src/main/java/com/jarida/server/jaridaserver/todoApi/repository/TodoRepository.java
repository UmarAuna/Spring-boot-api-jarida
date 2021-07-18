package com.jarida.server.jaridaserver.todoApi.repository;

import com.jarida.server.jaridaserver.todoApi.model.Todo;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TodoRepository extends CrudRepository<Todo, Long> {

    @Query("SELECT s FROM Todo s  WHERE s.title = ?1")
    Optional<Todo> findStudentByTitle(String title);
}
