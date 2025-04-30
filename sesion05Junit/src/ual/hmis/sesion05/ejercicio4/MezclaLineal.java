package ual.hmis.sesion05.ejercicio4;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MezclaLineal {
    
    // Constructor explícito para cobertura
    public MezclaLineal() {
        // Constructor vacío
    }
    
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