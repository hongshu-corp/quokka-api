package com.genesisfin.backend.web.controller;

import com.genesisfin.backend.web.model.Role;
import com.genesisfin.backend.web.repository.RoleRepository;
import com.genesisfin.backend.web.viewmodel.PagedResult;
import com.genesisfin.backend.web.viewmodel.PagedResultHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/roles")
public class RolesController {

    private final RoleRepository roleRepository;

    @Autowired
    public RolesController(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @GetMapping
    public PagedResult<Role> index(@RequestParam(value = "page", defaultValue = "0") int page,
                                   @RequestParam(value = "limit", defaultValue = "20") int limit) {
        if (--page < 0) {
            page = 0;
        }
        PageRequest request = PageRequest.of(page, limit, Sort.Direction.DESC, "id");

        return PagedResultHelper.from(roleRepository.findAll(request));
    }

    @PostMapping
    public Role create(@Valid @RequestBody Role role) {
//        role.setCreatedTime(LocalDateTime.now());
//        role.setUpdatedTime(LocalDateTime.now());

        return roleRepository.save(role);
    }

    @PutMapping
    public Role update(@Valid @RequestBody Role role) {
        Optional<Role> existing = roleRepository.findById(role.getId());
        if (existing == null) {
            return new Role();
        }

        Role originRole = existing.get();
        originRole.setName(role.getName());
//        originRole.setUpdatedTime(LocalDateTime.now());

        roleRepository.save(originRole);

        return originRole;
    }

    @PatchMapping(path = "/{id}")
    public Role partialUpdate(@PathVariable Long id, @RequestBody Map<String, Object> updates) {
        // TODO: implement partial update via reflection
        return roleRepository.findById(id).get();
    }

    @GetMapping(path = "/{id}")
    public Optional<Role> show(@PathVariable Long id) {
        return roleRepository.findById(id);
    }

    @DeleteMapping(path = "/{id}")
    public void destroy(@PathVariable Long id) {
        Optional<Role> role = roleRepository.findById(id);
        if (role != null) {
            roleRepository.delete(role.get());
        }
    }
}
