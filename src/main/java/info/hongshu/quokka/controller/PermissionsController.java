package info.hongshu.quokka.controller;

import info.hongshu.quokka.repository.PermissionRepository;
import info.hongshu.quokka.model.Permission;
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
