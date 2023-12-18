package br.edu.ufsj.dcomp.sgaq.repository;


import br.edu.ufsj.dcomp.sgaq.model.ReservaEquipamento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface ReservaEquipamentoRepository extends JpaRepository<ReservaEquipamento, Long> {
}

