package com.ppai.restaurante.model;

import java.util.ArrayList;
import java.util.List;

public class GestorFinalizarPreparacionPedido implements ISujetoPreparacionPedido {

    /**
     * Información que se entrega a los observadores.
     */
    private List<DetalleDePedido> detalleDePedidosSeleccionadosAServir = new ArrayList<>();

    /**
     * Detalle de pedidos en preparacion.
     */
    private List<DetalleDePedido> detallesDePedidoEnPreparacion;

    public GestorFinalizarPreparacionPedido(List<IObservadorPreparacionPedido> observadorPreparacionPedido,
                                            List<DetalleDePedido> detallesDePedidoEnPreparacion) {
        observadores.addAll(observadorPreparacionPedido);
        this.detallesDePedidoEnPreparacion = detallesDePedidoEnPreparacion;
    }

    /**
     * Inicializa todo el proceso
     * @param detalleDePedidosAServir
     */
    public void publicarDetPedidoAServir(List<DetalleDePedido> detalleDePedidosAServir) {
        detalleDePedidosSeleccionadosAServir.addAll(detalleDePedidosAServir);

        quitarPedidosEnPreparacion();

        notificar();
    }

    @Override
    public void notificar() {
        // Por cada detalle de pedido seleccionado a servir, se obtiene los datos
        // necesarios para entregar a los observadores.
        for (DetalleDePedido detalleDePedido : detalleDePedidosSeleccionadosAServir) {
            for (IObservadorPreparacionPedido observador : observadores) {
                observador.actualizar(detalleDePedido.getId(), detalleDePedido.getNombreProducto(),
                        detalleDePedido.getNumeroMesa(), detalleDePedido.getCantidad());
            }
        }
    }

    /**
     * Método que permite suscribir o agregar nuevos observadores.
     * @param nuevosObservadores observadores a agregar.
     */
    @Override
    public void suscribir(List<IObservadorPreparacionPedido> nuevosObservadores) {
        observadores.addAll(nuevosObservadores);
    }

    /**
     * Método que permite quitar o eliminar observadores.
     * @param observadoresABorrar observadores a borrar o quitar.
     */
    @Override
    public void quitar(List<IObservadorPreparacionPedido> observadoresABorrar) {
        observadores.removeAll(observadoresABorrar);
    }

    public PantallaDispositivoMovil obtenerInterfazDispositivoMovil(String nombreUsuarioMozo) {
        return (PantallaDispositivoMovil)observadores.stream().filter(observador -> nombreUsuarioMozo.equalsIgnoreCase(observador.getNombreMozo()))
                .findFirst()
                .get();
    }

    public IObservadorPreparacionPedido obtenerInterfazMonitor(Integer numeroPiso) {
        return  observadores.stream().filter(observador -> numeroPiso.equals(observador.getPiso()))
                .findFirst()
                .get();
    }

    public List<DetalleDePedido> obtenerDetallesDePedidoEnPreparacion() {
        return detallesDePedidoEnPreparacion;
    }

    public void quitarPedidosEnPreparacion() {
        List<DetalleDePedido> detallesAEliminar = new ArrayList<>();
        for(DetalleDePedido detalleSeleccionado : detalleDePedidosSeleccionadosAServir) {
            for (int i = 0; i < detallesDePedidoEnPreparacion.size() ; i++) {
                if(detalleSeleccionado.getId().equals(detallesDePedidoEnPreparacion.get(i).getId())) {
                    detallesAEliminar.add(detalleSeleccionado);
                }
            }
        }
        if (!detallesAEliminar.isEmpty()) {
            detallesDePedidoEnPreparacion.removeAll(detallesAEliminar);
        }
    }
}
