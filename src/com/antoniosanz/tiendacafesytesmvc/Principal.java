package com.antoniosanz.tiendacafesytesmvc;

import com.antoniosanz.tiendacafesytesmvc.gui.CafeteriaControlador;
import com.antoniosanz.tiendacafesytesmvc.gui.CafeteriaModelo;
import com.antoniosanz.tiendacafesytesmvc.gui.Ventana;

public class Principal {
    public static void main(String[] args) {
        Ventana vista = new Ventana();
        CafeteriaModelo modelo = new CafeteriaModelo();
        CafeteriaControlador controlador = new CafeteriaControlador();

    }
}
