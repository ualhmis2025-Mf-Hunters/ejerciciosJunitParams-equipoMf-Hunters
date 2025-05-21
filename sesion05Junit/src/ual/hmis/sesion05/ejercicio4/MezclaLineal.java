package ual.hmis.sesion05.ejercicio4;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Clase que mezcla dos listas ordenadas en una sola lista ordenada.
 * 
 * Esta clase contiene un método estático para mezclar dos listas de elementos
 * que implementan la interfaz Comparable. El resultado es una nueva lista
 * ordenada que contiene todos los elementos de ambas listas.
 */
public class MezclaLineal {
    
    /**
     * Constructor de la clase MezclaLineal.
     * 
     * Este constructor no realiza ninguna acción, pero se incluye para
     * permitir la creación de instancias de la clase.
     */
    public MezclaLineal() {
        // Constructor vacío
    }
    
    /**
     * Mezcla dos listas ordenadas en una sola lista ordenada.
     *
     * @param lista1 La primera lista a mezclar.
     * @param lista2 La segunda lista a mezclar.
     * @param <T> El tipo de los elementos en las listas, que debe implementar la interfaz Comparable.
     * @return La lista resultante de la mezcla.
     */
    public static <T extends Comparable<T>> List<T> mezclar(List<T> lista1, List<T> lista2) {
        List<T> resultado = new ArrayList<>();
        int i = 0, j = 0;
        
        // Manejo de listas nulas
        lista1 = lista1 == null ? Collections.emptyList() : lista1;
        lista2 = lista2 == null ? Collections.emptyList() : lista2;

        // Mezcla mientras ambas listas tengan elementos
        while (i < lista1.size() && j < lista2.size()) {
            T elem1 = lista1.get(i);
            T elem2 = lista2.get(j);
            
            if (elem1.compareTo(elem2) < 0) {
                resultado.add(elem1);
                i++;
            } else if (elem1.compareTo(elem2) > 0) {
                resultado.add(elem2);
                j++;
            } else { // Elementos iguales
                resultado.add(elem1);
                resultado.add(elem2);
                i++;
                j++;
            }
        }

        // Añadir elementos restantes
        while (i < lista1.size()) resultado.add(lista1.get(i++));
        while (j < lista2.size()) resultado.add(lista2.get(j++));

        return resultado;
    }
}