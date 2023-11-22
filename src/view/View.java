/** 
* Class Presenter
*Contiene la logica y conexiones de toda la aplicación
*
*@author Steven Leon
*@author Ricardo Forero
*@version 1.0
*
*
*/

package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.TreeMap;

import javax.swing.BorderFactory;
import javax.swing.JCheckBox;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import model.Product;

public class View implements IView {
	private MyScrollPane scrollProducts;
	// Color palette in: https://coolors.co/343330-45462a-7e5920-dc851f-ffa737;

	private MyFrame frame;
	private MyPanel panelLogin, panelInput, panelAdmin, panelEmployee, panelProducts, panelCheckout, panelOptions,
			panelInfoCart, panelAddProduct, panelEditProduct, panelDeleteProduct, billPanel;
	private MyImagePlacer logo;
	private MyButton loginButton, addAdminButton, deleteAdminButton, editAdminButton, buyButton, listButton, addButton,
			editButton, deleteButton, removeFromCart, idToEditButton;
	private MyLabel username, password, cartTitle, totalPrice;
	private JTextArea cart;
	private MyTextField usernameInput, nameInput, idInput, typeInput, priceInput, inventoryInput, imageInput;
	private MyPasswordField passwordInput;
	private Color backgroundColor, detailsColor, borderColor, fontColor, otherBackgroundColor, otherDetailsColor;
	private ArrayList<MyButton> productsBuy;
	private MyDialog cartDialog, createProductDialog, editProductDialog, deleteProductDialog;
	private JCheckBox checkBoxDelete;

	Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
	double width = screenSize.getWidth();
	double height = screenSize.getHeight();
	JOptionPane paneShowError;

