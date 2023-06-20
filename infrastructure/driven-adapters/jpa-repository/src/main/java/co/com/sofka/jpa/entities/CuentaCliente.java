package co.com.sofka.jpa.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
public class CuentaCliente {

    @Id
    @Column(name = "NUMEROCUENTA")
    private Integer numeroCuenta;
    @Column(name = "TIPOCUENTA")
    private String tipoCuenta;
    @Column(name = "SALDOINICIAL")
    private Double saldoInicial;
    @Column(name = "ESTADO")
    private boolean estado;
    @Column(name = "SALDODISPONIBLE")
    private Double saldoDisponible;
    @Column(name = "DIRECCION")
    private String direccion;
    @Column(name = "NOMBRE")
    private String nombres;
    @Column(name = "GENERO")
    private String genero;
    @Column(name = "EDAD")
    private Integer edad;
    @Column(name = "TELEFONO")
    private String telefono;
    @Column(name = "CONTRASENA")
    private String contrasena;
    @Column(name = "ESTADOCLIENTE")
    private boolean estadoCliente;
    @Column(name = "IDCLIENTE")
    private Integer idCliente;
    @Column(name = "IDENTIFICACION")
    private String identificacion;
}
