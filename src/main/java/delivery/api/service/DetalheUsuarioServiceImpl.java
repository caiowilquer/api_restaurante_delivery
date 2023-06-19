package delivery.api.service;

import delivery.api.model.Usuario;
import delivery.api.repository.UsuarioRepository;
import delivery.api.data.DetalheUsuarioData;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class DetalheUsuarioServiceImpl implements UserDetailsService {
    private final UsuarioRepository repository;

    public DetalheUsuarioServiceImpl(UsuarioRepository repository) {
        this.repository = repository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<Usuario> usuario = repository.findByLogin(username);
        if (!usuario.isPresent()) {
            throw new UsernameNotFoundException("Usuário " + username + " não encontrado");
        }
        return new DetalheUsuarioData(usuario);
    }
}
