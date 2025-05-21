package ual.hmis.sesion05.ejercicio5;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;

public class ContadorDePalabras {

    private final List<String> palabras;

    public ContadorDePalabras() throws IOException {
        String contenido = new String(Files.readAllBytes(Paths.get("./test/ual/hmis/sesion05/ejercicio5/entrada.txt")));
        palabras = Arrays.stream(contenido.toLowerCase().split("\\W+"))
                         .filter(p -> !p.isBlank())
                         .collect(Collectors.toList());
    }

    public List<String> getPalabrasOrdenAlfabetico() {
        List<String> ordenadas = new ArrayList<>(palabras);
        Collections.sort(ordenadas);
        return ordenadas;
    }

    public List<String> getPalabrasPorFrecuencia() {
        Map<String, Long> frecuencia = palabras.stream()
                .collect(Collectors.groupingBy(p -> p, Collectors.counting()));

        return frecuencia.entrySet()
                .stream()
                .sorted((e1, e2) -> Long.compare(e2.getValue(), e1.getValue()))
                .map(Map.Entry::getKey)
                .collect(Collectors.toList());
    }

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
