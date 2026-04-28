import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import listas.ListaDoblementeLigada;
import ordenamientos.OrdenamientosNoCuadraticos;
import torneo.AdivinadorResultados;
import torneo.Cinta;
import torneo.Participante;

/**
 * Clase principal la cúal muestra el resultado al usuario
 * 
 * @author Erick Xavier Martinez Briones
 * @author Luis Fernando Quintana López
 * @version 1.0.0
 * @since 2026
 */

public class Torneo{
	/** 
	 * Método principal que muestra el árbol binario generado.
	 * @param args argumentos pasados por la términal al ejecutar
	*/
    public static void main(String[] args) {
        /* ----------------------------------------------------- */
		/*     Lectura de archivo y extracción de información    */
		/* ----------------------------------------------------- */

		/* Nombre del archivo a leer */
		String nombreArchivo = "participantes.txt";

		/* Variables que almacenarán participantes */
		ListaDoblementeLigada<Participante> g = new ListaDoblementeLigada<>(); 
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


		/* Ordenando participantes */
		OrdenamientosNoCuadraticos.mergeSort(g);
		g = g.reversa();

		/* Calculando la altura del árbol */
		int cantidadDeElementos = g.devolverLongitud();
		int altura = 0;
		int hojasPosibles = 1;

		while(hojasPosibles < cantidadDeElementos){
			if (hojasPosibles < cantidadDeElementos) {
				altura = altura+1;
				hojasPosibles = (int) Math.pow(2, altura);
			} 
		}


		// Calculando elementos pendientes
		int numPendientes = 0;
		for (int i = 0; i < altura; i++) {
			numPendientes = (int) Math.floor(numPendientes + Math.pow(2, i));
		}

		// Creando arbol con elementos por defecto
		ArbolBinarioCompleto<String> arbol = new ArbolBinarioCompleto<>(); 
		for (int i = 0; i < numPendientes; i++) {
			arbol.agregar("pendiente");
		}

		// Agregando los participantes
		for (Participante part : g) {
			arbol.agregar(part.getNombre());
		}

		// Mostrando árbol
		System.out.println(arbol.toString());		

    }
}