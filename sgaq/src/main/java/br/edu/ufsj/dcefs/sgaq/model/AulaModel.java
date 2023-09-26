package br.edu.ufsj.dcefs.sgaq.model;

import jakarta.persistence.*;

import java.io.Serializable;
import java.util.Date;
import java.util.UUID;

@Entity
@Table(name = "TP_AULA")
public class AulaModel implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id_aula;

    @OneToOne
    private QuadraModel quadraModel;

    @ManyToOne
    private UserModel id_User;

    private String name_aula;
    private String descricao_aula;

    private Boolean status;
    private Date data_inicio;
    private Date hora_inicio;
    private Date data_fim;
    private Date hora_fim;

    public UUID getId_aula() {
        return id_aula;
    }

    public void setId_aula(UUID id_aula) {
        this.id_aula = id_aula;
    }

    public QuadraModel getQuadraModel() {
        return quadraModel;
    }

    public void setQuadraModel(QuadraModel quadraModel) {
        this.quadraModel = quadraModel;
    }

    public UserModel getId_User() {
        return id_User;
    }

    public void setId_User(UserModel id_User) {
        this.id_User = id_User;
    }

    public String getName_aula() {
        return name_aula;
    }

    public void setName_aula(String name_aula) {
        this.name_aula = name_aula;
    }

    public String getDescricao_aula() {
        return descricao_aula;
    }

    public void setDescricao_aula(String descricao_aula) {
        this.descricao_aula = descricao_aula;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public Date getData_inicio() {
        return data_inicio;
    }

    public void setData_inicio(Date data_inicio) {
        this.data_inicio = data_inicio;
    }

    public Date getHora_inicio() {
        return hora_inicio;
    }

    public void setHora_inicio(Date hora_inicio) {
        this.hora_inicio = hora_inicio;
    }

    public Date getData_fim() {
        return data_fim;
    }

    public void setData_fim(Date data_fim) {
        this.data_fim = data_fim;
    }

    public Date getHora_fim() {
        return hora_fim;
    }

    public void setHora_fim(Date hora_fim) {
        this.hora_fim = hora_fim;
    }
}
