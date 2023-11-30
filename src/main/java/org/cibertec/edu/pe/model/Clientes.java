package org.cibertec.edu.pe.model;

import javax.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "tb_clientes")
public class Clientes {
	@Id
	@GeneratedValue(strategy =  GenerationType.IDENTITY)
	private int id_cli;
	
	private String nombre_cli;
	private String apePat_cli;
	private String apeMat_cli;
	private String user_cli;
	private String pwd_cli;
	private String telf_cli;
}
