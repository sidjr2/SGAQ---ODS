package br.edu.ufsj.dcomp.sgaq.repository;


import br.edu.ufsj.dcomp.sgaq.model.Reserva;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface ReservaRepository extends JpaRepository<Reserva,Long>, CrudRepository<Reserva,Long> {

    @Query("SELECT a FROM Reserva a WHERE a.presenca = 'ATIVO' ")
    public List<Reserva> findByPresenca();

    @Query("SELECT i FROM Reserva i WHERE i.presenca = 'INATIVO' ")
    public List<Reserva> findByPresencaInativo();

    @Query("SELECT a FROM Reserva a WHERE a.punicao = 'ATIVO' ")
    public List<Reserva> findByPunicao();

    @Query("SELECT i FROM Reserva i WHERE i.punicao = 'INATIVO' ")
    public List<Reserva> findByPunicaoInativo();

    public Optional<Reserva> findById(Long Id);

    public List<Reserva> findByNomeContainingIgnoreCase(String nome);
}
