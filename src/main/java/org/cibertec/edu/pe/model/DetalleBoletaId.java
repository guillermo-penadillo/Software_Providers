package org.cibertec.edu.pe.model;

import javax.persistence.*;
import lombok.Data;

import java.io.Serializable;

@Embeddable
@Data
public class DetalleBoletaId implements Serializable {
    @Column(name = "num_bol")
    private String num_bol;

    @Column(name = "id_prod")
    private int id_prod;
}
