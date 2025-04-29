package ual.hmis.sesion05.ejercicio4;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.converter.ConvertWith;
import static org.junit.jupiter.api.Assertions.*;

import java.util.*;
import java.util.stream.Stream;

class Ejercicio4Test {
    private MezclaLineal mezclador = new MezclaLineal();

    // Conversor mejorado para CSV
    public static class StringToListConverter extends SimpleArgumentConverter {
        @Override
        protected Object convert(Object source, Class<?> targetType) {
            String str = ((String) source).trim();
            if (str.isEmpty()) return Collections.emptyList();
            if (str.equalsIgnoreCase("null")) return null;
            
            // Manejo especial para elementos null en la lista
            String[] parts = str.split("\\s*,\\s*");
            List<String> result = new ArrayList<>();
            for (String part : parts) {
                result.add(part.equals("null") ? null : part);
            }
            return result;
        }
    }

    // Pruebas con CSV
    @ParameterizedTest
    @CsvFileSource(files = "mezcla_test_cases.csv", 
                  numLinesToSkip = 1)
    void testMezclarConCSV(
        @ConvertWith(StringToListConverter.class) List<String> listaA,
        @ConvertWith(StringToListConverter.class) List<String> listaB,
        @ConvertWith(StringToListConverter.class) List<String> esperado) {
        
        assertEquals(esperado, mezclador.mezclar(listaA, listaB));
    }

    // Pruebas programáticas para cobertura completa
    static Stream<Arguments> proveedorDatos() {
        return Stream.of(
            // Casos null (4 ramas)
            Arguments.of(null, null, Collections.emptyList()),
            Arguments.of(null, Arrays.asList(1), Arrays.asList(1)),
            Arguments.of(Arrays.asList(1), null, Arrays.asList(1)),
            
            // Casos vacíos (2 ramas)
            Arguments.of(Collections.emptyList(), Collections.emptyList(), Collections.emptyList()),
            
            // Casos con elementos null en listas (rama adicional)
            Arguments.of(Arrays.asList(null, "a"), Arrays.asList("a", "b"), Arrays.asList(null, "a", "b")),
            
            // Todos los casos de comparación (3 ramas)
            Arguments.of(Arrays.asList(1), Arrays.asList(2), Arrays.asList(1,2)), // <
            Arguments.of(Arrays.asList(2), Arrays.asList(1), Arrays.asList(1,2)), // >
            Arguments.of(Arrays.asList(1), Arrays.asList(1), Arrays.asList(1)),   // =
            
            // Casos extremos de duplicados
            Arguments.of(Arrays.asList(1,1,1), Arrays.asList(1,1,2), Arrays.asList(1,2)),
            Arguments.of(Arrays.asList("a","a"), Arrays.asList("a","b"), Arrays.asList("a","b")),
            
            // Caso que probablemente falta
            Arguments.of(Arrays.asList(1), Arrays.asList(1,1,1), Arrays.asList(1))
        );
    }

    @ParameterizedTest
    @MethodSource("proveedorDatos")
    <T extends Comparable<T>> void testMezclarCompleto(List<T> listaA, List<T> listaB, List<T> esperado) {
        assertEquals(esperado, mezclador.mezclar(listaA, listaB));
    }

    // Test adicional para la rama faltante
    @Test
    void testElementosNullEnMedio() {
        List<String> resultado = mezclador.mezclar(
            Arrays.asList("a", null, "b"),
            Arrays.asList(null, "c", "d")
        );
        assertEquals(Arrays.asList("a", null, "b", "c", "d"), resultado);
    }
}