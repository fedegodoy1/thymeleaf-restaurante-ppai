package com.ppai.restaurante.model;

import java.util.ArrayList;
import java.util.List;

public class PantallaDispositivoMovil implements IObservadorPreparacionPedido {

    private List<InfoPedidosAServir> infoPedidosListosAServir;

    private String nombreUsuarioMozo;

    private boolean nuevoLoteIngresado = false;

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

    @Override
    public Integer getPiso() {
        return null;
    }

    @Override
    public String getNombreMozo() {
        return nombreUsuarioMozo;
    }

    @Override
    public List<InfoPedidosAServir> obtenerInfoPedidosListosAServir() {
        return infoPedidosListosAServir;
    }

    public boolean chequeoDeDuplicados(InfoPedidosAServir info) {
        for(InfoPedidosAServir infoPedidosAServir : infoPedidosListosAServir) {
            if(infoPedidosAServir.getId()==info.getId()) {
                return true;
            }
        }
        return false;
    }

    public void sonarAlarma() {
        nuevoLoteIngresado = true;
    }

    public boolean isNuevoLoteIngresado() {
        return nuevoLoteIngresado;
    }

    public void notificacionVista() {
        nuevoLoteIngresado = false;
    }
}
