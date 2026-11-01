package com.antoniosanz.tiendacafesytesmvc.base;

import java.time.LocalDate;

public class Te extends Bebida{
    private String tipo;
    private int tiempoInfusion;

    public Te() {
        super();
    }

    public Te(String nombre, String productor, int altitud, float notaCata, LocalDate fecha, String tipo, int tiempoInfusion) {
        super(nombre, productor, altitud, notaCata, fecha);
        this.tipo = tipo;
        this.tiempoInfusion = tiempoInfusion;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public int getTiempoInfusion() {
        return tiempoInfusion;
    }

    public void setTiempoInfusion(int tiempoInfusion) {
        this.tiempoInfusion = tiempoInfusion;
    }

    @Override
    public String toString() {
        return "Té: " + getNombre() + " (" + tipo + " - " + tiempoInfusion + " min)";
    }
}
