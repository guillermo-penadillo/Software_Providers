	package org.cibertec.edu.pe.controller;
	
	import java.util.ArrayList;
	import java.util.Date;
	import java.util.List;
	import java.util.Optional;
	
	import javax.servlet.http.HttpSession;

import org.cibertec.edu.pe.dto.UsuarioRegistroDTO;
import org.cibertec.edu.pe.model.Boleta;
	import org.cibertec.edu.pe.model.Carrito;
	import org.cibertec.edu.pe.model.DetalleBoleta;
	import org.cibertec.edu.pe.model.DetalleBoletaId;
	import org.cibertec.edu.pe.model.Productos;
	import org.cibertec.edu.pe.model.Usuario;
	import org.cibertec.edu.pe.repository.IBoletaRepo;
	import org.cibertec.edu.pe.repository.IDetalleBoletaRepo;
	import org.cibertec.edu.pe.repository.IProductosRepo;
	import org.cibertec.edu.pe.repository.IUsuarioRepo;
	import org.cibertec.edu.pe.service.UsuarioService;
	import org.springframework.beans.factory.annotation.Autowired;
	import org.springframework.security.core.Authentication;
	import org.springframework.security.core.context.SecurityContextHolder;
	import org.springframework.stereotype.Controller;
	import org.springframework.ui.Model;
	import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
	import org.springframework.web.bind.annotation.PostMapping;
	import org.springframework.web.bind.annotation.RequestMapping;
	import org.springframework.web.bind.annotation.RequestParam;
	
	@Controller
	@RequestMapping("/cliente")
	public class ClienteController {
		@Autowired
		IUsuarioRepo usuarioRepo;
		
	    @Autowired
	    IProductosRepo productosRepo;
	    
	    @Autowired
	    IBoletaRepo boletaRepo;
	    
	    @Autowired
	    IDetalleBoletaRepo detalleBoletaRepo;
		@Autowired
		UsuarioService usuarioService;
	
	    @RequestMapping("/verProductos")
	    public String productosView(Model model) {
	        model.addAttribute("lstProducto", productosRepo.findAll());
	        return "ClienteVerProductos";
	    }

		@PostMapping("/actualizarPerfil")
		public String actualizarPerfil(@ModelAttribute("cliente") UsuarioRegistroDTO cliente, Model model) {
			Usuario usuarioActualizado = usuarioService.actualizaCliente(cliente);

			if (usuarioActualizado != null) {
				model.addAttribute("cliente", cliente);
				model.addAttribute("mensaje", "Perfil actualizado con éxito");
				return "redirect:/cliente/verPerfil/" + cliente.getUsername();
			} else {
				model.addAttribute("error", "No se pudo actualizar el perfil. Por favor, inténtelo de nuevo.");
				return "redirect:/";
			}
		}

		@GetMapping("/verDetalle")
	    public String detalleView(@RequestParam("prodId") int prodId, Model model)  {
	        try {
	            Productos productos = productosRepo.findById(prodId).orElse(null);
	            model.addAttribute("producto",productos);
	        } catch (Exception e) {
	            System.out.println(e.getMessage());
	        }
	        return "ClienteVerDetalleProducto";
	    }
	
		@GetMapping("/verPerfil/{username}")
		public String perfilView(Model model, @PathVariable("username") String username) {
			Usuario user = usuarioRepo.findByUsername(username);

			if (user != null) {
				model.addAttribute("cliente", user);
				return "ClienteVerPerfil";
			} else {
				// Redirigir al usuario a la página de inicio, por ejemplo
				return "redirect:/login";
			}
		}
	
	    //
	    // Proceso de Compra
	    //
	    @GetMapping("/agregarAlCarrito")
	    public String agregarAlCarrito(@RequestParam("Id") int id, @RequestParam("cantidad") int cantidad, HttpSession session) {
	        System.out.println("ID: " + id + ", Cantidad: " + cantidad);
	        List<Carrito> carrito = (List<Carrito>) session.getAttribute("carrito");
	
	        if (carrito == null) {
	            carrito = new ArrayList<>();
	        }
	
	        Optional<Productos> optionalProductos = productosRepo.findById(id);
	
	        if (optionalProductos.isPresent()) {
	            Productos productos = optionalProductos.get();
	
	            boolean encontrado = false;
	            for (Carrito item : carrito) {
	                if (item.getCod_prod() == id) {
	                    item.setCantidad(item.getCantidad() + cantidad);
	                    item.setSubtotal(item.getPrecio() * item.getCantidad());
	                    encontrado = true;
	                    break;
	                }
	            }
	
	            if (!encontrado) {
	                Carrito newProd = new Carrito();
	                newProd.setCod_prod(id);
	                newProd.setCantidad(cantidad);
	                newProd.setNombre(productos.getNombre_prod());
	                newProd.setPrecio(productos.getPrecio());
	                newProd.setSubtotal(productos.getPrecio() * cantidad);
	
	                carrito.add(newProd);
	            }
	            session.setAttribute("carrito", carrito);
	            System.out.println("Carrito después de agregar: " + carrito);
	        }
	
	        return "redirect:/cliente/verCarrito";
	    }
	
	    @GetMapping("/verCarrito")
	    public String verCarrito(Model model, HttpSession session) {
	        List<Carrito> carrito = (List<Carrito>) session.getAttribute("carrito");
	
	        if (carrito == null) {
	            carrito = new ArrayList<>();
	        }
	
	        double total = carrito.stream().mapToDouble(Carrito::getSubtotal).sum();


			// Obtener el nombre de usuario del principal autenticado
			String username = getUsernameFromPrincipal();

			// Buscar al usuario por nombre de usuario
			Usuario user = usuarioRepo.findByUsername(username);

			if (user != null) {
				model.addAttribute("cliente", user);
			} else {
				// Redirigir al usuario a la página de inicio, por ejemplo
				return "redirect:/login";
			}

	        model.addAttribute("carrito", carrito);
	        model.addAttribute("total", total);
	
	        return "ClienteVerCarrito";
	    }
	
	    @GetMapping("/eliminarDelCarrito")
	    public String eliminarDelCarrito(@RequestParam("id") int id, HttpSession session) {
	        List<Carrito> carrito = (List<Carrito>) session.getAttribute("carrito");
	        if (carrito != null) {
	            Carrito productoAEliminar = null;
	            for (Carrito item : carrito) {
	                if (item.getCod_prod()== id) {
	                    productoAEliminar = item;
	                    break;
	                }
	            }
	            if (productoAEliminar != null) {
	                carrito.remove(productoAEliminar);
	                session.setAttribute("carrito", carrito);
	            }
	        }
	        return "redirect:/cliente/verCarrito";
	    }
	    
	    @GetMapping("/cancelarCompra")
	    public String cancelarCompra(HttpSession session) {
	        session.removeAttribute("carrito");
	        return "redirect:/cliente";
	    }
	
	    @PostMapping("/confirmar")
	    public String confirmarCompra(@RequestParam("clienteId") int clienteId,
	                                  @RequestParam("total") double total,
	                                  HttpSession session,Model model) {
	        List<Carrito> carrito = (List<Carrito>) session.getAttribute("carrito");
	
	        // Crear una nueva boleta
	        Boleta boleta = new Boleta();
	        boleta.setUsuario(usuarioRepo.findById(clienteId).orElse(null));
	        boleta.setFechaBoleta(new Date());
	        boleta.setNumBol(generarNumeroBoleta());
	        boleta.setTotal(total);
	        boletaRepo.save(boleta);
	
	        // Procesar cada producto en el carrito
	        for (Carrito item : carrito) {
	            DetalleBoleta detalle = new DetalleBoleta();
	
	            DetalleBoletaId detalleId = new DetalleBoletaId();
	            detalleId.setNum_bol(boleta.getNumBol());
	            detalleId.setId_prod(item.getCod_prod());
	
	            detalle.setId(detalleId);
	            detalle.setBoleta(boleta);
	            detalle.setProductos(productosRepo.findById(item.getCod_prod()).orElse(null));
	            detalle.setCantidad(item.getCantidad());
	            detalle.setPrecioVenta(item.getPrecio());
	
	            detalleBoletaRepo.save(detalle);
	        }
	        session.removeAttribute("carrito");
	
	        // DespuÃ©s de guardar la boleta
	        model.addAttribute("boleta", boleta);
	        return "ClienteConfirmarCompra";
	    }
	
	    @GetMapping("/verBoleta/{numBoleta}")
	    public String verBoleta(@PathVariable String numBoleta, Model model) {
	        Boleta boleta = boletaRepo.findByNumBol(numBoleta);
	
	        List<DetalleBoleta> detallesBoleta = detalleBoletaRepo.findByBoleta(boleta);
	
	        // Agregar la boleta y sus detalles al modelo
	        model.addAttribute("boleta", boleta);
	        model.addAttribute("detallesBoleta", detallesBoleta);
	
	        return "ClienteVerBoleta";
	    }
	
	    public String generarNumeroBoleta() {
	        Boleta ultimaBoleta = boletaRepo.findTopByOrderByNumBolDesc();
	
	        if (ultimaBoleta != null) {
	            String numeroActual = ultimaBoleta.getNumBol();
	
	            int numero = Integer.parseInt(numeroActual.substring(1));
	            numero++;
	
	            String nuevoNumero = String.format("B%04d", numero);
	
	            return nuevoNumero;
	        } else {
	            return "B0001";
	        }
	    }

		// Método para obtener el nombre de usuario del principal autenticado
		private String getUsernameFromPrincipal() {
			Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
			return authentication.getName();
		}
	}
