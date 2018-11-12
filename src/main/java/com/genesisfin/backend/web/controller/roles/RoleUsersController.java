package com.genesisfin.backend.web.controller.roles;

import com.genesisfin.backend.web.model.Role;
import com.genesisfin.backend.web.model.User;
import com.genesisfin.backend.web.repository.RoleRepository;
import com.genesisfin.backend.web.repository.UserRepository;
import com.genesisfin.backend.web.viewmodel.PagedResult;
import com.genesisfin.backend.web.viewmodel.PagedResultHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.web.bind.annotation.*;

import javax.persistence.criteria.Join;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Predicate;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/roles/{role_id}")
public class RoleUsersController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    // todo: may be we can abstract one base controller
    // for the role id => change the name to nested_id, something like that.
    @GetMapping(path = "/users")
    public PagedResult<User> index(@PathVariable("role_id") Long roleId,
                                   @RequestParam(value = "page", defaultValue = "0") int page,
                                   @RequestParam(value = "limit", defaultValue = "20") int limit) {
        Optional<Role> role = roleRepository.findById(roleId);
        if (role == null) {
            return new PagedResult<>();
        }

        Specification<User> specification = (Specification<User>) (root, query, cb) -> {
            Join<User, Role> join = root.join("roles", JoinType.INNER);
            Predicate predicate = cb.equal(join.get("id"), roleId);

            return cb.and(predicate);
        };


        if (--page < 0) {
            page = 0;
        }
        PageRequest request = PageRequest.of(page, limit, Sort.Direction.DESC, "id");
        Page<User> list = userRepository.findAll(specification, request);

        return PagedResultHelper.from(list);
    }

    @PostMapping(path = "/users")
    public User create(@PathVariable("role_id")Long roleId) {
        return new User();
    }

    @DeleteMapping(path = "/users/{id}")
    public void destroy(@PathVariable("role_id")Long roleId, @PathVariable("id") Long id) {
        Optional<Role> role = roleRepository.findById(roleId);
        Optional<User> optionUser = userRepository.findById(id);

        if (role.isPresent() && optionUser.isPresent()) {
            User user = optionUser.get();
            List<Role> roles = user.getRoles();
            roles.removeIf(x -> x.getId() == role.get().getId());

            user.setRoles(roles);
            userRepository.save(user);
        }
    }
}
