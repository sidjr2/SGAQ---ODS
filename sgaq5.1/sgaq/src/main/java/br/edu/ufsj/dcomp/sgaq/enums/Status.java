package br.edu.ufsj.dcomp.sgaq.enums;

public enum Status {

    ATIVO("Ativo"),
    INATIVO("Inativo"),

    NAOREALIZADO("Não Realizado"),
    REALIZADO("Realizado"),

    DENTRODOHORARIO("Dentro do Horario");



    private String status;

    private Status(String status) {
        this.status = status;
    }
}
