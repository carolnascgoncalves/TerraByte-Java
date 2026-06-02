package br.com.fiap.javaadv.blog.backend.domainmodel.services;

import br.com.fiap.javaadv.blog.backend.domainmodel.enums.TipoSoloEnum;
import org.springframework.stereotype.Component;

@Component
public class TipoSoloClassifier {
    public TipoSoloEnum classificar(double clay, double sand, double silt) {

        // 1. Arenoso
        if (sand >= 60) {
            return TipoSoloEnum.ARENOSO;
        }

        // 2. Argiloso
        if (clay >= 40) {
            return TipoSoloEnum.ARGILOSO;
        }

        // 3. Siltoso
        if (silt >= 50) {
            return TipoSoloEnum.SILTOSO;
        }

        // 4. Franco-arenoso
        if (sand < 60 && sand >= 45 && clay < 20) {
            return TipoSoloEnum.FRANCO_ARENOSO;
        }

        // 5. Franco-argiloso
        if (clay < 40 &&  clay >= 25) {
            return TipoSoloEnum.FRANCO_ARGILOSO;
        }

        // 6. Humoso (aproximação)
        if (silt <= 50 && silt >= 35) {
            return TipoSoloEnum.HUMOSO;
        }

        // 7. Orgânico (fallback equilibrado)
        if (clay >= 20 && sand >= 20 && silt >= 20) {
            return TipoSoloEnum.ORGANICO;
        }

        // 8. Calcário (fallback extremo)
        return TipoSoloEnum.CALCARIO;
    }
}