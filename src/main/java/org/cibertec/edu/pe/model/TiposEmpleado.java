package org.cibertec.edu.pe.model;

import javax.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "tb_tipos_empleado")
public class TiposEmpleado {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id_tipo;
	
	private String nombre_tipo;
	private String desc_tipo;
	
}
