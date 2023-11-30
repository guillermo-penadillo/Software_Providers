package org.cibertec.edu.pe.model;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name = "tb_productos")
public class Productos {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id_prod;
	private String nombre_prod;	
	private String desc_prod;
	
	@ManyToOne
	@JoinColumn(name = "id_cat", nullable = false)
	private Categorias categoria;
	
	private String imagen;
	private double precio;
	private double calificacion;
}
