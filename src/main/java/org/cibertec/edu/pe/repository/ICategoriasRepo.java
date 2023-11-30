package org.cibertec.edu.pe.repository;

import org.cibertec.edu.pe.model.Categorias;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ICategoriasRepo extends JpaRepository<Categorias, Integer>{

}
