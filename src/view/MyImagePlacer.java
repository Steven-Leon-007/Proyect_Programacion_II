package view;


import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class MyImagePlacer extends JLabel {
    /**
     * Constructor encargado de setear los estilos de las etiquetas que contienen
     * imagenes.
     * 
     * @param URL    La dirección de la imagen de la etiqueta;
     * @param width  El ancho de ventana de la etiqueta
     * @param height El ancho de ventana de la etiqueta
     * 
     */
    public MyImagePlacer(String URL, int width, int height) {
        super(new ImageIcon(URL));
        this.setSize(width, height);
    }
}
