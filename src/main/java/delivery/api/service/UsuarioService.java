package delivery.api.service;

import delivery.api.model.Usuario;
import delivery.api.repository.UsuarioRepository;
import org.springframework.stereotype.Service;

@Service
public class UsuarioService {
    private final UsuarioRepository repository;

    public UsuarioService(UsuarioRepository repository) {
        this.repository = repository;
    }

    public void save(Usuario usuario) {
        repository.save(usuario);
    }

    public void update(Usuario usuario) {
        repository.save(usuario);
    }

    public Iterable<Usuario> findAll() {
        return this.repository.findAll();
    }

    public void destroy(Usuario usuario) {
        repository.deleteById(usuario.getId());
    }
}
