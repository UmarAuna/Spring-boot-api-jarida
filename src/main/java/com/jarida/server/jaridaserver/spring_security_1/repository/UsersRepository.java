package com.jarida.server.jaridaserver.spring_security_1.repository;

import com.jarida.server.jaridaserver.spring_security_1.model.UserDao;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsersRepository extends CrudRepository<UserDao, Integer> {
    UserDao findByUsername(String username);
}
