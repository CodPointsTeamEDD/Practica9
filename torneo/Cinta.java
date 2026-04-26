package torneo;
/**
 * Clase {@code Cinta}
 * 
 * @author Luis Fernando Quintana López
 * @author Erick Xavier Martinez Briones
 * @version 1.0.0
 * @since 2026
 * 
 */

public class Cinta {

	/** Nombre de identificacion de la cinta */
	private String nombre;

	/** Nivel numerico */
	private int nivelNumerico;

	/** CONSTANTES */
	public static String[] NOMBRES_CINTAS = {"Kaimua", "Moli", "Lua", "Moana", "Ulakui"};

	/**
	 * Constructor de la clase {@code Cinta}
	 * 
	 * @param nombre nombre de la cinta
	 */
	public Cinta(String nombre) throws IllegalArgumentException{
		if (nombreEnLista(nombre) != -1) {
			this.nombre = nombre;
			this.nivelNumerico = nombreEnLista(nombre);
		} else {
			throw new IllegalArgumentException();
		}
	}

	/** Getters */

	/**
	 * Metodo que obtiene el nombre de la cinta
	 * 
	 * @return Nombre de la cinta
	 */
	public String getNombre() {
		return nombre;
	}
	/**
	 * Metodo que obtiene el nivel numerico de la cinta
	 * @return Nivel de la cinta
	 */
	public int getNivelNumerico() {
		return nivelNumerico;
	}

	/**
	 * Metodo que obtiene el nivel numerico de la cinta dado un nombre
	 * @param nombreCinta nombre de la cinta
	 * @return Nivel de la cinta
	 */
	public static int getNivelNumerico(String nombreCinta) {
		int indiceNombre = 0;

		/* Obteniendo el indice del nombre*/
		for (int i = 0; i < Cinta.NOMBRES_CINTAS.length; i++) {
			if (!nombreCinta.equals(NOMBRES_CINTAS[i]) || !nombreCinta.equals(NOMBRES_CINTAS[i] + " ") ) {
				indiceNombre++;
			} else{
				break;
			}
		}

		return indiceNombre;
	}

	/**
	 * Metodo que verifica si el un string pertenece a la constante NOMBRES_CINTA
	 * @param nombreCinta nombre de la cinta
	 * @return Nivel de la cinta
	 */
	public static int nombreEnLista(String nombreCinta) {
		int indice=0;

		/* Obteniendo el indice del nombre*/
		for (int i = 0; i < Cinta.NOMBRES_CINTAS.length; i++) {
			if (nombreCinta.trim().equals(NOMBRES_CINTAS[i]) ) {
				break;
			} else {
				indice++;
			}
		}

		/* Verificando si el elemento estuvo en la lista */
		if (indice==Cinta.NOMBRES_CINTAS.length) {
			return -1;
		}

		return indice;
	}

	/** Setters */

	/**
	 * Metodo que asigna el nombre a la cinta
	 * 
	 * @param nombre nombre a asignar
	 */
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	/**
	 * Metodo que asigna el nivel numerico de la cinta
	 * 
	 * @param nivel nivel a asignar
	 */
	public void setNivelNumerico(int nivel) {
		this.nivelNumerico = nivel;
	}

}
