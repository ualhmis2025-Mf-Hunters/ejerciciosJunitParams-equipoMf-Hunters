package ual.hmis.sesion05.ejercicio5;

import java.io.*;
import java.util.*;

public class ContadorDePalabras {
    String path; 
    private Map<String, Integer> contador;
   

    public ContadorDePalabras(String path) {
        contador = new HashMap<>();
        this.path = path;
    }

    public void contarPalabras() throws IOException {
        File archivo = new File(path);


        try (BufferedReader br = new BufferedReader(new FileReader(archivo))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                String[] palabras = linea.split("\\s+");
                for (String palabra : palabras) {
                    palabra = palabra.toLowerCase().replaceAll("[^a-zA-Záéíóúüñ]", "");
                    if (!palabra.isEmpty()) {
                        contador.put(palabra, contador.getOrDefault(palabra, 0) + 1);
                    }
                }
            }
        }
    }

    public List<String> obtenerPalabrasOrdenadas() {
        List<String> listaPalabras = new ArrayList<>(contador.keySet());
        Collections.sort(listaPalabras);
        return listaPalabras;
    }

    public List<String> obtenerPalabrasPorFrecuencia() {
        List<Map.Entry<String, Integer>> lista = new ArrayList<>(contador.entrySet());
        lista.sort((entry1, entry2) -> entry2.getValue().compareTo(entry1.getValue()));
        List<String> resultado = new ArrayList<>();
        for (Map.Entry<String, Integer> entry : lista) {
            resultado.add(entry.getKey());
        }
        return resultado;
    }

    public static void main(String[] args) {
        String path;
        if (args.length > 0) {
            path = args[0]; // usar argumento si se proporciona
        } else {
            path = "./sesion05Junit/test/ual/hmis/sesion05/ejercicio5/entrada.txt"; // por defecto
        }
    
        try {
            ContadorDePalabras contadorDePalabras = new ContadorDePalabras(path);
    
            contadorDePalabras.contarPalabras();
    
            System.out.println("Palabras ordenadas alfabéticamente:");
            List<String> palabrasOrdenadas = contadorDePalabras.obtenerPalabrasOrdenadas();
            for (String palabra : palabrasOrdenadas) {
                System.out.println(palabra);
            }
    
            System.out.println("\nPalabras ordenadas por frecuencia:");
            List<String> palabrasPorFrecuencia = contadorDePalabras.obtenerPalabrasPorFrecuencia();
            for (String palabra : palabrasPorFrecuencia) {
                System.out.println(palabra);
            }
    
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
