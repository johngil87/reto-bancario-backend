package co.com.sofka.jpa.persona;

import co.com.sofka.jpa.entities.PersonaEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.QueryByExampleExecutor;

public interface PersonaJpaRepository extends CrudRepository<PersonaEntity, String> , QueryByExampleExecutor<PersonaEntity> {

}
