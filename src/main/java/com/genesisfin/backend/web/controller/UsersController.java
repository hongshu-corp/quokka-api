package com.genesisfin.backend.web.controller;

import com.genesisfin.backend.web.model.User;
import com.genesisfin.backend.web.repository.UserRepository;
import com.genesisfin.backend.web.viewmodel.PagedResult;
import com.genesisfin.backend.web.viewmodel.PagedResultHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/users")
public class UsersController {

    public final static String DEFAULT_EMAIL = "admin@hongshu.io";

    private final UserRepository userRepository;

    @Autowired
    public UsersController(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    @GetMapping
    public PagedResult<User> index(@RequestParam(value = "page", defaultValue = "0") int page,
                                   @RequestParam(value = "limit", defaultValue = "20") int limit) {
        if (--page < 0) {
            page = 0;
        }
        PageRequest request = PageRequest.of(page, limit, Sort.Direction.DESC, "id");

        return PagedResultHelper.from(userRepository.findAll(request));
    }

    @PostMapping
    public User create(@Valid @RequestBody User user) {
        user.setPassword(new BCryptPasswordEncoder().encode(user.getPassword()));

        return userRepository.save(user);
    }

    @PutMapping
    public User update(@Valid @RequestBody User user) {
        Optional<User> existing = userRepository.findById(user.getId());
        if (existing == null) {
            return new User();
        }

        User originUser = existing.get();
        originUser.setName(user.getName());
        originUser.setPassword(new BCryptPasswordEncoder().encode(user.getPassword()));
        originUser.setEmail(user.getEmail());

        userRepository.save(originUser);

        return originUser;
    }

    @PatchMapping(path = "/{id}")
    public User partialUpdate(@PathVariable Long id, @RequestBody Map<String, Object> updates) {
        // TODO: implement partial update via reflection
        // Rails is great!!
        return userRepository.findById(id).get();
    }

    @GetMapping(path = "/{id}")
    public Optional<User> show(@PathVariable Long id) {
        return userRepository.findById(id);
    }

    @DeleteMapping(path = "/{id}")
    public void destroy(@PathVariable Long id) {
        Optional<User> user = userRepository.findById(id);
        if (user != null) {
            userRepository.delete(user.get());
        }
    }

    @PostMapping(path = "/default")
    public String createDefaultUser() {
        User user = userRepository.findByEmail(DEFAULT_EMAIL);
        if (user != null) {
            return "User already exists";
        }

        String password = UUID.randomUUID().toString().replaceAll("-", "").substring(0, 8);

        user = new User().setEmail(DEFAULT_EMAIL).setName("admin").setPassword(new BCryptPasswordEncoder().encode(password));
        userRepository.save(user);

        return password;
    }
}
