package ual.hmis.sesion05.ejercicio4;

import java.util.ArrayList;
import java.util.List;

public class MezclaLineal {
    public <T extends Comparable<T>> List<T> mezclar(List<T> listaA, List<T> listaB) {
        List<T> resultado = new ArrayList<>();
        if (listaA == null) listaA = new ArrayList<>();
        if (listaB == null) listaB = new ArrayList<>();

        int i = 0, j = 0;
        while (i < listaA.size() && j < listaB.size()) {
            int cmp = listaA.get(i).compareTo(listaB.get(j));
            if (cmp < 0) {
                agregarSinDuplicados(resultado, listaA.get(i++));
            } else if (cmp > 0) {
                agregarSinDuplicados(resultado, listaB.get(j++));
            } else {
                agregarSinDuplicados(resultado, listaA.get(i++));
                j++;
            }
        }
        while (i < listaA.size()) agregarSinDuplicados(resultado, listaA.get(i++));
        while (j < listaB.size()) agregarSinDuplicados(resultado, listaB.get(j++));
        return resultado;
    }

    private <T> void agregarSinDuplicados(List<T> lista, T elemento) {
        if (lista.isEmpty() || !lista.get(lista.size() - 1).equals(elemento)) {
            lista.add(elemento);
        }
    }
}