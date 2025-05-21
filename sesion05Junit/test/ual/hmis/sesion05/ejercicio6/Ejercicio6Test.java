package ual.hmis.sesion05.ejercicio6;

import static org.junit.Assert.assertEquals;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

/**
 * Clase de prueba para la clase ConversionTemperatura.
 * 
 * Esta clase contiene pruebas unitarias para el método convertTemperature
 * de la clase ConversionTemperatura. Se utilizan diferentes casos de prueba
 * para verificar que las conversiones de temperatura se realizan correctamente.
 */
public class Ejercicio6Test {

    /**
     * Prueba el método convertTemperature de la clase ConversionTemperatura.
     * 
     * @param temperature la temperatura a convertir
     * @param fromUnit la unidad de origen
     * @param toUnit la unidad de destino
     * @param expected el resultado esperado
     */
    @ParameterizedTest(name = "{index} => Con entrada ({0}) sale {1}")
    @CsvSource({
        "0, Celsius, Fahrenheit, 32.0",
        "0, Celsius, Kelvin, 273.15",
        "0, Celsius, Celsius, NaN",
        "32, Fahrenheit, Celsius, 0.0",
        "32, Fahrenheit, Kelvin, 273.15",
        "32, Fahrenheit, Fahrenheit, NaN",
        "273.15, Kelvin, Celsius, 0.0",
        "273.15, Kelvin, Fahrenheit, 32.0",
        "273.15, Kelvin, Kelvin, NaN",
    })
    
    public void testConvertTemperature(double temperature, String fromUnit, String toUnit, double expected) {
        ConversionTemperatura c = new ConversionTemperatura();
        if (Double.isNaN(expected)) {
            assertEquals(Double.NaN, c.convertTemperature(temperature, fromUnit, toUnit), 0.001);
        } else {
            assertEquals(expected, c.convertTemperature(temperature, fromUnit, toUnit), 0.001);
        }
    }
}
