package ual.hmis.sesion05;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;


class Ejercicio1Test {

	
	@CsvSource({"2, 1",
				"10, 1",
				"6, 1",
				"14, 7"})
	
	@ParameterizedTest (name = "{index} => Con entrada ({0}) sale {1}")
	void testTransformar(int input, int expected) {
		Ejercicio1 c = new Ejercicio1();
		assertEquals(expected, c.transformar(input));
		// assertTrue(c.transformar(input)==expected);
	}

}
