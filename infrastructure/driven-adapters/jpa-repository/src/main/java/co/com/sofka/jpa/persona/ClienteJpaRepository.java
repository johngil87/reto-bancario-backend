package co.com.sofka.jpa.persona;

import co.com.sofka.jpa.entities.ClienteEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.QueryByExampleExecutor;

public interface ClienteJpaRepository extends CrudRepository<ClienteEntity, Integer> , QueryByExampleExecutor<ClienteEntity> {

    Iterable<ClienteEntity> getByIdentificacion(String identificacion);
}
