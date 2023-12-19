package br.edu.ufsj.dcomp.sgaq.enums;

public enum Departamento {
    DEPEL("Departamento de Engenharia Elétrica (DEPEL)"),
    DEMEP("Departamento de Mecânica e Produção (DEMEP)"),
    DEMAT("Departamento de Matemática e Estatística (DEMAT)"),
    DECIS("Departamento de Ciências Sociais (DECIS)"),
    DECED("Departamento de Ciências da Educação (DECED)"),
    DCNAT("Departamento de Ciências Naturais (DCNAT)"),
    DBTEC("Departamento de Biotecnologia (DBTEC)"),
    DFIME("Departamento de Filosofia e Métodos (DFIME)"),
    DELAC("Departamento de Letras, Artes e Cultura (DELAC)"),
    DEMED("Departamento de Medicina (DEMED)"),
    DPSIC("Departamento de Psicologia (DPSIC)"),
    DEACE("Departamento de Artes da Cena (DEACE)"),
    DAUAP("Departamento de Arquitetura, Urbanismo e Artes Aplicadas (DAUAP)"),
    DCOMP("Departamento de Ciência da Computação (DCOMP)"),
    DECAC("Departamento de Ciências Administrativas e Contábeis (DECAC)"),
    DCEFS("Departamento de Ciências da Educação Física e Saúde (DCEFS)"),
    DCECO("Departamento de Ciências Econômicas (DCECO)"),
    DCOMS("Departamento de Comunicação Social (DCOMS)"),
    DEGEO("Departamento de Geociências (DEGEO)"),
    DMUSI("Departamento de Música (DMUSI)"),
    DEZOO("Departamento de Zootecnia (DEZOO)");

    private String departamento;

    private Departamento(String departamento) {
        this.departamento = departamento;
    }
}