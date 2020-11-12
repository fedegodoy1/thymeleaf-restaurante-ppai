package com.ppai.restaurante.model;

import java.util.List;

public class InfoPedidosAServirRQ {
    private int id;
    private List<InfoPedidosAServir> infoPedidosAServirList;

    public InfoPedidosAServirRQ(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<InfoPedidosAServir> getInfoPedidosAServirList() {
        return infoPedidosAServirList;
    }

    public void setInfoPedidosAServirList(List<InfoPedidosAServir> infoPedidosAServirList) {
        this.infoPedidosAServirList = infoPedidosAServirList;
    }
}
