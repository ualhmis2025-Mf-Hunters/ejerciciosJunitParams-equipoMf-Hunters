package ual.hmis.sesion05.ejercicio5;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

public class ContadorDePalabrasTest {

    @TempDir
    File tempDir;

    /**
     * Crea un archivo temporal en el directorio temporal con el contenido
     * especificado y devuelve el archivo creado.
     *
     * @param contenido el contenido del archivo a crear
     * @return el archivo creado
     * @throws IOException si hay un error al crear el archivo
     */
    private File crearArchivoTemporal(String contenido) throws IOException {
        File tempFile = new File(tempDir, "temp_test.txt");
        try (FileWriter writer = new FileWriter(tempFile)) {
            writer.write(contenido);
        }
        return tempFile;
    }
    
    /**
     * Prueba el método contarPalabras de la clase ContadorDePalabras.
     * 
     * Crea un archivo temporal con el contenido especificado y verifica que el 
     * número de palabras en el archivo coincide con el esperado. También comprueba 
     * que las palabras están ordenadas alfabéticamente y por frecuencia de 
     * aparición según lo indicado.
     * 
     * @param contenido el contenido del archivo a crear
     * @param numPalabras el número esperado de palabras únicas
     * @param p1Ordenada la primera palabra esperada en orden alfabético
     * @param p2Ordenada la segunda palabra esperada en orden alfabético
     * @param p3Ordenada la tercera palabra esperada en orden alfabético
     * @param p1Frecuencia la primera palabra esperada en orden de frecuencia
     * @param p2Frecuencia la segunda palabra esperada en orden de frecuencia
     * @param p3Frecuencia la tercera palabra esperada en orden de frecuencia
     * @throws IOException si hay un error al crear o leer el archivo
     */
    @ParameterizedTest
    @CsvSource({
            "Hola mundo hola Adiós mundo, 3, adiós, hola, mundo, mundo, hola, adiós"
    })
    public void testContarPalabras(String contenido, int numPalabras, String p1Ordenada, String p2Ordenada, String p3Ordenada, String p1Frecuencia, String p2Frecuencia, String p3Frecuencia) throws IOException {
        File tempFile = crearArchivoTemporal(contenido);
        ContadorDePalabras contador = new ContadorDePalabras(tempFile.getAbsolutePath());
        contador.contarPalabras();

        List<String> palabrasOrdenadas = contador.obtenerPalabrasOrdenadas();
        System.out.println("Palabras Ordenadas Actual: " + palabrasOrdenadas);
        assertEquals(numPalabras, palabrasOrdenadas.size());
        assertEquals(Arrays.asList(p1Ordenada, p2Ordenada, p3Ordenada), palabrasOrdenadas);

        List<String> palabrasPorFrecuencia = contador.obtenerPalabrasPorFrecuencia();
        assertEquals(Arrays.asList(p1Frecuencia, p2Frecuencia, p3Frecuencia), palabrasPorFrecuencia);
    }

    /**
     * Comprueba que el m todo obtenerPalabrasOrdenadas no devuelva palabras
     * cuando el archivo este vacio.
     * @param contenido el contenido del archivo a crear
     * @throws IOException si hay un error al crear o leer el archivo
     */
    @ParameterizedTest
    @ValueSource(strings = {"", " ", "\n", "\t"})
    public void testObtenerPalabrasOrdenadasVacio(String contenido) throws IOException {
        File tempFile = crearArchivoTemporal(contenido);
        ContadorDePalabras contador = new ContadorDePalabras(tempFile.getAbsolutePath());
        contador.contarPalabras();
        List<String> palabrasOrdenadas = contador.obtenerPalabrasOrdenadas();
        assertTrue(palabrasOrdenadas.isEmpty());
    }

    /**
     * Comprueba que el metodo obtenerPalabrasPorFrecuencia no devuelva palabras
     * cuando el archivo esta vacio.
     * @param contenido el contenido del archivo a crear
     * @throws IOException si hay un error al crear o leer el archivo
     */
    @ParameterizedTest
    @ValueSource(strings = {"", " ", "\n", "\t"})
    public void testObtenerPalabrasPorFrecuenciaVacio(String contenido) throws IOException {
        File tempFile = crearArchivoTemporal(contenido);
        ContadorDePalabras contador = new ContadorDePalabras(tempFile.getAbsolutePath());
        contador.contarPalabras();
        List<String> palabrasPorFrecuencia = contador.obtenerPalabrasPorFrecuencia();
        assertTrue(palabrasPorFrecuencia.isEmpty());
    }

    /**
     * Prueba que el metodo main de la clase ContadorDePalabras se ejecute
     * correctamente con un argumento. El argumento es el path de un archivo
     * temporal que se crea previamente en el directorio temporal.
     * @throws IOException si hay un error al crear o leer el archivo
     */
    @Test
    public void testMainConArgumento() throws IOException {
        File tempFile = crearArchivoTemporal("test content");
        String[] args = {tempFile.getAbsolutePath()};
        ContadorDePalabras.main(args);
    }
    /**
     * Prueba que el metodo main de la clase ContadorDePalabras se ejecute
     * correctamente sin argumentos. Se espera que no se produzca ninguna
     * excepción.
     * @throws IOException si hay un error al crear o leer el archivo
     */
    @Test
    public void testMainSinArgumento() throws IOException {
        String[] args = {};
        ContadorDePalabras.main(args);
    }

    /**
     * Prueba que el metodo main de la clase ContadorDePalabras se ejecute
     * correctamente con un argumento vacio. Se espera que no se produzca
     * ninguna excepción.
     * @throws IOException si hay un error al crear o leer el archivo
     */
    @ParameterizedTest
    @ValueSource(strings = {"", " "})
    public void testMainConError(String arg) {
        ByteArrayOutputStream errContent = new ByteArrayOutputStream();
        PrintStream originalErr = System.err;
        System.setErr(new PrintStream(errContent));
        try {
            ContadorDePalabras.main(new String[]{arg});
            String errorOutput = errContent.toString();
            assertFalse(errorOutput.isEmpty());
        } finally {
            System.setErr(originalErr);
        }
    }

    /**
     * Prueba que el metodo contarPalabras de la clase ContadorDePalabras
     * no devuelva palabras cuando el archivo contiene solo simbolos no
     * alfabeticos.
     * @param contenido el contenido del archivo a crear
     * @throws IOException si hay un error al crear o leer el archivo
     */
    @ParameterizedTest
    @ValueSource(strings = {"123 !!! ??? ###", "!@# $ % ^ & * ()"})
    public void testContarPalabrasConSimbolosNoAlfabeticos(String contenido) throws IOException {
        File tempFile = crearArchivoTemporal(contenido);
        ContadorDePalabras contador = new ContadorDePalabras(tempFile.getAbsolutePath());
        contador.contarPalabras();
        List<String> palabrasOrdenadas = contador.obtenerPalabrasOrdenadas();
        assertTrue(palabrasOrdenadas.isEmpty());
    }

    /**
     * Prueba que el metodo contarPalabras de la clase ContadorDePalabras
     * no devuelva palabras cuando el archivo contiene solo simbolos no
     * alfabeticos.
     * @param contenido el contenido del archivo a crear
     * @throws IOException si hay un error al crear o leer el archivo
     */
    @Test
    public void testContadorDePalabras_Constructor_IOException() throws IOException {
        String nonExistentPath = new File(tempDir, "no_existe.txt").getAbsolutePath();
        try {
            new ContadorDePalabras(nonExistentPath);
        } catch (RuntimeException e) {
            assertTrue(e.getCause() instanceof FileNotFoundException);
        }
    }
}