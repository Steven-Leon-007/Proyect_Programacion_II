package view;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.SwingConstants;

public class MyLabel extends JLabel {
    /**
     * Constructor encargado de setear los estilos de las etiquetas.
     * 
     * @param content         El contenido de la etiqueta
     * @param foregroundColor El color del texto de la etiqueta
     * @param isCentered      Booleano para determinar si debe ir centrado el texto
     *                        o no
     * 
     */
    public MyLabel(String content, Color foregroundColor, boolean isCentered) {
        super(content);
        this.setForeground(foregroundColor);
        if (isCentered) {
            this.setHorizontalAlignment(SwingConstants.CENTER);
            this.setFont(new Font("Arial", Font.BOLD, 15));
        }
    }
}
