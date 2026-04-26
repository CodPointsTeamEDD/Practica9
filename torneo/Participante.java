package torneo;
/**
 * Clase {@code Participantes}
 * 
 * @author Luis Fernando Quintana López
 * @author Erick Xavier Martinez Briones
 * @version 1.0.0
 * @since 2026
 * 
 */

public class Participante implements Comparable<Participante>{
	/*numero de medallas conseguidas por el participante*/
	int numMedallas;

	/*numero de torneos en los que a participado el participante*/
	int numTorneos;
	
	/*cinta que posee el participante*/
	Cinta cinta;

	/* Nombre del participante */
	String nombre;

	/** 
	 * Constructor por parametro de Participante
	 * @param nombre nombre dle participante
	 * @param numMedallas numero de medallas conseguidas por el participante
	 * @param numTorneos numero de torneos en los que a participado el participante
	 * @param cinta cinta que posee el participante
	*/
	public Participante(String nombre, int numMedallas, int numTorneos, Cinta cinta){	
		this.nombre = nombre;
		this.numMedallas = numMedallas;
		this.numTorneos = numTorneos;
		this.cinta = cinta;
	}

	/** 
	 * Metodo que calcula el indice de victorias del Participante
	 * @return numero Real que representa el indice de victorias
	*/
	public double indiceDeVictorias(){	
		return (double) this.getNumMedallas() / (double) this.getNumTorneos();
	}

	// Getters

	/** 
	 * metodo para obtener el nombre del participante
	 * @return devuelve un String con el nombre del participante
	*/
	public String getNombre(){
		return this.nombre;
	}

	/** 
	 * metodo para obtener el numero de medallas
	 * @return devuelve el numero de medallas de Participante
	*/
	public int getNumMedallas(){
		return this.numMedallas;
	}

	/** 
	 * metodo para obtener el numero de torneos en los que ha participado el individuo
	 * @return devuelve el numero de torneos 
	*/
	public int getNumTorneos(){
		return this.numTorneos;
	}

	/** 
	 * metodo para obtener la cinta del participante
	 * @return devuelve la cinta
	*/
	public Cinta getCinta(){
		return this.cinta;
	}

	/** 
	 * método que compara participantes
	*/
	public int compareTo(Participante p){
		if(this.indiceDeVictorias() > p.indiceDeVictorias()) return 1;
		if(this.cinta.getNivelNumerico() > this.cinta.getNivelNumerico()) return 1;

		if(this.indiceDeVictorias() == p.indiceDeVictorias()) return 0;
		if(this.cinta.getNivelNumerico() == this.cinta.getNivelNumerico()) return 0;

		return -1;
	}

	@Override
	public String toString(){
		String cadena = "";
		cadena = cadena + "Nombre: " + getNombre() + ", ";
		cadena = cadena + "NumMedallas: " + getNumMedallas() + ", ";
		cadena = cadena + "Num torneos: " + getNumTorneos() + ", ";
		cadena = cadena + "Cinta: " + getCinta().getNombre();
		cadena = cadena + "Nivel numerico: " + getCinta().getNivelNumerico();
		cadena = cadena + "\n";

		return cadena;
	}
}
