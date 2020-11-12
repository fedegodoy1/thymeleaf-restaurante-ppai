package com.ppai.restaurante.model;

public class DetalleDePedido {

    private Integer id;
    private String numeroMesa;
    private Integer cantidad;
    private Double tiempoEspera;
    private String nombreProducto;
    private String nombreMenu;

    public DetalleDePedido(Integer id, String numeroMesa, Integer cantidad, Double tiempoEspera, String nombreProducto, String nombreMenu) {
        this.id = id;
        this.numeroMesa = numeroMesa;
        this.cantidad = cantidad;
        this.tiempoEspera = tiempoEspera;
        this.nombreProducto = nombreProducto;
        this.nombreMenu = nombreMenu;
    }

    public Integer getId() {
        return id;
    }

    public String getNumeroMesa() {
        return numeroMesa;
    }

    public Integer getCantidad() {
        return cantidad;
    }

    public Double getTiempoEspera() {
        return tiempoEspera;
    }

    public String getNombreProducto() {
        return nombreProducto;
    }

    public String getNombreMenu() {
        return nombreMenu;
    }
}
