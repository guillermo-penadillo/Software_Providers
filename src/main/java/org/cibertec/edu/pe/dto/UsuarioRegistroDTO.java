package org.cibertec.edu.pe.dto;

import lombok.Data;

@Data
public class UsuarioRegistroDTO {
    private int id;
    private String nombre;
    private String apellidoPat;
    private String apellidoMat;
    private String username;
    private String password;
    private String telefono;

    public UsuarioRegistroDTO() {

    }

	public UsuarioRegistroDTO(String nombre, String apellidoPat, String apellidoMat, String username, String password,
			String telefono) {
		this.nombre = nombre;
		this.apellidoPat = apellidoPat;
		this.apellidoMat = apellidoMat;
		this.username = username;
		this.password = password;
		this.telefono = telefono;
	}


}
