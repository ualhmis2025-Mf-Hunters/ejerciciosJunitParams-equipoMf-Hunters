package ual.hmis.sesion05.ejercicio5;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import java.io.*;
import java.util.List;
import java.util.Arrays;


public class ContadorDePalabrasTest {

    @Test
    public void testContarPalabras() throws IOException {
        File tempFile = crearArchivoTemporal("Hola mundo hola\nAdiós mundo");
        ContadorDePalabras contador = new ContadorDePalabras(tempFile.getAbsolutePath());
        contador.contarPalabras();
    
        List<String> palabrasOrdenadas = contador.obtenerPalabrasOrdenadas();
        assertEquals(3, palabrasOrdenadas.size());
        assertEquals(Arrays.asList("adiós", "hola", "mundo"), palabrasOrdenadas);
    
        List<String> palabrasPorFrecuencia = contador.obtenerPalabrasPorFrecuencia();
        assertEquals(Arrays.asList("mundo", "hola", "adiós"), palabrasPorFrecuencia);
    }
    

    @Test
    public void testObtenerPalabrasOrdenadasVacio() throws IOException {
        File tempFile = crearArchivoTemporal("");
        ContadorDePalabras contador = new ContadorDePalabras(tempFile.getAbsolutePath());
        contador.contarPalabras();
        List<String> palabrasOrdenadas = contador.obtenerPalabrasOrdenadas();
        assertTrue(palabrasOrdenadas.isEmpty());
    }

    @Test
    public void testObtenerPalabrasPorFrecuenciaVacio() throws IOException {
        File tempFile = crearArchivoTemporal("");
        ContadorDePalabras contador = new ContadorDePalabras(tempFile.getAbsolutePath());
        contador.contarPalabras();
        List<String> palabrasPorFrecuencia = contador.obtenerPalabrasPorFrecuencia();
        assertTrue(palabrasPorFrecuencia.isEmpty());
    }

    @Test
    public void testMain() {
        File tempFile = null;
        try {
            tempFile = File.createTempFile("testMain", ".txt");
            try (FileWriter writer = new FileWriter(tempFile)) {
                writer.write("hola mundo\nadios mundo hola");
            }

            ByteArrayOutputStream outContent = new ByteArrayOutputStream();
            PrintStream originalOut = System.out;
            System.setOut(new PrintStream(outContent));

            try {
                ContadorDePalabras.main(new String[]{tempFile.getAbsolutePath()});

                String output = outContent.toString();
                assertTrue(output.contains("Palabras ordenadas alfabéticamente:"));
                assertTrue(output.contains("Palabras ordenadas por frecuencia:"));
                assertTrue(output.contains("hola"));
                assertTrue(output.contains("mundo"));
                assertTrue(output.contains("adios"));
                
            } finally {
                System.setOut(originalOut);
            }
        } catch (IOException e) {
            fail("Error al crear archivo temporal: " + e.getMessage());
        } finally {
            if (tempFile != null) tempFile.delete();
        }
    }

    @Test
    public void testMainConError() {
        ByteArrayOutputStream errContent = new ByteArrayOutputStream();
        PrintStream originalErr = System.err;
        System.setErr(new PrintStream(errContent));

        try {
            ContadorDePalabras.main(new String[]{});

            String errorOutput = errContent.toString();
            assertFalse(errorOutput.isEmpty());
        } finally {
            System.setErr(originalErr);
        }
    }

    private File crearArchivoTemporal(String contenido) throws IOException {
        File tempFile = File.createTempFile("temp_test", ".txt");
        try (FileWriter writer = new FileWriter(tempFile)) {
            writer.write(contenido);
        }
        tempFile.deleteOnExit();
        return tempFile;
    }


    @Test
    public void testContarPalabrasConSimbolosNoAlfabeticos() throws IOException {
        File tempFile = crearArchivoTemporal("123 !!! ??? ###");
        ContadorDePalabras contador = new ContadorDePalabras(tempFile.getAbsolutePath());
        contador.contarPalabras();
    
        List<String> palabrasOrdenadas = contador.obtenerPalabrasOrdenadas();
        assertTrue(palabrasOrdenadas.isEmpty());
    }

}
