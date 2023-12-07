package br.edu.ufsj.dcomp.sgaq.model;

import br.edu.ufsj.dcomp.sgaq.enums.Campus;
import br.edu.ufsj.dcomp.sgaq.enums.Status;
import br.edu.ufsj.dcomp.sgaq.enums.TipoQuadra;
import com.sun.istack.NotNull;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Entity
public class Quadra {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nome")
    @Size(min = 1, max = 35, message = "O Nome deve conter entre 1 a 35 caracteres")
    @NotBlank(message = "O nome não pode ser vazio")
    @NotNull
    private String nome;

    @Column(name = "tipoQuadra_id")
    @Enumerated(EnumType.STRING)
    @NotNull
    private TipoQuadra tipoQuadra;

    @Column(name = "campus_id")
    @Enumerated(EnumType.STRING)
    @NotNull
    private Campus campus;

    @Column(name = "disponivelQuadra")
    @Enumerated(EnumType.STRING)
    @NotNull
    private Status disponivel;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public TipoQuadra getTipoQuadra() {
        return tipoQuadra;
    }

    public void setTipoQuadra(TipoQuadra tipoQuadra) {
        this.tipoQuadra = tipoQuadra;
    }

    public Campus getCampus() {
        return campus;
    }

    public void setCampus(Campus campus) {
        this.campus = campus;
    }

    public Status getDisponivel() {
        return disponivel;
    }

    public void setDisponivel(Status disponivel) {
        this.disponivel = disponivel;
    }
}
