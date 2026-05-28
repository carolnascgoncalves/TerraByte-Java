package br.com.fiap.javaadv.blog.backend.resources.dtos;

import br.com.fiap.javaadv.blog.backend.domainmodel.entities.AnalisePlantio;
import lombok.*;

import java.sql.Date;

@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AnaliseRequest {
    private @Getter @Setter double tempMed;
    private @Getter @Setter double umidadeMed;
    private @Getter @Setter double probabilidadeSucesso;
    private @Getter @Setter String epoca;
    private @Getter @Setter Date data;

    public static AnaliseRequest toDto(AnalisePlantio analisePlantio){
        return AnaliseRequest.builder()
                .tempMed(analisePlantio.getTempMed())
                .umidadeMed(analisePlantio.getUmidadeMed())
                .probabilidadeSucesso(analisePlantio.getProbabilidadeSucesso())
                .epoca(analisePlantio.getEpoca())
                .data(analisePlantio.getData())
                .build();
    }

    public static AnalisePlantio toEntity(AnaliseRequest dto){
        return AnalisePlantio.builder()
                .tempMed(dto.getTempMed())
                .umidadeMed(dto.getUmidadeMed())
                .probabilidadeSucesso(dto.getProbabilidadeSucesso())
                .epoca(dto.getEpoca())
                .data(dto.getData())
                .build();
    }
}
