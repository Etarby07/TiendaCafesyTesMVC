package com.antoniosanz.tiendacafesytesmvc.gui;

import com.antoniosanz.tiendacafesytesmvc.base.Bebida;

import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Properties;

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

    private boolean hayCamposVacios() {
        if (vista.nombreTxt.getText().isEmpty() ||
                vista.productorTxt.getText().isEmpty() ||
                vista.fechaDatePicker.getText().isEmpty()) {
            return true;
        }
        return false;
    }

    private void limpiarCampos() {
        vista.nombreTxt.setText(null);
        vista.productorTxt.setText(null);
        vista.altitudSpinner.setValue(0);
        vista.notaCata.setValue(0);
        vista.fechaDatePicker.setText(null);
        vista.expressoRadioButton.setSelected(true); // que salga marcado por defecto, como el "checked" de html que dabamos en lenguaje de
                                                     // marcas con el <input type>
        vista.nombreTxt.requestFocus();
    }

    public void refrescar() {
        vista.dlmBebidas.clear();
        for (Bebida unaBebida:modelo.obtenerBebidas()) {
            vista.dlmBebidas.addElement(unaBebida);
        }
    }

    private void addActionListener(ActionListener listener) {
        vista.nuevoButton.addActionListener(listener);
        vista.importarButton.addActionListener(listener);
        vista.exportarButton.addActionListener(listener);
        vista.cafeRadioButtonRadioButton.addActionListener(listener);
        vista.teRadioButtonRadioButton.addActionListener(listener);
    }
    private void addListSelectionListener(ListSelectionListener listener) {
        vista.listaBebidas.addListSelectionListener(listener);
    }

    private void addWindowListener(WindowListener listener) {
        vista.frame.addWindowListener(listener);
    }


    private void cargarDatosConfiguracion() throws IOException {
        Properties configuracion = new Properties();
        configuracion.load(new FileReader("bebidas.conf"));
        ultimaRutaExportada=new File(configuracion.getProperty("ultimaRutaExportada"));
    }

    private void actualizarDatosConfiguracion(File ultimaRutaExportada) {
        this.ultimaRutaExportada=ultimaRutaExportada;
    }

    private void guardarDatosConfiguracion() throws IOException {
        Properties configuracion = new Properties();
        configuracion.setProperty("ultimaRutaExportada",ultimaRutaExportada.getAbsolutePath());
        configuracion.store(new PrintWriter("bebidas.conf"),"Datos configuracion vehiculos");
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