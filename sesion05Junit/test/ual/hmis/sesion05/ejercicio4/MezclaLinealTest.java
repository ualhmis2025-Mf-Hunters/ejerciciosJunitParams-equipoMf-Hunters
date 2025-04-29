package ual.hmis.sesion05.ejercicio4;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.*;
import org.junit.jupiter.params.provider.*;
import java.util.*;
import java.util.stream.*;

class MezclaLinealTest {

    @BeforeEach
    void setUp() {
        new MezclaLineal(); // Cubre el constructor
    }

    private List<Integer> crearLista(String numeros) {
        if (numeros == null || numeros.trim().isEmpty()) {
            return Collections.emptyList();
        }
        return Arrays.stream(numeros.split(","))
                   .map(String::trim)
                   .map(Integer::parseInt)
                   .collect(Collectors.toList());
    }

    @ParameterizedTest(name = "Test {index}: mezclar({0}, {1}) = {2}")
    @CsvSource({
        // listaA, listaB, resultadoEsperado
        "'', '', ''",                       // Ambas vacías
        "'', '1,2,3', '1,2,3'",            // A vacía
        "'1,2,3', '', '1,2,3'",            // B vacía
        "null, '1,2,3', '1,2,3'",          // A nula
        "'1,2,3', null, '1,2,3'",          // B nula
        "'1,3,5', '2,4,6', '1,2,3,4,5,6'", // Sin duplicados
        "'1,2,2', '2,3,3', '1,2,2,2,3,3'", // Con duplicados
        "'1,1,1', '1,1,1', '1,1,1,1,1,1'", // Todos iguales
        "'10', '5,15', '5,10,15'"           // Tamaños diferentes
    })
    void testMezclaParametrizado(String inputA, String inputB, String expected) {
        List<Integer> listaA = inputA == null ? null : crearLista(inputA);
        List<Integer> listaB = inputB == null ? null : crearLista(inputB);
        List<Integer> esperado = crearLista(expected);

        List<Integer> resultado = MezclaLineal.mezclar(listaA, listaB);

        assertEquals(esperado, resultado, 
            String.format("Fallo al mezclar %s con %s", listaA, listaB));
    }

    @Test
    void testConstructor() {
        assertDoesNotThrow(() -> new MezclaLineal());
    }

    @Test
    void testMezclaStrings() {
        List<String> a = Arrays.asList("a", "c", "e");
        List<String> b = Arrays.asList("b", "d", "f");
        List<String> esperado = Arrays.asList("a", "b", "c", "d", "e", "f");
        
        assertEquals(esperado, MezclaLineal.mezclar(a, b));
    }
}