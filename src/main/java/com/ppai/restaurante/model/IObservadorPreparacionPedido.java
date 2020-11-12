package com.ppai.restaurante.model;

import java.util.List;

public interface IObservadorPreparacionPedido {
    void actualizar(int id, String nombre, String numeroMesa, int productosListos);
    Integer getPiso();
    String getNombreMozo();
    List<InfoPedidosAServir> obtenerInfoPedidosListosAServir();
    boolean chequeoDeDuplicados(InfoPedidosAServir info);
}
