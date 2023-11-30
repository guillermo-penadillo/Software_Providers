package org.cibertec.edu.pe.repository;

import org.cibertec.edu.pe.model.Boleta;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IBoletaRepo extends JpaRepository<Boleta,String> {
    Boleta findTopByOrderByNumBolDesc();
    Boleta findByNumBol(String numBoleta);
}
