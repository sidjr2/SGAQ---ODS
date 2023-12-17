package br.edu.ufsj.dcomp.sgaq.model;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

@Entity
public class Punicao {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

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

    public Reserva getReserva() {
        return reserva;
    }

    public void setReserva(Reserva reserva) {
        this.reserva = reserva;
    }

    public void setDataHora(LocalDateTime dataHora) {
        this.dataHora = dataHora;
    }

    @ManyToOne
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;
    @ManyToOne
    @JoinColumn(name = "reserva_id")
    private Reserva reserva;

    @Column(name = "data_hora")
    private LocalDateTime dataHora;

    @Transient
    private String dataHoraStr;

    public String getDataHoraStr() {
        // Converte LocalDateTime para String no formato "yyyy-MM-dd'T'HH:mm"
        return dataHora != null ? dataHora.format(DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm")) : null;
    }

    public void setDataHoraStr(String dataHoraStr) {
        this.dataHoraStr = dataHoraStr;

        // Atualiza dataHora ao definir dataHoraStr
        try {
            this.dataHora = LocalDateTime.parse(dataHoraStr, DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm"));
        } catch (DateTimeParseException e) {
            // Lide com a exceção conforme necessário
            e.printStackTrace(); // Isso imprime a exceção, você pode tratar de outra forma se preferir
        }
    }

    public LocalDateTime getDataHora() {
        return this.dataHora;
    }

    public void setDataHora(String dataHoraStr) {
        // Defina o formato esperado para a data e hora
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm");

        try {
            // Faça a conversão da string para LocalDateTime usando o formato especificado
            LocalDateTime dataHora = LocalDateTime.parse(dataHoraStr, formatter);
            this.dataHora = dataHora;
        } catch (Exception e) {
            // Trate a exceção ou registre-a conforme necessário
            e.printStackTrace(); // Isso imprime a exceção, mas você pode lidar de maneira diferente
        }
    }
}
