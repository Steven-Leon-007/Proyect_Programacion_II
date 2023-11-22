/** 
* Class PropertiesHandler
*Contiene los metodos para trabajar con archivos properties
*
*@author Steven Leon
*@author Ricardo Forero
*@version 1.0
*
*
*/

package persistence;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.io.FileInputStream;

public class PropertiesHandler {
	/**
	 * constructor vacio para realizar instancia
	 */
	public PropertiesHandler() {

	}

	/**
	 * Metodo que obtiene el usuario del administrador desde el archivo
	 * 
	 * @return Un String que representa la credencial del administrador
	 * 
	 * @throws IOException En caso de que no se encuentre la credencial en el
	 *                     archivo
	 */
	public String getAdminUser() throws IOException {
		String aux = null;
		try {
			InputStream inputStream = new FileInputStream("./src/persistence/credentials.properties");
			Properties prop = new Properties();
			prop.load(inputStream);
			aux = prop.getProperty("admin.username");
		} catch (IOException e) {
			throw e;
		}
		return aux;
	}

	/**
	 * Metodo que obtiene la contraseña del administrador desde el archivo
	 * 
	 * @return Un String que representa la credencial del administrador
	 * 
	 * @throws IOException En caso de que no se encuentre la credencial en el
	 *                     archivo
	 */
	public String getAdminPassword() throws IOException {
		String aux = null;
		try {
			InputStream inputStream = new FileInputStream("./src/persistence/credentials.properties");
			Properties prop = new Properties();
			prop.load(inputStream);
			aux = prop.getProperty("admin.password");

		} catch (IOException e) {
			throw e;
		}
		return aux;
	}

	/**
	 * Metodo que obtiene el usuario del empleado desde el archivo
	 * 
	 * @return Un String que representa la credencial del empleado
	 * 
	 * @throws IOException En caso de que no se encuentre la credencial en el
	 *                     archivo
	 */
	public String getEmployeeUser() throws IOException {
		String aux = null;
		try {
			InputStream inputStream = new FileInputStream("./src/persistence/credentials.properties");
			Properties prop = new Properties();
			prop.load(inputStream);
			aux = prop.getProperty("employee.username");
		} catch (IOException e) {
			throw e;
		}
		return aux;
	}

	/**
	 * Metodo que obtiene la contraseña del empleado desde el archivo
	 * 
	 * @return Un String que representa la credencial del empleado
	 * 
	 * @throws IOException En caso de que no se encuentre la credencial en el
	 *                     archivo
	 */
	public String getEmployeePassword() throws IOException {
		String aux = null;
		try {
			InputStream inputStream = new FileInputStream("./src/persistence/credentials.properties");
			Properties prop = new Properties();
			prop.load(inputStream);
			aux = prop.getProperty("employee.password");
		} catch (IOException e) {
			throw e;
		}
		return aux;
	}
}
