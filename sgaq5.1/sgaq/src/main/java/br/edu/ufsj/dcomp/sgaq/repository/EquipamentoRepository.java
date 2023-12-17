package br.edu.ufsj.dcomp.sgaq.repository;

import br.edu.ufsj.dcomp.sgaq.model.Equipamento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface EquipamentoRepository extends JpaRepository<Equipamento, Long>, CrudRepository<Equipamento, Long> {

    @Query("SELECT a FROM Equipamento a WHERE a.disponivel = 'ATIVO' ")
    public List<Equipamento> findByStatusAtivo();

    @Query("SELECT i FROM Equipamento i WHERE i.disponivel = 'INATIVO' ")
    public List<Equipamento> findByStatusInativo();

    //@Query("SELECT e FROM Equipamento e INNER JOIN e.quadra q WHERE e.reserva = 'APROVADO' AND q.id = :quadra")
    //public List<Equipamento> findByReserva(@Param("quadra") String quadra);

    public List<Equipamento> findByNomeContainingIgnoreCase(String nome);

}
