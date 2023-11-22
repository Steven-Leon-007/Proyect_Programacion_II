package view;

import java.awt.Color;
import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.JTextField;
import javax.swing.border.Border;

public class MyTextField extends JTextField {
    /**
     * Constructor encargado de setear los estilos de los campos de texto.
     * 
     * 
     * @param borderColor     Color del borde de los campos de texto
     * @param backgroundColor Color de fondo de los campos de texto
     * @param foregroundColor Color del texto de los campos de texto
     * @param shouldSpace     Booleano que indica si debe tener un borde titulado
     * @param text            texto dentro del campo de texto
     * 
     */
    public MyTextField(Color borderColor, Color backgroundColor, Color foregroundColor, boolean shouldSpace,
            String text) {
        super();
        Font font = new Font("Arial", Font.ITALIC, 12);
        this.setBackground(backgroundColor);
        this.setForeground(foregroundColor);
        Border border = BorderFactory.createLineBorder(borderColor);
        if (shouldSpace) {
            this.setBorder(BorderFactory.createTitledBorder(border, text, 0, 0, font, foregroundColor));
        } else {

            this.setBorder(border);
        }

    }
}
