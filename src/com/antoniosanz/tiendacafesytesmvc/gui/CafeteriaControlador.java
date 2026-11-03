package com.antoniosanz.tiendacafesytesmvc.gui;

import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.File;
import java.io.IOException;

public class CafeteriaControlador implements ActionListener, ListSelectionListener, WindowListener {
    private Ventana vista;
    private CafeteriaModelo modelo;
    private File ultimaRutaExportada;

    public CafeteriaControlador(Ventana vista, CafeteriaModelo modelo) {
        this.vista=vista;
        this.modelo=modelo;
        try {
            cargarDatosConfiguracion();
        } catch (IOException e) {
            System.out.println("No es encontro el archivo "+e.getMessage());
        }
        addActionListener(this);
        addListSelectionListener(this);
        addWindowListener(this);
    }

    private void cargarDatosConfiguracion() {
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }

    @Override
    public void windowOpened(WindowEvent e) {

    }

    @Override
    public void windowClosing(WindowEvent e) {

    }

    @Override
    public void windowClosed(WindowEvent e) {

    }

    @Override
    public void windowIconified(WindowEvent e) {

    }

    @Override
    public void windowDeiconified(WindowEvent e) {

    }

    @Override
    public void windowActivated(WindowEvent e) {

    }

    @Override
    public void windowDeactivated(WindowEvent e) {

    }

    @Override
    public void valueChanged(ListSelectionEvent e) {

    }
}