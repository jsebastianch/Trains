/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package trains;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author schavez
 */
public class MapaCiudad {

	private Map<Ciudad, LinkedList<Ruta>> ciudades;

	public MapaCiudad() {
		super();
		this.ciudades = new HashMap<>();
	}

	public MapaCiudad(List<Ciudad> listaCiudades) {
		this();
		for (Ciudad ciudad : listaCiudades) {
			this.ciudades.put(ciudad, new LinkedList<Ruta>());
		}
	}

	public void agregarRuta(Ciudad ciudad, Ruta ruta) {
		if (this.ciudades.containsKey(ciudad)) {
			this.ciudades.get(ciudad).add(ruta);
		} else {
			System.out.println("No existe la ciudad " + ciudad.getNombre());
		}
	}

	public Integer obtenerlongitudTotalRuta(String... ciudades) {
		Integer longitud = 0;
		List<String> ciudadesArray = Arrays.asList(ciudades);
		for (String nombreCiudad : ciudadesArray) {
			if (ciudadesArray.indexOf(nombreCiudad) >= ciudadesArray.size() - 1) {
				break;
			}
			if (this.ciudades.containsKey(new Ciudad(nombreCiudad))) {

				for (Ruta ruta : this.ciudades.get(new Ciudad(nombreCiudad))) {
					if (ruta.getDestino()
							.equals(new Ciudad(ciudadesArray.get(ciudadesArray.indexOf(nombreCiudad) + 1)))) {
						longitud = longitud + ruta.getDistancia();
					}
				}
			}
		}
		return longitud;
	}

	public Map<Ciudad, LinkedList<Ruta>> getCiudades() {
		return this.ciudades;
	}

}
