package br.com.fiap.javaadv.blog.backend.resources.dtos;

import br.com.fiap.javaadv.blog.backend.domainmodel.entities.Plantio;

import lombok.*;

import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PlantioResponse {
    private @Getter @Setter UUID id;
    private @Getter @Setter String nome;
    private @Getter @Setter double tempMin;
    private @Getter @Setter double tempMax;
    private @Getter @Setter double aguaMM;
    private @Getter @Setter String epoca;
    private @Getter @Setter String urlImg;

    public static PlantioResponse ToDto(final Plantio plantio){
        return PlantioResponse.builder()
                .id(plantio.getId())
                .nome(plantio.getNome())
                .tempMin(plantio.getTempMin())
                .tempMax(plantio.getTempMax())
                .aguaMM(plantio.getAguaMM())
                .epoca(plantio.getEpoca())
                .urlImg(plantio.getUrlImg())
                .build();
    }
}
