package com.genesisfin.backend.web.controller;

import com.genesisfin.backend.web.model.User;
import com.genesisfin.backend.web.repository.UserRepository;
import com.genesisfin.backend.web.viewmodel.PagedResult;
import com.genesisfin.backend.web.viewmodel.PagedResultHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDateTime;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/users")
public class UsersController {

    @Autowired
    private UserRepository userRepository;

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
        user.setCreatedTime(LocalDateTime.now());
        user.setUpdatedTime(LocalDateTime.now());
        user.setPassword(new BCryptPasswordEncoder().encode(user.getPassword()));

        return userRepository.save(user);
    }

    @PutMapping(path = "/{id}")
    public User update(@PathVariable Long id, @Valid @RequestBody User user) {
        Optional<User> existing= userRepository.findById(id);
        if (existing == null) { return new User(); }

        User originUser = existing.get();
        originUser.setName(user.getName());
        originUser.setPassword(new BCryptPasswordEncoder().encode(user.getPassword()));
        originUser.setUpdatedTime(LocalDateTime.now());
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
    public Optional<User> destroy(@PathVariable Long id) {
        Optional<User> user = userRepository.findById(id);
        if (user != null) {
            userRepository.delete(user.get());
        }
        return user;
    }
}
