package info.hongshu.backend.web.controller;

import info.hongshu.backend.web.model.User;
import info.hongshu.backend.web.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/users")
public class UsersController extends ControllerBase<User, UserRepository> {

    public final static String DEFAULT_EMAIL = "admin@hongshu.io";

    @Autowired
    public UsersController(UserRepository userRepository){
        this.repository = userRepository;
    }

    @Override
    protected void beforeCreate(User user){
        EncryptPassword(user);
    }

    @Override
    protected void beforeUpdate(User user) {
        EncryptPassword(user);
    }

    private void EncryptPassword(User user) {
        user.setPassword(new BCryptPasswordEncoder().encode(user.getPassword()));
    }

//    @PutMapping
//    @Override
//    public User update(@Valid @RequestBody User user) {
//        Optional<User> existing = userRepository.findById(user.getId());
//        if (existing == null) {
//            return new User();
//        }
//
//        User originUser = existing.get();
//        originUser.setName(user.getName());
//        originUser.setPassword(new BCryptPasswordEncoder().encode(user.getPassword()));
//        originUser.setEmail(user.getEmail());
//
//        userRepository.save(originUser);
//
//        return originUser;
//    }


    @PostMapping(path = "/default")
    public String createDefaultUser() {
        User user = repository.findByEmail(DEFAULT_EMAIL);
        if (user != null) {
            return "User already exists";
        }

        String password = UUID.randomUUID().toString().replaceAll("-", "").substring(0, 8);

        user = new User().setEmail(DEFAULT_EMAIL).setName("admin").setPassword(new BCryptPasswordEncoder().encode(password));
        repository.save(user);

        return password;
    }
}
