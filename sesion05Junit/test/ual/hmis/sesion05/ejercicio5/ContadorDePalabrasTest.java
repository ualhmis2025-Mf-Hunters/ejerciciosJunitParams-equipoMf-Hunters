package ual.hmis.sesion05.ejercicio5;

import ual.hmis.sesion05.ejercicio5.ContadorDePalabras;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class ContadorDePalabrasTest {

    @BeforeAll
    static void setup() throws IOException {
        Files.write(Paths.get("./test/ual/hmis/sesion05/ejercicio5/entrada.txt"), "Hola mundo hola prueba prueba prueba".getBytes());
    }

    @Test
    @DisplayName("Verifica palabras ordenadas alfabéticamente con repeticiones")
    void testPalabrasOrdenAlfabetico() throws IOException {
    Files.write(Paths.get("./test/ual/hmis/sesion05/ejercicio5/entrada.txt"),
                "Hola mundo hola prueba prueba prueba".getBytes());

    ContadorDePalabras contador = new ContadorDePalabras();
    List<String> esperado = List.of("hola", "hola", "mundo", "prueba", "prueba", "prueba");
    assertEquals(esperado, contador.getPalabrasOrdenAlfabetico());
}

   @Test
@DisplayName("Verifica palabras ordenadas por frecuencia sin repeticiones")
void testPalabrasPorFrecuencia() throws IOException {
    Files.write(Paths.get("./test/ual/hmis/sesion05/ejercicio5/entrada.txt"),
                "Hola mundo hola prueba prueba prueba".getBytes());

    ContadorDePalabras contador = new ContadorDePalabras();
    List<String> esperado = List.of("prueba", "hola", "mundo");
    assertEquals(esperado, contador.getPalabrasPorFrecuencia());
}

    static Stream<String> entradasLimite() {
        return Stream.of("", " ", "a", "b b b a");
    }

    @ParameterizedTest
@MethodSource("entradasLimite")
@DisplayName("Test de valores límite con entradas personalizadas")
void testValoresLimite(String contenido) throws IOException {
    var path = Paths.get("./test/ual/hmis/sesion05/ejercicio5/entrada.txt");

    byte[] contenidoOriginal = Files.readAllBytes(path);

    try {
        Files.write(path, contenido.getBytes());
        ContadorDePalabras contador = new ContadorDePalabras();
        List<String> palabras = contador.getPalabrasOrdenAlfabetico();
        assertNotNull(palabras);

    } finally {
        Files.write(path, contenidoOriginal);
    }
}

    @ParameterizedTest
@MethodSource("entradasParaMain")
@DisplayName("Cobertura del método main con diferentes entradas")
void testMainConParametros(String contenido, List<String> palabrasEsperadas) throws IOException {
    Files.write(Paths.get("./test/ual/hmis/sesion05/ejercicio5/entrada.txt"), contenido.getBytes());

    java.io.ByteArrayOutputStream outContent = new java.io.ByteArrayOutputStream();
    System.setOut(new java.io.PrintStream(outContent));

    ContadorDePalabras.main(new String[]{});

    System.setOut(System.out);

    String salida = outContent.toString().toLowerCase();
    assertTrue(salida.contains("=== palabras en orden alfabético ==="));
    assertTrue(salida.contains("=== palabras por frecuencia ==="));
    for (String palabra : palabrasEsperadas) {
        assertTrue(salida.contains(palabra), "No se encontró la palabra esperada: " + palabra);
    }
}

static Stream<Arguments> entradasParaMain() {
    return Stream.of(
        Arguments.of("Hola mundo hola prueba prueba prueba", List.of("hola", "mundo", "prueba")),
        Arguments.of("uno dos dos tres tres tres", List.of("uno", "dos", "tres")),
        Arguments.of("A a A a b", List.of("a", "b")),
        Arguments.of("", List.of())
    );
}

@Test
@DisplayName("Cobertura del bloque catch en main por archivo inexistente")
void testMainConArchivoInexistente() throws IOException {
    Files.deleteIfExists(Paths.get("./test/ual/hmis/sesion05/ejercicio5/entrada.txt"));

    java.io.ByteArrayOutputStream errContent = new java.io.ByteArrayOutputStream();
    System.setErr(new java.io.PrintStream(errContent));

    ContadorDePalabras.main(new String[]{});

    System.setErr(System.err);

    String salidaError = errContent.toString().toLowerCase();
    assertTrue(salidaError.contains("error al leer el archivo"), "No se detectó la impresión del error.");
}

}
