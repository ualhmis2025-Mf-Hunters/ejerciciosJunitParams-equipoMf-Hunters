package ual.hmis.sesion05.ejercicio4;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.junit.jupiter.api.Test;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import static org.junit.jupiter.api.Assertions.*;

public class Ejercicio4Test {

    @ParameterizedTest
    @CsvFileSource(resources = "/ual/hmis/sesion05/ejercicio4/mezcla_test_cases.csv", numLinesToSkip = 1)
    void testMezclarConjuntosOrdenados(String listaAStr, String listaBStr, String resultadoEsperadoStr) {
        List<Integer> listaA = parseList(listaAStr);
        List<Integer> listaB = parseList(listaBStr);
        List<Integer> esperado = parseList(resultadoEsperadoStr);
        
        List<Integer> resultado = MezclaLineal.mezclarConjuntosOrdenados(listaA, listaB);
        
        assertEquals(esperado, resultado);
    }

    @Test
    void testAmbosParametrosNulos() {
        List<Integer> resultado = MezclaLineal.mezclarConjuntosOrdenados(null, null);
        assertTrue(resultado.isEmpty());
    }

    @Test
    void testPrimerParametroNulo() {
        List<Integer> listaB = Arrays.asList(1, 2, 3);
        List<Integer> resultado = MezclaLineal.mezclarConjuntosOrdenados(null, listaB);
        assertEquals(listaB, resultado);
    }

    @Test
    void testSegundoParametroNulo() {
        List<Integer> listaA = Arrays.asList(4, 5, 6);
        List<Integer> resultado = MezclaLineal.mezclarConjuntosOrdenados(listaA, null);
        assertEquals(listaA, resultado);
    }

    @Test
    void testAmbasListasVacias() {
        List<Integer> resultado = MezclaLineal.mezclarConjuntosOrdenados(
            Collections.emptyList(), 
            Collections.emptyList());
        assertTrue(resultado.isEmpty());
    }

    @Test
    void testElementosIguales() {
        List<Integer> listaA = Arrays.asList(1, 1, 1);
        List<Integer> listaB = Arrays.asList(1, 1, 1);
        List<Integer> esperado = Arrays.asList(1, 1, 1, 1, 1, 1);
        List<Integer> resultado = MezclaLineal.mezclarConjuntosOrdenados(listaA, listaB);
        assertEquals(esperado, resultado);
    }

    @Test
    void testListaAMayorQueListaB() {
        List<Integer> listaA = Arrays.asList(1, 3, 5, 7, 9);
        List<Integer> listaB = Arrays.asList(2, 4);
        List<Integer> esperado = Arrays.asList(1, 2, 3, 4, 5, 7, 9);
        List<Integer> resultado = MezclaLineal.mezclarConjuntosOrdenados(listaA, listaB);
        assertEquals(esperado, resultado);
    }

    @Test
    void testListaBMayorQueListaA() {
        List<Integer> listaA = Arrays.asList(2, 4);
        List<Integer> listaB = Arrays.asList(1, 3, 5, 7, 9);
        List<Integer> esperado = Arrays.asList(1, 2, 3, 4, 5, 7, 9);
        List<Integer> resultado = MezclaLineal.mezclarConjuntosOrdenados(listaA, listaB);
        assertEquals(esperado, resultado);
    }

    private static List<Integer> parseList(String str) {
        if (str == null || "null".equalsIgnoreCase(str.trim()) || str.trim().isEmpty()) {
            return Collections.emptyList();
        }
        return Arrays.stream(str.split("\\s*,\\s*"))
                   .map(String::trim)
                   .filter(s -> !s.isEmpty())
                   .map(Integer::parseInt)
                   .collect(Collectors.toList());
    }
}