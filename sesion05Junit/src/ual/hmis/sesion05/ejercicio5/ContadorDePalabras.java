package ual.hmis.sesion05.ejercicio5;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Clase que cuenta las palabras de un archivo de texto y las ordena alfabéticamente o por frecuencia.
 */
public class ContadorDePalabras {

    /**
     * Lista de palabras leídas del archivo.
     */
    private final List<String> palabras;

    /**
     * Constructor que inicializa la lista de palabras leyendo el contenido de un archivo.
     *
     * @throws IOException si ocurre un error al leer el archivo.
     */
    public ContadorDePalabras() throws IOException {
        String contenido = new String(Files.readAllBytes(Paths.get("./test/ual/hmis/sesion05/ejercicio5/entrada.txt")));
        palabras = Arrays.stream(contenido.toLowerCase().split("\\W+"))
                         .filter(p -> !p.isBlank())
                         .collect(Collectors.toList());
    }

    /**
     * Devuelve una lista de palabras ordenadas alfabeticamente.
     *
     * La lista devuelve contiene las mismas palabras que la lista original, pero
     * ordenadas alfabeticamente.
     *
     * @return lista de palabras ordenadas alfabeticamente
     */
    public List<String> getPalabrasOrdenAlfabetico() {
        List<String> ordenadas = new ArrayList<>(palabras);
        Collections.sort(ordenadas);
        return ordenadas;
    }

    /**
     * Devuelve una lista de palabras ordenadas por su frecuencia de aparicion.
     *
     * La lista devuelve contiene las mismas palabras que la lista original, pero
     * ordenadas por su frecuencia de aparicion. Las palabras m s frecuentes se
     * encuentran al principio de la lista.
     *
     * @return lista de palabras ordenadas por su frecuencia
     */
    public List<String> getPalabrasPorFrecuencia() {
        Map<String, Long> frecuencia = palabras.stream()
                .collect(Collectors.groupingBy(p -> p, Collectors.counting()));

        return frecuencia.entrySet()
                .stream()
                .sorted((e1, e2) -> Long.compare(e2.getValue(), e1.getValue()))
                .map(Map.Entry::getKey)
                .collect(Collectors.toList());
    }

    /**
     * Método principal para ejecutar la clase y mostrar las palabras ordenadas.
     *
     * @param args argumentos de línea de comandos (no se utilizan).
     */
    public static void main(String[] args) {
        try {
            ContadorDePalabras contador = new ContadorDePalabras();

            System.out.println("=== Palabras en orden alfabético ===");
            contador.getPalabrasOrdenAlfabetico().forEach(System.out::println);

            System.out.println("\n=== Palabras por frecuencia ===");
            contador.getPalabrasPorFrecuencia().forEach(System.out::println);

        } catch (IOException e) {
            System.err.println("Error al leer el archivo: " + e.getMessage());
        }
    }
}
