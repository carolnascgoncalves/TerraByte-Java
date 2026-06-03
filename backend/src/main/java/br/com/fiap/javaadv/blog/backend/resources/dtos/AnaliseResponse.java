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
    private @Getter @Setter Date data;
    private @Getter @Setter String nomeEndereco;
    private @Getter @Setter String nomePlantio;
    private @Getter @Setter double adequadoPlantio;
    private @Getter @Setter String nivelRisco;
    private @Getter @Setter double latitude;
    private @Getter @Setter double longitude;
    private @Getter @Setter String tipoSolo;
    private @Getter @Setter double argila;
    private @Getter @Setter double areia;
    private @Getter @Setter double silte;
    private @Getter @Setter double raioKM;
    private @Getter @Setter double tempMin;
    private @Getter @Setter double tempMax;
    private @Getter @Setter double umidadeMed;
    private @Getter @Setter String recomendacao;


    public static AnaliseResponse toDto(final AnalisePlantio analisePlantio){
        return AnaliseResponse.builder()
                .id(analisePlantio.getId())
                .data(analisePlantio.getData())
                .nomeEndereco(analisePlantio.getEnderecoPlantio().getNome())
                .nomePlantio(analisePlantio.getPlantio().getNome())
                .adequadoPlantio(analisePlantio.getAdequadoPlantio())
                .nivelRisco(analisePlantio.getNivelRisco())
                .latitude(analisePlantio.getEnderecoPlantio().getLatitude())
                .longitude(analisePlantio.getEnderecoPlantio().getLongitude())
                .tipoSolo(analisePlantio.getEnderecoPlantio().getTipoSolo().toString())
                .argila(analisePlantio.getEnderecoPlantio().getArgila())
                .areia(analisePlantio.getEnderecoPlantio().getAreia())
                .silte(analisePlantio.getEnderecoPlantio().getSilto())
                .raioKM(analisePlantio.getEnderecoPlantio().getRaioSoloKm())
                .tempMin((analisePlantio.getTempMin()))
                .tempMax(analisePlantio.getTempMax())
                .umidadeMed(analisePlantio.getUmidadeMed())
                .recomendacao(analisePlantio.getRecomendacao())
                .build();
    }
}
