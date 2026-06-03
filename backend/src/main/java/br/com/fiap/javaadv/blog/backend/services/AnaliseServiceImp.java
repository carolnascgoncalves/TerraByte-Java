package br.com.fiap.javaadv.blog.backend.services;

import br.com.fiap.javaadv.blog.backend.anticorruptionlayer.WeatherServiceImp;
import br.com.fiap.javaadv.blog.backend.datasource.repositories.AnalisePlantioRepository;
import br.com.fiap.javaadv.blog.backend.datasource.repositories.EnderecoPlantioRepository;
import br.com.fiap.javaadv.blog.backend.datasource.repositories.PlantioRepository;
import br.com.fiap.javaadv.blog.backend.domainmodel.entities.AnalisePlantio;
import br.com.fiap.javaadv.blog.backend.domainmodel.entities.EnderecoPlantio;
import br.com.fiap.javaadv.blog.backend.domainmodel.entities.Plantio;
import br.com.fiap.javaadv.blog.backend.resources.dtos.WeatherResponse;
import br.com.fiap.javaadv.blog.backend.services.interfaces.AnaliseService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Date;
import java.time.LocalDate;
import java.time.Month;
import java.util.*;

@Service
@RequiredArgsConstructor
@Transactional( propagation = Propagation.REQUIRED)
public class AnaliseServiceImp implements AnaliseService {
    private final AnalisePlantioRepository analiseRepository;
    private final WeatherServiceImp weatherServiceImp;
    private final EnderecoPlantioRepository enderecoRepository;
    private final PlantioRepository plantioRepository;

    @Override
    public AnalisePlantio create(AnalisePlantio analise) {

        EnderecoPlantio endereco = enderecoRepository.findById(analise.getEnderecoPlantio().getId())
                .orElseThrow(() -> new RuntimeException("Endereço não encontrado"));
        Plantio plantio = plantioRepository.findById(analise.getPlantio().getId())
                .orElseThrow(() -> new RuntimeException("Plantio não encontrado"));

        WeatherResponse weather = weatherServiceImp.getForecast(endereco.getLatitude(), endereco.getLongitude());

        double mediaMax = Optional.ofNullable(weather.getDaily())
                .map(d -> d.getTemperature_2m_max())
                .orElse(Collections.emptyList())
                .stream()
                .filter(Objects::nonNull)
                .mapToDouble(Double::doubleValue)
                .average()
                .orElse(0);

        double mediaMin = Optional.ofNullable(weather.getDaily())
                .map(d -> d.getTemperature_2m_min())
                .orElse(Collections.emptyList())
                .stream()
                .filter(Objects::nonNull)
                .mapToDouble(Double::doubleValue)
                .average()
                .orElse(0);

        double mediaChuva = Optional.ofNullable(weather.getDaily())
                .map(d -> d.getPrecipitation_sum())
                .orElse(Collections.emptyList())
                .stream()
                .filter(Objects::nonNull)
                .mapToDouble(Double::doubleValue)
                .average()
                .orElse(0);


        double climaScore = 100;
        climaScore -= Math.abs(mediaMax - plantio.getTempMax()) * 2;
        climaScore -= Math.abs(mediaMin - plantio.getTempMin()) * 2;
        climaScore = Math.clamp(climaScore, 0, 100);


        double umidadeScore = 100;
        umidadeScore -= Math.abs(mediaChuva - plantio.getAguaMM()) * 3;
        umidadeScore = Math.clamp(umidadeScore, 0, 100);

        double soloScore = 100;
        if (!Optional.ofNullable(plantio.getTiposSolo())
                .orElse(Collections.emptySet())
                .contains(endereco.getTipoSolo())) {
            soloScore -= 25;
        }
        soloScore = Math.clamp(soloScore, 0, 100);

        double epocaPenalty = 0;
        if (plantio.getMesesIdeais() != null) {

            Month mesAtual = LocalDate.now().getMonth();

            Set<Month> meses = Optional.ofNullable(plantio.getMesesIdeais())
                    .orElse(Collections.emptySet());

            if (!meses.contains(mesAtual)) {
                epocaPenalty = 15;
            }
        }
        climaScore -= epocaPenalty;
        climaScore = Math.clamp(climaScore, 0, 100);

        double scoreFinal = (climaScore * 0.50) + (umidadeScore * 0.25) + (soloScore * 0.25);
        scoreFinal = Math.clamp(scoreFinal, 0, 100);

        String nivel;

        if (scoreFinal >= 80) {nivel = "MUITO_PROVAVEL";}
        else if (scoreFinal >= 65) {nivel = "ALTA";}
        else if (scoreFinal >= 45) {nivel = "MEDIA";}
        else {nivel = "BAIXA";}

        AnalisePlantio result = AnalisePlantio.builder()
                .data(Date.valueOf(LocalDate.now()))
                .enderecoPlantio(endereco)
                .plantio(plantio)
                .tempMax(mediaMax)
                .tempMin(mediaMin)
                .umidadeMed(mediaChuva)
                .adequadoPlantio(scoreFinal)
                .nivelRisco(nivel)
                .build();

        return analiseRepository.save(result);
    }

    @Override
    public void delete(UUID id){
        analiseRepository.deleteById(id);
    }

    @Override
    @Transactional(propagation = Propagation.NOT_SUPPORTED)
    public Optional<AnalisePlantio> fetchById(UUID id){
        return this.analiseRepository.findById(id);
    }

    @Override
    @Transactional(propagation = Propagation.NOT_SUPPORTED)
    public boolean existsById(UUID id){
        return this.analiseRepository.existsById(id);
    }

    public Page<AnalisePlantio> fetchAll(Pageable pageable){
        return this.analiseRepository.findAll(pageable);
    }
}
