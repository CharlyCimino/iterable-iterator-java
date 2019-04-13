package clases;

import java.util.Iterator;

/**
 * Prueba de la colección <code>Sorteador</code>
 * @author Carlos E. Cimino
 * @see <a href="http://www.github.com/caemci"/>http://www.github.com/caemci</a>
 */

public class Principal {

	public static void main(String[] args) {
		int cantPaises = 5;
		Sorteador<String> miSorteador = new Sorteador<String>(cantPaises);
		
		miSorteador.insertar("Argentina");
		miSorteador.insertar("Brasil");
		miSorteador.insertar("Perú");
		miSorteador.insertar("Bolivia");
		miSorteador.insertar("Venezuela");
		
		Iterator it = miSorteador.iterator(); // Pido un iterador al sorteador
		// Los iteradores permiten iterar sin conocer cómo funciona la colección por dentro
		while (it.hasNext()) {
			System.out.println( it.next() );
		}
	}
}
