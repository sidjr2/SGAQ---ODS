package br.edu.ufsj.dcomp.sgaq.repository;

import br.edu.ufsj.dcomp.sgaq.model.Presenca;
import br.edu.ufsj.dcomp.sgaq.model.Quadra;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface PresencaRepository extends JpaRepository<Presenca, Long>, CrudRepository<Presenca, Long> {
    public List<Presenca> findAll();
    public Optional<Presenca> findById(Long Id);

    @Query("SELECT a FROM Presenca a WHERE a.presenca = 'ATIVO' ")
    public List<Presenca> findByStatusAtivo();

    @Query("SELECT i FROM Presenca i WHERE i.presenca = 'INATIVO' ")
    public List<Presenca> findByStatusInativo();



}
