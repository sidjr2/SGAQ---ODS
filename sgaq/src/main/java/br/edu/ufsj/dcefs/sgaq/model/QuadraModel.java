package br.edu.ufsj.dcefs.sgaq.model;

import jakarta.persistence.*;
import org.springframework.hateoas.RepresentationModel;

import java.io.Serializable;
import java.util.UUID;

@Entity
@Table(name = "TP_QUADRA")
public class QuadraModel extends RepresentationModel<QuadraModel>  implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id_Quadra;
    @Column(nullable = false,unique = true)
    private String quadra_name;
    private String local;
    private String descricao;
    private Integer capacidade;

    private Boolean reservado;

    public UUID getId_Quadra() {
        return id_Quadra;
    }

    public void setId_Quadra(UUID idQuadra) {
        this.id_Quadra = idQuadra;
    }

    public String getQuadra_name() {
        return quadra_name;
    }

    public void setQuadra_name(String quadra_name) {
        this.quadra_name = quadra_name;
    }

    public String getLocal() {
        return local;
    }

    public void setLocal(String local) {
        this.local = local;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Integer getCapacidade() {
        return capacidade;
    }

    public void setCapacidade(Integer capacidade) {
        this.capacidade = capacidade;
    }

    public Boolean getReservado() {
        return reservado;
    }

    public void setReservado(Boolean reservado) {
        this.reservado = reservado;
    }
}
