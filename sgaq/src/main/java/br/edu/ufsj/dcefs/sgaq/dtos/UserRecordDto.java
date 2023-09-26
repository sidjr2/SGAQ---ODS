package br.edu.ufsj.dcefs.sgaq.dtos;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record UserRecordDto(@NotBlank @NotNull String user_name,
                            @NotBlank @NotNull String senha,
                            @NotNull String nome,
                            @NotNull String departamento,
                            @NotNull String matricula,
                            @NotNull String tipo,
                            @NotNull String telefone,
                            @NotNull String cidade,
                            @NotNull Integer punicao,
                            @NotNull Boolean reserva,
                            @NotNull Integer prioridade) {

}
