package com.ppai.restaurante.model;

import java.util.ArrayList;
import java.util.List;

public interface ISujetoPreparacionPedido {
    // Lista de observadores
    List<IObservadorPreparacionPedido> observadores = new ArrayList<>();

    void notificar();
    void quitar(List<IObservadorPreparacionPedido> observadores);
    void suscribir(List<IObservadorPreparacionPedido> observadores);
}
