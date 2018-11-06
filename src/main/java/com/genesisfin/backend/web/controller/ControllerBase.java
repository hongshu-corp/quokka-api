package com.genesisfin.backend.web.controller;

import com.genesisfin.backend.web.model.ModelBase;
import com.genesisfin.backend.web.viewmodel.PagedResult;
import com.genesisfin.backend.web.viewmodel.PagedResultHelper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.web.bind.annotation.*;

import javax.persistence.Column;
import javax.validation.Valid;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Map;
import java.util.Optional;

public abstract class ControllerBase<T extends ModelBase, TRepository extends JpaRepository<T, Long>> {

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

        setProperties(model, original);

        this.beforeUpdate(original);
        this.beforeSave(original);
        repository.save(original);

        return original;
    }

    private void setProperties(T model, T original) {
        if (model == null) return;

        for (Field field : model.getClass().getDeclaredFields()) {
            if (field.isAnnotationPresent(Column.class)) {
                String name = field.getName();
                name = name.substring(0, 1).toUpperCase() + name.substring(1);

                Method getMethod = null;
                Method setMethod = null;
                try {
                    getMethod = model.getClass().getMethod("get" + name);
                    Object value = getMethod.invoke(model);
                    setMethod = model.getClass().getMethod("set" + name, value.getClass());
                } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
                }

                if (getMethod == null || setMethod == null) {
                    continue;
                }

                try {
                    setMethod.invoke(original, getMethod.invoke(model));
                } catch (IllegalAccessException | InvocationTargetException e) {
                    e.printStackTrace();
                }
            }
        }

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
