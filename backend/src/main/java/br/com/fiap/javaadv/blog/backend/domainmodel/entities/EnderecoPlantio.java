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

    @NotBlank(message= "O nome é obrigatorio")
    @Size(min = 2, max=100, message="O nome deve ter entre 2 à 100 caracteres")
    @Column(name="NOME_end", length = 100, nullable = false)
    private @Getter @Setter String nome;

    @NotBlank(message= "O CEP é obrigatorio")
    @Size(min = 8, max=9, message="O CEP deve ter entre 8 à 9 caracteres")
    @Column(name="CEP_end", length = 10, nullable = false)
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

    @Size(min = 2, max=100, message="O bairro deve ter entre 2 à 100 caracteres")
    @Column(name="BAIRRO_end", length = 100)
    private @Getter @Setter String bairro;

    @Column(name="LAT_end")
    private @Getter @Setter double latitude;

    @Column(name="LONG_end")
    private @Getter @Setter double longitude;

    //RELACIONAMENTOS
    //N:1 Tipo solo
    @ManyToOne
    @JoinColumn(name = "ID_SOLO_FK")
    private TipoSolo tipoSolo;

    //1:N Analise
    @OneToMany(mappedBy = "enderecoPlantio", fetch = FetchType.LAZY)
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
