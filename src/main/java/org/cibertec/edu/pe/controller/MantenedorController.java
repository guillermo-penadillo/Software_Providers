package org.cibertec.edu.pe.controller;

import java.util.List;

import org.cibertec.edu.pe.model.Categorias;
import org.cibertec.edu.pe.model.Productos;
import org.cibertec.edu.pe.repository.ICategoriasRepo;
import org.cibertec.edu.pe.repository.IProductosRepo;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/empleado")
public class MantenedorController {
	
	@Autowired
	IProductosRepo productRepo;
	
	@Autowired
	ICategoriasRepo categoriaRepo;
	
	@RequestMapping("/ListarProductos")
    public String listarProductos(Model model) {
        List<Productos> productos = productRepo.findAll();
        model.addAttribute("productos", productos);
        return "EmpleadoListarProductos";
    }
	
    @ModelAttribute("categorias")
    public List<Categorias> listarCategorias() {
        return categoriaRepo.findAll();
    }
    @GetMapping("/agregar")
    public String mostrarFormularioAgregar(Model model) {
        List<Categorias> categorias = categoriaRepo.findAll();
        model.addAttribute("categorias", categorias);
        model.addAttribute("nuevoProducto", new Productos());
        return "EmpleadoAgregarProducto";
    }

    @PostMapping("/agregarProducto")
    public String agregarProducto(@ModelAttribute("nuevoProducto") Productos nuevoProducto) {
        productRepo.save(nuevoProducto);
        return "redirect:/empleado/ListarProductos";
    }

    
    @PostMapping("/eliminar/{id}")
    public String eliminarProducto(@PathVariable("id") int id) {
        productRepo.deleteById(id);
        return "redirect:/empleado/ListarProductos";
    }
    
    @GetMapping("/actualizar/{id}")
    public String mostrarFormularioEditar(@PathVariable("id") int id, Model model) {
        Productos producto = productRepo.findById(id).orElse(null);
        List<Categorias> categorias = categoriaRepo.findAll();
        if (producto != null) {
            model.addAttribute("producto", producto);
            model.addAttribute("categorias", categorias);
            return "EmpleadoEditarProducto";
        } else {
            return "redirect:/empleado/ListarProductos";
        }
    }

    @PostMapping("/actualizar/{id}")
    public String editarProducto(@PathVariable("id") int id, @ModelAttribute("producto") Productos productoActualizado) {
        Productos productoExistente = productRepo.findById(id).orElse(null);
        if (productoExistente != null) {
            productoExistente.setNombre_prod(productoActualizado.getNombre_prod());
            productoExistente.setDesc_prod(productoActualizado.getDesc_prod());
            productoExistente.setCategoria(productoActualizado.getCategoria());
            productoExistente.setImagen(productoActualizado.getImagen());
            productoExistente.setPrecio(productoActualizado.getPrecio());
            productoExistente.setCalificacion(productoActualizado.getCalificacion());

            productRepo.save(productoExistente);
        }
        
        return "redirect:/empleado/ListarProductos";
    }

}
