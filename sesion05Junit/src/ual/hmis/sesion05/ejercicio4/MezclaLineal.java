package ual.hmis.sesion05.ejercicio4;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MezclaLineal {

    public static <T extends Comparable<T>> List<T> mezclarConjuntosOrdenados(List<T> listaA, List<T> listaB) {
        // Validación de parámetros nulos
        listaA = listaA == null ? Collections.emptyList() : listaA;
        listaB = listaB == null ? Collections.emptyList() : listaB;
        
        List<T> resultado = new ArrayList<>();
        int i = 0, j = 0;

        while (i < listaA.size() && j < listaB.size()) {
            T elementoA = listaA.get(i);
            T elementoB = listaB.get(j);
            int comparacion = elementoA.compareTo(elementoB);

            if (comparacion < 0) {
                resultado.add(elementoA);
                i++;
            } else if (comparacion > 0) {
                resultado.add(elementoB);
                j++;
            } else {
                resultado.add(elementoA);
                i++;
                j++;
            }
        }

        // Añadir elementos restantes
        agregarRestantes(listaA, resultado, i);
        agregarRestantes(listaB, resultado, j);

        return resultado;
    }

    private static <T> void agregarRestantes(List<T> lista, List<T> resultado, int indice) {
        while (indice < lista.size()) {
            resultado.add(lista.get(indice));
            indice++;
        }
    }
}