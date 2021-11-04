package iterableiterator;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

/**
 * Colección que permite ingresar elementos de cualquier tipo y devolverlos en
   orden aleatorio.
 * 
 * @author Charly Cimino Aprendé más Java en mi canal:
 * https://www.youtube.com/c/CharlyCimino Encontrá más código en mi repo de
 * GitHub: https://github.com/CharlyCimino
 * @param <T> Tipo de los elementos del sorteador
 */
public class Sorteador<T> implements Iterable<T> {

    private List<T> elementos;
    private Random random;

    /**
     * Construye un nuevo sorteador de elementos.
     */
    public Sorteador() {
        this.elementos = new ArrayList<>();
        this.random = new Random();
    }

    /**
     * Agrega <code>elemento</code> en este sorteador.
     *
     * @param elemento El elemento a agregar.
     */
    public void add(T elemento) {
        elementos.add(elemento);
    }

    /**
     * Inserta cada uno de los elementos de <code>elementos</code> en este
     * sorteador.
     *
     * @param elementos El array de elementos a add.
     */
    /*public void add(T[] elementos) {
        this.add(Arrays.asList(elementos));
    }*/

    /**
     * Inserta cada uno de los elementos de <code>elementos</code> en este
     * sorteador.
     *
     * @param elementos La colección con los elementos a agregar.
     */
    public void add(Collection<T> elementos) {
        this.elementos.addAll(elementos);
    }
    
    /**
     * Remueve un elemento de este sorteador de manera pseudoaleatoria. Retorna
     * el elemento removido. 
     *
     * @return el elemento removido de este sorteador.
     * @throws IllegalStateException Si ya no quedan elementos en este
     * sorteador.
     */
    public T remove() {
        checkEmptyness();
        int a = randomBetween(0, this.elementos.size() - 1);
        return this.elementos.remove(a);
    }

    /**
     * Retorna un elemento de este sorteador de manera pseudoaleatoria.
     *
     * @return un elemento de este sorteador de manera pseudoaleatoria.
     * @throws IllegalStateException Si ya no quedan elementos en este
     * sorteador.
     */
    public T get() {
        checkEmptyness();
        int a = randomBetween(0, this.elementos.size() - 1);
        return this.elementos.remove(a);
    }
    
    /**
     * Chequea si el sorteador está vacío y lanza una excepción en tal caso.
     */
    private void checkEmptyness() {
        if (isEmpty()) {
            throw new IllegalStateException("No hay elementos en el sorteador");
        }
    }

    /**
     * Retorna <code>true</code> si no hay elementos en este sorteador.
     *
     * @return <code>true</code> si no hay elementos en este sorteador.
     */
    public boolean isEmpty() {
        return this.elementos.isEmpty();
    }

    /**
     * Retorna un número entero pseudoaleatorio entre <code>min</code> y
     * <code>max</code> (inclusive).
     *
     * @param min Entero mínimo a devolver (inclusive).
     * @param max Entero máximo a devolver (inclusive).
     * @return un número entero pseudoaleatorio entre <code>min</code> y
     * <code>max</code> (inclusive).
     */
    private int randomBetween(int min, int max) {
        if (min > max) {
            throw new IllegalArgumentException("El parámetro 'min' (" + min + ") supera al parámetro 'max' (" + max + ")");
        }
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
        private final int ULTIMO;
        private int tope;
        
        private IteratorSorteador() {
            ULTIMO = elementos.size() - 1;
            tope = ULTIMO;
        }

        @Override
        public boolean hasNext() {
            return tope >= 0;
        }

        @Override
        public T next() {
            if (tope < 0) {
                throw new IllegalStateException("No hay más elementos en la iteración");
            }
            int rnd = randomBetween(0, tope);
            T elemento = elementos.remove(rnd);
            elementos.add(elemento); // Pone el sorteado al final
            tope--;
            return elemento;
        }        
    }
}
