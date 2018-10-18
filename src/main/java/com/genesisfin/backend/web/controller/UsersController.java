package com.genesisfin.backend.web.controller;

import com.genesisfin.backend.web.model.User;
import com.genesisfin.backend.web.repository.UserRepository;
import com.genesisfin.backend.web.viewmodel.UserView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/users")
public class UsersController {

    @Autowired
    private UserRepository userRepository;

    @GetMapping
    public List<User> index(@RequestParam(value = "page", defaultValue = "0") int page,
                            @RequestParam(value = "page_size", defaultValue = "20") int size) {
        PageRequest request = PageRequest.of(page, size, Sort.Direction.DESC, "id");

        return userRepository.findAll(request).getContent();
    }

    @PostMapping
    public void create(@Valid @RequestBody UserView userView) {
        User user = new User();
        user.setName(userView.getName())
                .setEmail(userView.getEmail())
                .setPassword(new BCryptPasswordEncoder().encode(userView.getPassword()))
                .setUpdatedTime(new Date())
                .setUpdatedTime(new Date());

        userRepository.save(user);
    }
}
