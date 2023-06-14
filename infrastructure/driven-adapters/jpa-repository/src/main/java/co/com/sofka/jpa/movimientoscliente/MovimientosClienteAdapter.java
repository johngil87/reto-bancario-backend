package co.com.sofka.jpa.movimientoscliente;

import co.com.sofka.jpa.entities.MovimientosClienteEntity;
import co.com.sofka.jpa.helper.AdapterOperations;
import co.com.sofka.jpa.mapper.MovimientoMapper;
import co.com.sofka.model.movimientoscliente.MovimientosCliente;
import co.com.sofka.model.movimientoscliente.gateways.MovimientosClienteRepository;
import org.reactivecommons.utils.ObjectMapper;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;

import java.util.Date;
import java.util.function.Function;

@Repository
public class MovimientosClienteAdapter  extends AdapterOperations<MovimientosCliente, MovimientosClienteEntity, Integer, MovimientosClienteJpaRepository> implements MovimientosClienteRepository {


    protected MovimientosClienteAdapter(MovimientosClienteJpaRepository repository, ObjectMapper mapper) {
        super(repository, mapper, d-> mapper.mapBuilder(d, MovimientosCliente.MovimientosClienteBuilder.class).build());
    }

    @Override
    public Flux<MovimientosCliente> obtenerMovimientosClienteFecha(Integer id, Date fechaInicial, Date fechaFinal) {
        return Flux.fromIterable(MovimientoMapper.tolistData(repository.listMovedByClient(id,fechaInicial,fechaFinal)));
    }
}
