package org.cibertec.edu.pe.dto;

import lombok.Data;

@Data
public class UsuarioRegistroDTO {
    private Long id;
    private String nombre;
    private String apellido;
    private String email;
    private String password;

    public UsuarioRegistroDTO() {

    }

    public UsuarioRegistroDTO(String nombre, String apellido, String email, String password) {
        super();
        this.nombre = nombre;
        this.apellido = apellido;
        this.email = email;
        this.password = password;
    }
}
