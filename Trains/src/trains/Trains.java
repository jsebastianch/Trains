/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package trains;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author schavez
 */
public class Trains {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
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
    	//Graph: AB5, BC4, CD8, DC8, DE6, AD5, CE2, EB3, AE7
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
    	
    	for (Ruta ruta : rutas) {
			mapaCiudad.agregarRuta(ruta.getOrigen(), ruta);
		}
    	
    	System.out.println("La longitud de la ruta A-B-C es:" + mapaCiudad.obtenerlongitudTotalRuta("A","B","C"));
    	
    }
    
}
