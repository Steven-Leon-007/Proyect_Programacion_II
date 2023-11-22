/** 
* Class Employee
*contiene informacion acerca de los empleados
*
*@author Steven Leon
*@author Ricardo Forero
*@version 1.0
*
*/

package model;
import java.util.TreeMap;

public class Employee extends User implements IEmployee {
    /**
     * el horario del empleado 
     */
    private String schedule;
    /**
     * el carrito de compra
     */
    private TreeMap<Integer, Product> shoppingCart;

    /**
     * constructor para instancia 
     */
    public Employee() {
        shoppingCart = new TreeMap<Integer, Product>();
    }
    /**
     * @param schedule el horario del empleado
     * @param shopingCart el carrito de compra
     */
    public Employee(String schedule, TreeMap<Integer, Product> shoppingCart) {
        this.schedule = schedule;
        this.shoppingCart = shoppingCart;
    }
    
    /** 
     * @return String
     */
    public String getSchedule() {
        return schedule;
    }

    public void setSchedule(String schedule) {
        this.schedule = schedule;
    }

    public TreeMap<Integer, Product> getShoppingCart() {
        return shoppingCart;
    }

    public void setShoppingCart(TreeMap<Integer, Product> shoppingCart) {
        this.shoppingCart = shoppingCart;
    }

/**
 * este metodo agrega un producto a el carrito de compras
 * 
 * @param id el id del producto que se desea agregar al carrito de compra
 * @param product el producto que se desea agregar al carrito de compra
 * @param amount la cantidad de productos que se desea agregar al carrito de compra 
 * @return dato booleano true
 */
    @Override
    public boolean addShoppingCart(int id, Product product, int amount) {
        product.setAmount(amount);
        shoppingCart.put(id, product);
        return true;
    }
/**
 * este metodo elimina un producto de el carrito de compras
 * 
 * @param id el id del producto a eliminar del carrito de compra
 * @return bandera que indica si el producto a eliminar del carrito de compra existe 
 * 
 * 
 * */
    @Override
    public boolean removeShoppingCart(int id) {
        if (shoppingCart.get(id) != null) {
            shoppingCart.remove(id);
            return true;
        }
        return false;
    }
/**
 * este metodo retorna la lista de productos en el carrito de compra
 * 
 * @return la lista de productos del carrito de compra 
 */
    @Override
    public TreeMap<Integer, Product> sellCart() {
        return shoppingCart;
    }

}
