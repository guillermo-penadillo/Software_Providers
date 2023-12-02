package org.cibertec.edu.pe.repository;

import org.cibertec.edu.pe.model.Rol;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IRolRepo extends JpaRepository<Rol,Long> {
    public Rol findByNombre(String rol);
}
