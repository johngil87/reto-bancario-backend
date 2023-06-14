package co.com.sofka.jpa.movimientos;

import co.com.sofka.jpa.entities.MovimientosEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.repository.query.QueryByExampleExecutor;



public interface MovimientosJpaRepository extends CrudRepository<MovimientosEntity, Integer>, QueryByExampleExecutor<MovimientosEntity> {

    @Query(
            value = "SELECT * FROM  movimientos WHERE fecha = (SELECT MAX(fecha) FROM  movimientos WHERE id_cuenta = :id)",
            nativeQuery = true
    )
    MovimientosEntity findLastMovedByIdAcound(@Param("id") Integer id);

}
