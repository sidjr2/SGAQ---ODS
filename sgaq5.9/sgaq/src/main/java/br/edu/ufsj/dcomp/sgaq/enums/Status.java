package br.edu.ufsj.dcomp.sgaq.enums;

public enum Status {

    ATIVO("Ativo"),

    INATIVO("Inativo"),

    REALIZADO("Realizado"),

    NAOREALIZADO("Não Realizado"),

    DENTRODOHORARIO("Dentro do Horario"),

    FORADOHORARIO("Fora do Horario");

    private String status;

    private Status(String status) {
        this.status = status;
    }
}
