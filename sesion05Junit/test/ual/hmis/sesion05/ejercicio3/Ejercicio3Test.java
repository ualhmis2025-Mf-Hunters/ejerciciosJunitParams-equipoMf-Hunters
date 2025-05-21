package ual.hmis.sesion05.ejercicio3;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

public class Ejercicio3Test {
    private Ejercicio3 ejercicio3 = new Ejercicio3();
    
/**
 * Prueba el método enmascararPassword con diferentes combinaciones de entrada.
 * Se utiliza CsvSource para proporcionar múltiples casos de prueba, donde cada
 * caso consta de una entrada de contraseña y el resultado esperado.
 * Verifica que el método enmascararPassword devuelva el resultado correcto
 * según las reglas de enmascaramiento definidas:
 * - "password demasiado corto" para null, cadenas vacías o de longitud < 5
 * - "********" para longitudes entre 5 y 8
 * - "************" para longitudes entre 9 y 40
 * - "password demasiado largo" para longitudes mayores de 40
 * 
 * @param input la contraseña de entrada
 * @param expected el resultado esperado después de enmascarar
 */

    @CsvSource({
        "null,password demasiado corto",
        ",password demasiado corto",
        "a,password demasiado corto",
        "abcd,password demasiado corto",
        "abcde,********",
        "abcdefgh,********",
        "abcdefghi,************",
        "abcdefghijkl,************",
        "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ,password demasiado largo",
        "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890,password demasiado largo"
    })

    @ParameterizedTest(name = "{index} => Con entrada ({0}) sale {1}")
    public void testEnmascararPassword(String input, String expected) {
        String resultado = ejercicio3.enmascararPassword(input);
        assertEquals(expected, resultado);
    }
}