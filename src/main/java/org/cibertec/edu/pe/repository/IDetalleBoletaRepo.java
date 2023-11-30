package org.cibertec.edu.pe.repository;

import org.cibertec.edu.pe.model.Boleta;
import org.cibertec.edu.pe.model.DetalleBoleta;
import org.cibertec.edu.pe.model.DetalleBoletaId;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IDetalleBoletaRepo extends JpaRepository<DetalleBoleta, DetalleBoletaId> {
    List<DetalleBoleta> findByBoleta(Boleta boleta);
}
