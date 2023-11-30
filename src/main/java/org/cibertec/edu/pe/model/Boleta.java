package org.cibertec.edu.pe.model;

import javax.persistence.*;
import lombok.Data;

import java.util.Date;

@Entity
@Table(name = "tb_boleta")
@Data
public class Boleta {

    @Id
    @Column(name = "num_bol")
    private String numBol;

    @Column(name = "fch_bol")
    private Date fechaBoleta;

    @ManyToOne
    @JoinColumn(name = "id_cli")
    private Clientes clientes;
}