	/**
	 * Constructor que instancia los objetos necesarios y setea estilos necesarios.
	 * 
	 * @param listener Un ActionListener que sera propagado por el programa y
	 *                 empleado en el presentador.
	 * 
	 */
	public View(ActionListener listener) {
		super();

		backgroundColor = new Color(52, 51, 48);
		borderColor = new Color(255, 167, 55);
		fontColor = new Color(245, 245, 245);
		detailsColor = new Color(69, 70, 42);
		otherBackgroundColor = new Color(32, 32, 32);
		otherDetailsColor = new Color(220, 133, 31);

		panelLogin = new MyPanel(new GridLayout(3, 1), width * 0.5, height * 0.7, backgroundColor, true, false);
		panelInput = new MyPanel(new GridLayout(4, 1), width, height, backgroundColor, true, false);
		panelAdmin = new MyPanel(new GridLayout(2, 1), width, height, backgroundColor, true, true);
		panelOptions = new MyPanel(new GridLayout(1, 3), width, height, backgroundColor, true, false);
		panelEmployee = new MyPanel(new BorderLayout(30, 0), width, height, backgroundColor, true, false);
		panelProducts = new MyPanel(new GridLayout(4, 4), width * 0.8, height, backgroundColor, true, false);
		panelCheckout = new MyPanel(new GridLayout(3, 1), width * 0.2, height, backgroundColor, true, false);
		panelInfoCart = new MyPanel(new GridLayout(2, 1), width * 0.2, height, backgroundColor, true, false);
		panelAddProduct = new MyPanel(new GridLayout(7, 1), width, height, backgroundColor, true, false);
		panelEditProduct = new MyPanel(new GridLayout(7, 1), width, height, backgroundColor, true, false);
		panelDeleteProduct = new MyPanel(new GridLayout(3, 1), width, height, backgroundColor, true, false);

		productsBuy = new ArrayList<MyButton>();

		logo = new MyImagePlacer("src/resources/logo-project.png", 20, 20);

		loginButton = new MyButton("Ingresar", "", otherDetailsColor, Color.BLACK, true);
		buyButton = new MyButton("Comprar", "", otherDetailsColor, Color.BLACK, true);
		addAdminButton = new MyButton("", "src/resources/add-admin.png", otherDetailsColor, Color.BLACK, true);
		editAdminButton = new MyButton("", "src/resources/edit-admin.png", otherDetailsColor, Color.BLACK, true);
		deleteAdminButton = new MyButton("", "src/resources/delete-admin.png", otherDetailsColor, Color.BLACK, true);
		listButton = new MyButton("Carrito", "", otherDetailsColor, Color.BLACK, true);
		listButton.setPreferredSize(new Dimension(130, 300));
		buyButton.setPreferredSize(new Dimension(130, 300));
		addButton = new MyButton("Agregar Producto", "", otherDetailsColor, Color.BLACK, true);
		editButton = new MyButton("Editar Producto", "", otherDetailsColor, Color.BLACK, true);
		idToEditButton = new MyButton("Editar", "", otherDetailsColor, Color.BLACK, true);
		deleteButton = new MyButton("Eliminar Producto", "", otherDetailsColor, Color.BLACK, true);
		removeFromCart = new MyButton("Eliminar un Producto", "", otherDetailsColor, Color.BLACK, true);
		removeFromCart.setPreferredSize(new Dimension(130, 100));

		checkBoxDelete = new JCheckBox("¿Está seguro de eliminar el producto?");
		checkBoxDelete.setBackground(backgroundColor);
		checkBoxDelete.setForeground(fontColor);

		addMouseListenerBtn(listButton);

		username = new MyLabel("Usuario: ", fontColor, true);
		password = new MyLabel("Contraseña: ", fontColor, true);
		cartTitle = new MyLabel("Bienvenid@, ", fontColor, true);

		cart = new JTextArea();
		cart.setEditable(false);
		cart.setBackground(backgroundColor);
		cart.setForeground(fontColor);

		totalPrice = new MyLabel("Total: $", fontColor, false);

		usernameInput = new MyTextField(borderColor, otherBackgroundColor, fontColor, false, "");
		passwordInput = new MyPasswordField(borderColor, otherBackgroundColor, fontColor);
		nameInput = new MyTextField(borderColor, otherBackgroundColor, fontColor, true, "Nombre del producto");
		idInput = new MyTextField(borderColor, otherBackgroundColor, fontColor, true, "ID del producto");
		idInput.setText("");
		typeInput = new MyTextField(borderColor, otherBackgroundColor, fontColor, true, "Tipo del producto");
		inventoryInput = new MyTextField(borderColor, otherBackgroundColor, fontColor, true,
				"Inventario disponible del producto");
		imageInput = new MyTextField(borderColor, otherBackgroundColor, fontColor, true,
				"Ubicación de la imagen del producto");
		priceInput = new MyTextField(borderColor, otherBackgroundColor, fontColor, true, "Precio del producto");

		createProductDialog = new MyDialog(frame, "Crear Producto", true);
		editProductDialog = new MyDialog(frame, "Editar Producto", true);
		deleteProductDialog = new MyDialog(frame, "Eliminar Producto", true);
	}

