package com.ppai.restaurante.converter;


import com.ppai.restaurante.model.DetalleDePedido;
import com.ppai.restaurante.model.GestorFinalizarPreparacionPedido;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class DetalleDePedidoConverter implements Converter<String, DetalleDePedido> {

    @Autowired
    private GestorFinalizarPreparacionPedido gestorFinalizarPreparacionPedido;

    @Override
    public DetalleDePedido convert(String id) {
        List<DetalleDePedido> detalleDePedidos = gestorFinalizarPreparacionPedido.obtenerDetallesDePedidoEnPreparacion();

        int parsedId = Integer.parseInt(id);

        return detalleDePedidos.stream()
                .filter(detalleDePedido -> parsedId == detalleDePedido.getId())
                .findFirst()
                .get();
    }
}
