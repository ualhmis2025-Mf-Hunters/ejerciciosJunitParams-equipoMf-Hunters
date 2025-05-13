package ual.hmis.sesion05.ejercicio3;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import static org.junit.jupiter.api.Assertions.assertEquals;

class Ejercicio3Test {
    private Ejercicio3 ejercicio3 = new Ejercicio3();

    @ParameterizedTest
    @CsvFileSource(resources = "ejercicio3_test_cases.csv",
                  numLinesToSkip = 1,
                  nullValues = {"null"})
    void testEnmascararPassword(String input, String expected) {
        String resultado = ejercicio3.enmascararPassword(input);
        assertEquals(expected, resultado);
    }
}