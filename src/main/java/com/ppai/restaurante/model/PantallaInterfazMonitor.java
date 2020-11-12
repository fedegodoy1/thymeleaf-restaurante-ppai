package com.ppai.restaurante.model;

import java.util.ArrayList;
import java.util.List;

public class PantallaInterfazMonitor implements IObservadorPreparacionPedido {

    /**
     * Lista de pedidos que se notifican a la pantalla.
     */
    private List<InfoPedidosAServir> infoPedidosListosAServir;

    /**
     * Número del piso.
     */
    private Integer numeroPiso;

    /**
     * Constructor de la PantallaInterfazMonitor.
     * @param numeroPiso numero de piso que corresponde la pantalla interfaz monitor.
     */
    public PantallaInterfazMonitor(Integer numeroPiso) {
        this.infoPedidosListosAServir = new ArrayList<>();
        this.numeroPiso = numeroPiso;
    }

    // A través del método actualizar, se realiza la notificación nueva a la pantalla.
    // En donde cada boundary, implementa su propia funcionalidad para publicar en la pantalla.
    @Override
    public void actualizar(int id, String nombre, String numeroMesa, int productosListos) {
        // Se agrega un nuevo item dentro de la lista de pedidos listos a servir.
        final InfoPedidosAServir infoPedidoAServir = new InfoPedidosAServir(id, numeroMesa, productosListos, nombre);
        if(chequeoDeDuplicados(infoPedidoAServir)) {
            return;
        }
        infoPedidosListosAServir.add(infoPedidoAServir);
    }

    /**
     * Método que implementa de la interface IObservadorPreparacionPedido, en donde devuelve el numero del piso.
     * @return numero de piso.
     */
    @Override
    public Integer getPiso() {
        return numeroPiso;
    }

    /**
     * Método que implementa de la interface IObservadorPreparacionPedido, en donde la PantallaInterfazMonitor,
     * no tiene un nombre de mozo, por lo que devolverá null.
     */
    @Override
    public String getNombreMozo() {
        return null;
    }

    /**
     * Método que implementa de la interface IObservadorPreparacionPedido, que se encarga de devolver la lista
     * de pedidos listos a servir.
     * @return lista de pedidos a servir.
     */
    @Override
    public List<InfoPedidosAServir> obtenerInfoPedidosListosAServir() {
        return infoPedidosListosAServir;
    }

    /**
     * Método que implementa de la interface IObservadorPreparacionPedido, que se ecarga de chequear por duplicados
     * dentro de la lista de pedidos a servir, en funcion al pedido que se le pase por parámetro.
     * @param info pedido a chequear por duplicados dentro de la lista de pedidos listos a servir.
     * @return true si ya existe dentro de la lista o false si no se encuentra.
     */
    @Override
    public boolean chequeoDeDuplicados(InfoPedidosAServir info) {
        for(InfoPedidosAServir infoPedidosAServir : infoPedidosListosAServir) {
            if(infoPedidosAServir.getId()==info.getId()) {
                return true;
            }
        }
        return false;
    }
}
