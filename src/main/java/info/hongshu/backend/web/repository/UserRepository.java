package info.hongshu.backend.web.repository;

import info.hongshu.backend.web.model.User;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends IRepository<User, Long> {
    User findByEmail(String email);

}
