package ual.hmis.sesion05.ejercicio2;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class Ejercicio2Test {

	/**
	 * Prueba el m todo login con diferentes combinaciones de usuario y contrase a.
	 * La primera columna es el usuario, la segunda columna es la contrase a, y la tercera columna indica
	 * si el usuario se puede loguear o no.
	 * @param username el nombre de usuario
	 * @param password la contrase a
	 * @param result indica si el usuario se puede loguear o no
	 */
	@CsvSource({
		"user,pass,true",
		"'',pass,false",
		"user,'',false",
		"nombredeusuariossuperlagruisimomayorde30caraceteres,pass,false",
		"user,unsuperpasswordlarguisimoconmasde30caracteresgigantedelto,false",
 		"user,passwordincorrect,false",
		"usuario,pass,false"
	})
	
	@ParameterizedTest(name = "{index} => Con usuario ({0}) y password ({1}) sale {2}")
	void testLogin_parametrized(String username, String password, String result) {
		// Arrange
		Ejercicio2 e2 = new Ejercicio2();
		// Act
		// Assert
		assertEquals(Boolean.valueOf(result), e2.login(username, password));
	}
	
}
