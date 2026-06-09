package br.com.fiap.javaadv.blog.backend.anticorruptionlayer.interfaces;

import br.com.fiap.javaadv.blog.backend.resources.dtos.WeatherResponse;

public interface WeatherService {
    WeatherResponse getForecast(double lat, double lon);
}
