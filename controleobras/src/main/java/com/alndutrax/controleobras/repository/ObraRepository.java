package com.alndutrax.controleobras.repository;

import com.alndutrax.controleobras.model.Obra;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ObraRepository extends JpaRepository<Obra, Long> {
}
