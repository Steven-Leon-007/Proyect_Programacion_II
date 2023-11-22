package view;

import java.awt.Color;

import javax.swing.BorderFactory;
import javax.swing.JPasswordField;
import javax.swing.border.Border;

public class MyPasswordField extends JPasswordField {
    /**
     * Constructor encargado de setear los estilos del campo de texto de la contraseña.
     * 
     * 
     * @param borderColor     Color del borde del espacio de texto de la contraseña
     * @param backgroundColor Color de fondo del espacio de texto de la contraseña
     * @param foregroundColor Color del texto del espacio de texto de la contraseña
     * 
     */
    public MyPasswordField(Color borderColor, Color backgroundColor, Color foregroundColor) {
        super();
        Border border = BorderFactory.createLineBorder(borderColor);
        this.setBorder(border);
        this.setBackground(backgroundColor);
        this.setForeground(foregroundColor);

    }

}
