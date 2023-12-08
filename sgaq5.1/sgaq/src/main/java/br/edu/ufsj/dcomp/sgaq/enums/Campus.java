package br.edu.ufsj.dcomp.sgaq.enums;

public enum Campus {
    CAP("Campus Alto Paraopeba (CAP)"),
    CCO("Campus Cento-Oeste Dona Lindu (CCO):"),
    CDB("Campus Dom Bosco (CDB)"),
    CSA("Campus Santo Antônio (CSA)"),
    CSL("Campus Sete Lagoas (CSL)"),
    CTAN("Campus Tancredo Neves (CTAn)");

    private String campus;

    private Campus(String campus) {
        this.campus = campus;
    }

}