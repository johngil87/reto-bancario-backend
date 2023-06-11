package co.com.sofka.jpa.cliente;

import co.com.sofka.jpa.entities.ClienteEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.QueryByExampleExecutor;

public interface ClienteJpaRepository extends CrudRepository<ClienteEntity, String>, QueryByExampleExecutor<ClienteEntity> {
}
