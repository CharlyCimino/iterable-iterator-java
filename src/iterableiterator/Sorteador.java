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
    private Random random;

    /**
     * Construye un nuevo sorteador de elementos.
     */
    public Sorteador() {
        this.elementos = new ArrayList<T>();
        this.random = new Random();
    }

    /**
     * Inserta <code>elemento</code> en este sorteador.
     *
     * @param elemento El elemento a insertar.
     */
    public void insertar(T elemento) {
        elementos.add(elemento);
    }

    /**
     * Inserta cada uno de los elementos de <code>elementos</code> en este
     * sorteador.
     *
     * @param elementos El array de elementos a insertar.
     */
    public void insertar(T[] elementos) {
        this.insertar(Arrays.asList(elementos));
    }

    /**
     * Inserta cada uno de los elementos de <code>elementos</code> en este
     * sorteador.
     *
     * @param elementos La lista de elementos a insertar.
     */
    public void insertar(List<T> elementos) {
        this.elementos.addAll(elementos);
    }

    /**
     * Retorna un elemento de este sorteador de manera aleatoria.
     *
     * @return un elemento de este sorteador de manera aleatoria.
     * @throws IllegalStateException Si ya no quedan elementos en este
     * sorteador.
     */
    public T proximoSorteado() {
        if (estaVacio()) {
            throw new IllegalStateException("No hay elementos en el sorteador");
        }
        int a = aleatorio(0, this.elementos.size() - 1);
        return this.elementos.remove(a);
    }

    /**
     * Retorna <code>true</code> si no hay elementos en este sorteador.
     *
     * @return <code>true</code> si no hay elementos en este sorteador.
     */
    public boolean estaVacio() {
        return this.elementos.isEmpty();
    }

    /**
     * Retorna un número entero aleatorio entre <code>min</code> y
     * <code>max</code>.
     *
     * @param min Entero mínimo a devolver (incluído).
     * @param max Entero máximo a devolver (incluído).
     * @return un número entero aleatorio entre <code>min</code> y
     * <code>max</code>.
     */
    private int aleatorio(int min, int max) {
        return random.nextInt((max - min + 1)) + min;
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
