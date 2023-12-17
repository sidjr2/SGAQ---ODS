package br.edu.ufsj.dcomp.sgaq.enums;

public enum TipoUsuario {

    PROFESSOR("Professor"),
    ATLETICA("Atletica"),
    TECNICO("Tecnico"),
    ALUNO("Aluno"),
    COMUNIDADE("Comunidade");

    private String tipo;

    private TipoUsuario(String tipo) {
        this.tipo = tipo;
    }

}
