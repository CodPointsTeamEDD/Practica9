package torneo;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import listas.*;

/**
 * Clase concreta {@code AdviniadorResultados}
 * 
 * @author Luis Fernando Quintana López
 * @author Erick Xavier Martinez Briones
 * @version 1.0.0
 * @since 2026
 * 
 */
public class AdivinadorResultados {

	/**
	 * Metodo que determina cúales participantes de un grupo perderían un
	 * enfrentamiento ante el Participante "a"
	 * @param a Participante denotado por a
	 * @param g Lista ligada simple de participantes denotados por g
	 * @return Lista ligada simple de tipo Participante que contiene solo los participantes
	 *		   que perdirían un enfrentamiento ante el participante "a", usando
	 *		   como criterio el indice de victorias. Ante un posible empate se
	 *		   usa como criterio el rango de la cienta que poseen
	 */
	public static ListaLigadaSimple<Participante> alumnoALesGana(Participante a, ListaLigadaSimple<Participante> g) {
		ListaLigadaSimple<Participante> perdedores = new ListaLigadaSimple<>();
		double indiceA = a.indiceDeVictorias();
		int nvlNumericoCinta = a.cinta.getNivelNumerico();

		for (Participante aux : g) {
			if ((indiceA > aux.indiceDeVictorias()) || (nvlNumericoCinta > aux.cinta.getNivelNumerico())) {
				perdedores.agregar(aux);
			}
		}

		return perdedores;
	}

	/**
	 * Metodo que determina cúales participantes de un grupo ganarían un
	 * enfrentamiento ante el Participante "a"
	 *
	 * @param a Participante denotado por a
	 * @param g ListaLigadaSimple de participantes denotados por g
	 * @return ListaLigadaSimple de tipo Participante que contiene solo los participantes
	 *		   que ganarían un enfrentamiento ante el participante "a", usando
	 *		   como criterio el indice de victorias. Ante un posible empate se
	 *		   usa como criterio el rango de la cienta que poseen
	 */
	public static ListaLigadaSimple<Participante> alumnoAPierde(Participante a, ListaLigadaSimple<Participante> g) {
		ListaLigadaSimple<Participante> ganadores = new ListaLigadaSimple<>();
		double indiceA = a.indiceDeVictorias();
		int nvlNumericoCinta = a.cinta.getNivelNumerico();

		for (Participante aux : g) {
			if ((indiceA < aux.indiceDeVictorias()) || (nvlNumericoCinta < aux.cinta.getNivelNumerico())) {
				ganadores.agregar(aux);
			}
		}

		return ganadores;
	}

	/**
	 * Metodo que guarda en una ListaLigadaSimple los indices de victoria de los participantes
	 *
	 * @param g ListaLigadaSimple de participantes denotados por g
	 * @return ListaLigadaSimplecon los indices de victoria de los participantes
	 */
	public static ListaLigadaSimple<Double> indicesVictoria(ListaLigadaSimple<Participante> g) {
		ListaLigadaSimple<Double> indicesVic = new ListaLigadaSimple<>();

		for (Participante aux : g) {
			indicesVic.agregar(aux.indiceDeVictorias());
		}

		return indicesVic;
	}

	/**
	 * Método que devuelve el promedio del indice de victorias de un grupo de participantes
	 *
	 * @param g ListaLigadaSimple de participantes denotados por g
	 * @return Un número real que representa el promedio de indices de victoria
	 */
	public static double indiceVictoriaPromedio(ListaLigadaSimple<Participante> g) {
		int longitudLista = g.devolverLongitud();
		double sumaVictorias = 0;

		for (Participante aux : g) {
			sumaVictorias += aux.indiceDeVictorias();
		}

		return sumaVictorias / longitudLista;
	}

	/**
	 * Metodo que determina cuales participantes tienen la misma cinta que el
	 * participante a.
	 *
	 * @param a Participante denotado por a
	 * @param g ListaLigadaSimple de participantes denotados por g
	 * @return ListaLigadaSimple con los participantes tienen la misma cinta que el
	 *         participsante a
	 */
	public static ListaLigadaSimple<Participante> mismaCinta(Participante a, ListaLigadaSimple<Participante> g) {
		ListaLigadaSimple<Participante> misma = new ListaLigadaSimple<>();
		String nombreA = a.cinta.getNombre();

		for (Participante aux : g) {
			if (nombreA.equals(aux.cinta.getNombre())) {
				misma.agregar(aux);
			}
		}

		return misma;
	}

