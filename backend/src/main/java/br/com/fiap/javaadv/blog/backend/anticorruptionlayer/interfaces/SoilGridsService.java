package br.com.fiap.javaadv.blog.backend.anticorruptionlayer.interfaces;

import br.com.fiap.javaadv.blog.backend.domainmodel.enums.TipoSoloEnum;
import br.com.fiap.javaadv.blog.backend.domainmodel.services.SoilValues;
import br.com.fiap.javaadv.blog.backend.resources.dtos.SoilGridsResponse;
import br.com.fiap.javaadv.blog.backend.resources.dtos.SoilGridsResultado;
import br.com.fiap.javaadv.blog.backend.resources.dtos.SoilResponse;

import java.util.List;
import java.util.Map;

public interface SoilGridsService {
    SoilGridsResultado buscarTipoSolo(double lat, double lon);
}
