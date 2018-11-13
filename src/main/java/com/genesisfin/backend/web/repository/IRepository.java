package com.genesisfin.backend.web.repository;

import com.genesisfin.backend.web.model.ModelBase;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface IRepository<T extends ModelBase, ID> extends JpaRepository<T, ID>, JpaSpecificationExecutor<T> {
}
