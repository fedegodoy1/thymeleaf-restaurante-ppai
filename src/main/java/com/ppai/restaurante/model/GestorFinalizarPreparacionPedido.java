package com.ppai.restaurante.model;

import java.util.ArrayList;
import java.util.List;

public class GestorFinalizarPreparacionPedido implements ISujetoPreparacionPedido {

    /**
     * Información que se entrega a los observadores.
     */
    private final List<DetalleDePedido> detalleDePedidosSeleccionadosAServir = new ArrayList<>();

    /**
     * Detalle de pedidos en preparacion.
     */
    private final List<DetalleDePedido> detallesDePedidoEnPreparacion;

    /**
     * Constructor del GestorFinalizarPreparacionPedido.
     * @param observadorPreparacionPedido observadores que se encuentran suscriptos a las nuevas novedades.
     * @param detallesDePedidoEnPreparacion detalles de pedido que se encuentran en preparación.
     */
    public GestorFinalizarPreparacionPedido(List<IObservadorPreparacionPedido> observadorPreparacionPedido,
                                            List<DetalleDePedido> detallesDePedidoEnPreparacion) {
        observadores.addAll(observadorPreparacionPedido);
        this.detallesDePedidoEnPreparacion = detallesDePedidoEnPreparacion;
    }

    /**
     * Método que inicializa el proceso de notificacion a los observadores. En donde tambien llama al metodo
     * quitarPedidosEnPreparacion(), que se va a encargar de eliminar de la lista de pedidos en preparacion, aquellos
     * que fueron seleccionados mediante la interfaz.
     * @param detalleDePedidosAServir lista que representa los detalle de pedidos seleccionados.
     */
    public void publicarDetPedidoAServir(List<DetalleDePedido> detalleDePedidosAServir) {
        detalleDePedidosSeleccionadosAServir.addAll(detalleDePedidosAServir);

        quitarPedidosEnPreparacion();

        notificar();
    }

    /**
     * Método que implementa de la interface ISujetoPreparacionPedido.
     */
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

    /**
     * Método que obtiene los datos de la PantallaDispositivoMovil.
     * @param nombreUsuarioMozo nombre del mozo que se encuentra asignado en el observador.
     * @return DatosInterfazDispositivoMovil que representa los datos necesarios para llenar
     * en la interfaz pantallaNotificacionDispositivoMovil.html.
     * Evitando una dependencia entre el controlador y el observador.
     */
    public DatosInterfazDispositivoMovil obtenerDatosInterfazDispositivoMovil(String nombreUsuarioMozo) {
        PantallaDispositivoMovil dispositivoMovil = (PantallaDispositivoMovil)obtenerInterfazDispositivoMovil(nombreUsuarioMozo);
        DatosInterfazDispositivoMovil interfazDispositivoMovil = new DatosInterfazDispositivoMovil
                (dispositivoMovil.obtenerInfoPedidosListosAServir(), dispositivoMovil.isNuevoLoteIngresado());
        return interfazDispositivoMovil;
    }

    /**
     * Método que obtiene los datos de la PantallaInterfazMonitor.
     * @param numeroPiso numero de piso que se encuentra asignado en el observador.
     * @return List<InfoPedidosAServir> ue representa los datos necesarios para llenar
     * en la interfaz pantallaNotificacionInterfazMonitor.html.
     * Evitando una dependencia entre el controlador y el observador.
     */
    public List<InfoPedidosAServir> obtenerDatosInterfazMonitor(Integer numeroPiso) {
        PantallaInterfazMonitor interfazMonitor = (PantallaInterfazMonitor)obtenerInterfazMonitor(numeroPiso);
        return interfazMonitor.obtenerInfoPedidosListosAServir();
    }

    /**
     * Método que informa a la PantallaDispositivoMovil que la notificación fue vista en función al nombre del mozo.
     * @param nombreUsuarioMozo nombre del mozo que se encuentra asignado en el observador.
     */
    public void informarNotificacionVista(String nombreUsuarioMozo) {
        PantallaDispositivoMovil dispositivoMovil = (PantallaDispositivoMovil)obtenerInterfazDispositivoMovil(nombreUsuarioMozo);
        dispositivoMovil.notificacionVista();
    }

    /**
     * Método que en funcion del nombre del mozo, devuelve el observador correspondiente.
     * @param nombreUsuarioMozo nombre del mozo que se encuentra asignado en el observador.
     * @return PantallaDispositivoMovil asociado al nombre del mozo.
     */
    public IObservadorPreparacionPedido obtenerInterfazDispositivoMovil(String nombreUsuarioMozo) {
        return observadores.stream()
                .filter(observador -> nombreUsuarioMozo.equalsIgnoreCase(observador.getNombreMozo()))
                .findFirst()
                .get();
    }

    /**
     * Método que en funcion del numero de piso, devuelve el observador correspondiente.
     * @param numeroPiso numero de piso que se encuentra asignado en el observador.
     * @return IObservadorPreparacionPedido asociado al numero de piso.
     */
    public IObservadorPreparacionPedido obtenerInterfazMonitor(Integer numeroPiso) {
        return  observadores.stream()
                .filter(observador -> numeroPiso.equals(observador.getPiso()))
                .findFirst()
                .get();
    }

    public List<DetalleDePedido> obtenerDetallesDePedidoEnPreparacion() {
        return detallesDePedidoEnPreparacion;
    }

    /**
     * Método que elimina los pedidos en preparacion en función a los pedidos seleccionados
     * desde la interfaz pedidosEnPreparacion.html.
     */
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
