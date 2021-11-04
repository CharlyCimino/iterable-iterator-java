package iterableiterator;

import java.util.Iterator;

/**
 * Prueba de la colección <code>Sorteador</code>
 *
 * @author Carlos E. Cimino
 * @see <a href="http://www.github.com/CharlyCimino"/>http://www.github.com/CharlyCimino</a>
 */
public class Principal {

    public static void main(String[] args) {
        Sorteador<String> miSorteador = new Sorteador<>(); // Instancia un sorteador de Strings

        cargarSorteadorConDatos(miSorteador); // Carga algunos Strings

        ///////////////// Iterar con un iterador /////////////////////
        System.out.println("Iterar con un iterador");
        // Los iteradores permiten iterar cualquier colección sin conocer sus detalles de implementación
        Iterator<String> it = miSorteador.iterator(); // Pido un iterador al sorteador
        while (it.hasNext()) { // Mientras haya siguiente
            String elemento = it.next(); // Guardar siguiente en 'elemento'
            System.out.println(elemento); // Mostrar 'elemento'
        }

        ///////////////// Iterar con foreach /////////////////////
        System.out.println("Iterar con foreach");
        // Foreach permite iterar cualquier colección sin conocer sus detalles de implementación. Utiliza el iterador de la colección.
        for (String elemento : miSorteador) { // Para cada 'elemento' de tipo 'String' en la colección 'miSorteador'
            System.out.println(elemento); // Mostrar 'elemento'
        }
    }

    public static void cargarSorteadorConDatos(Sorteador<String> miSorteador) {
        miSorteador.add("1. Asia");
        miSorteador.add("2. América");
        miSorteador.add("3. África");
        miSorteador.add("4. Antártida");
        miSorteador.add("5. Europa");
        miSorteador.add("6. Oceanía");
    }
}
