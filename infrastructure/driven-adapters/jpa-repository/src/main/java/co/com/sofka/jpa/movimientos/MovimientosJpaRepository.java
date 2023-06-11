package co.com.sofka.jpa.movimientos;

import co.com.sofka.jpa.entities.MovimientosEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.QueryByExampleExecutor;

public interface MovimientosJpaRepository extends CrudRepository<MovimientosEntity, Integer>, QueryByExampleExecutor<MovimientosEntity> {
}
