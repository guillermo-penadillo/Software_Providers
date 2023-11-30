package org.cibertec.edu.pe.model;

import lombok.Data;

@Data
public class Carrito {
    private int cod_prod;
    private String nombre;
    private double precio;
    private int cantidad;
    private double subtotal;
}
