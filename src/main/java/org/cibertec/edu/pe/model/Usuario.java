package org.cibertec.edu.pe.model;

import javax.persistence.*;
import lombok.Data;

import java.util.Collection;

@Entity
@Table(name = "usuarios", uniqueConstraints = {
        @UniqueConstraint(columnNames = "username"),
        @UniqueConstraint(columnNames = "telefono")
})
@Data
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "nombre")
    private String nombre;

    @Column(name = "apellido_paterno")
    private String apellidoPat;
    
    @Column(name = "apellido_materno")
    private String apellidoMat;
    
    private String username;
    private String password;
    private String telefono;

    @ManyToMany(fetch = FetchType.EAGER, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(
            name = "usuarios_roles",
            joinColumns = @JoinColumn(name = "usuario_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "rol_id", referencedColumnName = "id")
    )
    private Collection<Rol> roles;


    public Usuario() {
    }

	public Usuario(int id, String nombre, String apellidoPat, String apellidoMat, String username, String password,
			String telefono, Collection<Rol> roles) {
		this.id = id;
		this.nombre = nombre;
		this.apellidoPat = apellidoPat;
		this.apellidoMat = apellidoMat;
		this.username = username;
		this.password = password;
		this.telefono = telefono;
		this.roles = roles;
	}

	public Usuario(String nombre, String apellidoPat, String apellidoMat, String username, String password,
			String telefono, Collection<Rol> roles) {
		this.nombre = nombre;
		this.apellidoPat = apellidoPat;
		this.apellidoMat = apellidoMat;
		this.username = username;
		this.password = password;
		this.telefono = telefono;
		this.roles = roles;
	}


}
