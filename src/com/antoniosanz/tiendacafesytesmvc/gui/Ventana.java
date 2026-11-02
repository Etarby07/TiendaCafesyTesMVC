package com.antoniosanz.tiendacafesytesmvc.gui;

import com.antoniosanz.tiendacafesytesmvc.base.Bebida;
import com.github.lgooddatepicker.components.DatePicker;

import javax.swing.*;

public class Ventana {
    public JPanel panel1;
    public JRadioButton cafeRadioButtonRadioButton;
    public JRadioButton teRadioButtonRadioButton;
    public JTextField productorTxt;
    public JSpinner altitudSpinner;
    public JSpinner notaCata;
    public JRadioButton expressoRadioButton;
    public JRadioButton filtroRadioButton;
    public JComboBox moliendasComboBox;
    public JComboBox origenesComboBox;
    public JButton NuevoButton;
    public JButton exportarButton;
    public JButton importarButton;
    public JButton cerrarButton;
    public JList listaBebidas;
    public JTextField nombreTxt;
    public DatePicker fechaDatePicker;

    public JFrame frame;
    public DefaultListModel<Bebida> dlmBebidas;
    public JPanel panelGifOverlay;

    public Ventana() {
        frame = new JFrame("Ventana");
        frame.setContentPane(panel1);
        frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.pack();
        initComponents();
        prepararGifOverlay();
        frame.setVisible(true);
    }

    private void prepararGifOverlay() {
    }

    private void initComponents() {
        dlmBebidas = new DefaultListModel<Bebida>();
        listaBebidas.setModel(dlmBebidas);

        // los metemos en un grupo para que solo se pueda elegir uno

        // Grupo tipo bebida
        ButtonGroup grupoTipo = new ButtonGroup();
        grupoTipo.add(cafeRadioButtonRadioButton);
        grupoTipo.add(teRadioButtonRadioButton);
        cafeRadioButtonRadioButton.setSelected(true); // para marcar tipoBebida de cafe por defecto, igual que el "checked" en html

        // Grupo tipo Preparación
        ButtonGroup grupoPrep = new ButtonGroup();
        grupoPrep.add(expressoRadioButton);
        grupoPrep.add(filtroRadioButton);
        expressoRadioButton.setSelected(true); // para marcar tipoPrep de expresso por defecto, igual que el "checked" en html
    }
}
