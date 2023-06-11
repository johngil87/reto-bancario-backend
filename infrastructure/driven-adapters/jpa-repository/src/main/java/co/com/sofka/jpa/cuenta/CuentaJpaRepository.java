package co.com.sofka.jpa.cuenta;

import co.com.sofka.jpa.entities.CuentaEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.QueryByExampleExecutor;

public interface CuentaJpaRepository extends CrudRepository<CuentaEntity, String>, QueryByExampleExecutor<CuentaEntity> {
}