	/**
	 * Metodo que determina que participantes del grupo presentan un ındice de
	 * victoria cuya diferencia respecto al ındice de victoria del alumno a sea,
	 * como maximo, de 0 - 10.
	 *
	 * @param a Participante denotado por a
	 * @param g ListaLigadaSimple de participantes denotados por g
	 * @return ListaLigadaSimple con participantes del grupo presentan un ındice de victoria
	 *         cuya diferencia respecto al ındice de victoria del alumno a sea, como
	 *         maximo, de 0 - 10.
	 */
	public static ListaLigadaSimple<Participante> indicesimilarAlAlumnoA(Participante a,
			ListaLigadaSimple<Participante> g) {
		ListaLigadaSimple<Participante> indiceSimilar = new ListaLigadaSimple<>();
		double indiceA = a.indiceDeVictorias();

		for (Participante aux : g) {
			if (aux.indiceDeVictorias() >= indiceA - 0.10 && aux.indiceDeVictorias() <= indiceA + 0.10) {
				indiceSimilar.agregar(aux);
			}
		}

		return indiceSimilar;
	}

	/** 
	 * Metodo que dada una cadena, la divide en fragmentos o subcadenas delimitadas por un 
	 * caracter "separador". 
	 * 
	 * @param cadena cadena original a separar
	 * @param separador Char que representa el elemento separador
	 * @param posicionElemento fragmento especifico que se desea recuperar
	 * @return String que representa un fragmento de la cadena original obtenido a partir de
	 * 
	*/
	public static String extraerSubcadena(String cadena, char separador, int posicionElemento){
		/* Inicializando variables */
		String subCadena = cadena;
		int indiceSeparador;

		/*
			Borrando elementos de la cadena anteriores a posicionElemento,
			usando el separador pasado como parametro al metodo
		*/
		for (int i = 0; i < posicionElemento; i++) {
			/* Obteniendo el indice del separador i */
			indiceSeparador = subCadena.indexOf(separador);

			/* Removiendo el primer elemento de la cadena */
			subCadena = subCadena.substring(indiceSeparador+1);
		}

		/* Obteniendo indice del siguiente separador */
		indiceSeparador = subCadena.indexOf(separador);

		if (indiceSeparador == -1) {
			/* Obteniendo el elemento buscado */
			return subCadena;			
		} 

		/* Obteniendo el elemento buscado */
		String elemento = subCadena.substring(0, indiceSeparador);			

		/* Eliminando posibles espacios extra */
		elemento = elemento.trim();

		/* Retornando el elemento */
		return elemento;
	}

	/** 
	 * Metodo que dada una cadena con información de un Participante
	 * extrae su nombre
	 * @param cadena cadena que contiene la información del participante
	 * @return String con el nombre del participante
	*/
	public static String extraerNombre(String cadena){
		return AdivinadorResultados.extraerSubcadena(cadena, ',', 0);
	}
	/** 
	 * Metodo que dada una cadena con información de un Participante
	 * extrae el numero de medallas que posee
	 * @param cadena cadena que contiene la información del participante
	 * @return INT con el numero de medallas del participante
	*/
	public static int extraerNumMedallas(String cadena){
		int numMedallas;
		String subcadena = AdivinadorResultados.extraerSubcadena(cadena, ',', 1);

		/* Quitando "M:" */
		subcadena = AdivinadorResultados.extraerSubcadena(subcadena, ':', 1);

		/* Asignar un INT a numMedallas */
		numMedallas = Integer.parseInt(subcadena);

		return numMedallas;
	}
	/** 
	 * Metodo que dada una cadena con información de un Participante
	 * extrae el numero de participaciones en torneos
	 * @param cadena cadena que contiene la información del participante
	 * @return INT con el numero de participaciones
	*/
	public static int extraerParticipaciones(String cadena){
		int numPart;
		String subcadena = AdivinadorResultados.extraerSubcadena(cadena, ',', 2);

		/* Quitando "P:" */
		subcadena = AdivinadorResultados.extraerSubcadena(subcadena, ':', 1);

		/* Asignar un INT a numPart */
		numPart = Integer.parseInt(subcadena);

		return numPart;
	}
	/** 
	 * Metodo que dada una cadena con información de un Participante
	 * extrae el nombre de la cinta que poseen
	 * @param cadena cadena que contiene la información del participante
	 * @return String con el nombre de la cinta del participante
	*/
	public static String extraerNombreCinta(String cadena){
		String subcadena = AdivinadorResultados.extraerSubcadena(cadena, ',', 3);

		/* Quitando "N:" */
		subcadena = AdivinadorResultados.extraerSubcadena(subcadena, ':', 1);

		return subcadena;
	}

