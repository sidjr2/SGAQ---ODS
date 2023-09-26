package br.edu.ufsj.dcefs.sgaq.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import org.hibernate.validator.constraints.NotBlank;
import org.springframework.hateoas.RepresentationModel;

import java.io.Serializable;
import java.util.UUID;

@Entity
@Table(name = "TP_USERS")
public class UserModel extends RepresentationModel<UserModel> implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id_User;
    @Column(nullable = false,unique = true)
    @NotBlank(message = "o user_name deve ser informado")
    private String user_name;
    private String senha;
    @Column(nullable = false,unique = true)
    private String nome;
    private String departamento;
    @Column(nullable = false,unique = true)
    private String matricula;
    private String tipo;
    private String telefone;
    private String cidade;
    private Integer punicao;
    private Boolean reserva;
    private Integer Prioridade;

    public Integer getPrioridade() {
        return Prioridade;
    }

    public void setPrioridade(Integer prioridade) {
        Prioridade = prioridade;
    }

    public UUID getId_User() {
        return id_User;
    }

    public void setId_User(UUID idUser) {
        this.id_User = idUser;
    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDepartamento() {
        return departamento;
    }

    public void setDepartamento(String departamento) {
        this.departamento = departamento;
    }

    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }
    public Integer getPunicao() {
        return punicao;
    }

    public void setPunicao(Integer punicao) {
        this.punicao = punicao;
    }

    public Boolean getReserva() {
        return reserva;
    }

    public void setReserva(Boolean reserva) {
        this.reserva = reserva;
    }


}
