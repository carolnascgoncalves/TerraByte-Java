package br.com.fiap.javaadv.blog.backend.resources.dtos;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

public class SoilGridsResponse {
    private Properties properties;

    public Properties getProperties() {
        return properties;
    }

    public static class Properties {
        private List<Layers> layers;
        public List<Layers> getLayers() {return layers;}
    }

    public static class Layers {
        private String name;
        private List<Depths> depths;

        public String getName() {return name;}
        public List<Depths> getDepths() {return depths;}
    }

    public static class Depths {
        private Values values;

        public Values getValues() {return values;}
    }

    public static class Values {
        private Double mean;

        public Double getMean() {return mean;}
    }
}
