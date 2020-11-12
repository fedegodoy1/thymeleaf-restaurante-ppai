package com.ppai.restaurante.model;

import java.util.List;

public class PedidosAServir {
    private String id;
    private List<DetalleDePedido> detalleDePedidoList;

    public PedidosAServir(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public List<DetalleDePedido> getDetalleDePedidoList() {
        return detalleDePedidoList;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setDetalleDePedidoList(List<DetalleDePedido> detalleDePedidoList) {
        this.detalleDePedidoList = detalleDePedidoList;
    }
}
