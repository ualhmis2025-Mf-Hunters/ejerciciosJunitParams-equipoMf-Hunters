package ual.hmis.sesion05;

/**
 * Clase que contiene un método para transformar un número entero según su divisibilidad.
 */
public class Ejercicio1{
	
	/**
	 * Constructor de la clase Ejercicio1.
	 * 
	 * Este constructor no realiza ninguna acción, pero se incluye para
	 * permitir la creación de instancias de la clase.
	 */
	public Ejercicio1() {
		// Constructor vacío
	}

	/**
	 * Recibe un entero y devuelve otro entero:
	 * si x es divisible entre 2, 3 o 5, devuelve el resultado de llamar a este
	 * mismo método con x dividido entre el mayor divisor encontrado (2, 3 o 5);
	 * en caso contrario, devuelve x.
	 * 
	 * @param x entero a transformar
	 * @return entero transformado
	 */
	public int transformar (int x) {
		int resultado = 0;
		if (x % 2 == 0) // % Resto de una división entre enteros (mod)
			resultado = transformar (x/2);
		else if (x % 3 == 0)
			resultado = transformar (x/3);
		else if (x % 5 == 0)
			resultado = transformar (x/5);
		else return x;
		return resultado;
	}
}
