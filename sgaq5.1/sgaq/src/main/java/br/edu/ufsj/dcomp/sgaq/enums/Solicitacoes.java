package br.edu.ufsj.dcomp.sgaq.enums;

public enum Solicitacoes {
    APROVADO("Aprovado"),
    PENDENTE("Pendente"),
    REJEITADO("Rejeitado");

    private String solicitacoes;

    private Solicitacoes(String solicitacoes) {
        this.solicitacoes = solicitacoes;
    }
}
