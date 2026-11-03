package com.antoniosanz.tiendacafesytesmvc.test;

import javax.swing.*;

public class Gif {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                JFrame frame = new JFrame("Prueba de Animación GIF");
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.setSize(400, 400);
                frame.setLocationRelativeTo(null);

                ImageIcon icono = new ImageIcon(
                        "src/com/antoniosanz/tiendacafesytesmvc/res/animacionExpresso.gif");

                frame.add(new JLabel(icono, SwingConstants.CENTER)); // centramos el contenido dentro de la etiqueta
                frame.setVisible(true);
            }
        });
    }
}