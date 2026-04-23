import colas.Cola;
import listas.ListaDoblementeLigada;

public class ArbolBinarioCompleto<T> extends ArbolBinario<T> {

    private Vertice ultimoAgregado;

    public ArbolBinarioCompleto() {
        super();
        ultimoAgregado = null;
    }

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

    @Override
    public boolean buscar(T elemento) {
        return buscar(elemento, this.raiz);
    }

    private boolean buscar(T elemento, Vertice v) {
        if (v == null) {
            return false;
        }

        if (v.elemento.equals(elemento)) {
            return true;
        }

        return buscar(elemento, v.izquierdo) || buscar(elemento, v.derecho);
    }

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
