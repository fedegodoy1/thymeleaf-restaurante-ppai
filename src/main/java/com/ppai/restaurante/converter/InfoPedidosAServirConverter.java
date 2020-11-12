package com.ppai.restaurante.converter;

import com.ppai.restaurante.model.GestorFinalizarPreparacionPedido;
import com.ppai.restaurante.model.InfoPedidosAServir;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;

public class InfoPedidosAServirConverter implements Converter<String, InfoPedidosAServir> {

    @Autowired
    private GestorFinalizarPreparacionPedido gestorFinalizarPreparacionPedido;

    @Override
    public InfoPedidosAServir convert(String numeroMesa) {


        return null;
    }
}
