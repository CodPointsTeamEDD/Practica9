package ordenamientos;

import listas.*;

/**
 * Clase que encapsula métodos de ordenamiento no cuadráticos
 * 
 * @author Erick Xavier Martinez Briones
 * @author Luis Fernando Quintana López
 * @version 1.0.0
 * @since 2026
 */

import java.util.Iterator;
public class OrdenamientosNoCuadraticos {

    /**
     * Ordena un arreglo de enteros utilizando el algoritmo Counting Sort.
     * El algoritmo itera el arreglo y lleva un conteo (en otro arreglo) sobre 
     * la cantidad de apariciones que tiene cada número. Posteriormente, 
     * el arreglo que lleva el conteo es "vaciado" y los elementos son agregados
     * al arreglo original en sus posiciones correspondientes. 
     *
     * @param arreglo el arreglo de enteros a ordenar
     */
    public static void countingSort(Integer[] arreglo) {
        int mayor = obtenerMax(arreglo);
        int[] conteo = new int[mayor+1];
        for (int i = 0; i < arreglo.length; i++) {
            conteo[arreglo[i]] = conteo[arreglo[i]] + 1; 
        }    
        int indice = 0;

        for (int i = 0; i < conteo.length; i++) {
            while(conteo[i] > 0){
                arreglo[indice] = i;
                indice = indice + 1; 
                conteo[i] = conteo[i] - 1;
            }
        }
    }

    /**
     * Obtiene el valor máximo dentro de un arreglo de enteros.
     *
     * @param arreglo el arreglo de enteros
     * @return el valor máximo encontrado en el arreglo
     */
    private static int obtenerMax(Integer[] arreglo) {
        int max = arreglo[0];

        for (Integer elem : arreglo) {
            if (elem > max) {
                max = elem;
            }
        }
        return max;
    }

    /**
     * Ordena una lista doblemente ligada utilizando el algoritmo Merge Sort.
     * Divide la lista en dos mitades, ordena cada mitad recursivamente
     * y luego las mezcla en una sola lista ordenada.
     *
     * @param lista la lista doblemente ligada que se desea ordenar
     * 
     */
    public static <T extends Comparable<T>> void mergeSort(ListaDoblementeLigada<T> lista) {
        if (lista.devolverLongitud() < 2) {
            return;
        }
        ListaDoblementeLigada<T> izquierda = new ListaDoblementeLigada<>();        
        ListaDoblementeLigada<T> derecha = new ListaDoblementeLigada<>();        

        int mitad = (int) lista.devolverLongitud()/2;

        int i = 0;

        for (T aux : lista) {
            if(i < mitad){
                izquierda.agregarFinal(aux);
            } else {
                derecha.agregarFinal(aux);
            }
            i = i+1;
        }

        mergeSort(izquierda);
        mergeSort(derecha);

        ListaDoblementeLigada<T> resultado = mezclar(izquierda, derecha);        
        ListaDoblementeLigada<T>.Nodo nodoActualLista = lista.cabeza;        
        ListaDoblementeLigada<T>.Nodo nodoActualResultado = resultado.cabeza;        


        while (nodoActualLista != null && nodoActualResultado != null) { 
            nodoActualLista.elemento = nodoActualResultado.elemento;
            nodoActualLista = nodoActualLista.siguiente;
            nodoActualResultado = nodoActualResultado.siguiente;
        }

        
    }

