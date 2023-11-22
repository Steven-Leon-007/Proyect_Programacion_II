/** 
* Class User
*contiene informacion sobre los tipos de usuarios a ser herados de la misma
*
*@author Steven Leon
*@author Ricardo Forero
*@version 1.0
*
*/
package model;

public class User {
    private String name;
    private int document;
    private String password;

    public User() {
    }
/**
 * constructor con 3 parametros
 * @param name el nombre del usuario 
 * @param document el documento del usuario 
 * @param password la clave del usuario 
 */
    public User(String name, int document, String password) {
        this.name = name;
        this.document = document;
        this.password = password;
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

    public int getDocument() {
        return document;
    }

    public void setDocument(int document) {
        this.document = document;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}
