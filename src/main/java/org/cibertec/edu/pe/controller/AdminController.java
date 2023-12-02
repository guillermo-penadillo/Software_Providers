package org.cibertec.edu.pe.controller;

import org.cibertec.edu.pe.model.Boleta;
import org.cibertec.edu.pe.model.DetalleBoleta;
import org.cibertec.edu.pe.repository.IBoletaRepo;
import org.cibertec.edu.pe.repository.IDetalleBoletaRepo;
import org.cibertec.edu.pe.repository.IUsuarioRepo;
import org.cibertec.edu.pe.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/admin")
public class AdminController {
    @Autowired
    UsuarioService usuarioService;

    @Autowired
    IBoletaRepo boletaRepo;
    @Autowired
    IDetalleBoletaRepo detalleBoletaRepo;

    @GetMapping("/verUsuarios")
    public String verUsuarios(Model model) {
        model.addAttribute("usuarios", usuarioService.listarUsuarios());
        return "AdminVerUsuarios";
    }

    @GetMapping("/verVentas")
    public String verVentas(Model model) {
        // Obtener todas las boletas
        List<Boleta> boletas = boletaRepo.findAll();

        // Crear una lista para almacenar los detalles de las boletas
        List<DetalleBoleta> detalles = new ArrayList<>();

        // Iterar sobre cada boleta y obtener sus detalles
        for (Boleta boleta : boletas) {
            List<DetalleBoleta> detallesBoleta = detalleBoletaRepo.findByBoleta(boleta);
            detalles.addAll(detallesBoleta);
        }

        // Agregar las boletas y detalles al modelo
        model.addAttribute("boletas", boletas);
        model.addAttribute("detalles", detalles);
        return "AdminVerVentas";
    }
}
