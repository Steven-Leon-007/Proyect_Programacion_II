/** 
* Class producto
*contiene informacion acerca de los productos de la tienda
*
*@author Steven Leon
*@author Ricardo Forero
*@version 1.0
*
*/
package model;

public class Product {
    /**
     * el nombre del producto 
     */
    private String name;
    /**
     * el tipo del producto 
     */
    private String type;
    /**
     * el precio del producto
     */
    private double price;
    /**
     * el identificador del producto
     */
    private int id;
    /**
     * la cantidad de productos a seleccionar
     */
    private int amount;
    /**
     * la cantidad del productos en el inventario
     */
    private int inventory;
    /**
     * la direccion de la imagen asosiada al producto 
     */
    private String image;
/**
 * constructor vacio para instancias 
 */
    public Product() {
    }
/**
 * @param name el nombre del producto que se desea crear
 * @param type el tipo de producto que se desea crear
 * @param id el identificador del producto que se desea crear
 * 
 * 
 * 
 * */
    public Product(String name, String type, double price, int id, int amount, String image, int inventory) {
        this.name = name;
        this.type = type;
        this.price = price;
        this.id = id;
        this.amount = amount;
        this.image = image;
        this.inventory = inventory;
    }

    
    /** 
     * @return String
     */
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
    
    public int getInventory() {
        return inventory;
    }

    public void setInventory(int inventory) {
        this.inventory = inventory;
    }

    @Override
    public String toString() {
        return "Product [name=" + name + ", type=" + type + ", price=" + price + ", id=" + id + ", amount=" + amount
                + ", inventory=" + inventory + ", image=" + image + "]";
    }

    


}
