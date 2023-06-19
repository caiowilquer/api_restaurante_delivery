package delivery.api.service;

import delivery.api.repository.PedidoRepository;
import delivery.api.model.Pedido;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PedidoService {


    private final PedidoRepository repository;

    public PedidoService(PedidoRepository repository) {
        this.repository = repository;
    }

    public Pedido save(Pedido pedido) {
        return repository.save(pedido);
    }

    public Optional<Pedido> findById(Integer id) {
        return this.repository.findById(id);
    }

    public Pedido update(Pedido pedido) {
        return repository.save(pedido);
    }

    public Iterable<Pedido> findAll() {
        return this.repository.findAll();
    }

    public boolean existsById(Integer id) {
        return repository.existsById(id);
    }

    public void destroy(Integer id) {
        repository.deleteById(id);
    }
}
