package ual.hmis.sesion05.ejercicio6;

public class ConversionTemperatura {

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
