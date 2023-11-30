package org.cibertec.edu.pe.repository;

import org.cibertec.edu.pe.model.Clientes;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IClientesRepo extends JpaRepository<Clientes, Integer>{

}
