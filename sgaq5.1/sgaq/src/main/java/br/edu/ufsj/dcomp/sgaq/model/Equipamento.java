package br.edu.ufsj.dcomp.sgaq.model;

import br.edu.ufsj.dcomp.sgaq.enums.Status;
import com.sun.istack.NotNull;

import javax.persistence.*;
@Entity
public class Equipamento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    private String nome;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    @ManyToOne
    @JoinColumn(name="reserva_id")
    private Reserva reserva;

    @ManyToOne
    @JoinColumn(name="quadra_id")
    private Quadra quadra;

    private Integer quantidade;

    @Column(name = "disponivel")
    @Enumerated(EnumType.STRING)
    @NotNull
    private Status disponivel;

    private String descricao;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Reserva getReserva() {
        return reserva;
    }

    public void setReserva(Reserva reserva) {
        this.reserva = reserva;
    }

    public Quadra getQuadra() {
        return quadra;
    }

    public void setQuadra(Quadra quadra) {
        this.quadra = quadra;
    }

    public Integer getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(Integer quantidade) {
        this.quantidade = quantidade;
    }

    public Status getDisponivel() {
        return disponivel;
    }

    public void setDisponivel(Status disponivel) {
        this.disponivel = disponivel;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
}
