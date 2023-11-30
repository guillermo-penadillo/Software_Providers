package org.cibertec.edu.pe.test;

import java.util.List;
import java.util.Optional;

import org.cibertec.edu.pe.model.Productos;
import org.cibertec.edu.pe.repository.IProductosRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("test/v1/producto")
public class TestController {
	
	@Autowired
	IProductosRepo prodRepo;
	
	@GetMapping
	public List<Productos> listar(){
		return prodRepo.findAll();
	}
	
	@PostMapping
	public void insertar(@RequestBody Productos p) {
		prodRepo.save(p);
	}
	
	@PutMapping
	public void modificar(@RequestBody Productos p) {
		prodRepo.save(p);
	}
	
	@GetMapping(value = "/{id}")
	public Optional<Productos> listarPorId(@PathVariable("id") int id) {
		return prodRepo.findById(id);
	}
	
	@DeleteMapping(value = "/{id}")
	public void eliminar(@PathVariable("id") int id) {
		prodRepo.deleteById(id);
	} 
}
