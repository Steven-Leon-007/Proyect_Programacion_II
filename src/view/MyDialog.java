package view;

import javax.swing.JDialog;
import javax.swing.JFrame;

public class MyDialog extends JDialog {
    /**
     * Constructor encargado de setear los estilos de los cuadros de dialogo.
     * 
     * @param frame      El frame asociado al cuadros de dialogo
     * @param title      Titulo del cuadro de dialogo
     * @param isBordered Booleano que define si es modal o no
     * 
     */
    public MyDialog(JFrame frame, String title, boolean modal) {
        super(frame, title, modal);
    }
}
