package com.genesisfin.backend.web.controller;

import com.genesisfin.backend.web.model.User;
import com.genesisfin.backend.web.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/users")
public class UsersController {

    @Autowired
    private UserRepository userRepository;

    @GetMapping
    public List<User> index(@RequestParam(value = "page", defaultValue = "0") int page,
                            @RequestParam(value = "limit", defaultValue = "20") int limit) {
        if (--page < 0) { page = 0; }
        PageRequest request = PageRequest.of(page, limit, Sort.Direction.DESC, "id");

        return userRepository.findAll(request).getContent();
    }

    @PostMapping
    public void create(@Valid @RequestBody User user) {
        user.setCreatedTime(LocalDateTime.now());
        user.setUpdatedTime(LocalDateTime.now());
        user.setPassword(new BCryptPasswordEncoder().encode(user.getPassword()));

        userRepository.save(user);
    }
}
