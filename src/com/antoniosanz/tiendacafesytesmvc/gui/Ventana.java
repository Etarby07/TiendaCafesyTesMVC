package com.antoniosanz.tiendacafesytesmvc.gui;

import com.antoniosanz.tiendacafesytesmvc.base.Bebida;

import javax.swing.*;

public class Ventana {
    private JPanel panel1;
    private JRadioButton cafeRadioButtonRadioButton;
    private JRadioButton teRadioButtonRadioButton;
    private JTextField productorTxt;
    private JSpinner altitudSpinner;
    private JSpinner notaCata;
    private JRadioButton expressoRadioButton;
    private JRadioButton filtroRadioButton;
    private JComboBox moliendasComboBox;
    private JComboBox origenesComboBox;

    public JFrame frame;
    public DefaultListModel<Bebida> dlmBebidas;
    private JPanel panelGifOverlay;

    public Ventana() {
        JFrame frame = new JFrame("Ventana");
        frame.setContentPane(new Ventana().panel1);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        initComponents();
        prepararGifOverlay();
        frame.setVisible(true);
    }

    private void prepararGifOverlay() {
    }

    private void initComponents() {
    }
}
