package com.ppai.restaurante.model;

import java.util.ArrayList;
import java.util.List;

public class PantallaDispositivoMovil implements IObservadorPreparacionPedido {

    /**
     * Lista de pedidos que se notifican a la pantalla.
     */
    private List<InfoPedidosAServir> infoPedidosListosAServir;

    /**
     * Nombre de usuario del mozo.
     */
    private String nombreUsuarioMozo;

    /**
     * Booleano que me permite saber si hubo un nuevo lote ingresado para la generacion de la notificación.
     */
    private boolean nuevoLoteIngresado = false;

    /**
     * Constructor de la PantallaDispositivoMovil.
     * @param nombreUsuarioMozo nombre del mozo que corresponde la pantalla dispositivo movil.
     */
    public PantallaDispositivoMovil(String nombreUsuarioMozo) {
        this.nombreUsuarioMozo = nombreUsuarioMozo;
        this.infoPedidosListosAServir = new ArrayList<>();
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
        sonarAlarma();
    }

    /**
     * Método que implementa de la interface IObservadorPreparacionPedido, en donde la PantallaDispositivoMovil,
     * no tiene un piso, por lo que devolverá null.
     * @return null.
     */
    @Override
    public Integer getPiso() {
        return null;
    }

    /**
     * Método que implementa de la interface IObservadorPreparacionPedido, que devuelve el nombre del mozo.
     * @return nombre del usuario mozo.
     */
    @Override
    public String getNombreMozo() {
        return nombreUsuarioMozo;
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

    /**
     * Método que sobre escribe el atributo nuevoLoteIngresado por true, para poder crear la notificacion en la
     * pantalla.
     */
    public void sonarAlarma() {
        nuevoLoteIngresado = true;
    }

    /**
     * Método que devuelve el valor del booleano nuevoLoteIngresado.
     * @return valor de nuevoLoteIngresado.
     */
    public boolean isNuevoLoteIngresado() {
        return nuevoLoteIngresado;
    }

    /**
     * Método que sobre escribe el atributo nuevoLoteIngresado por false, para poder saber si ya se leyó
     * la notificación.
     */
    public void notificacionVista() {
        nuevoLoteIngresado = false;
    }
}
