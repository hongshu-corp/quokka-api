package com.genesisfin.backend.web.repository;

import com.genesisfin.backend.web.model.User;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends IRepository<User, Long> {
    User findByEmail(String email);

}
