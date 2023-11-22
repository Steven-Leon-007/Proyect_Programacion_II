/** 
* Class Presenter
*Contiene la logica y conexiones de toda la aplicación
*
*@author Steven Leon
*@author Ricardo Forero
*@version 1.1
*
*
*/

package presenter;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.TreeMap;

import model.Product;
import model.Store;
import model.Bill;
import view.View;
import persistence.*;

public class Presenter implements IPresenter, ActionListener {

	private View view;
	private Store store;
	private PropertiesHandler propertiesHandler;
	private JSONHandler jsonHandler;
	private TXTHandler txthandler;
	private Bill bill;

	/**
	 * Constructor que instancia los objetos necesarios.
	 *
	 */
	public Presenter() {
		store = new Store();
		jsonHandler = new JSONHandler();
		propertiesHandler = new PropertiesHandler();
		txthandler = new TXTHandler();
		this.readProductsFromFile();
		view = new View(this);
		initButtons();
		this.showLogin();

	}

	/**
	 * Metodo que añade los eventListener a los botones de la vista.
	 * 
	 */
	public void initButtons() {
		view.getLoginButton().addActionListener(this);
		view.getAddAdminButton().addActionListener(this);
		view.getDeleteAdminButton().addActionListener(this);
		view.getEditAdminButton().addActionListener(this);
		view.getAddButton().addActionListener(this);
		view.getDeleteButton().addActionListener(this);
		view.getEditButton().addActionListener(this);
		view.getIdToEditButton().addActionListener(this);

		view.getBuyButton().addActionListener(this);
		view.getRemoveFromCart().addActionListener(this);
	}

	/**
	 * Metodo que actualiza el carrito de productos visualmente,
	 * mostrando el listado de productos y el valor total del carrito.
	 */
	private void updateLabelProducts() {
		String productList = "Producto:  Precio - Cantidad \n";
		int counter = 1;
		for (Product products : store.getEmployee().getShoppingCart().values()) {
			productList += counter + ". " + products.getName() + ": $ " + products.getPrice() + " - "
					+ products.getAmount() + "\n \n";
			counter++;
		}
		view.getTotalPrice().setText("Total: $ " + updateTotalPrice());
		view.setProductsCart(productList);
	}

	
	/** 
	 * @param e
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource().equals(view.getLoginButton())) {
			if (!this.readCredentialsFromFile()) {
				view.showAlert("Credenciales incorrectas");
			}
		} else if (e.getSource().equals(view.getAddAdminButton())) {
			view.addProductsView();
		} else if (e.getSource().equals(view.getEditAdminButton())) {
			view.askForId();
		} else if (e.getSource().equals(view.getIdToEditButton())) {
			if (store.getProductList().containsKey(Integer.parseInt(view.getIdInput().getText()))) {
				Product product = store.getProductList().get(Integer.parseInt(view.getIdInput().getText()));
				String name = product.getName();
				double price = product.getPrice();
				String type = product.getType();
				int id = product.getId();
				String image = product.getImage();
				int inventory = product.getInventory();

				view.editProductsView(id, name, type, price, inventory, image);
			} else {
				view.showAlert("El producto no existe");
			}

		} else if (e.getSource().equals(view.getEditButton())) {
			String name = view.getNameInput().getText();
			double price = Double.parseDouble(view.getPriceInput().getText());
			String type = view.getTypeInput().getText();
			int id = Integer.parseInt(view.getIdInput().getText());
			String image = view.getImageInput().getText();
			int inventory = Integer.parseInt(view.getInventoryInput().getText());

			this.editProduct(name, price, type, id, id, image, inventory);

		} else if (e.getSource().equals(view.getDeleteAdminButton())) {
			view.deleteProductsView();
		} else if (e.getSource().equals(view.getDeleteButton())) {
			this.removeProduct(Integer.parseInt(view.getIdInput().getText()));
		} else if (e.getSource().equals(view.getAddButton())) {
			String name = view.getNameInput().getText();
			double price = Double.parseDouble(view.getPriceInput().getText());
			String type = view.getTypeInput().getText();
			int id = Integer.parseInt(view.getIdInput().getText());
			String image = view.getImageInput().getText();
			int inventory = Integer.parseInt(view.getInventoryInput().getText());
			this.addProduct(name, price, type, id, 0, image, inventory);
		} else if (e.getSource().equals(view.getEditAdminButton())) {
			String name = view.getNameInput().getText();
			double price = Double.parseDouble(view.getPriceInput().getText());
			String type = view.getTypeInput().getText();
			int id = Integer.parseInt(view.getIdInput().getText());
			String image = view.getImageInput().getText();
			int inventory = Integer.parseInt(view.getInventoryInput().getText());
			this.editProduct(name, price, type, id, 0, image, inventory);
		} else if (e.getSource().equals(view.getRemoveFromCart())) {
			view.deleteProductsView();
			removeShoppingCart(Integer.parseInt(view.getIdInput().getText()));

		} else if (e.getSource().equals(view.getBuyButton())) {
			this.sellCart(store.getEmployee().getShoppingCart(), store.getProductList());
		} else {
			for (int i = 0; i < view.getProductsBuy().size(); i++) {
				if (e.getSource().equals(view.getProductsBuy().get(i))) {
					this.addShoppingCart(i + 1);
					updateLabelProducts();
				}
			}
		}
	}

	/**
	 * Metodo que implementa la persistencia y se encarga de setear la lista de
	 * productos en base a los obtenidos del archivo JSON.
	 * 
	 */
	@Override
	public void readProductsFromFile() {
		try {
			store.setProductList(jsonHandler.fillProducts("./src/persistence/products.json"));
		} catch (FileNotFoundException e) {
			view.showAlert("Error al cargar los productos: " + e.getMessage());
		}

	}

