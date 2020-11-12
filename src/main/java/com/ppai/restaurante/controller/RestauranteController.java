package com.ppai.restaurante.controller;

import com.ppai.restaurante.model.GestorFinalizarPreparacionPedido;
import com.ppai.restaurante.model.IObservadorPreparacionPedido;
import com.ppai.restaurante.model.PantallaDispositivoMovil;
import com.ppai.restaurante.model.PedidosAServir;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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
        IObservadorPreparacionPedido observadorDispositivoMovil =
                gestorFinalizarPreparacionPedido.obtenerInterfazMonitor(piso);

        model.addAttribute("listaInfoPedidosAServir",observadorDispositivoMovil.obtenerInfoPedidosListosAServir());
        model.addAttribute("piso", String.format("Piso: %s",piso));

        return "pantallaNotificacionInterfazMonitor";
    }

    @GetMapping("/notificaciones/pantallaDispositivoMovil/nombreMozo/{nombreMozo}")
    public String pantallaDispositivoMovil(Model model,
                               @PathVariable String nombreMozo) {

        PantallaDispositivoMovil observadorDispositivoMovil =
                gestorFinalizarPreparacionPedido.obtenerInterfazDispositivoMovil(nombreMozo);

        model.addAttribute("listaInfoPedidosAServir",observadorDispositivoMovil.obtenerInfoPedidosListosAServir());
        model.addAttribute("nombreMozo", String.format("Mozo: %s",nombreMozo));
        model.addAttribute("notificacion", observadorDispositivoMovil.isNuevoLoteIngresado());

        observadorDispositivoMovil.notificacionVista();

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
