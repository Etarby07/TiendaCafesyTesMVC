package com.antoniosanz.tiendacafesytesmvc.base;

import java.time.LocalDate;

public class Cafe extends Bebida {
    private String origen;
    private String proceso;
    private String variedad;
    private boolean tipoPreparacion; // Expresso o de filtro
    private double peso;
    private String molienda;

    public Cafe() {
        super();
    }

    public Cafe(String nombre, String productor, int altitud, float notaCata, LocalDate fecha, String origen, String proceso, String variedad, boolean tipoPreparacion, double peso, String molienda) {
        super(nombre, productor, altitud, notaCata, fecha);
        this.origen = origen;
        this.proceso = proceso;
        this.variedad = variedad;
        this.tipoPreparacion = tipoPreparacion;
        this.peso = peso;
        this.molienda = molienda;
    }

    public String getOrigen() {
        return origen;
    }

    public void setOrigen(String origen) {
        this.origen = origen;
    }

    public String getProceso() {
        return proceso;
    }

    public void setProceso(String proceso) {
        this.proceso = proceso;
    }

    public String getVariedad() {
        return variedad;
    }

    public void setVariedad(String variedad) {
        this.variedad = variedad;
    }

    public boolean isTipoPreparacion() {
        return tipoPreparacion;
    }

    public void setTipoPreparacion(boolean tipoPreparacion) {
        this.tipoPreparacion = tipoPreparacion;
    }

    public double getPeso() {
        return peso;
    }

    public void setPeso(double peso) {
        this.peso = peso;
    }

    public String getMolienda() {
        return molienda;
    }

    public void setMolienda(String molienda) {
        this.molienda = molienda;
    }

    @Override
    public String toString() {
        return "Cafe{" + getNombre() + " (" + origen + " - " + molienda + ")";
    }
}
