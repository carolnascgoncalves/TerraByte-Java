package br.com.fiap.javaadv.blog.backend.domainmodel.entities;

import br.com.fiap.javaadv.blog.backend.domainmodel.enums.SexoEnum;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.sql.Date;
import java.util.Objects;
import java.util.Set;
import java.util.UUID;

@Entity
@Table(name="EnderecoPlantio_terrabyte")
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class EnderecoPlantio {
    @Id
    private @Getter @Setter UUID id;

    @NotBlank(message= "O CEP é obrigatorio")
    @Size(min = 8, max=10, message="O nome deve ter entre 8 à 9 caracteres")
    @Column(name="CEP_end", length = 100, nullable = false)
    private @Getter @Setter String cep;

    @Size(min = 2, max=100, message="O logradouro deve ter entre 2 à 100 caracteres")
    @Column(name="LOGRAD_end", length = 100)
    private @Getter @Setter String logradouro;

    @Size(min = 2, max=100, message="O cidade deve ter entre 2 à 100 caracteres")
    @Column(name="CIDADE_end", length = 100)
    private @Getter @Setter String cidade;

    @Size(min = 2, max=100, message="O estado deve ter entre 2 à 100 caracteres")
    @Column(name="ESTADO_end", length = 100)
    private @Getter @Setter String estado;

    @Column(name="LAT_end")
    private @Getter @Setter double latitude;

    @Column(name="LONG_end")
    private @Getter @Setter double longitude;

    //RELACIONAMENTOS
    //1:N Tipo solo
    @OneToMany(mappedBy = "enderecos", fetch = FetchType.LAZY)
    private @Getter @Setter Set<TipoSolo> tiposSolo;

    //1:N Analise
    @OneToMany(mappedBy = "enderecos", fetch = FetchType.LAZY)
    private @Getter @Setter Set<AnalisePlantio> analises;



    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        EnderecoPlantio ent = (EnderecoPlantio) o;
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
