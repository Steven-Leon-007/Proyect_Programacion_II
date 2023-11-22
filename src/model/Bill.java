/** 
* Class Bill
*Contiene informacion acerca de el recibo 
*
*@author Steven Leon
*@author Ricardo Forero
*@version 1.0
*
*/

package model;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.TreeMap;

public class Bill {
    /**
     * id del recibo
     */
    private int id;
    /**
     * fecha y hora de creacion del recibo
     */
    private LocalDate generatedAt;
    /**
     * Lista de los productos en el inventario
     */
    private TreeMap<Integer, Product> products;

/**
 *  constructor vacio para instancia
 */
    public Bill() {
    }
/**
 * constructor con 2 parametros
 * @param id id de el recibo
 * @param products lista de productos en el inventario
 */
    public Bill(int id, TreeMap<Integer, Product> products) {
        this.id = id;
        this.generatedAt = LocalDate.now();
        this.products = products;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public LocalDate getGeneratedAt() {
        return generatedAt;
    }

    public void setGeneratedAt(LocalDate generatedAt) {
        this.generatedAt = generatedAt;
    }

    public TreeMap<Integer, Product> getProducts() {
        return products;
    }

    public void setProducts(TreeMap<Integer, Product> products) {
        this.products = products;
    }
/**
 * @return el nombre del recibo 
 */
    public String nameBillHour() {
        return "receipt_" + LocalDateTime.now().getHour() + "_" + LocalDateTime.now().getMinute() + "_"
                + LocalDateTime.now().getSecond();
    }

}
