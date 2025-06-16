package ual.hmis.sesion05.ejercicio6;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

/**
 * Test parametrizado para la clase ConversionTemperatura.
 * 
 * Utiliza un archivo CSV externo ubicado en src/test/resources/ual/hmis/sesion05/ejercicio6/datos_temperatura.csv.
 * Cada línea del archivo contiene:
 * - el valor a convertir (double)
 * - la unidad de origen (String)
 * - la unidad de destino (String)
 * - el resultado esperado (double)
 */
public class Ejercicio6Test {

    /**
     * Prueba parametrizada que convierte temperaturas entre distintas unidades.
     * 
     * Usa el archivo CSV como fuente de datos, ignorando la primera línea (encabezado).
     * Soporta comparaciones con valores esperados válidos o NaN para conversiones no válidas (misma unidad).
     */
    @ParameterizedTest(name = "{index} => {0} {1} a {2} = {3}")
    @CsvFileSource(resources = "/ual/hmis/sesion05/ejercicio6/datos_temperatura.csv", numLinesToSkip = 1)
    void testConvertTemperature(double temperature, String fromUnit, String toUnit, double expected) {
        ConversionTemperatura c = new ConversionTemperatura();

        if (Double.isNaN(expected)) {
            // Comprobamos que el resultado también sea NaN si el esperado lo es
            assertEquals(Double.NaN, c.convertTemperature(temperature, fromUnit, toUnit), 0.001);
        } else {
            // Comprobamos con una tolerancia de 0.001 para decimales
            assertEquals(expected, c.convertTemperature(temperature, fromUnit, toUnit), 0.001);
        }
    }
}
