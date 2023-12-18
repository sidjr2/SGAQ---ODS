package br.edu.ufsj.dcomp.sgaq.repository;


import br.edu.ufsj.dcomp.sgaq.enums.Status;
import br.edu.ufsj.dcomp.sgaq.model.Reserva;
import br.edu.ufsj.dcomp.sgaq.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface ReservaRepository extends JpaRepository<Reserva,Long>, CrudRepository<Reserva,Long> {

    @Query("SELECT a FROM Reserva a WHERE a.presenca = 'REALIZADO' ")
    public List<Reserva> findByPresenca();

    @Query("SELECT i FROM Reserva i WHERE i.presenca = 'NAOREALIZADO' ")
    public List<Reserva> findByPresencaInativo();

    @Query("SELECT a FROM Reserva a WHERE a.punicao = 'ATIVO' ")
    public List<Reserva> findByPunicao();

    @Query("SELECT i FROM Reserva i WHERE i.punicao = 'INATIVO' ")
    public List<Reserva> findByPunicaoInativo();


    @Modifying
    @Query("UPDATE Presenca p SET p.presenca = :novoStatus WHERE p.reserva.id = :id")
    int atualizarStatusPorId(@Param("id") Long id, @Param("novoStatus") Status novoStatus);

    public Optional<Reserva> findById(Long id);

    public List<Reserva> findByNomeContainingIgnoreCase(String nome);
}
