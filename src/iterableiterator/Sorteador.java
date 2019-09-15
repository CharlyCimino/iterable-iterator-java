package iterableiterator;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

/**
 * Colección que permite ingresar elementos de cualquier tipo y devolverlos en
 * orden aleatorio.
 *
 * @author Carlos E. Cimino
 * @see <a href="http://www.github.com/caemci"/>http://www.github.com/caemci</a>
 */
public class Sorteador<T> implements Iterable<T> {

    private ArrayList<T> elementos;
    private ArrayList<T> candidatos;
    private Random random;

    /**
     * Construye un nuevo sorteador de elementos.
     */
    public Sorteador() {
        this.candidatos = new ArrayList<T>();
        this.elementos = new ArrayList<T>();
        this.random = new Random();
    }

    /**
     * Inserta <code>elemento</code> en el sorteador siempre y cuando no exista.
     *
     * @param elemento El elemento a insertar.
     */
    public void insertar(T elemento) {
        if (existe(elemento)) {
            throw new IllegalArgumentException("Ya existe el elemento " + elemento);
        } else {
            candidatos.add(elemento);
            elementos.add(elemento);
        }
    }

    /**
     * Inserta cada uno de los elementos de <code>elementos</code> en la
     * colección.
     *
     * @param elementos Los elementos a insertar.
     */
    public void insertar(T[] elementos) {
        this.insertar(Arrays.asList(elementos));
    }

    /**
     * Inserta cada uno de los elementos de <code>elementos</code> en la
     * colección.
     *
     * @param elementos Los elementos a insertar.
     */
    public void insertar(List<T> elementos) {
        this.candidatos.addAll(elementos);
        this.elementos.addAll(elementos);
    }

    /**
     * Retorna un elemento de la colección de manera aleatoria.
     *
     * @return un elemento de la colección de manera aleatoria.
     */
    public T proximoSorteado() {
        T elemento;
        if (estaVacio()) {
            throw new UnsupportedOperationException("No hay elementos en el sorteador");
        } else {
            int a = aleatorio(this.candidatos.size());
            elemento = this.candidatos.remove(a); // remove() lo devuelve antes de borrarlo
        }
        return elemento;
    }

    /**
     * Retorna <code>true</code> si no hay elementos en la colección.
     *
     * @return <code>true</code> si no hay elementos en esta colección.
     */
    public boolean estaVacio() {
        return this.candidatos.isEmpty();
    }

    /**
     * Retorna <code>true</code> si existe <code>elemento</code> en la
     * colección.
     *
     * @param elemento El elemento a comprobar.
     * @return <code>true</code> si existe <code>elemento</code> en esta
     * colección.
     */
    private boolean existe(T elemento) {
        return this.candidatos.contains(elemento);
    }

    /**
     * Retorna <code>true</code> si existe <code>elemento</code> en la
     * colección.
     *
     * @param elemento El elemento a comprobar.
     * @return <code>true</code> si existe <code>elemento</code> en esta
     * colección.
     */
    public void reiniciar() {
        this.candidatos = new ArrayList<T>(this.elementos);
    }

    /**
     * Retorna un número entero aleatorio entre 0 y <code>n-1</code>
     *
     * @param n Entero máximo a devolver (sin incluir)
     * @return un número entero aleatorio entre 0 y <code>n-1</code>
     */
    private int aleatorio(int n) {
        return random.nextInt(n);
    }

    /**
     * Retorna un iterador para esta colección
     *
     * @return un iterador para esta colección
     */
    @Override
    public Iterator<T> iterator() {
        return new IteratorSorteador();
    }

    private class IteratorSorteador implements Iterator<T> {

        @Override
        public boolean hasNext() {
            return !estaVacio();
        }

        @Override
        public T next() {
            return proximoSorteado();
        }
    }
}
