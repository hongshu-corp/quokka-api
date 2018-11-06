package com.genesisfin.backend.web.model;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import java.util.Collection;

@Entity
@Getter
@Setter
@Accessors(chain = true)
@Table(name = "permissions")
public class Permission extends ModelBase {
    private String name;

    @ManyToMany(mappedBy = "permissions")
    private Collection<Role> roles;
}
