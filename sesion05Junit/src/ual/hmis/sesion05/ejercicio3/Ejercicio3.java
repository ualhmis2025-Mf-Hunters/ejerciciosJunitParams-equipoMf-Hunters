package ual.hmis.sesion05.ejercicio3;

/**
 * Clase que contiene un método para enmascarar una contraseña según su longitud.
 */
public class Ejercicio3 {
    
    /**
     * Constructor de la clase Ejercicio3.
     * 
     * Este constructor no realiza ninguna acción, pero se incluye para
     * permitir la creación de instancias de la clase.
     */
    public Ejercicio3() {
        // Constructor vacío
    }

    /**
     * Enmascara una contrasena segun su longitud:
     * Rama 1: null: "password demasiado corto"
     * Rama 2: length menor que 5: "password demasiado corto"
     * Rama 3: 5 menor o igual que length menor o igual que 8: "********"
     * Rama 4: 9 menor o igual que length menor o igual que 40: "************"
     * Rama 5: length mayor que 40: "password demasiado largo"
     * @param password la contrasena a enmascarar
     * @return una cadena que enmascara la contrase a
     */
    public String enmascararPassword(String password) {
        // Rama 1: null check
        if (password == null) {
            return "password demasiado corto";
        }
        
        int length = password.length();
        
        // Rama 2: length < 5
        if (length < 5) {
            return "password demasiado corto";
        } 
        // Rama 3: 5 <= length <= 8
        else if (length <= 8) {
            return "********";
        } 
        // Rama 4: 9 <= length <= 40
        else if (length <= 40) {
            return "************";
        } 
        // Rama 5: length > 40
        else {
            return "password demasiado largo";
        }
    }
}