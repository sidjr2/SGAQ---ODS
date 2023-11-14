package br.edu.ufsj.dcefs.sgaq.repositories;

import br.edu.ufsj.dcefs.sgaq.model.QuadraModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface QuadraRepository extends JpaRepository<QuadraModel, UUID> {
}
