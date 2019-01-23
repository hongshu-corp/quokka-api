package info.hongshu.quokka.repository;

import info.hongshu.quokka.model.ModelBase;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface IRepository<T extends ModelBase, ID> extends JpaRepository<T, ID>, JpaSpecificationExecutor<T> {
}
