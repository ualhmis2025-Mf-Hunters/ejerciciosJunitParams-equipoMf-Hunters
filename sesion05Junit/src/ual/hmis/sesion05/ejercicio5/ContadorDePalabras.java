package ual.hmis.sesion05.ejercicio5;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Clase que cuenta el número de veces que aparece cada palabra en un archivo de texto.
 * Las palabras se consideran iguales si son iguales sin tener en cuenta mayúsculas y minúsculas.
 * Se ignoran los signos de puntuación y los números.
 */
public class ContadorDePalabras {
    /**
     * Ruta del archivo a leer
     */
    String path; 
    /**
     * Mapa que almacena las palabras y su frecuencia
     */
    private final Map<String, Integer> contador;
   
    /**
     * Constructor de la clase ContadorDePalabras.
     * @param path ruta del archivo a leer
     */
    public ContadorDePalabras(String path) {
        contador = new HashMap<>();
        this.path = path;
    }

    /**
     * Lee un archivo de texto y cuenta el n mero de veces que se
     * repite cada palabra en el mismo. El archivo se lee desde el
     * fichero cuyo path se pasa al constructor.
     *
     * @throws IOException si hay un error al leer el archivo
     */
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

    /**
     * Devuelve una lista de las palabras encontradas en el archivo,
     * ordenadas alfabeticamente.
     * @return lista de palabras ordenadas
     */
    public List<String> obtenerPalabrasOrdenadas() {
        List<String> listaPalabras = new ArrayList<>(contador.keySet());
        Collections.sort(listaPalabras);
        return listaPalabras;
    }

    /**
     * Devuelve una lista de las palabras encontradas en el archivo,
     * ordenadas por su frecuencia de aparici n (m s frecuentes primero).
     * @return lista de palabras ordenadas por frecuencia
     */
    public List<String> obtenerPalabrasPorFrecuencia() {
        List<Map.Entry<String, Integer>> lista = new ArrayList<>(contador.entrySet());
        lista.sort((entry1, entry2) -> entry2.getValue().compareTo(entry1.getValue()));
        List<String> resultado = new ArrayList<>();
        for (Map.Entry<String, Integer> entry : lista) {
            resultado.add(entry.getKey());
        }
        return resultado;
    }

    /**
     * Método principal para ejecutar el programa.
     * @param args argumentos de línea de comandos
     */
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
