package org.cibertec.edu.pe.controller;

import org.cibertec.edu.pe.dto.UsuarioRegistroDTO;
import org.cibertec.edu.pe.service.UsuarioService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/registro")
public class RegistroUsuarioController {

    private UsuarioService usuarioService;

    public RegistroUsuarioController(UsuarioService usuarioServicio) {
        super();
        this.usuarioService = usuarioServicio;
    }

    @ModelAttribute("usuario")
    public UsuarioRegistroDTO retornarNuevoUsuarioRegistroDTO() {
        return new UsuarioRegistroDTO();
    }

    @GetMapping
    public String mostrarFormularioDeRegistro() {
        return "registro";
    }

    @PostMapping
    public String registrarCuentaDeUsuario(@ModelAttribute("usuario") UsuarioRegistroDTO registroDTO) {
        usuarioService.guardar(registroDTO);
        return "redirect:/registro?exito";
    }

}
