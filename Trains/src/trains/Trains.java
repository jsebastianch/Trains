/*
 * To change this license header, choose License Headers in Project Properties. To change this template file, choose Tools |
 * Templates and open the template in the editor.
 */
package trains;

import java.util.ArrayList;
import java.util.List;

/**
 * Clase principal que inicia el programa.
 * @author schavez
 */
public class Trains {
    /**
     * Metodo que da inicio a la ejecucion del programa.
     * @param args argumentos de linea de comandos
     */
    public static void main(String[] args) {
        // Creacion de ciudades
        Ciudad ciudadA = new Ciudad("A");
        Ciudad ciudadB = new Ciudad("B");
        Ciudad ciudadC = new Ciudad("C");
        Ciudad ciudadD = new Ciudad("D");
        Ciudad ciudadE = new Ciudad("E");
        List<Ciudad> ciudades = new ArrayList<>();
        ciudades.add(ciudadA);
        ciudades.add(ciudadB);
        ciudades.add(ciudadC);
        ciudades.add(ciudadD);
        ciudades.add(ciudadE);
        MapaCiudad mapaCiudad = new MapaCiudad(ciudades);
        // Lista de rutas segun la definicion del grafo
        // Grafo: AB5, BC4, CD8, DC8, DE6, AD5, CE2, EB3, AE7
        List<Ruta> rutas = new ArrayList<>();
        rutas.add(new Ruta(ciudadA, ciudadB, 5));
        rutas.add(new Ruta(ciudadB, ciudadC, 4));
        rutas.add(new Ruta(ciudadC, ciudadD, 8));
        rutas.add(new Ruta(ciudadD, ciudadC, 8));
        rutas.add(new Ruta(ciudadD, ciudadE, 6));
        rutas.add(new Ruta(ciudadA, ciudadD, 5));
        rutas.add(new Ruta(ciudadC, ciudadE, 2));
        rutas.add(new Ruta(ciudadE, ciudadB, 3));
        rutas.add(new Ruta(ciudadA, ciudadE, 7));
        // Creacion del mapa de ciudades con sus rutas
        for (Ruta ruta : rutas) {
            mapaCiudad.agregarRuta(ruta.getOrigen(), ruta);
        }
        calcularLongitud(mapaCiudad, "A-B-C");
        calcularLongitud(mapaCiudad, "A-D");
        calcularLongitud(mapaCiudad, "A-D-C");
        calcularLongitud(mapaCiudad, "A-E-B-C-D");
        calcularLongitud(mapaCiudad, "A-E-D");
        numeroRutas(mapaCiudad, ciudadC, ciudadC, 3, Boolean.FALSE);
        numeroRutas(mapaCiudad, ciudadA, ciudadC, 4, Boolean.TRUE);
        rutaMasCorta(mapaCiudad, ciudadA, ciudadC);
        rutaMasCorta(mapaCiudad, ciudadB, ciudadB);
        numeroRutas(mapaCiudad, ciudadC, ciudadC, 30);
    }

    /**
     * Calcula la longitud total de una ruta de ciudades recibida.
     * @param mapaCiudad mapa total de la ciudad
     * @param ruta string con la lista de ciudades de la ruta separadas por un guion (-)
     */
    static void calcularLongitud(MapaCiudad mapaCiudad, String ruta) {
        try {
            System.out.println("La longitud de la ruta " + ruta + " es: " + mapaCiudad.obtenerLongitudTotalRuta(ruta));
        } catch (RuntimeException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Calcula el numero de rutas posibles entre dos ciudades.
     * @param mapaCiudad mapa total de la ciudad
     * @param ciudadOrigen ciudad de origen de las rutas
     * @param ciudadDestino ciudad destino a la que llegaran las rutas
     * @param paradas define el numero de paradas que podra tener la ruta
     * @param paradasExactas si el valor es verdadero, identifica que la ruta debe tener un numero exacto de paradas, caso
     *        contrario la ruta puede tener un numero igual o menor de paradas que el parametro {@code paradas}
     */
    static void numeroRutas(MapaCiudad mapaCiudad, Ciudad ciudadOrigen, Ciudad ciudadDestino, Integer paradas,
            Boolean paradasExactas) {
        try {
            System.out.println("Numero rutas posibles con " + (paradasExactas ? "exactamente " : "maximo ") + paradas
                    + " paradas, desde " + ciudadOrigen.getNombre() + " a " + ciudadDestino.getNombre() + " es: "
                    + mapaCiudad.obtenerNumeroRutasPosibles(ciudadOrigen, ciudadDestino, paradas, paradasExactas));
        } catch (RuntimeException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Calcula la ruta mas corta entre dos ciudades.
     * @param mapaCiudad mapa total de la ciudad
     * @param ciudadOrigen ciudad de origen de las rutas
     * @param ciudadDestino ciudad destino a la que llegaran las rutas
     */
    static void rutaMasCorta(MapaCiudad mapaCiudad, Ciudad ciudadOrigen, Ciudad ciudadDestino) {
        try {
            System.out.println("Ruta mas corta, desde " + ciudadOrigen.getNombre() + " a " + ciudadDestino.getNombre() + " es: "
                    + mapaCiudad.obtenerRutaMasCorta(ciudadOrigen, ciudadDestino));
        } catch (RuntimeException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Calcula un numero de rutas posibles entre dos ciudades siempre y cuando que la distancia sea menor al parametro
     * {@code longitudMaxima}
     * @param mapaCiudad mapa total de la ciudad
     * @param ciudadOrigen ciudad de origen de las rutas
     * @param ciudadDestino ciudad destino a la que llegaran las rutas
     * @param longitudMaxima distancia maxima de la ruta
     */
    static void numeroRutas(MapaCiudad mapaCiudad, Ciudad ciudadOrigen, Ciudad ciudadDestino, Integer longitudMaxima) {
        try {
            System.out.println("Numero rutas posibles desde la ciudad " + ciudadOrigen.getNombre() + " a la ciudad "
                    + ciudadDestino.getNombre() + " con longitud menor a: " + longitudMaxima + ", desde "
                    + ciudadOrigen.getNombre() + " a " + ciudadDestino.getNombre() + " es: "
                    + mapaCiudad.obtenerNumeroRutasConMaximoLongitud(ciudadOrigen, ciudadDestino, longitudMaxima));
        } catch (RuntimeException e) {
            System.out.println(e.getMessage());
        }
    }
}
