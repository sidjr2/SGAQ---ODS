package br.edu.ufsj.dcomp.sgaq.enums;

public enum Status {

    ATIVO("Ativo"),
    INATIVO("Inativo");

    private String status;

    private Status(String status) {
        this.status = status;
    }
}
