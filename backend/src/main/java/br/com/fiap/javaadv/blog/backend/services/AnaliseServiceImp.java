package br.com.fiap.javaadv.blog.backend.services;

import br.com.fiap.javaadv.blog.backend.anticorruptionlayer.WeatherServiceImp;
import br.com.fiap.javaadv.blog.backend.datasource.repositories.AnalisePlantioRepository;
import br.com.fiap.javaadv.blog.backend.datasource.repositories.EnderecoPlantioRepository;
import br.com.fiap.javaadv.blog.backend.datasource.repositories.PlantioRepository;
import br.com.fiap.javaadv.blog.backend.datasource.repositories.UsuarioRepository;
import br.com.fiap.javaadv.blog.backend.domainmodel.entities.AnalisePlantio;
import br.com.fiap.javaadv.blog.backend.domainmodel.entities.EnderecoPlantio;
import br.com.fiap.javaadv.blog.backend.domainmodel.entities.Plantio;
import br.com.fiap.javaadv.blog.backend.domainmodel.entities.Usuario;
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
    private final UsuarioRepository usuarioRepository;
    private final WeatherServiceImp weatherServiceImp;
    private final EnderecoPlantioRepository enderecoRepository;
    private final PlantioRepository plantioRepository;

    @Override
    public AnalisePlantio create(AnalisePlantio analise, String email) {
        Usuario usuario = usuarioRepository.findByEmail(email).orElseThrow(() -> new RuntimeException("Usuário não encontrado"));
        EnderecoPlantio endereco = enderecoRepository.findById(analise.getEnderecoPlantio().getId()).orElseThrow(() -> new RuntimeException("Endereço não encontrado"));
        Plantio plantio = plantioRepository.findById(analise.getPlantio().getId()).orElseThrow(() -> new RuntimeException("Plantio não encontrado"));

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

        double chuvaPrevista = Optional.ofNullable(weather.getDaily())
                .map(d -> d.getPrecipitation_sum())
                .orElse(Collections.emptyList())
                .stream()
                .filter(Objects::nonNull)
                .mapToDouble(Double::doubleValue)
                .sum();

        double diferencaMax = Math.abs(mediaMax - plantio.getTempMax());
        double diferencaMin = Math.abs(mediaMin - plantio.getTempMin());

        double climaScore = 100;
        climaScore -= diferencaMax * 2;
        climaScore -= diferencaMin * 2;
        climaScore = Math.clamp(climaScore, 0, 100);


        double aguaNecessaria = plantio.getAguaMM();

        double umidadeScore = 100;

        if (aguaNecessaria > 0) {
            double diferencaAgua = Math.abs(chuvaPrevista - aguaNecessaria);
            umidadeScore = 100 - ((diferencaAgua / aguaNecessaria) * 100);
        }

        umidadeScore = Math.clamp(umidadeScore, 0, 100);


        boolean soloCompativel = Optional.ofNullable(plantio.getTiposSolo())
                .orElse(Collections.emptySet())
                .stream()
                .anyMatch(tp -> tp.getNome().equalsIgnoreCase(endereco.getTipoSolo().getNome()));

        double soloScore = soloCompativel ? 100 : 0;


        double epocaPenalty = 0;

        Set<Month> mesesIdeais = Optional.ofNullable(plantio.getMesesIdeais()).orElse(Collections.emptySet());

        Month mesAtual = LocalDate.now().getMonth();

        if (!mesesIdeais.isEmpty() && !mesesIdeais.contains(mesAtual)) {
            epocaPenalty = 25;
        }


        double scoreFinal = (soloScore * 0.45) + (climaScore * 0.35) + (umidadeScore * 0.20);

        scoreFinal -= epocaPenalty;

        scoreFinal = Math.clamp(scoreFinal, 0, 100);

        if (!soloCompativel) {
            scoreFinal = Math.min(scoreFinal, 40);
        }


        String nivel;

        if (scoreFinal >= 80) {
            nivel = "MUITO_PROVAVEL";
        }
        else if (scoreFinal >= 65) {
            nivel = "ALTA";
        }
        else if (scoreFinal >= 45) {
            nivel = "MEDIA";
        }
        else {
            nivel = "BAIXA";
        }

        String mesesTexto = plantio.getMesesIdeais()
                .stream()
                .map(m -> switch (m) {
                    case JANUARY -> "Janeiro";
                    case FEBRUARY -> "Fevereiro";
                    case MARCH -> "Março";
                    case APRIL -> "Abril";
                    case MAY -> "Maio";
                    case JUNE -> "Junho";
                    case JULY -> "Julho";
                    case AUGUST -> "Agosto";
                    case SEPTEMBER -> "Setembro";
                    case OCTOBER -> "Outubro";
                    case NOVEMBER -> "Novembro";
                    case DECEMBER -> "Dezembro";
                })
                .sorted()
                .collect(java.util.stream.Collectors.joining(", "));

        String defensivosTexto = Optional.ofNullable(plantio.getDefensivos())
                .orElse(Collections.emptySet())
                .stream()
                .map(def -> def.getNome() + " (" + def.getTipo() + ")")
                .sorted()
                .collect(java.util.stream.Collectors.joining(", "));

        String recom = "Cultura analisada: " + plantio.getNome() + " | " +
                "Compatibilidade com o solo: " + (soloCompativel ? "ALTA" : "BAIXA")+ " | " +
                "Temperatura previsa para a região: " +
                " - Mínima media: "+String.format("%.2f",mediaMin) + "°C " +
                " - Máxima média: "+String.format("%.2f",mediaMax) + "°C  | " +
                "Faixa ideal da cultura: " + plantio.getTempMin() + "°C até " + plantio.getTempMax() + "°C | "+
                "Chuva prevista para os próximos dias: " + chuvaPrevista + " | " +
                "Necessidade hídrica da cultura: " + plantio.getAguaMM() + " | " +
                "Melhor época para plantio: " + mesesTexto + " | " +
                "Defensivo indicado: " + defensivosTexto + " | " +
                "Índice final de adequação: " + String.format("%.2f",scoreFinal) + " | " +
                "Classificação: " + nivel;

        AnalisePlantio result = AnalisePlantio.builder()
                .usuario(usuario)
                .data(Date.valueOf(LocalDate.now()))
                .enderecoPlantio(endereco)
                .plantio(plantio)
                .tempMin(mediaMin)
                .tempMax(mediaMax)
                .umidadeMed(chuvaPrevista)
                .adequadoPlantio(scoreFinal)
                .nivelRisco(nivel)
                .recomendacao(recom)
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

    @Override
    public Page<AnalisePlantio> fetchAll(Pageable pageable){
        return this.analiseRepository.findAll(pageable);
    }

    @Override
    public Page<AnalisePlantio> fetchAllByUsuario(String email, Pageable pageable) {
        return analiseRepository.findByUsuarioEmail(email, pageable);
    }

    @Override
    public Optional<AnalisePlantio> fetchByIdAndUsuario(UUID id, String email) {
        return analiseRepository.findByIdAndUsuarioEmail(id, email);
    }
}
