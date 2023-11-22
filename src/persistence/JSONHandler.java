/** 
* Class JSONHandler
*Contiene los metodos para trabajar con archivos JSON
*
*@author Steven Leon
*@author Ricardo Forero
* @version 1.0
*
*
*/

package persistence;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.TreeMap;

import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObject;
import javax.json.JsonObjectBuilder;
import javax.json.JsonReader;
import javax.json.JsonValue;
import javax.json.JsonWriter;

import model.*;

public class JSONHandler {
    /**
     * Metodo que carga los productos en la aplicación
     * 
     * @param path La ubicación del archivo JSON
     * 
     * @return Un Treemap que funciona para cargar en el modelo Store
     * 
     * @throws FileNotFoundException En caso de que el codigo no encuentre el
     *                               archivo
     */
    public TreeMap<Integer, Product> fillProducts(String path) throws FileNotFoundException {
        File file = new File(path);
        TreeMap<Integer, Product> productMap = new TreeMap<Integer, Product>();
        try {
            FileInputStream fis = new FileInputStream(file);
            JsonReader reader = Json.createReader(fis);

            JsonObject content = reader.readObject();

            reader.close();

            JsonArray jsonArray = content.getJsonArray("Products");
            for (JsonValue jsonValue : jsonArray) {
                Product product = new Product();
                product.setName(jsonValue.asJsonObject().getString("name"));
                product.setId(jsonValue.asJsonObject().getInt("id"));
                product.setType(jsonValue.asJsonObject().getString("type"));
                product.setPrice(Double.parseDouble(jsonValue.asJsonObject().getString("price")));
                product.setAmount(0);
                product.setInventory(jsonValue.asJsonObject().getInt("inventory"));
                product.setImage(jsonValue.asJsonObject().getString("image"));
                productMap.put(product.getId(), product);
            }

            return productMap;
        } catch (FileNotFoundException e) {
            throw e;
        }
    }

    /**
     * Metodo que sobreescribe el JSON con información actualizada
     * 
     * @param path        La ubicación del archivo JSON
     *
     * @param productList El TreeMap que contiene los productos del inventario
     * 
     * @throws FileNotFoundException En caso de que el codigo no encuentre el
     *                               archivo
     */
    public void writeJSON(String path, TreeMap<Integer, Product> productList) throws FileNotFoundException {
        try {
            JsonObjectBuilder inventoryBuilder = Json.createObjectBuilder();
            JsonArrayBuilder productArrayBuilder = Json.createArrayBuilder();

            for (Product product : productList.values()) {
                JsonObjectBuilder productBuilder = Json.createObjectBuilder()
                        .add("name", product.getName())
                        .add("price", String.valueOf(product.getPrice()))
                        .add("type", product.getType())
                        .add("id", product.getId())
                        .add("inventory", product.getInventory())
                        .add("image", product.getImage());
                productArrayBuilder.add(productBuilder);
            }

            inventoryBuilder.add("Products", productArrayBuilder);

            JsonObject libraryInJSON = inventoryBuilder.build();
            OutputStream outSt = new FileOutputStream(path);

            JsonWriter jsonWriter = Json.createWriter(outSt);

            jsonWriter.writeObject(libraryInJSON);
            jsonWriter.close();

        } catch (FileNotFoundException e) {
            throw e;
        }

    }
}
