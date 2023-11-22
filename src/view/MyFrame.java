package view;

import java.awt.Color;
import java.awt.GridBagLayout;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JFrame;

public class MyFrame extends JFrame {
    /**
     * Constructor encargado de setear los estilos del frame principal.
     * 
     * @param width           El ancho de ventana del frame
     * @param height          El ancho de ventana del frame
     * @param backgroundColor Color de fondo del frame
     * 
     * @throws IOException En caso de que no se encuentre la imagen icono
     */
    public MyFrame(double width, double height, Color backgroundColor) throws IOException {
        super("Variedades R&S");
        this.setSize((int) width, (int) height);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(new GridBagLayout());
        this.setVisible(true);
        this.getContentPane().setBackground(new Color(32, 32, 32));
        try {
            this.setIconImage(ImageIO.read(new File("src/resources/favicon.png")));
        } catch (IOException e) {
            throw e;
        }

    }
}
