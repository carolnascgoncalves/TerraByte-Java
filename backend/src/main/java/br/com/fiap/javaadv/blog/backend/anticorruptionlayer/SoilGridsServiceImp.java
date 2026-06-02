package br.com.fiap.javaadv.blog.backend.anticorruptionlayer;

import br.com.fiap.javaadv.blog.backend.anticorruptionlayer.interfaces.SoilGridsService;
import br.com.fiap.javaadv.blog.backend.domainmodel.services.SoilValues;
import br.com.fiap.javaadv.blog.backend.resources.dtos.SoilGridsResponse;
import br.com.fiap.javaadv.blog.backend.resources.dtos.SoilResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class SoilGridsServiceImp implements SoilGridsService {

    private final RestTemplate restTemplate;

    @Override
    public SoilValues buscarESumarizar(double lat, double lon) {

        String url = "https://rest.isric.org/soilgrids/v2.0/properties/query?lat=" + lat + "&lon=" + lon;

        SoilGridsResponse response =
                restTemplate.getForObject(url, SoilGridsResponse.class);

        List<SoilGridsResponse.Layers> layers =
                response.getProperties().getLayers();

        double clay = getValue(layers, "clay");
        double sand = getValue(layers, "sand");
        double silt = getValue(layers, "silt");

        return new SoilValues(clay, sand, silt);
    }

    public Double getValue(List<SoilGridsResponse.Layers> layers, String type) {

        return layers.stream()
                .filter(l -> type.equalsIgnoreCase(l.getName()))
                .findFirst()
                .map(l -> l.getDepths().get(0).getValues().getMean())
                .orElse(0.0);
    }
}