	/**
	 * Metodo que implementa la persistencia y se encarga validar las credenciales
	 * del usuario y mostrar una vista y otra en caso de que las credenciales sean
	 * correctas, de lo contrario muestra en la vista un mensaje de error.
	 * 
	 */
	@Override
	public boolean readCredentialsFromFile() {
		try {
			if (view.getUsernameInput().getText().equals(propertiesHandler.getAdminUser())) {

				char aux[] = view.getPasswordInput().getPassword();
				String password = new String(aux);
				password = password.toString().substring(0, password.length());

				if (password.equals(propertiesHandler.getAdminPassword())) {
					view.frameAdmin();
					return true;
				}
			} else if (view.getUsernameInput().getText().equals(propertiesHandler.getEmployeeUser())) {
				char aux[] = view.getPasswordInput().getPassword();
				String password = new String(aux);
				password = password.toString().substring(0, password.length());

				if (password.equals(propertiesHandler.getEmployeePassword())) {
					this.employeeMenu();
					return true;
				}
			}
		} catch (IOException e) {
			view.showAlert("Error al leer las credenciales: " + e.getMessage());
		}
		return false;
	}

	/**
	 * Metodo encargado de actualizar el valor total del carrito, empleando la
	 * información del mismo.
	 * 
	 * @return Un String que representa el valor total del carrito.
	 * 
	 */
	public String updateTotalPrice() {
		double totalPrice = 0;
		for (Product product : store.getEmployee().getShoppingCart().values()) {
			totalPrice += (product.getPrice() * product.getAmount());
		}

		return String.format("%.2f", totalPrice);
	}

	/**
	 * Metodo encargado de actualizar el valor de la cantidad de cada producto
	 * dentro del carrito, haciendo uso de la información del mismo y restandola
	 * total de cada producto.
	 * 
	 */
	public void updateInventory() {
		for (Product product : store.getEmployee().getShoppingCart().values()) {
			if (product != null && store.getProductList().containsValue(product)) {
				Product holder = store.getProductList().get(product.getId());
				holder.setInventory((holder.getInventory() - product.getAmount()));
			}
		}
	}

