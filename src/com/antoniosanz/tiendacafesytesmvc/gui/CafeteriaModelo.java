package com.antoniosanz.tiendacafesytesmvc.gui;

import com.antoniosanz.tiendacafesytesmvc.base.Bebida;

import java.util.ArrayList;

public class CafeteriaModelo {
    private ArrayList<Bebida> listaBebidas;

    public CafeteriaModelo() {
        listaBebidas = new ArrayList<>();
    }

    public ArrayList<Bebida> obtenerBebidas() {
        return listaBebidas;
    }

    public void altaCafe() {
        // mañana lo hago
    }
    public void altaTe() {
        // mañana lo hago
    }

    public boolean existeNombre(String nombre) {
        for (Bebida b : listaBebidas) {
            if (b.getNombre().equalsIgnoreCase(nombre)) {
                return true;
            }
        }
        return false;
    }

    public void exportarXML() {
        // lo hacemos mañana
    }

    public void importarXML() {
        // lo hacemos mañana tmb
    }

    // faltan métodos que sí están en VehiculosMVC
}
