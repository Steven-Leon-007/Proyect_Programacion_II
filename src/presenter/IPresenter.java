package presenter;

import java.util.TreeMap;

import model.Product;

public interface IPresenter {
    public void readProductsFromFile();

    public boolean readCredentialsFromFile();

    public void showLogin();

    public void employeeMenu();


    public boolean addProduct(String name, double price, String type, int id, int amount, String image, int inventory);

    public boolean editProduct(String newName, double newPrice, String newType, int id, int newAmount, String newImage, int newInventory);

    public boolean removeProduct(int id);

    public void addShoppingCart(int id);

    public boolean removeShoppingCart(int id);

    public void sellCart(TreeMap<Integer, Product> shoppingCart, TreeMap<Integer, Product> productsInventory);

}
