package br.edu.ufsj.dcomp.sgaq.model;

import br.edu.ufsj.dcomp.sgaq.enums.Campus;
import br.edu.ufsj.dcomp.sgaq.enums.Solicitacoes;
import br.edu.ufsj.dcomp.sgaq.enums.Status;
import com.sun.istack.NotNull;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

@Entity
public class Reserva {
    private Campus campus;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;

    @ManyToOne
    @JoinColumn(name = "quadra_id")
    private Quadra quadra;

    @Column(name = "presenca")
    @Enumerated(EnumType.STRING)
    @NotNull
    private Status presenca;

    @Column(name = "punicao")
    @Enumerated(EnumType.STRING)
    @NotNull
    private Status punicao;

    @Column(name = "data_hora_inicial")
    private LocalDateTime dataHoraInical;

    @Transient
    private String dataHoraStrInical;

    @Column(name = "data_hora_final")
    private LocalDateTime dataHoraFinal;

    @Transient
    private String dataHoraStrFinal;

    // Outros campos e métodos necessários...
    public Status getStatus() {
        return presenca;
    }

    public void setStatus(Status presenca) {
        this.presenca = presenca;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Quadra getQuadra() {
        return quadra;
    }

    public void setQuadra(Quadra quadra) {
        this.quadra = quadra;
    }

    private String nome; // Adicione este campo

    public Status getPresenca() {
        return presenca;
    }

    public void setPresenca(Status presenca) {
        this.presenca = presenca;
    }

    public Status getPunicao() {
        return punicao;
    }

    public void setPunicao(Status punicao) {
        this.punicao = punicao;
    }

    public Campus getCampus() {
        return campus;
    }

    public void setCampus(Campus campus) {
        this.campus = campus;
    }

    // Data e Hora Inicial

    public LocalDateTime getDataHoraInical() {
        return this.dataHoraInical;
    }

    public String getDataHoraStrInical() {
        // Converte LocalDateTime para String no formato "yyyy-MM-dd'T'HH:mm"
        return dataHoraInical != null ? dataHoraInical.format(DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm")) : null;
    }

    public void setDataHoraStrInical(String dataHoraStrInical) {
        this.dataHoraStrInical = dataHoraStrInical;

        // Atualiza dataHora ao definir dataHoraStr
        try {
            this.dataHoraInical = LocalDateTime.parse(dataHoraStrInical, DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm"));
        } catch (DateTimeParseException e) {
            // Lide com a exceção conforme necessário
            e.printStackTrace(); // Isso imprime a exceção, você pode tratar de outra forma se preferir
        }
    }


    public void setDataHoraInical(String dataHoraStrInical) {
        // Defina o formato esperado para a data e hora
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm");

        try {
            // Faça a conversão da string para LocalDateTime usando o formato especificado
            LocalDateTime dataHoraInicial = LocalDateTime.parse(dataHoraStrInical, formatter);
            this.dataHoraInical = dataHoraInicial;
        } catch (Exception e) {
            // Trate a exceção ou registre-a conforme necessário
            e.printStackTrace(); // Isso imprime a exceção, mas você pode lidar de maneira diferente
        }
    }

    //Data e Hora Final

    public LocalDateTime getDataHoraFinal() {
        return this.dataHoraFinal;
    }

    public String getDataHoraStrFinal() {
        // Converte LocalDateTime para String no formato "yyyy-MM-dd'T'HH:mm"
        return dataHoraFinal != null ? dataHoraFinal.format(DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm")) : null;
    }

    public void setDataHoraStrFinal(String dataHoraStrFinal) {
        this.dataHoraStrFinal = dataHoraStrFinal;

        // Atualiza dataHora ao definir dataHoraStr
        try {
            this.dataHoraFinal = LocalDateTime.parse(dataHoraStrFinal, DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm"));
        } catch (DateTimeParseException e) {
            // Lide com a exceção conforme necessário
            e.printStackTrace(); // Isso imprime a exceção, você pode tratar de outra forma se preferir
        }
    }


    public void setDataHoraFinal(String dataHoraStrFinal) {
        // Defina o formato esperado para a data e hora
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm");

        try {
            // Faça a conversão da string para LocalDateTime usando o formato especificado
            LocalDateTime dataHoraFinal = LocalDateTime.parse(dataHoraStrFinal, formatter);
            this.dataHoraFinal = dataHoraFinal;
        } catch (Exception e) {
            // Trate a exceção ou registre-a conforme necessário
            e.printStackTrace(); // Isso imprime a exceção, mas você pode lidar de maneira diferente
        }
    }
}
