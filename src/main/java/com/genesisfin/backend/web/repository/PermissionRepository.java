package com.genesisfin.backend.web.repository;

import com.genesisfin.backend.web.model.Permission;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PermissionRepository extends JpaRepository<Permission, Long> {
}
