package clases;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;

/**
 * Colección que permite ingresar elementos de cualquier tipo y devolverlos en orden aleatorio.
 * @author Carlos E. Cimino
 * @see <a href="http://www.github.com/caemci"/>http://www.github.com/caemci</a>
 */

public class Sorteador <T> implements Iterable<T> {
	private ArrayList<T> elementos;
	private final int CANT_MAXIMA;
	
	/**
     * Construye un nuevo <code>Sorteador</code> de elementos.
     * @param cantMaxima Número máximo de elementos que alojará el sorteador
     */
	public Sorteador(int cantMaxima) {
		this.CANT_MAXIMA = cantMaxima;
		this.elementos = new ArrayList<T>();
	}
	
	/**
     * Inserta <code>elemento</code> en la colección siempre y cuando no esté llena ni ya exista.
     * @param elemento El elemento a insertar.
     */
	public void insertar (T elemento) {
		if ( estaLleno() ) {
			System.out.println("Sorteador lleno.");
		} else if ( existe(elemento) ) {
			System.out.println("Ya existe el elemento " + elemento);
		} else {
			elementos.add(elemento);
		}
	}
	
	/**
     * Obtiene un elemento de la colección de manera aleatoria.
     * @return El elemento obtenido
     */
	public T obtener() {
		T elemento = null;
		if ( !estaVacio() ) {
			int a = aleatorio(this.elementos.size());
			elemento = this.elementos.remove(a); // Lo elimina y lo devuelve
		}
		return elemento;
	}
	
	/**
     * Retorna <code>true</code> si no hay elementos en la colección.
     * @return Retorna <code>true</code> si no hay elementos en esta colección.
     */
	public boolean estaVacio () {
		return this.elementos.isEmpty();
	}
	
	/**
     * Retorna <code>true</code> si se llegó al máximo de elementos en la colección.
     * @return Retorna <code>true</code> si se llegó al máximo elementos en esta colección.
     */
	public boolean estaLleno () {
		return this.elementos.size() == CANT_MAXIMA;
	}
	
	/**
     * Retorna <code>true</code> si existe <code>elemento</code> en la colección.
     * @param elemento El elemento a comprobar.
     * @return Retorna <code>true</code> si existe <code>elemento</code> en esta colección.
     */
	private boolean existe (T elemento) {
		return this.elementos.contains(elemento);
	}
	
	/**
     * Retorna un entero aleatorio entre 0 y <code>n-1</code>
     * @param n Entero máximo a devolver (sin incluir)
     * @return Retorna <code>true</code> si existe <code>elemento</code> en esta colección.
     */
	private int aleatorio (int n) {
		Random r = new Random(System.currentTimeMillis());
		return r.nextInt(n);
	}

	// La interfaz 'Iterable' obliga a sobreescribir este método
	@Override
	public Iterator<T> iterator() {
		return new IteratorSorteador(); // Retorno un iterador propio de este tipo de colección
	}
	
	private class IteratorSorteador implements Iterator<T> {
		// Una clase anidada puede ver todos los miembros de la clase contenedora
		@Override
		public boolean hasNext() {
			return !estaVacio(); // Para saber si hay un siguiente, basta responde si NO está vacío
		}

		@Override
		public T next() {
			return obtener(); // Para retorna el siguiente, invocamos al método obtener()
		}
	}
}
