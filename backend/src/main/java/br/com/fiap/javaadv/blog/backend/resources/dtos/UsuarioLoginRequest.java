package br.com.fiap.javaadv.blog.backend.resources.dtos;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UsuarioLoginRequest {
    private @Getter @Setter String email;
    private @Getter @Setter String senha;
}
