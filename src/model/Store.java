/** 
* Class Store
*contiene informacion acerca de la tienda
*
*@author Steven Leon
*@author Ricardo Forero
*@version 1.0
*
*/
package model;

import java.util.TreeMap;

public class Store implements IAdmin, IEmployee {
    private TreeMap<Integer, Product> productList;
    Admin admin = new Admin();
    Employee employee = new Employee();
    /**
     * constructor vacio para instancias
     */
    public Store() {
    }
    /** constructor con un parametro 
     * @param productList la lista de productos en el inventario
     */
    public Store(TreeMap<Integer, Product> productList) {
        this.productList = productList;
    }
    
    /** 
     * @return TreeMap<Integer, Product>
     */
    public TreeMap<Integer, Product> getProductList() {
        return productList;
    }

    public void setProductList(TreeMap<Integer, Product> productList) {
        this.productList = productList;
    }
 /**
 * este metodo crea los productos
 * 
 * @param name el nombre del producto 
 * @param price el precio del producto
 * @param type el typo de producto
 * @param id el identificador del producto
 * @param image la direccion de la imagen relacionada al producto 
 * @param inventory la cantidad de productos en stock
 * @return el producto creado 
 * 
 */
    @Override
    public Product addProduct(String name, double price, String type, int id, String image, int inventory) {
        productList.put(id, admin.addProduct(name, price, type, id, image, inventory));
        return productList.get(id);
    }
/**
 * este metodo modifica un producto 
 * 
 * @param newName el nuevo nombre del producto a modificar
 * @param newPrice el nuevo precio del producto a modificar
 * @param newType el nuevo tipo del producto
 * @param id el id del producto que se quiere modificar
 * @param newInventory el nuevo numero de productos que se tiene en el inventario 
 * @return bandera que indica si el producto a modificar existe 
 */
    @Override
    public boolean editProduct(String newName, double newPrice, String newType, int id, int newInventory, TreeMap<Integer, Product> productList) {
        if (admin.editProduct(newName, newPrice, newType, id,newInventory, this.productList )) {
            return true;
        }
        return false;
    }

/**
 * este metodo elimina un producto
 * 
 * @param id el id del producto que se desea eliminar
 * @return una bandera que indica si el producto a eliminar existe
 */
    @Override
    public boolean removeProduct(int id, TreeMap<Integer, Product> productList) {
        if (admin.removeProduct(id, this.productList )) {
            return true;
        }
        return false;
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
        if (productList.get(id) != null) {
            product = productList.get(id);
            employee.addShoppingCart(id, product, amount);
            return true;
        }
        return false;
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
        if (productList.get(id) != null && employee.removeShoppingCart(id)) {
            employee.removeShoppingCart(id);
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
        if (!employee.getShoppingCart().isEmpty()) {
            TreeMap<Integer, Product> sendedCart = employee.sellCart();
            employee.sellCart().clear();
            return sendedCart;
        }
        return null;
    }

    public Employee getEmployee() {
        return employee;
    }
}


