package br.com.fiap.javaadv.blog.backend.resources.dtos;

import br.com.fiap.javaadv.blog.backend.domainmodel.entities.Plantio;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PlantioRequest {
    private @Getter @Setter String nome;
    private @Getter @Setter double tempMin;
    private @Getter @Setter double tempMax;
    private @Getter @Setter double aguaMM;
    private @Getter @Setter String epoca;
    private @Getter @Setter String urlImg;

    public static PlantioRequest toDto(final Plantio plantio){
        return PlantioRequest.builder()
                .nome(plantio.getNome())
                .tempMin(plantio.getTempMin())
                .tempMax(plantio.getTempMax())
                .aguaMM(plantio.getAguaMM())
                .epoca(plantio.getEpoca())
                .urlImg(plantio.getUrlImg())
                .build();
    }

    public static Plantio toEntity(final PlantioRequest dto){
        return Plantio.builder()
                .nome(dto.getNome())
                .tempMin(dto.getTempMin())
                .tempMax(dto.getTempMax())
                .aguaMM(dto.getAguaMM())
                .epoca(dto.getEpoca())
                .urlImg(dto.getUrlImg())
                .build();
    }
}
