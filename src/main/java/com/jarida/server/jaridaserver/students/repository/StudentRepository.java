package com.jarida.server.jaridaserver.students.repository;

import com.jarida.server.jaridaserver.students.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {

    @Query("SELECT s FROM Student s  WHERE s.email = ?1")
    Optional<Student> findStudentByEmail(String email);

    @Query("SELECT s FROM Student s  WHERE s.id = :id")
    Optional<Student> findStudentById(Long id);
}
