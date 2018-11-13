package com.genesisfin.backend.web.controller;

import com.genesisfin.backend.web.helper.ReflectionHelper;
import com.genesisfin.backend.web.model.ModelBase;
import com.genesisfin.backend.web.repository.IRepository;
import com.genesisfin.backend.web.viewmodel.PagedResult;
import com.genesisfin.backend.web.viewmodel.PagedResultHelper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Map;
import java.util.Optional;

public abstract class ControllerBase<T extends ModelBase, TRepository extends IRepository<T, Long>> {

    protected TRepository repository;

    public ControllerBase() {
    }

    @GetMapping
    public PagedResult<T> index(@RequestParam(value = "page", defaultValue = "0") int page,
                                @RequestParam(value = "limit", defaultValue = "20") int limit) {
        if (--page < 0) {
            page = 0;
        }
        PageRequest request = PageRequest.of(page, limit, Sort.Direction.DESC, "id");

        Page<T> list = repository.findAll(request);

        return PagedResultHelper.from(list);
    }


    @PostMapping
    public T create(@Valid @RequestBody T model) {
        this.beforeCreate(model);
        this.beforeSave(model);
        return repository.save(model);
    }

    @PutMapping
    public T update(@Valid @RequestBody T model) {
        Optional<T> existing = repository.findById(model.getId());
        if (existing == null) {
            return model;
        }

        T original = existing.get();

        ReflectionHelper.copyProperties(model, original);

        this.beforeUpdate(original);
        this.beforeSave(original);
        repository.save(original);

        return original;
    }

    @PatchMapping(path = "/{id}")
    public T partialUpdate(@PathVariable Long id, @RequestBody Map<String, Object> updates) {
        // TODO: implement partial update via reflection
        return repository.findById(id).get();
    }

    @GetMapping(path = "/{id}")
    public Optional<T> show(@PathVariable Long id) {
        return repository.findById(id);
    }

    @DeleteMapping(path = "/{id}")
    public void destroy(@PathVariable Long id) {
        Optional<T> role = repository.findById(id);
        if (role != null) {
            repository.delete(role.get());
        }
    }

    protected void beforeCreate(T model) {

    }

    protected void beforeUpdate(T model) {

    }

    protected void beforeSave(T model) {

    }
}