	/**
	 * Metodo que crea un MouseListener para el boton de la factura.
	 * 
	 * @param listButton el boton al cual se le añadirá el MouseListener.
	 * 
	 */
	private void addMouseListenerBtn(MyButton listButton) {
		listButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent e) {
				super.mouseReleased(e);
				billPanel = new MyPanel(new BorderLayout(), width, height, backgroundColor, true, false);
				billPanel.add(cart, BorderLayout.CENTER);
				billPanel.add(removeFromCart, BorderLayout.SOUTH);

				cartDialog = new MyDialog(frame, "Carrito", true);
				panelOptions.setBorder(BorderFactory.createEmptyBorder(30, 30, 30, 30));

				cartDialog.add(billPanel);
				cartDialog.setSize(new Dimension(400, (int) (frame.getHeight() - 100)));
				cartDialog.setResizable(false);
				cartDialog.setVisible(true);
			}
		});
	}

	/**
	 * Metodo que inicializa la vista con el frame del login y se comunica con el
	 * presentador.
	 * 
	 * @throws IOException En caso de que no se pueda realizar el login se propaga
	 *                     la excepción al presentador.
	 * 
	 */
	public void initView() throws IOException {
		frame = new MyFrame(width, height, backgroundColor);
		frameLogin();
	}

	/**
	 * Metodo que inicializa y añade los componentes del panel del login.
	 * 
	 */
	@Override
	public void frameLogin() {
		panelLogin.add(logo);

		panelInput.add(username);
		panelInput.add(usernameInput);
		panelInput.add(password);
		panelInput.add(passwordInput);

		panelLogin.add(panelInput);

		MyPanel panelButton = new MyPanel(new GridLayout(1, 1), width, height, backgroundColor, true, true);

		panelButton.add(loginButton);
		panelLogin.add(panelButton);

		GridBagConstraints loginConst = new GridBagConstraints();
		loginConst.gridx = 0;
		loginConst.gridy = 0;
		loginConst.ipadx = 300;

		loginConst.insets = new Insets(5, 5, 5, 5);

		frame.add(panelLogin, loginConst);
		frame.repaint();
		frame.setVisible(true);

	}

	/**
	 * Metodo que inicializa y añade los componentes del panel del administrador.
	 * 
	 */
	@Override
	public void frameAdmin() {
		panelAdmin.add(logo);

		GridLayout layoutOptions = new GridLayout(1, 3);
		layoutOptions.setHgap(30);
		panelOptions.setBorder(BorderFactory.createEmptyBorder(30, 60, 30, 60));
		panelOptions.setLayout(layoutOptions);

		panelOptions.add(addAdminButton);
		panelOptions.add(editAdminButton);
		panelOptions.add(deleteAdminButton);

		panelAdmin.add(panelOptions);

		GridBagConstraints adminConst = new GridBagConstraints();
		adminConst.gridx = 0;
		adminConst.gridy = 0;
		adminConst.ipadx = 600;
		adminConst.ipady = 200;

		adminConst.insets = new Insets(5, 5, 5, 5);

		frame.remove(panelLogin);

		frame.add(panelAdmin, adminConst);
		frame.repaint();
		frame.revalidate();
		frame.setVisible(true);

	}

	/**
	 * Metodo que crea los botones de los productos para poder añadirles
	 * ActionListener y posteriormente ser añadidos a la vista
	 * 
	 * @param products El listado de productos.
	 * 
	 * @param al       ActionListener para ser agregado a cada boton.
	 * 
	 * @return un objeto MyPanel para poder ser añadido en otros metodos archivo.
	 */
	public MyPanel buttonsProductsCreator(TreeMap<Integer, Product> products, ActionListener al) {
		for (Product object : products.values()) {
			MyPanel product = new MyPanel(new BorderLayout(20, 20), 300, 300, backgroundColor, false, false);
			MyLabel productName = new MyLabel(object.getName(), fontColor, true);
			MyLabel productPrice = new MyLabel("Precio: $ " + object.getPrice(), fontColor, true);

			productName.setBorder(BorderFactory.createEmptyBorder(20, 5, 0, 5));
			productPrice.setBorder(BorderFactory.createEmptyBorder(0, 5, 20, 5));

			product.add(productName, BorderLayout.NORTH);
			MyButton buttonProduct = new MyButton("", object.getImage(), backgroundColor, backgroundColor, false);
			buttonProduct.addActionListener(al);

			productsBuy.add(buttonProduct);

			product.add(buttonProduct, BorderLayout.CENTER);
			product.add(productPrice, BorderLayout.SOUTH);
			product.setBorder(BorderFactory.createLineBorder(borderColor, 1));

			panelProducts.add(product);

		}
		return panelProducts;
	}

	/**
	 * Metodo que inicializa y añade los componentes del panel del empleado.
	 * 
	 * @param products El listado de productos.
	 * 
	 * @param al       ActionListener para ser agregado a cada boton.
	 * 
	 */
	@Override
	public void frameEmployee(TreeMap<Integer, Product> products, ActionListener al) {
		scrollProducts = new MyScrollPane(buttonsProductsCreator(products, al), (int) width, (int) height,
				backgroundColor);
		panelCheckout.add(cartTitle);

		panelInfoCart.add(listButton);
		panelInfoCart.add(totalPrice);

		panelInfoCart.setBorder(BorderFactory.createEmptyBorder(5, 0, 5, -5));

		MyPanel productsPanel = new MyPanel(new GridLayout(1, 1), width, height, backgroundColor, true, false);
		productsPanel.setBorder(BorderFactory.createEmptyBorder(20, -30, 20, 0));
		productsPanel.add(scrollProducts);

		panelCheckout.add(panelInfoCart);
		panelCheckout.add(buyButton);

		panelCheckout.setBorder(BorderFactory.createEmptyBorder(0, 0, 80, 0));

		panelEmployee.add(productsPanel, BorderLayout.CENTER);
		panelEmployee.add(panelCheckout, BorderLayout.EAST);

		GridBagConstraints employeeConst = new GridBagConstraints();
		employeeConst.gridx = 0;
		employeeConst.gridy = 0;
		employeeConst.ipadx = (int) (width * 0.85);
		employeeConst.ipady = (int) (height * 0.8);

		employeeConst.insets = new Insets(5, 5, 5, 5);

		frame.remove(panelLogin);

		frame.add(panelEmployee, employeeConst);
		frame.repaint();
		frame.revalidate();
		frame.setVisible(true);

	}

	/**
	 * Metodo que añade los componentes y estiliza el panel de añadir productos del
	 * administrador.
	 * 
	 */
	@Override
	public void addProductsView() {

		panelAddProduct.add(idInput);
		panelAddProduct.add(nameInput);
		panelAddProduct.add(typeInput);
		panelAddProduct.add(priceInput);
		panelAddProduct.add(inventoryInput);
		panelAddProduct.add(imageInput);
		panelAddProduct.add(addButton);

		panelAddProduct.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

		createProductDialog.add(panelAddProduct);
		createProductDialog.setSize(new Dimension(600, 600));
		createProductDialog.setResizable(true);
		createProductDialog.setVisible(true);
	}

	/**
	 * Metodo que añade los componentes y estiliza el panel que solicita el ID del
	 * producto a editar antes de permitir el acceso a este panel.
	 * 
	 */
	public void askForId() {
		MyDialog idDialog = new MyDialog(frame, "ID del producto a editar", true);
		MyPanel panel = new MyPanel(new GridLayout(2, 1), width, height, backgroundColor, true, false);
		panel.add(idInput);
		panel.add(idToEditButton);
		idDialog.add(panel);
		idDialog.setSize(new Dimension(300, 200));
		idDialog.setResizable(true);
		idDialog.setVisible(true);
	}

	/**
	 * Metodo encargado de setear la vista de edición de productos del
	 * administrador.
	 * 
	 * @param name      Nombre del producto
	 * @param price     Precio del producto
	 * @param type      Tipo del producto
	 * @param id        ID del producto
	 * @param image     Imagen del producto (Como dirección)
	 * @param inventory Cantidad del producto disponible
	 * 
	 */
	@Override
	public void editProductsView(int id, String name, String type, double price, int inventory, String image) {
		idInput.setEditable(false);
		idInput.setText("" + id);
		nameInput.setText(name);
		typeInput.setText(type);
		priceInput.setText("" + price);
		inventoryInput.setText(String.valueOf(inventory));
		imageInput.setText(image);

		panelEditProduct.add(idInput);
		panelEditProduct.add(nameInput);
		panelEditProduct.add(typeInput);
		panelEditProduct.add(priceInput);
		panelEditProduct.add(inventoryInput);
		panelEditProduct.add(imageInput);
		panelEditProduct.add(editButton);

		panelEditProduct.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

		editProductDialog.add(panelEditProduct);
		editProductDialog.setSize(new Dimension(600, 600));
		editProductDialog.setResizable(true);
		editProductDialog.setVisible(true);
	}

	/**
	 * Metodo que añade los componentes y estiliza el panel de eliminar productos
	 * del administrador.
	 * 
	 */
	@Override
	public void deleteProductsView() {
		panelDeleteProduct.add(idInput);

		checkBoxDelete.addChangeListener(new ChangeListener() {
			@Override
			public void stateChanged(ChangeEvent e) {
				if (!checkBoxDelete.isSelected()) {
					deleteButton.setEnabled(false);
				} else {
					deleteButton.setEnabled(true);
				}
			}
		});

		panelDeleteProduct.add(checkBoxDelete);
		panelDeleteProduct.add(deleteButton);

		panelDeleteProduct.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

		deleteProductDialog.add(panelDeleteProduct);
		deleteProductDialog.setSize(new Dimension(400, 300));
		deleteProductDialog.setResizable(true);
		deleteProductDialog.setVisible(true);

	}

	/**
	 * Metodo que actualiza el carrito de compras y muestra la información de los
	 * productos añadidos.
	 * 
	 */
	public void setProductsCart(String products) {
		cart.setText(products);
		panelInfoCart.revalidate();
		panelInfoCart.repaint();
	}

	/**
	 * Metodo que despliega una alerta como un JOptionPane en caso de que el
	 * programa haya encontrado un error o excepcion.
	 * 
	 * @param message El mensaje de error asociado al error encontrado.
	 * 
	 */
	public void showAlert(String message) {
		paneShowError.showMessageDialog(null, message, "Error", JOptionPane.WARNING_MESSAGE);
	}

	/**
	 * Metodo que despliega una alerta como un JOptionPane en caso de que el
	 * programa haya realizado una operación correctamente.
	 * 
	 * @param message El mensaje de información asociado a la opearacion
	 *                satisfactoria.
	 * 
	 */
	public void showSuccess(String message) {
		paneShowError.showMessageDialog(null, message, "Información", JOptionPane.INFORMATION_MESSAGE);
	}

	public MyFrame getFrame() {
		return frame;
	}

	public void setFrame(MyFrame frame) {
		this.frame = frame;
	}

	public MyDialog getCartDialog() {
		return cartDialog;
	}

	public void setCartDialog(MyDialog cartDialog) {
		this.cartDialog = cartDialog;
	}

	public MyButton getBuyButton() {
		return buyButton;
	}

	public void setBuyButton(MyButton buyButton) {
		this.buyButton = buyButton;
	}

	public MyPanel getPanelLogin() {
		return panelLogin;
	}

	public void setPanelLogin(MyPanel panelLogin) {
		this.panelLogin = panelLogin;
	}

	public MyPanel getPanelInput() {
		return panelInput;
	}

	public void setPanelInput(MyPanel panelInput) {
		this.panelInput = panelInput;
	}

	public MyPanel getPanelAdmin() {
		return panelAdmin;
	}

	public void setPanelAdmin(MyPanel panelAdmin) {
		this.panelAdmin = panelAdmin;
	}

	public MyPanel getPanelEmployee() {
		return panelEmployee;
	}

	public void setPanelEmployee(MyPanel panelEmployee) {
		this.panelEmployee = panelEmployee;
	}

	public MyPanel getPanelProducts() {
		return panelProducts;
	}

	public void setPanelProducts(MyPanel panelProducts) {
		this.panelProducts = panelProducts;
	}

	public MyPanel getPanelCheckout() {
		return panelCheckout;
	}

	public MyPanel getPanelInfoCart() {
		return panelInfoCart;
	}

	public void setPanelInfoCart(MyPanel panelInfoCart) {
		this.panelInfoCart = panelInfoCart;
	}

	public void setPanelCheckout(MyPanel panelCheckout) {
		this.panelCheckout = panelCheckout;
	}

	public MyPanel getPanelOptions() {
		return panelOptions;
	}

	public void setPanelOptions(MyPanel panelOptions) {
		this.panelOptions = panelOptions;
	}

	public MyImagePlacer getLogo() {
		return logo;
	}

	public void setLogo(MyImagePlacer logo) {
		this.logo = logo;
	}

	public MyButton getLoginButton() {
		return loginButton;
	}

	public void setLoginButton(MyButton loginButton) {
		this.loginButton = loginButton;
	}

	public MyButton getAddAdminButton() {
		return addAdminButton;
	}

	public void setAddAdminButton(MyButton addAdminButton) {
		this.addAdminButton = addAdminButton;
	}

	public MyButton getDeleteAdminButton() {
		return deleteAdminButton;
	}

	public void setDeleteAdminButton(MyButton deleteAdminButton) {
		this.deleteAdminButton = deleteAdminButton;
	}

	public MyButton getEditAdminButton() {
		return editAdminButton;
	}

	public void setEditAdminButton(MyButton editAdminButton) {
		this.editAdminButton = editAdminButton;
	}

	public MyLabel getUsername() {
		return username;
	}

	public void setUsername(MyLabel username) {
		this.username = username;
	}

	public MyLabel getPassword() {
		return password;
	}

	public void setPassword(MyLabel password) {
		this.password = password;
	}

	public JTextArea getCart() {
		return cart;
	}

	public void setCart(JTextArea cart) {
		this.cart = cart;
	}

	public MyLabel getCartTitle() {
		return cartTitle;
	}

	public void setCartTitle(MyLabel cartTitle) {
		this.cartTitle = cartTitle;
	}

	public MyLabel getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(MyLabel totalPrice) {
		this.totalPrice = totalPrice;
	}

	public MyTextField getUsernameInput() {
		return usernameInput;
	}

	public void setUsernameInput(MyTextField usernameInput) {
		this.usernameInput = usernameInput;
	}

	public MyPasswordField getPasswordInput() {
		return passwordInput;
	}

	public void setPasswordInput(MyPasswordField passwordInput) {
		this.passwordInput = passwordInput;
	}

	public void setOtherDetailsColor(Color otherDetailsColor) {
		this.otherDetailsColor = otherDetailsColor;
	}

	public ArrayList<MyButton> getProductsBuy() {
		return productsBuy;
	}

	public void setProductsBuy(ArrayList<MyButton> productsBuy) {
		this.productsBuy = productsBuy;
	}

	public MyButton getRemoveFromCart() {
		return removeFromCart;
	}

	public void setRemoveFromCart(MyButton removeFromCart) {
		this.removeFromCart = removeFromCart;
	}

	public MyTextField getNameInput() {
		return nameInput;
	}

	public void setNameInput(MyTextField nameInput) {
		this.nameInput = nameInput;
	}

	public MyTextField getIdInput() {
		return idInput;
	}

	public void setIdInput(MyTextField idInput) {
		this.idInput = idInput;
	}

	public MyTextField getTypeInput() {
		return typeInput;
	}

	public void setTypeInput(MyTextField typeInput) {
		this.typeInput = typeInput;
	}

	public MyTextField getPriceInput() {
		return priceInput;
	}

	public void setPriceInput(MyTextField priceInput) {
		this.priceInput = priceInput;
	}

	public MyTextField getInventoryInput() {
		return inventoryInput;
	}

	public void setInventoryInput(MyTextField inventoryInput) {
		this.inventoryInput = inventoryInput;
	}

	public MyTextField getImageInput() {
		return imageInput;
	}

	public void setImageInput(MyTextField imageInput) {
		this.imageInput = imageInput;
	}

	public MyButton getAddButton() {
		return addButton;
	}

	public void setAddButton(MyButton addButton) {
		this.addButton = addButton;
	}

	public MyButton getEditButton() {
		return editButton;
	}

	public void setEditButton(MyButton editButton) {
		this.editButton = editButton;
	}

	public MyButton getDeleteButton() {
		return deleteButton;
	}

	public void setDeleteButton(MyButton deleteButton) {
		this.deleteButton = deleteButton;
	}

	public MyButton getIdToEditButton() {
		return idToEditButton;
	}

	public void setIdToEditButton(MyButton idToEditButton) {
		this.idToEditButton = idToEditButton;
	}

}