	/**
	 * Metodo que llama a la vista para implementar su metodo initview.
	 * 
	 */
	@Override
	public void showLogin() {
		try {
			view.initView();
		} catch (IOException e) {
			view.showAlert("Error al iniciar el programa: " + e.getMessage());
		}
	}

	/**
	 * Metodo que inoca a la vista para mostrar la vista del empleado, setea el
	 * nombre del empleado en la vista usando el valor que encontró en la
	 * persistencia y carga los productos. Captura una excepcion IOException y
	 * muestra un mensaje en caso de ser invocada.
	 * 
	 * @exception IOException En caso de que no encuentre el archivo de propiedades.
	 */
	@Override
	public void employeeMenu() {
		try {
			view.getCartTitle().setText((view.getCartTitle().getText() + " " + propertiesHandler.getEmployeeUser()));
			view.frameEmployee(store.getProductList(), this);
		} catch (IOException e) {
			view.showAlert("Error en el login del empleado: " + e.getMessage());
		}
	}

	/**
	 * Metodo que añade productos al inventario.
	 * 
	 * @param name      Nombre del producto
	 * @param price     Precio del producto
	 * @param type      Tipo del producto
	 * @param id        ID del producto
	 * @param amount    Cantidad del producto en carrito (0)
	 * @param image     Imagen del producto (Como dirección)
	 * @param inventory Cantidad del producto disponible
	 * 
	 * @return Un booleano encargado de validar si la operacion fue satisfactoria o
	 *         desfavorable.
	 * 
	 * @exception IOException En caso de que el codigo no encuentre el archivo JSON
	 *                        para ser actualizado.
	 */
	@Override
	public boolean addProduct(String name, double price, String type, int id, int amount, String image, int inventory) {
		if (!store.getProductList().containsKey(id)) {
			Product product = new Product(name, type, price, id, amount, image, inventory);
			try {
				store.getProductList().put(id, product);
				jsonHandler.writeJSON("./src/persistence/products.json", store.getProductList());
			} catch (IOException e) {
				view.showAlert("Error al agregar el producto" + e.getMessage());
			}
			view.showSuccess("Producto agregado correctamente.");

			return true;
		}
		view.showAlert("El producto con ese ID ya existe. ");
		return false;
	}

	/**
	 * Metodo que edita un producto al inventario.
	 * 
	 * @param newName      Nombre del producto
	 * @param newPrice     Precio del producto
	 * @param newType      Tipo del producto
	 * @param newId        ID del producto
	 * @param newAmount    Cantidad del producto en carrito (0)
	 * @param newImage     Imagen del producto (Como dirección)
	 * @param newInventory Cantidad del producto disponible
	 * 
	 * @return Un booleano encargado de validar si la operacion fue satisfactoria o
	 *         desfavorable.
	 * 
	 * @exception FileNotFoundException En caso de que el codigo no encuentre el
	 *                                  archivo JSON para ser actualizado.
	 */
	@Override
	public boolean editProduct(String newName, double newPrice, String newType, int id, int newAmount, String newImage,
			int newInventory) {
		if (store.getProductList().containsKey(id) && store.getProductList().get(id) != null) {
			Product product = store.getProductList().get(id);
			product.setName(newName);
			product.setId(id);
			product.setPrice(newPrice);
			product.setType(newType);
			product.setAmount(newAmount);
			product.setImage(newImage);
			try {
				jsonHandler.writeJSON("./src/persistence/products.json", store.getProductList());
			} catch (FileNotFoundException e) {
				view.showAlert("Error actualizando el producto: " + e.getMessage());
			}

			view.showSuccess("Producto editado correctamente.");
			return true;
		}
		view.showAlert("Producto no encontrado.");
		return false;
	}

