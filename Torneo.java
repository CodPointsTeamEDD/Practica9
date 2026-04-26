import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import listas.ListaLigadaSimple;
import torneo.AdivinadorResultados;
import torneo.Cinta;
import torneo.Participante;

public class Torneo{
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

		/* JUsto ahora g contiene a los participantes */
		/* Ahora necesito ordenarlos, para lo cual deben tener un método compareTo */

		System.out.println(g.toString());

		OrdenamientosNoCuadraticos.mergeSort(g);

		System.out.println(g.toString());


        // Neceisto leer los participantes. Mientras los leo meterlos a alguna estructura
        // (me parece que lista), les aplico un algoritmo de ordenamiento. 

        // Cuento los bichos leidos. 

        // Calculo la altura para que todos los bichos queden en las hojas

        // calculo la cantidad de elementos de un arbol con un nivel menos que el resultante

        // meto la palabra "pendiente" tantas veces como diga el calculo anterior

        // LUego meto todos los bichos leidos del txt. 

        // Quería meter null en los espacios restantes, pero en realidad es un arbol completo
        // no perfecto, así que supongo lo puedo dejar sin nodos. 



    }
}