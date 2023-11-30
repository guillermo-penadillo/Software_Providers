package org.cibertec.edu.pe.controller;

import org.cibertec.edu.pe.model.Productos;
import org.cibertec.edu.pe.repository.IProductosRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class ProjectController {

    @RequestMapping("/")
    public String index(Model model) {
        return "indexMio";
    }

    @RequestMapping("/cliente")
    public String clienteView() {
        return "ClientePrincipal";
    }


}