	/**
	 * Metodo que elimina un producto al inventario.
	 * 
	 * @param id Identificador del producto a eliminar
	 * 
	 * @return Un booleano encargado de validar si la operacion fue satisfactoria o
	 *         desfavorable.
	 * 
	 * @exception FileNotFoundException En caso de que el codigo no encuentre el
	 *                                  archivo JSON para ser actualizado.
	 */
	@Override
	public boolean removeProduct(int id) {
		if (store.getProductList().containsKey(id) && store.getProductList().get(id) != null) {
			store.getProductList().remove(id);
			try {
				jsonHandler.writeJSON("./src/persistence/products.json", store.getProductList());
			} catch (FileNotFoundException e) {
				view.showAlert("Error eliminando el producto: " + e.getMessage());
			}

			view.showSuccess("Producto eliminado satisfactoriamente.");
			return true;
		}

		view.showAlert("Producto no encontrado.");
		return false;
	}

	/**
	 * Metodo que añade un producto al carrito de compras, si el producto ya se
	 * encuentra en el carrito modifica la propiedad "amount" para aumentarla, de lo
	 * contrario lo añade con normalidad.
	 * 
	 * @param id Identificador del producto a añadir al carrito
	 * 
	 */
	@Override
	public void addShoppingCart(int id) {
		if (store.getEmployee().getShoppingCart().containsKey(id)) {
			Product holder = store.getEmployee().getShoppingCart().get(id);

			holder.setAmount(holder.getAmount() + 1);

		} else if (store.getProductList().get(id) != null) {
			store.addShoppingCart(id, store.getProductList().get(id), 1);
		}

	}

	/**
	 * Metodo que elimina un producto del carrito de compras, si el producto existe
	 * dentro del carrito lo elimina con normalidad, de lo contrario se muestra un
	 * mensaje de error.
	 * 
	 * @param id Identificador del producto a eliminar del carrito
	 * 
	 * @return Un booleano encargado de validar si la operacion fue satisfactoria o
	 *         desfavorable.
	 */
	@Override
	public boolean removeShoppingCart(int id) {
		if (store.getEmployee().getShoppingCart().containsKey(id)) {
			store.getEmployee().getShoppingCart().remove(id);
			view.getPanelInfoCart().revalidate();
			view.getPanelInfoCart().repaint();
			view.showSuccess("Producto eliminado del carrito correctamente.");
			return true;
		}
		view.showAlert("Producto no encontrado.");
		return false;
	}

	/**
	 * Metodo que vende el carrito de compras, actualiza el inventario y genera una
	 * factura con la compra realizada.
	 * 
	 * @param shoppingCart      TreeMap que contiene los productos del carrito.
	 * 
	 * @param productsInventory TreeMap que contiene los productos del inventario.
	 * 
	 * @exception FileNotFoundException En caso de que el codigo no encuentre el
	 *                                  archivo JSON para ser actualizado.
	 * 
	 * @exception IOException           En caso de que la factura no pueda ser
	 *                                  generada.
	 */
	@Override
	public void sellCart(TreeMap<Integer, Product> shoppingCart, TreeMap<Integer, Product> productsInventory) {
		updateInventory();

		bill = new Bill(0, shoppingCart);
		bill.setId(bill.getId() + 1);
		try {
			txthandler.textWriter(shoppingCart, bill, this.updateTotalPrice());
			jsonHandler.writeJSON("./src/persistence/products.json", productsInventory);
		} catch (FileNotFoundException e) {
			view.showAlert("Error al sobreescribir el inventario: " + e.getMessage());
		} catch (IOException e) {
			view.showAlert("Error al generar la factura de compra: " + e.getMessage());
		}
		store.sellCart();
		view.getPanelInfoCart().revalidate();
		view.getPanelInfoCart().repaint();
		view.showSuccess("Carrito vendido correctamente.");

	}

}
