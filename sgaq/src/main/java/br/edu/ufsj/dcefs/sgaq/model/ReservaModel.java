package br.edu.ufsj.dcefs.sgaq.model;

import jakarta.persistence.*;

import java.io.Serializable;
import java.util.Date;
import java.util.UUID;

@Entity
@Table(name = "TP_RESERVA")
public class ReservaModel implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id_reserva;

    @OneToOne
    private QuadraModel id_quadra;
    @ManyToOne
    private UserModel id_usuario;
    private String descrição;
    private Boolean status;
    private Date data_inicio;
    private Date hora_inicio;
    private Date data_fim;
    private Date hora_fim;

    public UUID getId_reserva() {
        return id_reserva;
    }

    public void setId_reserva(UUID id_reserva) {
        this.id_reserva = id_reserva;
    }

    public QuadraModel getId_quadra() {
        return id_quadra;
    }

    public void setId_quadra(QuadraModel id_quadra) {
        this.id_quadra = id_quadra;
    }

    public UserModel getId_usuario() {
        return id_usuario;
    }

    public void setId_usuario(UserModel id_usuario) {
        this.id_usuario = id_usuario;
    }

    public String getDescrição() {
        return descrição;
    }

    public void setDescrição(String descrição) {
        this.descrição = descrição;
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
