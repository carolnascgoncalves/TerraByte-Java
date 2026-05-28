package br.com.fiap.javaadv.blog.backend.resources.dtos;

import br.com.fiap.javaadv.blog.backend.domainmodel.entities.EnderecoPlantio;
import lombok.*;

import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Builder
public class EnderecoResponse {
    private @Getter @Setter UUID id;
    private @Getter @Setter String nome;
    private @Getter @Setter String cep;
    private @Getter @Setter String logradouro;
    private @Getter @Setter String cidade;
    private @Getter @Setter String estado;
    private @Getter @Setter double latitude;
    private @Getter @Setter double longitude;

    public static EnderecoResponse toDto(final EnderecoPlantio enderecoPlantio){
        return EnderecoResponse.builder()
                .id(enderecoPlantio.getId())
                .nome(enderecoPlantio.getNome())
                .cep(enderecoPlantio.getCep())
                .logradouro(enderecoPlantio.getLogradouro())
                .cidade(enderecoPlantio.getCidade())
                .estado(enderecoPlantio.getEstado())
                .latitude(enderecoPlantio.getLatitude())
                .longitude(enderecoPlantio.getLongitude())
                .build();
    }
}