    /**
     * Mezcla dos listas doblemente ligadas previamente ordenadas en una sola lista ordenada.
     * Compara los elementos de ambas listas y los inserta en una nueva lista en orden ascendente.
     * Se asume que ambas listas de entrada ya están ordenadas.
     *
     * @param li la lista izquierda ordenada
     * @param ld la lista derecha ordenada
     * @return una nueva lista doblemente ligada que contiene todos los elementos de li y ld en orden
     */
    private static <T extends Comparable<T>> ListaDoblementeLigada<T> mezclar(ListaDoblementeLigada<T> li, ListaDoblementeLigada<T> ld) {
        ListaDoblementeLigada<T>.Nodo ni = li.cabeza;        
        ListaDoblementeLigada<T>.Nodo nd = ld.cabeza;        
        ListaDoblementeLigada<T> resultado = new ListaDoblementeLigada<>();        

        while(ni != null && nd != null){
            if (ni.elemento.compareTo(nd.elemento) <= 0) {
                resultado.agregarFinal(ni.elemento);
                ni = ni.siguiente;
            } else {
                resultado.agregarFinal(nd.elemento);
                nd = nd.siguiente;
            }
        }
        ListaDoblementeLigada<T>.Nodo restante;
        if (ni != null) {
            restante = ni;
        } else {
            restante = nd;
        }
        while(restante != null){
            resultado.agregarFinal(restante.elemento);
            restante = restante.siguiente;
        }
        return resultado;
    }

    /**
     * Ordena un arreglo utilizando el algoritmo Quick Sort.
     * Este método actúa como punto de entrada y llama a una función auxiliar
     * que aplica el algoritmo de manera recursiva.
     * 
     * @param arreglo el arreglo a ordenar
     */
    public static <T extends Comparable<T>> void quickSort(T[] arreglo) {
        quickSortAux(arreglo, 0, arreglo.length-1);
    }
    /**
     * Método auxiliar recursivo que implementa el algoritmo Quick Sort.
     * Divide el arreglo en subarreglos más pequeños alrededor de un pivote,
     * y ordena recursivamente las particiones izquierda y derecha.
     *
     * @param arreglo el arreglo a ordenar
     * @param inicio índice inicial del subarreglo
     * @param fin índice final del subarreglo
     *
     */
    private static <T extends Comparable<T>> void quickSortAux(T[] arreglo, int inicio, int fin) {
        if (inicio >= fin) {
            return;
        }
        int indicePivote = particionar(arreglo, inicio, fin);

        quickSortAux(arreglo, inicio, indicePivote-1);
        quickSortAux(arreglo, indicePivote + 1, fin);

    }

    /**
     * Reorganiza un subarreglo alrededor de un pivote utilizando.
     * Selecciona el último elemento como pivote y reordena el arreglo de tal manera que
     * todos los elementos menores o iguales al pivote quedan a su izquierda
     * y todos los elementos mayores quedan a su derecha
     *
     * Al finalizar, el pivote se coloca en su posición final dentro del arreglo ordenado.
     *
     * @param arreglo el arreglo que contiene los elementos a particionar
     * @param inicio índice inicial del subarreglo
     * @param fin índice final del subarreglo (posición del pivote)
     * @return la posición final del pivote después de la partición
     *
     */
    private static <T extends Comparable<T>> int particionar(T[] arreglo, int inicio, int fin) {
        T pivote = arreglo[fin];   // pivote ← arreglo[fin]
        int i = inicio - 1;        // i ← inicio - 1

        for (int j = inicio; j < fin; j++) {
            // arreglo[j] ≤ pivote usando compareTo
            if (arreglo[j].compareTo(pivote) <= 0) {
                i++;

                // intercambiar arreglo[i] y arreglo[j]
                intercambiar(arreglo, i, j);
            }
        }

        // intercambiar arreglo[i + 1] y arreglo[fin]
        intercambiar(arreglo, i + 1, fin);
        return i + 1;
    }

    /**
     * Intercambia dos elementos dentro de un arreglo.
     *
     * @param arreglo el arreglo en el que se realizará el intercambio
     * @param i índice del primer elemento
     * @param j índice del segundo elemento
     *
     */
    private static <T> void intercambiar(T[] arreglo, int i, int j) {
        T temp = arreglo[i];
        arreglo[i] = arreglo[j];
        arreglo[j] = temp;
    }
}
