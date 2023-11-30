package org.cibertec.edu.pe.model;

import javax.persistence.*;
import lombok.Data;

@Entity
@Table(name = "tb_det_boleta")
@Data
public class DetalleBoleta {

    @EmbeddedId
    private DetalleBoletaId id;

    @ManyToOne
    @MapsId("num_bol")
    @JoinColumn(name = "num_bol")
    private Boleta boleta;

    @ManyToOne
    @MapsId("id_prod")
    @JoinColumn(name = "id_prod")
    private Productos productos;

    @Column(name = "cantidad")
    private int cantidad;

    @Column(name = "preciovta")
    private double precioVenta;
}
