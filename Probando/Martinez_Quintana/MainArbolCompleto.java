/**
 * Clase concreta {@code MainArbolCompleto}
 * 
 * @author Luis Fernando Quintana López
 * @author Erick Xavier Martinez Briones
 * @version 1.0.0
 * @since 2026
 * 
 */

public class MainArbolCompleto {
    public static void main(String[] args) {

        /** Creando un objeto arbol de la clase ArbolBinarioCompleto */
        ArbolBinarioCompleto<Integer> arbol = new ArbolBinarioCompleto<>();

        // insertar elementos
        for (int i = 1; i <= 10; i++) {
            arbol.agregar(i);
        }

        /** Imprime el recorrido despues de insertar los elementos */
        System.out.println("recorrido en amplitud después de insertar los elementos");
        System.out.println(arbol.devolverRecorrido());

        // probando buscar
        System.out.println("\nel árbol tiene como elemento al 5? " + arbol.buscar(5));
        System.out.println("el árbol tiene como elemento al 11? " + arbol.buscar(11));

        // probando eliminar
        System.out.println("\neliminado al 4");
        arbol.eliminar(4);
        System.out.println("recorrido después de eliminar al 4");
        System.out.println(arbol.devolverRecorrido());
    }

}