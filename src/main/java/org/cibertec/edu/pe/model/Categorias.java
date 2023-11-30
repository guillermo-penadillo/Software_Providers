package org.cibertec.edu.pe.model;

import javax.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "tb_categorias")
public class Categorias {

	@Id
	@GeneratedValue(strategy =GenerationType.IDENTITY )
	private int id_cat;
	
	private String nombre_cat;
	private String desc_cat;
}
