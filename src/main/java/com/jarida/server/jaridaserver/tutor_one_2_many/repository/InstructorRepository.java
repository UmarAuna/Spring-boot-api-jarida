package com.jarida.server.jaridaserver.tutor_one_2_many.repository;

import com.jarida.server.jaridaserver.tutor_one_2_many.model.Instructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;

@Repository
public interface InstructorRepository extends JpaRepository<Instructor, Long> {

}
