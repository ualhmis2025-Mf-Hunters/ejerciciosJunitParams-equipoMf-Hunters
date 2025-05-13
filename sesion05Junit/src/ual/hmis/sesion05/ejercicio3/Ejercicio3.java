package ual.hmis.sesion05.ejercicio3;

public class Ejercicio3 {
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