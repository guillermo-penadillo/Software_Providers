package org.cibertec.edu.pe.test;

import java.util.List;
import java.util.Optional;

import org.cibertec.edu.pe.model.Productos;
import org.cibertec.edu.pe.model.Usuario;
import org.cibertec.edu.pe.repository.IProductosRepo;
import org.cibertec.edu.pe.repository.IUsuarioRepo;
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
@RequestMapping("test/usuarios")
public class TestController {
	
	@Autowired
	IUsuarioRepo usuarioRepo;
	
	@GetMapping
	public List<Usuario> listar(){
		return usuarioRepo.findAll();
	}
	
	@PostMapping
	public void insertar(@RequestBody Usuario usuario) {
		usuarioRepo.save(usuario);
	}
	
	@PutMapping
	public void modificar(@RequestBody Usuario usuario) {
		usuarioRepo.save(usuario);
	}
	
	@GetMapping(value = "/{id}")
	public Optional<Usuario> listarPorId(@PathVariable("id") int id) {
		return usuarioRepo.findById(id);
	}
	
	@DeleteMapping(value = "/{id}")
	public void eliminar(@PathVariable("id") int id) {
		usuarioRepo.deleteById(id);
	} 
}
