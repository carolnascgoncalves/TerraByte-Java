package br.com.fiap.javaadv.blog.backend.domainmodel.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.sql.Date;
import java.util.Objects;
import java.util.UUID;

@Entity
@Table(name="AnalisePlantio_terrabyte")
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class AnalisePlantio {
    @Id
    private @Getter @Setter UUID id;

    @Column(name="TEMP_MED_ana")
    private @Getter @Setter double tempMed;

    @Column(name="UMIDADE_MED_ana")
    private @Getter @Setter double umidadeMed;

    @Column(name="PROB_SUC_ana")
    private @Getter @Setter double probabilidadeSucesso;

    @Column(name="RESULTADO_ana", length = 400)
    private @Getter @Setter String epoca;

    @Column(name="DT_ANALISE_ana")
    private @Getter @Setter Date data;

    //RELACIONAMENTOS
    //N:1 Usuario
    @ManyToOne
    @JoinColumn(name="ID_usu_(PK)")
    private @Getter @Setter Usuario usuario;

    //N:1 Plantio
    @ManyToOne
    @JoinColumn(name="ID_plan_(PK)")
    private @Getter @Setter Plantio plantio;

    //N:1 Endereco
    @ManyToOne
    @JoinColumn(name="ID_end_(PK)")
    private  @Getter @Setter EnderecoPlantio enderecoPlantio;

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        AnalisePlantio ent = (AnalisePlantio) o;
        return Objects.equals(id, ent.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @PrePersist
    public void gerarId() {
        if (id == null) {
            id = UUID.randomUUID();
        }
    }
}
