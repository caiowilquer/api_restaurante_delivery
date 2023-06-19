package delivery.api.service;

import delivery.api.repository.EntregaRepository;
import delivery.api.model.Entrega;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class EntregaService {
    private final EntregaRepository repository;

    public EntregaService(EntregaRepository repository) {
        this.repository = repository;
    }

    public Entrega save(Entrega entrega) {
        return repository.save(entrega);
    }

    public Entrega update(Entrega entrega) {
        return repository.save(entrega);
    }

    public Iterable<Entrega> findAll() {
        return this.repository.findAll();
    }

    public void destroy(Integer id) {
        repository.deleteById(id);
    }

    public boolean existsById(Integer id) {
        return repository.existsById(id);
    }

    public Optional<Entrega> findById(Integer id) {
        return this.repository.findById(id);
    }
}
