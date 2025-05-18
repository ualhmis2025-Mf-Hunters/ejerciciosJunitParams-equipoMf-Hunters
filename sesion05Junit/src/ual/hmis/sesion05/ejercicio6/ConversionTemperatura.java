package ual.hmis.sesion05.ejercicio6;

/**
 * Clase que convierte temperaturas entre diferentes unidades.
 * Soporta conversiones entre Celsius, Fahrenheit y Kelvin.
 * 
 * Ejemplo de uso:
 * ConversionTemperatura converter = new ConversionTemperatura();
 * double fahrenheit = converter.convertTemperature(100, "Celsius", "Fahrenheit");
 */
public class ConversionTemperatura {

    /**
     * Constructor de la clase ConversionTemperatura.
     * 
     * Este constructor no realiza ninguna acción, pero se incluye para
     * permitir la creación de instancias de la clase.
     */
    public ConversionTemperatura() {
        // Constructor vacío
    }

    /**
     * Convierte una temperatura de una unidad a otra.
     * 
     * Las conversiones soportadas son:
     * - Celsius a Fahrenheit
     * - Celsius a Kelvin
     * - Fahrenheit a Celsius
     * - Fahrenheit a Kelvin
     * - Kelvin a Celsius
     * - Kelvin a Fahrenheit
     * 
     * Si se solicita una conversión no soportada, el método devuelve NaN.
     * 
     * @param temperature el valor de temperatura a convertir
     * @param fromUnit la unidad de la temperatura de entrada ("Celsius", "Fahrenheit" o "Kelvin")
     * @param toUnit la unidad a la que se desea convertir la temperatura ("Celsius", "Fahrenheit" o "Kelvin")
     * @return la temperatura convertida o NaN si la conversión no es válida
     */

    public double convertTemperature(double temperature, String fromUnit, String toUnit) {
        if (fromUnit.equals("Celsius") && toUnit.equals("Fahrenheit")) {
            return (temperature * 9 / 5) + 32;
        } else if (fromUnit.equals("Celsius") && toUnit.equals("Kelvin")) {
            return temperature + 273.15;
        } else if (fromUnit.equals("Fahrenheit") && toUnit.equals("Celsius")) {
            return (temperature - 32) * 5 / 9;
        } else if (fromUnit.equals("Fahrenheit") && toUnit.equals("Kelvin")) {
            return (temperature - 32) * 5 / 9 + 273.15;
        } else if (fromUnit.equals("Kelvin") && toUnit.equals("Celsius")) {
            return temperature - 273.15;
        } else if (fromUnit.equals("Kelvin") && toUnit.equals("Fahrenheit")) {
            return (temperature - 273.15) * 9 / 5 + 32;
        } else {
            return Double.NaN; // Invalid conversion
        }
    }
}
