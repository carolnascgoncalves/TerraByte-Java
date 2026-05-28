package br.com.fiap.javaadv.blog.backend.resources.dtos;

import br.com.fiap.javaadv.blog.backend.domainmodel.entities.AnalisePlantio;
import lombok.*;

import java.sql.Date;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AnaliseResponse {
    private @Getter @Setter UUID id;
    private @Getter @Setter double tempMed;
    private @Getter @Setter double umidadeMed;
    private @Getter @Setter double probabilidadeSucesso;
    private @Getter @Setter String epoca;
    private @Getter @Setter Date data;

    public static AnaliseResponse toDto(final AnalisePlantio analisePlantio){
        return AnaliseResponse.builder()
                .tempMed(analisePlantio.getTempMed())
                .umidadeMed(analisePlantio.getUmidadeMed())
                .probabilidadeSucesso(analisePlantio.getProbabilidadeSucesso())
                .epoca(analisePlantio.getEpoca())
                .data(analisePlantio.getData())
                .build();
    }
}
