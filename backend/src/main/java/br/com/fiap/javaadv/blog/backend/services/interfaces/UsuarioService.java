package br.com.fiap.javaadv.blog.backend.services.interfaces;

import br.com.fiap.javaadv.blog.backend.domainmodel.entities.Usuario;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface UsuarioService {
    Usuario create(Usuario usuario);

    Optional<Usuario> update(UUID id, Usuario usuario);

    void delete(UUID id);

    Page<Usuario> fetchAll(Pageable pageable);

    Optional<Usuario> fetchById(UUID id);

    boolean existsById(UUID id);

    //Optional<Usuario> fetchByEmail(String email, String senha);

    UserDetails fetchByEmail(String email);
}
