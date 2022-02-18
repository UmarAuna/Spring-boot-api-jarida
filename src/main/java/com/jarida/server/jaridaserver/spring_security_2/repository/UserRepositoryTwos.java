package com.jarida.server.jaridaserver.spring_security_2.repository;

import com.jarida.server.jaridaserver.spring_security_2.entity.UserTwos;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepositoryTwos extends JpaRepository<UserTwos, Long> {
    Optional<UserTwos> findByEmail(String email);
    Optional<UserTwos> findByUsernameOrEmail(String username,String email);
    Optional<UserTwos> findByUsername(String username);
    Boolean existsByUsername(String username);
    Boolean existsByEmail(String email);

    @Query("SELECT s FROM UserTwos s  WHERE s.id = ?1")
    UserTwos findByUserId(long  title);

    @Query("SELECT s FROM UserTwos s  WHERE s.userUUID = ?1")
    Optional<UserTwos> findByUUID(String uuid);

    @Modifying
    @Query("UPDATE UserTwos s SET s.firstName = ?1, s.lastName = ?2 WHERE s.id = ?3")
    int updateName(String firstName, String lastName, Long userId);

    @Modifying
    @Query("UPDATE UserTwos s SET s.phoneNumber = ?1 WHERE s.id = ?2")
    void updatePhoneNumber(String phoneNumber, Long userId);

}
