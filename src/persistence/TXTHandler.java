/** 
* Class TXTHandler
*Contiene los metodos para trabajar con archivos tipo TXT
*
*@author Steven Leon
*@author Ricardo Forero
* @version 1.0
*
*
*/

package persistence;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.TreeMap;

import model.Bill;
import model.Product;

public class TXTHandler {

    /**
     * Metodo que redacta el contenido en un archivo TXT
     * 
     * @param shoppingCart El carro de compras que se está facturando
     * 
     * @param bill Un objeto tipo Bill con información de la factura
     * 
     * @param totalPrice El valor total de los productos comprados
     * 
     * @return Un objeto de tipo File para ser consumido en el presentador
     * 
     * @throws IOException En caso de que el archivo no pueda ser encontrado
     */
    public File textWriter(TreeMap<Integer, Product> shoppingCart, Bill bill, String totalPrice) throws IOException {
        File fileTicket = null;
        FileWriter fwTicket = null;
        PrintWriter pwTicket = null;

        try {

            fileTicket = new File("./src/persistence/" + bill.nameBillHour() + ".txt");
            fwTicket = new FileWriter(fileTicket);
            pwTicket = new PrintWriter(fwTicket);

            pwTicket.println("__________________________________________________");
            pwTicket.println("Receipt ");
            pwTicket.println("id:" + bill.nameBillHour());
            pwTicket.println("Date:" + bill.getGeneratedAt());
            for (Product product : shoppingCart.values()) {
                pwTicket.println("Producto: " + product.getName());
                pwTicket.println("Cantidad: " + product.getAmount());
                pwTicket.println("Valor Unitario: " + product.getPrice());
                pwTicket.println("Valor Total: " + String.format("%.2f", (product.getPrice() * product.getAmount())));
                pwTicket.println("");
            }
            pwTicket.println("Total Compra: " + totalPrice);
            pwTicket.println("__________________________________________________");


        } catch (IOException e) {
            throw e;
        } finally {
            try {
                if (null != fwTicket) {
                    fwTicket.close();
                }
            } catch (IOException e) {
                throw e;
            }
        }
        return fileTicket;
    }

}
