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
        new MezclaLineal(); // Para cubrir el constructor
    }

    @ParameterizedTest(name = "Test {index}: mezclar({0}, {1}) = {2}")
    @MethodSource("datosParaMezcla")
    void testMezclaParametrizado(List<Integer> lista1, List<Integer> lista2, List<Integer> esperado) {
        List<Integer> resultado = MezclaLineal.mezclar(lista1, lista2);
        assertEquals(esperado, resultado);
    }

    private static Stream<Arguments> datosParaMezcla() {
        return Stream.of(
            Arguments.of(null, null, Collections.emptyList()),
            Arguments.of(Collections.emptyList(), Arrays.asList(1, 2, 3), Arrays.asList(1, 2, 3)),
            Arguments.of(Arrays.asList(1, 2, 2), Arrays.asList(2, 3, 3), Arrays.asList(1, 2, 2, 2, 3, 3)),
            // Resto de casos de prueba...
        );
    }

    @Test
    void testConstructor() {
        assertDoesNotThrow(() -> new MezclaLineal());
    }
}