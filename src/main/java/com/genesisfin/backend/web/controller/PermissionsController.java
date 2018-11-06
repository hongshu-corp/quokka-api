package com.genesisfin.backend.web.controller;

import com.genesisfin.backend.web.model.Permission;
import com.genesisfin.backend.web.repository.PermissionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/permissions")
public class PermissionsController extends ControllerBase<Permission, PermissionRepository> {

    @Autowired
    public PermissionsController(PermissionRepository repository) {
        this.repository = repository;
    }
}
