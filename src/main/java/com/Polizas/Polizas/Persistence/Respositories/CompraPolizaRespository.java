package com.Polizas.Polizas.Persistence.Respositories;

import com.Polizas.Polizas.Persistence.Entities.CompraPoliza;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CompraPolizaRespository extends JpaRepository<CompraPoliza, Long> {
}
