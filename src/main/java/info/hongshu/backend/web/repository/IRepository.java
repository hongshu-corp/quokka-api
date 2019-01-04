package info.hongshu.backend.web.repository;

import info.hongshu.backend.web.model.ModelBase;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface IRepository<T extends ModelBase, ID> extends JpaRepository<T, ID>, JpaSpecificationExecutor<T> {
}
