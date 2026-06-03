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

    @Column(name="DT_ana")
    private @Getter @Setter Date data;

    @Column(name="TEMP_MIN_ana")
    private @Getter @Setter double tempMin;

    @Column(name="TEMP_MAX_ana")
    private @Getter @Setter double tempMax;

    @Column(name="UMIDADE_MED_ana")
    private @Getter @Setter double umidadeMed;

    @Column(name="ADEQ_ana")
    private @Getter @Setter double adequadoPlantio;

    @Column(name="NVL_risc_ana")
    private @Getter @Setter String nivelRisco;

    @Column(name="REC_ana")
    private @Getter @Setter String recomendacao;



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
