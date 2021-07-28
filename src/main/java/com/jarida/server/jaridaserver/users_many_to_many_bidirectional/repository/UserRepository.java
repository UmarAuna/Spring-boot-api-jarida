package com.jarida.server.jaridaserver.users_many_to_many_bidirectional.repository;

import com.jarida.server.jaridaserver.users_many_to_many_bidirectional.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);

   /* @Query(value = "DELETE FROM t_user_roles where users_id= :user_id", nativeQuery = true)
    void deleteRelation(@Param("user_id") Long user_id);*/
}
