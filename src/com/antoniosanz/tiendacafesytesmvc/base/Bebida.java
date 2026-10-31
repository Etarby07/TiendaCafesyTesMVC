package com.antoniosanz.tiendacafesytesmvc.base;

import java.time.LocalDate;

public abstract class Bebida {
    private String nombre;
    private String productor;
    private int altitud;
    private float notaCata;
    private LocalDate fecha;

    public Bebida() {

    }

    public Bebida(String nombre, String productor, int altitud, float notaCata, LocalDate fecha) {
        this.nombre = nombre;
        this.productor = productor;
        this.altitud = altitud;
        this.notaCata = notaCata;
        this.fecha = fecha;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getProductor() {
        return productor;
    }

    public void setProductor(String productor) {
        this.productor = productor;
    }

    public int getAltitud() {
        return altitud;
    }

    public void setAltitud(int altitud) {
        this.altitud = altitud;
    }

    public float getNotaCata() {
        return notaCata;
    }

    public void setNotaCata(float notaCata) {
        this.notaCata = notaCata;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }
}
