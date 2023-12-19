package br.edu.ufsj.dcomp.sgaq.repository;

import br.edu.ufsj.dcomp.sgaq.model.Presenca;
import br.edu.ufsj.dcomp.sgaq.model.Punicao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface PunicaoRepository extends JpaRepository<Punicao, Long>, CrudRepository<Punicao, Long>{
    public List<Punicao> findAll();
    public Optional<Punicao> findById(Long Id);

    @Query("SELECT a FROM Punicao a WHERE a.punicao = 'ATIVO' ")
    public List<Punicao> findByStatusAtivo();

    @Query("SELECT i FROM Punicao i WHERE i.punicao = 'INATIVO' ")
    public List<Punicao> findByStatusInativo();

}
