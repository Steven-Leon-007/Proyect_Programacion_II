package view;

import java.awt.Color;
import java.awt.LayoutManager;

import javax.swing.BorderFactory;
import javax.swing.JPanel;

public class MyPanel extends JPanel {
    /**
     * Constructor encargado de setear los estilos de los paneles.
     * 
     * @param layout          El tipo de layout a usar
     * @param width           el ancho del panel
     * @param height          el alto del panel
     * @param backgroundColor Color del fondo del panel
     * @param foregroundColor Color del texto del panel
     * @param isBordered      Booleano que valida si el frame debe o no tener borde
     * @param shouldAlignY      Booleano que valida si el frame debe o no tener margenes mas amplios verticalmente
     * 
     */
    public MyPanel(LayoutManager layout, double width, double height, Color backgroundColor, boolean isBordered,
            boolean shouldAlignY) {
        this.setLayout(layout);
        this.setSize((int) width, (int) height);
        this.setBackground(backgroundColor);
        if (isBordered) {
            this.setBorder(BorderFactory.createEmptyBorder(0, 30, 0, 30));
        }
        if (shouldAlignY) {
            this.setBorder(BorderFactory.createEmptyBorder(60, 30, 60, 30));

        }
    }
}
