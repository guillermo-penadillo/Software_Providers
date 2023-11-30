package org.cibertec.edu.pe.model;

import javax.persistence.*;
import lombok.Data;

@Entity
@Table(name = "rol")
@Data
public class Rol {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nombre;

    public Rol() {
    }

    public Rol(Long id, String nombre) {
        super();
        this.id = id;
        this.nombre = nombre;
    }

    public Rol(String nombre) {
        super();
        this.nombre = nombre;
    }
}
