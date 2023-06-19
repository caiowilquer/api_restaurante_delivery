package delivery.api.service;

import delivery.api.repository.ClienteRepository;
import delivery.api.model.Cliente;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ClienteService {

    private final ClienteRepository repository;

    public ClienteService(ClienteRepository repository) {
        this.repository = repository;
    }

    public Iterable<Cliente> findAll() {return this.repository.findAll();}

    public Optional<Cliente> findById(Integer id) {
        return this.repository.findById(id);
    }

    public Cliente save(Cliente cliente) {return repository.save(cliente);}

    public Cliente update(Cliente cliente) {return repository.save(cliente);}

    public boolean existsById(Integer id) {
        return repository.existsById(id);
    }

    public void destroy(Integer id) {
        repository.deleteById(id);
    }
}
