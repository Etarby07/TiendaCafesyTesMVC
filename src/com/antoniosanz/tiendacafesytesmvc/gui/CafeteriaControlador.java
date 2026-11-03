package com.antoniosanz.tiendacafesytesmvc.gui;

import com.antoniosanz.tiendacafesytesmvc.base.Bebida;
import com.antoniosanz.tiendacafesytesmvc.base.Cafe;
import com.antoniosanz.tiendacafesytesmvc.base.Te;
import com.antoniosanz.tiendacafesytesmvc.util.Util;
import org.xml.sax.SAXException;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
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
        vista.cerrarButton.addActionListener(listener);
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
        configuracion.store(new PrintWriter("bebidas.conf"),"Datos configuracion bebidas");
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        String actionCommand = e.getActionCommand();

        switch (actionCommand) {
            case "Nuevo":
                if (hayCamposVacios()) {
                    Util.mensajeError("Los siguientes campos no pueden " +
                            "estar vacios: \nNombre\nProductor" +
                            "\nFecha de tueste");
                    break;
                }
                if (modelo.existeNombre(vista.nombreTxt.getText())) {
                    Util.mensajeError("Ya existe una bebida con ese nombre");
                    break;
                }
                if (vista.cafeRadioButtonRadioButton.isSelected()) {
                    modelo.altaCafe(vista.nombreTxt.getText(),vista.productorTxt.getText(),
                            (int) vista.altitudSpinner.getValue(),Float.parseFloat(vista.notaCata.getValue().toString()),
                            vista.fechaDatePicker.getDate(),vista.origenesComboBox.getSelectedItem().toString(),"Lavado","Arábica",
                            vista.expressoRadioButton.isSelected(), 250.0, vista.moliendasComboBox.getSelectedItem().toString());
                } else {
                    modelo.altaTe(vista.nombreTxt.getText(),vista.productorTxt.getText(),
                            (int) vista.altitudSpinner.getValue(), Float.parseFloat(vista.notaCata.getValue().toString()),vista.fechaDatePicker.getDate(), "Te verde", 3);
                }
                limpiarCampos();
                refrescar();
                break;
            case "Importar":
                JFileChooser selectorFichero = Util.crearSelectorFichero(ultimaRutaExportada,
                        "Archivos XML","xml");
                int opt=selectorFichero.showOpenDialog(null);
                if (opt==JFileChooser.APPROVE_OPTION) {
                    try {
                        modelo.importarXML(selectorFichero.getSelectedFile());
                    } catch (ParserConfigurationException ex) {
                        ex.printStackTrace();
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    } catch (SAXException ex) {
                        ex.printStackTrace();
                    }
                    refrescar();
                }
                break;
            case "Exportar":
                JFileChooser selectorFichero2 = Util.crearSelectorFichero(ultimaRutaExportada,
                        "Archivos XML","xml");
                int opt2=selectorFichero2.showSaveDialog(null);
                if (opt2==JFileChooser.APPROVE_OPTION) {
                    try {
                        modelo.exportarXML(selectorFichero2.getSelectedFile());
                        actualizarDatosConfiguracion(selectorFichero2.getSelectedFile());
                    } catch (ParserConfigurationException ex) {
                        ex.printStackTrace();
                    } catch (TransformerException ex) {
                        ex.printStackTrace();
                    }
                }
                break;
            case "Cerrar":
                cerrarAplicacion();
                break;
            case "Cafe":
                vista.moliendasComboBox.setEnabled(true);
                vista.origenesComboBox.setEnabled(true);
                vista.expressoRadioButton.setEnabled(true);
                vista.filtroRadioButton.setEnabled(true);
                break;
            case "Te":
                vista.moliendasComboBox.setEnabled(false);
                vista.origenesComboBox.setEnabled(false);
                vista.expressoRadioButton.setEnabled(false);
                vista.filtroRadioButton.setEnabled(false);
                break;
        }

    }

    private void cerrarAplicacion() {
        int resp= Util.mensajeConfirmacion("Deseas cerrar la ventana","Salir");
        if (resp==JOptionPane.OK_OPTION) {
            try {
                guardarDatosConfiguracion();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
            System.exit(0);
        }
    }

    @Override
    public void windowOpened(WindowEvent e) {

    }

    @Override
    public void windowClosing(WindowEvent e) {
        cerrarAplicacion();
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
        if (!e.getValueIsAdjusting()) {
            Bebida bebidaSeleccionada = (Bebida) vista.listaBebidas.getSelectedValue();
            if (bebidaSeleccionada != null) {
                vista.nombreTxt.setText(bebidaSeleccionada.getNombre());
                vista.productorTxt.setText(bebidaSeleccionada.getProductor());
                vista.altitudSpinner.setValue(bebidaSeleccionada.getAltitud());
                vista.notaCata.setValue(bebidaSeleccionada.getNotaCata());
                vista.fechaDatePicker.setDate(bebidaSeleccionada.getFecha());

                if (bebidaSeleccionada instanceof Cafe) {
                    vista.cafeRadioButtonRadioButton.doClick();
                    Cafe cafe = (Cafe) bebidaSeleccionada;
                    vista.origenesComboBox.setSelectedItem(cafe.getOrigen());
                    vista.moliendasComboBox.setSelectedItem(cafe.getMolienda());
                    if (cafe.isTipoPreparacion()) {
                        vista.expressoRadioButton.setSelected(true);
                    } else {
                        vista.filtroRadioButton.setSelected(true);
                    }
                } else if (bebidaSeleccionada instanceof Te) {
                    vista.teRadioButtonRadioButton.doClick();
                }
            }
        }
    }
}