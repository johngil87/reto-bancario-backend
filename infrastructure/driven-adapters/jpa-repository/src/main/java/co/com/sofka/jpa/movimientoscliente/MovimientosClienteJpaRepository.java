package co.com.sofka.jpa.movimientoscliente;

import co.com.sofka.jpa.entities.MovimientosClienteEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.repository.query.QueryByExampleExecutor;

import java.util.Date;
import java.util.List;

public interface MovimientosClienteJpaRepository extends CrudRepository<MovimientosClienteEntity, Integer>, QueryByExampleExecutor<MovimientosClienteEntity> {

    @Query(
            value = "SELECT MO.ID_MOVIMIENTO AS IDMOVIMIENTO, MO.TIPO, MO.VALOR, MO.SALDO, MO.FECHA, CLI.NOMBRE, " +
                    "CU.SALDO_INICIAL AS SALDOINICIAL, CU.ESTADO, CU.NUMERO_CUENTA AS NUMEROCUENTA, CU.TIPO_CUENTA as TIPOCUENTA " +
                    "FROM MOVIMIENTOS MO " +
                    "INNER JOIN CUENTA CU ON MO.ID_CUENTA = CU.NUMERO_CUENTA " +
                    "INNER JOIN CLIENTE CLI ON CLI.IDENTIFICACION = CU.ID_CLIENTE " +
                    "WHERE CLI.IDENTIFICACION = :id " +
                    "AND MO.FECHA BETWEEN :fechaInicial AND :fechaFinal ",
            nativeQuery = true
    )
    List<MovimientosClienteEntity> listMovedByClient(@Param("id") String id, @Param("fechaInicial") Date fechaInicial, @Param("fechaFinal") Date fechaFinal);
}
