package br.com.fiap.javaadv.blog.backend.resources.dtos;

import br.com.fiap.javaadv.blog.backend.domainmodel.entities.AnalisePlantio;
import br.com.fiap.javaadv.blog.backend.domainmodel.entities.EnderecoPlantio;
import br.com.fiap.javaadv.blog.backend.domainmodel.entities.Plantio;
import br.com.fiap.javaadv.blog.backend.domainmodel.entities.Usuario;
import lombok.*;

import java.sql.Date;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AnaliseRequest {
    private @Getter @Setter double tempMed;
    private @Getter @Setter double umidadeMed;
    private @Getter @Setter double probabilidadeSucesso;
    private @Getter @Setter String epoca;
    private @Getter @Setter Date data;

    private @Getter @Setter UUID idUsu;
    private @Getter @Setter UUID idEnd;
    private @Getter @Setter UUID idPlan;

    public static AnaliseRequest toDto(final AnalisePlantio analisePlantio){
        return AnaliseRequest.builder()
                .tempMed(analisePlantio.getTempMed())
                .umidadeMed(analisePlantio.getUmidadeMed())
                .probabilidadeSucesso(analisePlantio.getProbabilidadeSucesso())
                .epoca(analisePlantio.getEpoca())
                .data(analisePlantio.getData())
                .idUsu(analisePlantio.getUsuario().getId())
                .idEnd(analisePlantio.getEnderecoPlantio().getId())
                .idPlan(analisePlantio.getPlantio().getId())
                .build();
    }

    public static AnalisePlantio toEntity(final AnaliseRequest dto){
        return AnalisePlantio.builder()
                .tempMed(dto.getTempMed())
                .umidadeMed(dto.getUmidadeMed())
                .probabilidadeSucesso(dto.getProbabilidadeSucesso())
                .epoca(dto.getEpoca())
                .data(dto.getData())
                .usuario(Usuario.builder().id(dto.getIdUsu()).build())
                .enderecoPlantio(EnderecoPlantio.builder().id(dto.getIdEnd()).build())
                .plantio(Plantio.builder().id(dto.getIdPlan()).build())
                .build();
    }
}
