package model;

import java.util.TreeMap;

public interface IEmployee {
    public boolean addShoppingCart(int id, Product product, int amount);

    public boolean removeShoppingCart(int id);

    public TreeMap<Integer, Product> sellCart();
}
