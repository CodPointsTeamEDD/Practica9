import colas.Cola;
import listas.ListaDoblementeLigada;

/**
 * Clase concreta {@code ArbolBinarioCompleto}
 * Extiende de la clase {@link ArbolBinario}
 * 
 * @author Luis Fernando Quintana López
 * @author Erick Xavier Martinez Briones
 * @version 1.0.0
 * @since 2026
 * 
 */

public class ArbolBinarioCompleto<T> extends ArbolBinario<T> {

    /** Atributos de la clase ArbolBinarioCompleto */
    private Vertice ultimoAgregado;

    /** Constructor de la clase ArbolBinarioCompleto 
     * LLama al constructor de la clase ArbolBinario
     * Inicializa ultimoAgregado como null
    */
    public ArbolBinarioCompleto() {
        super();
        ultimoAgregado = null;
    }

    /**
     * Metodo que agrega un elemento de tipo T al arbol binario completo
     * @param elemento elemento de tipo T a agregar
     */
    @Override
    public void agregar(T elemento) {
        if (elemento == null) {
            throw new IllegalArgumentException("El elemento no puede ser vacio");
        }

        Vertice nuevo = new Vertice(elemento);

        if (this.estaVacio()) {
            this.raiz = nuevo;
            this.ultimoAgregado = nuevo;
            this.tamanio++;

        } else {

            Vertice vi = this.ultimoAgregado;

            if (vi.esHijoIzquierdo()) {
                vi.padre.agregaDerecho(nuevo);
                this.ultimoAgregado = nuevo;
                this.tamanio++;
                return;
            }

            while (vi.esHijoDerecho()) {
                vi = vi.padre;
            }

            if (vi.esHijoDerecho() || vi.esHijoIzquierdo()) {
                vi = vi.padre.derecho;
            }

            this.agregaEnPrimerEspacioIzquierdo(vi, nuevo);
            return;
        }
    }

    /**
     * Elimina un elemento del árbol binario completo.
     * 
     * La eliminación se realiza sustituyendo el elemento a eliminar
     * por el último elemento del árbol (según recorrido en amplitud)
     * y posteriormente eliminando dicho último vértice.
     *
     * @param elemento el elemento que se desea eliminar
     * @throws IllegalArgumentException si el elemento es nulo o el árbol está vacío
     */
    @Override
    public void eliminar(T elemento) {
        if (elemento == null || this.estaVacio()) {
            throw new IllegalArgumentException("Error al querer eliminar dicho elemento");
        }

        Vertice verticeAEliminar = null;
        Vertice ultimoVertice = null;
        Cola<Vertice> cola = new Cola<>();
        cola.meter(this.raiz);

        while (!cola.estaVacia()) {
            Vertice actual = cola.sacar();
            ultimoVertice = actual;

            if (actual.elemento.equals(elemento)) {
                verticeAEliminar = actual;
            }

            if (actual.izquierdo != null) {
                cola.meter(actual.izquierdo);
            }

            if (actual.derecho != null) {
                cola.meter(actual.derecho);
            }
        }

        if (verticeAEliminar == null) {
            return;
        }

        verticeAEliminar.elemento = ultimoVertice.elemento;

        if (ultimoVertice.padre == null) {
            this.raiz = null;
        } else {
            Vertice p = ultimoVertice.padre;
            if (p.izquierdo.equals(ultimoVertice)) {
                p.izquierdo = null;
            } else {
                p.derecho = null;
            }
        }

        this.tamanio--;
        this.actualizarUltimoAgregado();
    }

    /**
    * Metodo que ctualiza la referencia al último vértice agregado
    * recorriendo el árbol en amplitud.
    */
    private void actualizarUltimoAgregado() {
        if (this.estaVacio()) {
            this.ultimoAgregado = null;
            return;
        }

        Cola<Vertice> cola = new Cola<>();
        cola.meter(this.raiz);
        Vertice ultimo = null;

        while (!cola.estaVacia()) {
            ultimo = cola.sacar();

            if (ultimo.izquierdo != null) {
                cola.meter(ultimo.izquierdo);
            }

            if (ultimo.derecho != null) {
                cola.meter(ultimo.derecho);
            }
        }

        this.ultimoAgregado = ultimo;
    }

    /**
     * Metodo que dado un elemento de tipo T determina si esta o no en el arbol 
     * @param elemento elemento a buscar
     * @return {true} si el elementos esta en el arbol, {false} en otro caso
     */
    @Override
    public boolean buscar(T elemento) {
        return buscar(elemento, this.raiz);
    }

    /**
     * Busca recursivamente un elemento a partir de un vértice.
     *
     * @param elemento el elemento a buscar
     * @param v el vértice desde donde inicia la búsqueda
     * @return {true} si el elemento se encuentra, {false} en otro caso
     */
    private boolean buscar(T elemento, Vertice v) {
        if (v == null) {
            return false;
        }

        if (v.elemento.equals(elemento)) {
            return true;
        }

        return buscar(elemento, v.izquierdo) || buscar(elemento, v.derecho);
    }

    /**
     * Devuelve un recorrido del árbol en amplitud (por niveles).
     *
     * @return una ListaDoblementeLigada con los elementos del árbol en recorrido BFS
     */

    @Override
    public ListaDoblementeLigada<T> devolverRecorrido() {
        ListaDoblementeLigada<T> recorrido = new ListaDoblementeLigada<>();

        if (estaVacio()) {
            return recorrido;
        }

        Cola cola = new Cola<>();
        cola.meter(this.raiz);

        while (!cola.estaVacia()) {
            Vertice actual = (Vertice) cola.sacar();
            recorrido.agregarFinal(actual.elemento);

            if (actual.izquierdo != null) {
                cola.meter(actual.izquierdo);
            }

            if (actual.derecho != null) {
                cola.meter(actual.derecho);
            }
        }
        return recorrido;
    }

    /**
    * Inserta un nuevo vértice en el primer espacio disponible
    * siguiendo el camino más a la izquierda desde un vértice dado.
    *
    * @param desde el vértice desde donde se inicia la búsqueda
    * @param nuevo el vértice a insertar
    */
    private void agregaEnPrimerEspacioIzquierdo(Vertice desde, Vertice nuevo) {

        while (desde.izquierdo != null) {
            desde = desde.izquierdo;
        }

        desde.agregaIzquierdo(nuevo);
        this.ultimoAgregado = nuevo;
        this.tamanio++;
        return;
    }

}
