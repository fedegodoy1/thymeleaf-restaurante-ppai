package com.ppai.restaurante.controller;

import com.ppai.restaurante.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/restaurante")
public class RestauranteController {

    @Autowired
    private GestorFinalizarPreparacionPedido gestorFinalizarPreparacionPedido;

    @GetMapping(value = "/")
    public String index(Model model) {
        return "index";
    }


    @GetMapping("/notificaciones/pantallaMonitor/piso/{piso}")
    public String pantallaMonitor(Model model,
                                  @PathVariable int piso) {
        List<InfoPedidosAServir> datosInterfazMonitor =
                gestorFinalizarPreparacionPedido.obtenerDatosInterfazMonitor(piso);

        model.addAttribute("listaInfoPedidosAServir", datosInterfazMonitor);
        model.addAttribute("piso", String.format("Piso: %s",piso));

        return "pantallaNotificacionInterfazMonitor";
    }

    @GetMapping("/notificaciones/pantallaDispositivoMovil/nombreMozo/{nombreMozo}")
    public String pantallaDispositivoMovil(Model model,
                               @PathVariable String nombreMozo) {

        DatosInterfazDispositivoMovil datosInterfazDispositivoMovil =
                gestorFinalizarPreparacionPedido.obtenerDatosInterfazDispositivoMovil(nombreMozo);

        model.addAttribute("listaInfoPedidosAServir",datosInterfazDispositivoMovil.getInfoPedidosAServir());
        model.addAttribute("nombreMozo", String.format("Mozo: %s",nombreMozo));
        model.addAttribute("notificacion", datosInterfazDispositivoMovil.isNuevoLoteIngresado());

        gestorFinalizarPreparacionPedido.informarNotificacionVista(nombreMozo);

        return "pantallaNotificacionDispositivoMovil";
    }

    @GetMapping("/finalizarPreparacionPedido")
    public String getPedidosEnPreparacion(Model model) {
        PedidosAServir pedidosAServir = new PedidosAServir("1");

        model.addAttribute("pedidosAServir", pedidosAServir);
        model.addAttribute("listaPedidosAServir", gestorFinalizarPreparacionPedido.obtenerDetallesDePedidoEnPreparacion());

        return "pedidosEnPreparacion";
    }

    @PostMapping("/finalizarPreparacionPedido")
    public String finalizarPreparacionPedido(@ModelAttribute PedidosAServir pedidosAServir) {
        gestorFinalizarPreparacionPedido.publicarDetPedidoAServir(pedidosAServir.getDetalleDePedidoList());
        return "redirect:/";
    }
}
