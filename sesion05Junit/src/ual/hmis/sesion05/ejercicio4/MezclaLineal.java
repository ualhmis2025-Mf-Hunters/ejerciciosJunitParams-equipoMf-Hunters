package ual.hmis.sesion05.ejercicio4;

import java.util.*;

public class MezclaLineal {
    
    public MezclaLineal() {
        // Constructor vacío necesario para cobertura
    }
    
    public static <T extends Comparable<T>> List<T> mezclar(List<T> a, List<T> b) {
        List<T> resultado = new ArrayList<>();
        int i = 0, j = 0;
        
        a = a == null ? Collections.emptyList() : a;
        b = b == null ? Collections.emptyList() : b;

        while (i < a.size() && j < b.size()) {
            T elemA = a.get(i);
            T elemB = b.get(j);
            
            if (elemA.compareTo(elemB) < 0) {
                resultado.add(elemA);
                i++;
            } else if (elemA.compareTo(elemB) > 0) {
                resultado.add(elemB);
                j++;
            } else {
                resultado.add(elemA);
                i++;
                j++;
            }
        }

        while (i < a.size()) resultado.add(a.get(i++));
        while (j < b.size()) resultado.add(b.get(j++));

        return resultado;
    }
}