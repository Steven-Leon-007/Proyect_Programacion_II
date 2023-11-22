package view;

import java.awt.Color;

import javax.swing.BorderFactory;
import javax.swing.JComponent;
import javax.swing.JScrollPane;

public class MyScrollPane extends JScrollPane {
    /**
     * Constructor encargado de setear los estilos de los paneles de scroll.
     * 
     * 
     * @param panel           El panel a añadir dentro del scrollPane
     * @param width           Tamaño del ancho del scrollPane
     * @param height           Tamaño del alto del scrollPane
     * @param backgroundColor Color de fondo del scrollPane
     * 
     */
    public MyScrollPane(JComponent panel, int width, int height, Color backgroundColor) {
        super(panel);
        this.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        this.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        this.setBounds(0, 0, width, height);
        this.setBackground(backgroundColor);
        this.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0));
        this.getVerticalScrollBar().setUnitIncrement(8);

    }
}
