package ual.hmis.sesion05.ejercicio5;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ContadorDePalabras {

    private List<String> palabras;

    public ContadorDePalabras(String archivo) throws IOException {
        palabras = new ArrayList<>();
        leerArchivo(archivo);
    }

    private void leerArchivo(String archivo) throws IOException {
        try (InputStream is = getClass().getResourceAsStream(archivo);
             BufferedReader br = new BufferedReader(new InputStreamReader(is))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                String[] palabrasLinea = linea.split("\\s+");
                for (String palabra : palabrasLinea) {
                    if (!palabra.isEmpty()) {
                        palabras.add(palabra.toLowerCase());
                    }
                }
            }
        }
    }

    public List<String> palabrasOrdenAlfabetico() {
        List<String> copia = new ArrayList<>(palabras);
        Collections.sort(copia);
        return copia;
    }

    public List<String> palabrasOrdenOcurrencias() {
        Map<String, Integer> frecuencia = new HashMap<>();
        for (String palabra : palabras) {
            frecuencia.put(palabra, frecuencia.getOrDefault(palabra, 0) + 1);
        }

        List<Map.Entry<String, Integer>> entradas = new ArrayList<>(frecuencia.entrySet());
        entradas.sort((a, b) -> b.getValue().compareTo(a.getValue()));

        List<String> resultado = new ArrayList<>();
        for (Map.Entry<String, Integer> entrada : entradas) {
            resultado.add(entrada.getKey());
        }

        return resultado;
    }
}