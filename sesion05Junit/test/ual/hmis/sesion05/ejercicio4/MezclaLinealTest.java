package ual.hmis.sesion05.ejercicio4;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class MezclaLinealTest {

    /**
     * Testea que el constructor de la clase no lance excepciones
     */
    @Test
    void testConstructor() {
        assertDoesNotThrow(() -> new MezclaLineal());
    }

    /**
     * Testea que el metodo mezclar() de la clase MezclaLineal
     * devuelva la lista esperada al mezclar las dos listas dadas.
     * El metodo prueba diferentes combinaciones de listas nulas,
     * vacias y con elementos duplicados.
     */
    @ParameterizedTest(name = "Test {index}: mezclar({0}, {1}) = {2}")
    @MethodSource("proveedorDatosMezcla")
    void testMezclaParametrizado(List<Integer> lista1, List<Integer> lista2, List<Integer> esperado) {
        List<Integer> resultado = MezclaLineal.mezclar(lista1, lista2);
        assertEquals(esperado, resultado);
    }

    /**
     * Proveedor de datos para el test parametrizado testMezclaParametrizado.
     * El proveedor devuelve un stream de argumentos que se corresponden con:
     * - lista1: la primera lista a mezclar
     * - lista2: la segunda lista a mezclar
     * - esperado: la lista esperada al mezclar las dos listas
     * 
     * El proveedor incluye casos con:
     * - listas nulas
     * - listas vacías
     * - listas sin elementos duplicados
     * - listas con elementos duplicados
     * - listas con todos elementos iguales
     * - listas de diferente tamaño
     * - listas con un solo elemento en ambas listas
     */
    private static Stream<Arguments> proveedorDatosMezcla() {
        return Stream.of(
            // Casos con listas nulas
            Arguments.of(null, null, Collections.emptyList()),
            Arguments.of(null, Arrays.asList(1, 2, 3), Arrays.asList(1, 2, 3)),
            Arguments.of(Arrays.asList(1, 2, 3), null, Arrays.asList(1, 2, 3)),
            
            // Casos con listas vacías
            Arguments.of(Collections.emptyList(), Collections.emptyList(), Collections.emptyList()),
            Arguments.of(Collections.emptyList(), Arrays.asList(1, 2), Arrays.asList(1, 2)),
            Arguments.of(Arrays.asList(1, 2), Collections.emptyList(), Arrays.asList(1, 2)),
            
            // Casos sin elementos duplicados
            Arguments.of(Arrays.asList(1, 3, 5), Arrays.asList(2, 4, 6), Arrays.asList(1, 2, 3, 4, 5, 6)),
            Arguments.of(Arrays.asList(10, 20, 30), Arrays.asList(15, 25, 35), Arrays.asList(10, 15, 20, 25, 30, 35)),
            
            // Casos con elementos duplicados
            Arguments.of(Arrays.asList(1, 2, 2), Arrays.asList(2, 3, 3), Arrays.asList(1, 2, 2, 2, 3, 3)),
            Arguments.of(Arrays.asList(1, 1, 2), Arrays.asList(1, 2, 2), Arrays.asList(1, 1, 1, 2, 2, 2)),
            
            // Casos con todos elementos iguales
            Arguments.of(Arrays.asList(1, 1, 1), Arrays.asList(1, 1, 1), Arrays.asList(1, 1, 1, 1, 1, 1)),
            
            // Casos con listas de diferente tamaño
            Arguments.of(Arrays.asList(1), Arrays.asList(2, 3, 4), Arrays.asList(1, 2, 3, 4)),
            Arguments.of(Arrays.asList(1, 3, 5, 7), Arrays.asList(2, 4), Arrays.asList(1, 2, 3, 4, 5, 7)),
            
            // Caso con un solo elemento en ambas listas
            Arguments.of(Arrays.asList(1), Arrays.asList(2), Arrays.asList(1, 2)),
            Arguments.of(Arrays.asList(1), Arrays.asList(1), Arrays.asList(1, 1))
        );
    }

    /**
     * Testea el método mezclar() de la clase MezclaLineal con listas de Strings.
     * Verifica que mezclar las listas ["a", "c", "e"] y ["b", "d", "f"]
     * produce la lista ordenada esperada ["a", "b", "c", "d", "e", "f"].
     */
    @Test
    void testMezclaConStrings() {
        List<String> lista1 = Arrays.asList("a", "c", "e");
        List<String> lista2 = Arrays.asList("b", "d", "f");
        List<String> esperado = Arrays.asList("a", "b", "c", "d", "e", "f");
        
        assertEquals(esperado, MezclaLineal.mezclar(lista1, lista2));
    }

    /**
     * Testea el método mezclar() de la clase MezclaLineal con listas grandes.
     * Verifica que al mezclar una lista de números pares del 0 al 999 y
     * una lista de números impares del 0 al 999, se obtiene la lista ordenada
     * del 0 al 999.
     */
    @Test
    void testMezclaConListasGrandes() {
        List<Integer> lista1 = IntStream.range(0, 1000)
                                .filter(i -> i % 2 == 0)
                                .boxed()
                                .collect(Collectors.toList());
        
        List<Integer> lista2 = IntStream.range(0, 1000)
                                .filter(i -> i % 2 != 0)
                                .boxed()
                                .collect(Collectors.toList());
        
        List<Integer> esperado = IntStream.range(0, 1000)
                                  .boxed()
                                  .collect(Collectors.toList());
        
        assertEquals(esperado, MezclaLineal.mezclar(lista1, lista2));
    }
}