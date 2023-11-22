/** 
* Class Admin
*Contiene informacion acerca del administrador
*
*@author Steven Leon
*@author Ricardo Forero
* @version 1.0
*
*
*/
package model;

import java.util.TreeMap;

public class Admin extends User implements IAdmin {
/**
 * constructor vacio para realizar instancia
 */
    public Admin() {
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
        return new Product(name, type, price, id, 0, image, inventory);
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
    public boolean editProduct(String newName, double newPrice, String newType, int id, int newInventory,
            TreeMap<Integer, Product> productList) {
        Product productToEdit = productList.get(id);
        if (productList.get(id) != null) {
            productToEdit.setName(newName);
            productToEdit.setPrice(newPrice);
            productToEdit.setType(newType);
            productToEdit.setInventory(newInventory);
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
    public boolean removeProduct(int id, TreeMap <Integer, Product> productList) {
        if (productList.get(id) != null) {
            productList.remove(id);
            return true;
        }
        return false;
    }

}
