package info.hongshu.backend.web.controller;

import info.hongshu.backend.web.model.Permission;
import info.hongshu.backend.web.repository.PermissionRepository;
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
