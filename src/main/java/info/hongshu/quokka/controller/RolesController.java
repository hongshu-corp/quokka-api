package info.hongshu.quokka.controller;

import info.hongshu.quokka.model.Role;
import info.hongshu.quokka.repository.RoleRepository;
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

