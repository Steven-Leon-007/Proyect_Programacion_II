package view;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;

public class MyButton extends JButton {

    /**
     * Constructor encargado de setear los estilos de los botones.
     * 
     * @param text            Texto del boton
     * @param URL             URL de la imagen del boton (Para productos)
     * @param backgroundColor Color de fondo del boton
     * @param foregroundColor Color del texto del boton
     * @param isBordered      Booleano que valida si el boton debe o no tener borde
     * 
     */

    public MyButton(String text, String URL, Color backgroundColor, Color foregroundColor, boolean isBordered) {
        super(text, new ImageIcon(URL));
        this.setBackground(backgroundColor);
        this.setForeground(foregroundColor);
        if (isBordered) {
            this.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        } else {
            this.setBorder(BorderFactory.createEmptyBorder());
        }
        this.setFocusPainted(false);
        Cursor cursor = new Cursor(12);
        this.setCursor(cursor);
        this.setFont(new Font("Arial", Font.BOLD, 15));

    }
}