	public static void main(String[] args) {
		/* ----------------------------------------------------- */
		/*     Lectura de archivo y extracción de información    */
		/* ----------------------------------------------------- */

		/* Nombre del archivo a leer */
		String nombreArchivo = "participantes.txt";

		/* Variables que almacenarán participantes */
		ListaLigadaSimple<Participante> g = new ListaLigadaSimple<>(); 
		Participante a = new Participante("", 0, 0, new Cinta(Cinta.NOMBRES_CINTAS[0]));

		/* Variables temporales que se pasarán al constructor de Participante*/
		String nombreParticipante;
		int numMedallas;
		int numTorneos;
		String nombreCinta;
		Cinta cinta;

		/* Lectura del archivo para crear participates */
        try (BufferedReader br = new BufferedReader(new FileReader(nombreArchivo))) {
			/* Variable auxiliar que almacena la linea leida */
            String linea;

			/* Cantidad de lineas leidas */
			int i = 0;

            while ((linea = br.readLine()) != null) {
				/* Recuperando valores de la linea de texto */
				nombreParticipante = AdivinadorResultados.extraerNombre(linea); 
				numMedallas = AdivinadorResultados.extraerNumMedallas(linea); 
				numTorneos = AdivinadorResultados.extraerParticipaciones(linea); 
				nombreCinta = AdivinadorResultados.extraerNombreCinta(linea); 
				cinta = new Cinta(nombreCinta);				

				/* 
					Creando el jugador "a" o insertando jugadores en arreglo de tipo 
					Participante"g"
				*/
				if (i != 0) {
					Participante aux = new Participante(nombreParticipante, numMedallas, numTorneos, cinta);
					g.agregar(aux);
					i++;
				} else {
					a = new Participante(nombreParticipante, numMedallas, numTorneos, cinta);
					i++;
				}
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

		/* ----------------------------------------------------- */
		/* Probando metodos y mostrando el resultado en pantalla */
		/* ----------------------------------------------------- */
		
		System.out.println("¡¡Bienvenido usuario!!");
		System.out.println("Este programa lee la información de un archivo .txt y la usa para extraer información sobre Participantes.");

		System.out.println("");

		System.out.println("PRIMER USUARIO (USUARIO A COMPARAR):");
		System.out.println(a);

		System.out.println("");

		System.out.println("OTROS PARTICIPANTES: ");
		for (Participante part : g) {
			System.out.println(part);
		}

		System.out.println("");

		/* Probando metodo alumnoALesGana */
		System.out.println("El participante " + a.getNombre() + "les gana a los siguientes participantes:");
		ListaLigadaSimple<Participante> perdedores = alumnoALesGana(a, g);
		for (Participante part : perdedores) {
			System.out.println("-" + part.getNombre());
		}

		System.out.println(" ");

		/* Probando metodo alumnoALesGana */
		System.out.println("El participante " + a.getNombre() + " pierde contra los siguientes participantes:");
		ListaLigadaSimple<Participante> ganadores = alumnoAPierde(a, g);
		for (Participante part : ganadores) {
			System.out.println("-" + part.getNombre());
		}

		System.out.println(" ");


		/* Probando metodo indicesDeVictoria */
		System.out.println("Los siguientes son los indices de victoria de todos los participantes del arreglo");
		ListaLigadaSimple<Double> indicesVictoria = indicesVictoria(g);
		for (Double ind : indicesVictoria) {
			System.out.println(ind);
		}

		System.out.println(" ");

		/* Probando metodo mismaCinta */
		System.out.println("El participante " + a.getNombre() + " tiene la misma cinta que los siguientes participantes:");
		ListaLigadaSimple<Participante> mismaCinta = mismaCinta(a, g);
		for (Participante part : mismaCinta) {
			System.out.println("-" + part.getNombre());
		}

		/* Probando metodo indiceSimilarAlAlumnoA */
		System.out.println("El participante " + a.getNombre() + " tiene un indice de victoria similar al de los siguientes participantes:");
		ListaLigadaSimple<Participante> indSimilar = indicesimilarAlAlumnoA(a, g);
		for (Participante part : indSimilar) {
			System.out.println("-" + part.getNombre());
		}

	
	}

}
