package view;


import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.TreeMap;

import model.Product;

public interface IView {
    public void frameLogin();
    public void frameAdmin();
    public void frameEmployee(TreeMap<Integer, Product> products, ActionListener al);
    public void addProductsView();
    public void editProductsView(int id, String name, String type, double price, int inventory, String image);
    public void deleteProductsView();
	public void initView() throws IOException;
}
