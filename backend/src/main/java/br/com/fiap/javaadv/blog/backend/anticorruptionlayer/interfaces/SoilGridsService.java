package br.com.fiap.javaadv.blog.backend.anticorruptionlayer.interfaces;

import br.com.fiap.javaadv.blog.backend.domainmodel.services.SoilValues;
import br.com.fiap.javaadv.blog.backend.resources.dtos.SoilGridsResponse;
import br.com.fiap.javaadv.blog.backend.resources.dtos.SoilResponse;

import java.util.List;
import java.util.Map;

public interface SoilGridsService {
    SoilValues buscarESumarizar(double lat, double lon);
    Double getValue(List<SoilGridsResponse.Layers> layers, String type);
}
