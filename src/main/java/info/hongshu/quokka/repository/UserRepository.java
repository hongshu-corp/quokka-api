package info.hongshu.quokka.repository;

import info.hongshu.quokka.model.User;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends IRepository<User, Long> {
    User findByEmail(String email);

}
