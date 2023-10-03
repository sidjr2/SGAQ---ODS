package br.edu.ufsj.dcefs.sgaq.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record QuadraRecordDto(@NotBlank @NotNull String quadra_name,
                              @NotNull String local,
                              String descricao,
                              @NotNull Integer capacidade) {
}
