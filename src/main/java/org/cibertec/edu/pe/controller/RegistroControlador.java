package org.cibertec.edu.pe.controller;

import org.cibertec.edu.pe.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class RegistroControlador {
    @Autowired
    private UsuarioService usuarioService;
    

    @GetMapping({ "/", "/login" })
    public String iniciarSesion() {
      return "login";
    }

    @GetMapping("/default")
    public String redirigirSegunRol(Authentication authentication) {
        if (authentication.getAuthorities().stream().anyMatch(r -> r.getAuthority().equals("ROLE_MANTENEDOR"))) {
            return "redirect:/empleado";
        } else if (authentication.getAuthorities().stream().anyMatch(r -> r.getAuthority().equals("ROLE_ADMIN"))) {
            return "redirect:/admin";
        } else {
        	return "redirect:/cliente";
        }
    }
    @GetMapping("/cliente")
    @PreAuthorize("hasRole('CLIENTE')")
    public String verPaginaDeInicioCliente(Model modelo) {
        modelo.addAttribute("usuarios", usuarioService.listarUsuarios());
        return "index";
    }

    @GetMapping("/admin")
    @PreAuthorize("hasRole('ADMIN')")
    public String verPaginaDeInicioAdmin(Model modelo) {
        modelo.addAttribute("usuarios", usuarioService.listarUsuarios());
        return "AdminPrincipal";
    }

    @GetMapping("/empleado")
    @PreAuthorize("hasRole('MANTENEDOR')")
    public String empleadoView() {
        return "EmpleadoPrincipal";
    }
    

}
