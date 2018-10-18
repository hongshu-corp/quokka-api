package com.genesisfin.backend.web.controller;

import com.genesisfin.backend.web.model.User;
import com.genesisfin.backend.web.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
public class UsersController {

    @Autowired
    private UserRepository userRepository;

    @GetMapping
    public Page<User> index(@RequestParam(value = "page", defaultValue = "0") int page,
                            @RequestParam(value = "page_size", defaultValue = "20") int size) {
        PageRequest request = PageRequest.of(page, size, Sort.Direction.DESC, "id");

        return userRepository.findAll(request);
    }
}
