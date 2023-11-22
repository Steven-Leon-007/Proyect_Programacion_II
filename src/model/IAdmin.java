package model;

import java.util.TreeMap;

public interface IAdmin {
    public Product addProduct(String name, double price, String type, int id, String image, int inventory);

    public boolean editProduct(String newName, double newPrice, String newType, int id, int newInventory,
            TreeMap<Integer, Product> productList);

    public boolean removeProduct(int id, TreeMap<Integer, Product> productList);
}
