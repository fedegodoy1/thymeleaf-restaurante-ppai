package com.ppai.restaurante.model;

import java.util.List;

/**
 * Clase que representa los datos necesarios para armar InterfazDispositivoMovil
 */
public class DatosInterfazDispositivoMovil {

    private List<InfoPedidosAServir> infoPedidosAServir;
    private boolean nuevoLoteIngresado;

    public DatosInterfazDispositivoMovil(List<InfoPedidosAServir> infoPedidosAServir, boolean nuevoLoteIngresado) {
        this.infoPedidosAServir = infoPedidosAServir;
        this.nuevoLoteIngresado = nuevoLoteIngresado;
    }

    public List<InfoPedidosAServir> getInfoPedidosAServir() {
        return infoPedidosAServir;
    }

    public boolean isNuevoLoteIngresado() {
        return nuevoLoteIngresado;
    }
}
