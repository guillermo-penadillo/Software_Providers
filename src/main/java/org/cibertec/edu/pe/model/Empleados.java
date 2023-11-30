package org.cibertec.edu.pe.model;

import javax.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "tb_empleados")
public class Empleados {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id_emp;
	private String nombre_emp;
	private String apePat_emp;
	private String apeMat_emp;
	
	@ManyToOne
	@JoinColumn(name = "id_tipo", nullable = false)
	private TiposEmpleado tipoEmpleado;
	private String usr_emp;
	private String pwd_emp;
	private String telf_emp;
	
	
	
}
