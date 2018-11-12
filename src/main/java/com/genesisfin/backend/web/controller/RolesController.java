package com.genesisfin.backend.web.controller;

import com.genesisfin.backend.web.model.Role;
import com.genesisfin.backend.web.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/roles")
public class RolesController extends ControllerBase<Role, RoleRepository> {

    @Autowired
    public RolesController(RoleRepository repository) {
        this.repository = repository;
    }

}

