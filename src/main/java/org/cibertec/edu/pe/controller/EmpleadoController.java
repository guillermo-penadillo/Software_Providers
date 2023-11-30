package org.cibertec.edu.pe.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class EmpleadoController {

    @RequestMapping("/empleado")
    public String empleadoView() {
        return "EmpleadoPrincipal";
    }

}
