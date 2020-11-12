package com.ppai.restaurante.config;

import com.ppai.restaurante.model.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

@Component
@Configuration
public class RestauranteConfiguration {

    @Bean
    public GestorFinalizarPreparacionPedido gestorFinalizarPreparacionPedido() {

        PantallaInterfazMonitor monitorPiso1 = new PantallaInterfazMonitor(1);
        PantallaInterfazMonitor monitorPiso2 = new PantallaInterfazMonitor(2);

        PantallaDispositivoMovil pantallaDispositivoMovil1 = new PantallaDispositivoMovil("fegodoy");
        PantallaDispositivoMovil pantallaDispositivoMovil2 = new PantallaDispositivoMovil("maquiroga");

        List<IObservadorPreparacionPedido> observadores = Arrays.asList(monitorPiso1, monitorPiso2,
                pantallaDispositivoMovil1, pantallaDispositivoMovil2);

        DetalleDePedido detalleDePedido1 = new DetalleDePedido(1, "1,2", 2, 15.0, "Empanadas Criollas", "Menú Criollo");
        DetalleDePedido detalleDePedido2 = new DetalleDePedido(2, "1,2", 1, 12.0, "Tabla Fiambres", "Menú Criollo");
        DetalleDePedido detalleDePedido3 = new DetalleDePedido(3, "5", 2, 20.0, "Canelones Caseros", "Menú Caserito");
        DetalleDePedido detalleDePedido4 = new DetalleDePedido(4, "5", 1, 10.0, "Pan Casero", "Menú Caserito");
        DetalleDePedido detalleDePedido5 = new DetalleDePedido(5, "4", 1, 18.0, "Lomito Completo", "Menú Lomito");

        List<DetalleDePedido> detallesDePedidosEnPreparacion = new ArrayList(Arrays.asList(detalleDePedido1, detalleDePedido2, detalleDePedido3, detalleDePedido4, detalleDePedido5));
        detallesDePedidosEnPreparacion.sort(Comparator.comparingDouble(DetalleDePedido::getTiempoEspera).reversed());

        return new GestorFinalizarPreparacionPedido(observadores, detallesDePedidosEnPreparacion);
    }
}
