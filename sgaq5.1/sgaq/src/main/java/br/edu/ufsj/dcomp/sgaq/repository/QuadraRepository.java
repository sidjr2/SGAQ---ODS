package br.edu.ufsj.dcomp.sgaq.repository;


import br.edu.ufsj.dcomp.sgaq.enums.Campus;
import br.edu.ufsj.dcomp.sgaq.model.Quadra;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface QuadraRepository extends JpaRepository<Quadra, Long>, CrudRepository<Quadra, Long> {

    @Query("SELECT a FROM Quadra a WHERE a.disponivel = 'ATIVO' ")
    public List<Quadra> findByStatusAtivo();

    @Query("SELECT i FROM Quadra i WHERE i.disponivel = 'INATIVO' ")
    public List<Quadra> findByStatusInativo();

   public List<Quadra> findAll();

    public Optional<Quadra> findById(Long Id);

    public List<Quadra> findByNomeContainingIgnoreCase(String nome);

    @Query("SELECT q FROM Quadra q WHERE q.campus = :campus")
    List<Quadra> findByCampusName(@Param("campus") Campus campus);
}
