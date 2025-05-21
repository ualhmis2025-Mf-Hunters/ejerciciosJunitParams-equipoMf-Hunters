package ual.hmis.sesion05.ejercicio2;

/**
 * Clase que contiene un método para comprobar si un usuario se puede loguear
 * en la base de datos.
 */
public class Ejercicio2 {

	/**
	 * Constructor de la clase Ejercicio2.
	 * 
	 * Este constructor no realiza ninguna acción, pero se incluye para
	 * permitir la creación de instancias de la clase.
	 */
	public Ejercicio2() {
		// Constructor vacío
	}

	/**
	 * Comprueba que un usuario se pueda loguear en la base de datos.
	 * @param username el nombre de usuario
	 * @param password la contrasena del usuario
	 * @return true si el usuario se puede loguear, false en caso contrario
	 */
	public boolean login (String username, String password){
		// comprobar que sean distintos de vacio
		if (username.isEmpty() || password.isEmpty())
			return false;
		// comprobar que la longitud sea < 30
		if (username.length()>= 30 || password.length()>=30)
			return false;
		// llamar al método de la bbdd
		return compruebaLoginEnBD(username, password);

	}

	/**
	 * Método mock (simulado) que comprueba si un usuario se puede
	 * loguear en la base de datos.
	 * @param username el nombre de usuario
	 * @param password la contrasena del usuario
	 * @return true si el usuario se puede loguear, false en caso contrario
	 */
	public boolean compruebaLoginEnBD
	(String username, String password){
		// método mock (simulado)
		if (username.equals("user") && password.equals("pass"))
			return true;
		else
			return false;
	}
}
