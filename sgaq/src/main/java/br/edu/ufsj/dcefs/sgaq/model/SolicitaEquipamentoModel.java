package br.edu.ufsj.dcefs.sgaq.model;

import jakarta.persistence.*;

import java.io.Serializable;
import java.util.UUID;

@Entity
@Table(name = "TP_SOLICITA_EQUIPAMENTO")
public class SolicitaEquipamentoModel implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id_solicitacao_equipamento;

}
