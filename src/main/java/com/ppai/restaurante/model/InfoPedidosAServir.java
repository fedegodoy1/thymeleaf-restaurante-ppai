package com.ppai.restaurante.model;

public class InfoPedidosAServir {
    private int id;
    private String numeroMesa;
    private int productosListos;
    private String nombre;

    public InfoPedidosAServir(int id, String numeroMesa, int productosListos, String nombre) {
        this.id = id;
        this.numeroMesa = numeroMesa;
        this.productosListos = productosListos;
        this.nombre = nombre;
    }

    public String getNumeroMesa() {
        return numeroMesa;
    }

    public int getProductosListos() {
        return productosListos;
    }

    public int getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }
}
