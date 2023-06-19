package co.com.sofka.jpa.cuentacliente;

import co.com.sofka.jpa.entities.CuentaCliente;
import co.com.sofka.jpa.entities.CuentaEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.repository.query.QueryByExampleExecutor;

public interface CuentaClienteJpaRepository extends CrudRepository<CuentaCliente, Integer>, QueryByExampleExecutor<CuentaCliente> {

    @Query(
            value = "SELECT CT.NUMERO_CUENTA AS NUMEROCUENTA, CT.TIPO_CUENTA AS TIPOCUENTA, CT.SALDO_INICIAL AS SALDOINICIAL, " +
                    "CT.ESTADO AS ESTADO, CT.ID_CLIENTE AS IDCLIENTE, CT.SALDO_DISPONIBLE AS SALDODISPONIBLE,CL.DIRECCION, CL.NOMBRE, " +
                    "CL.GENERO, CL.EDAD, CL.TELEFONO, CL.CONTRASENA, CL.ESTADO AS ESTADOCLIENTE, CL.IDENTIFICACION " +
                    "FROM CUENTA CT " +
                    "INNER JOIN CLIENTE CL ON CL.ID_CLIENTE = CT.ID_CLIENTE " +
                    "WHERE CL.IDENTIFICACION = :id ",
            nativeQuery = true
    )
    Iterable<CuentaCliente> listCuentaClient(@Param("id") String id);

    @Query(
            value = "SELECT CT.NUMERO_CUENTA AS NUMEROCUENTA, CT.TIPO_CUENTA AS TIPOCUENTA, CT.SALDO_INICIAL AS SALDOINICIAL, " +
                    "CT.ESTADO, CT.ID_CLIENTE AS IDCLIENTE, CT.SALDO_DISPONIBLE AS SALDODISPONIBLE, CL.DIRECCION, CL.NOMBRE, " +
                    "CL.GENERO, CL.EDAD, CL.TELEFONO, CL.CONTRASENA, CL.ESTADO AS ESTADOCLIENTE,  CL.IDENTIFICACION " +
                    "FROM CUENTA CT " +
                    "INNER JOIN CLIENTE CL ON CL.ID_CLIENTE = CT.ID_CLIENTE " +
                    "WHERE CL.IDENTIFICACION = :id AND CL.CONTRASENA = :pass AND CT.NUMERO_CUENTA = :idCuenta ",
            nativeQuery = true
    )
    Iterable<CuentaCliente> cuentaClient(@Param("id") String id, @Param("pass") String pass, @Param("idCuenta") Integer idCuenta);
}
