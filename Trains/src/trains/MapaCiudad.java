/*
 * To change this license header, choose License Headers in Project Properties. To change this template file, choose Tools |
 * Templates and open the template in the editor.
 */
package trains;

import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.LogManager;
import java.util.logging.Logger;

/**
 * Esta clase contiene la informacion del grafo direccionado conformado por las ciudades y las rutas que las conectan entre si.
 * @author schavez
 */
public class MapaCiudad {
    /** Mapa de las ciudades con sus respectivas rutas. */
    private Map<Ciudad, LinkedList<Ruta>> ciudades;

    /**
     * Crea una nueva instancia de la clase MapaCiudad
     */
    public MapaCiudad() {
        super();
        this.ciudades = new HashMap<>();
    }

    /**
     * Crea una nueva instancia de la clase MapaCiudad.
     * @param listaCiudades lista de ciudades que conforman el mapa
     */
    public MapaCiudad(List<Ciudad> listaCiudades) {
        this();
        LogManager.getLogManager().reset();
        for (Ciudad ciudad : listaCiudades) {
            this.ciudades.put(ciudad, new LinkedList<Ruta>());
        }
    }

    /**
     * Agrega una ruta a una ciudad.
     * @param ciudad ciudad a la que se agrega la ruta
     * @param ruta ruta a ser agregada a la lista
     */
    public void agregarRuta(Ciudad ciudad, Ruta ruta) {
        if (this.ciudades.containsKey(ciudad)) {
            this.ciudades.get(ciudad).add(ruta);
        } else {
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "No existe la ciudad " + ciudad.getNombre());
        }
    }

    /**
     * Obtiene la longitud de una ruta entre ciudades.
     * @param rutaCiudades ruta de las ciudades, las cuales estan separadas por un guion (-)
     * @return la longitud total de la ruta
     * @throws RuntimeException si no se encuentra una ruta entre dos ciudades
     */
    public Integer obtenerLongitudTotalRuta(String rutaCiudades) throws RuntimeException {
        String[] ciudades = rutaCiudades.split("-");
        Integer longitud = 0;
        List<String> ciudadesArray = Arrays.asList(ciudades);
        for (String nombreCiudad : ciudadesArray) {
            if (ciudadesArray.indexOf(nombreCiudad) >= ciudadesArray.size() - 1) {
                break;
            }
            if (this.ciudades.containsKey(new Ciudad(nombreCiudad))) {
                Boolean destinoEncontrado = Boolean.FALSE;
                for (Ruta ruta : this.ciudades.get(new Ciudad(nombreCiudad))) {
                    if (ruta.getDestino().equals(new Ciudad(ciudadesArray.get(ciudadesArray.indexOf(nombreCiudad) + 1)))) {
                        destinoEncontrado = Boolean.TRUE;
                        longitud = longitud + ruta.getDistancia();
                    }
                }
                if (Boolean.FALSE.equals(destinoEncontrado)) {
                    throw new RuntimeException("NO EXISTE LA RUTA " + rutaCiudades);
                }
            } else {
                throw new RuntimeException("NO EXISTE LA RUTA " + rutaCiudades);
            }
        }
        return longitud;
    }

    /**
     * Calcula el numero de rutas posibles entre dos ciudades.
     * @param ciudadOrigen ciudad de origen de las rutas
     * @param ciudadDestino ciudad destino a la que llegaran las rutas
     * @param paradas define el numero de paradas que podra tener la ruta
     * @param paradasExactas si el valor es verdadero, identifica que la ruta debe tener un numero exacto de paradas, caso
     *        contrario la ruta puede tener un numero igual o menor de paradas que el parametro {@code paradas}
     * @return el numero de rutas encontradas
     */
    public Integer obtenerNumeroRutasPosibles(Ciudad ciudadOrigen, Ciudad ciudadDestino, Integer paradas,
            Boolean paradasExactas) {
        Integer viajes = 0;
        if (this.ciudades.containsKey(ciudadOrigen)) {
            for (Ruta ruta : this.ciudades.get(ciudadOrigen)) {
                Logger.getLogger(this.getClass().getName()).log(Level.INFO,
                        "Inicio Ruta ciudad origen: " + ciudadOrigen.getNombre());
                try {
                    viajes = this.obtenerNumeroParadas(ruta.getDestino(), ciudadDestino, paradas, paradasExactas, 0, viajes);
                } catch (RuntimeException e) {
                    continue;
                }
            }
        } else {
            throw new RuntimeException("No existe la ciudad de origen");
        }
        return viajes;
    }

    /**
     * Obtiene la longitud de la ruta mas corta entre dos ciudades.
     * @param ciudadOrigen ciudad de donde parte la ruta
     * @param ciudadDestino ciudad destino de la ruta
     * @return longitud total de la ruta mas corta
     */
    public Integer obtenerRutaMasCorta(Ciudad ciudadOrigen, Ciudad ciudadDestino) {
        Integer rutaMasCorta = null;
        if (this.ciudades.containsKey(ciudadOrigen)) {
            for (Ruta ruta : this.ciudades.get(ciudadOrigen)) {
                Logger.getLogger(this.getClass().getName()).log(Level.INFO,
                        "Inicio Ruta ciudad origen: " + ciudadOrigen.getNombre());
                try {
                    rutaMasCorta = this.rutaMasCorta(ruta.getDestino(), ciudadDestino, ruta.getDistancia(), rutaMasCorta);
                } catch (RuntimeException e) {
                    continue;
                }
            }
        } else {
            throw new RuntimeException("No existe la ciudad de origen");
        }
        return rutaMasCorta;
    }

    /**
     * Calcula un numero de rutas posibles entre dos ciudades siempre y cuando que la distancia sea menor al parametro
     * {@code longitudMaxima}
     * @param ciudadOrigen ciudad de origen de las rutas
     * @param ciudadDestino ciudad destino a la que llegaran las rutas
     * @param maximaDistancia distancia maxima de la ruta
     * @return un numero de rutas posibles entre dos ciudades
     */
    public Integer obtenerNumeroRutasConMaximoLongitud(Ciudad ciudadOrigen, Ciudad ciudadDestino, Integer maximaDistancia) {
        Integer viajes = 0;
        if (this.ciudades.containsKey(ciudadOrigen)) {
            for (Ruta ruta : this.ciudades.get(ciudadOrigen)) {
                Logger.getLogger(this.getClass().getName()).log(Level.INFO,
                        "Inicio Ruta ciudad origen: " + ciudadOrigen.getNombre());
                try {
                    viajes = this.rutaConMaximoLongitud(ruta.getDestino(), ciudadDestino, ruta.getDistancia(), maximaDistancia,
                            viajes);
                } catch (RuntimeException e) {
                    continue;
                }
            }
        } else {
            throw new RuntimeException("No existe la ciudad de origen");
        }
        return viajes;
    }

    /**
     * Obtiene el atributo de clase: "ciudades"
     * @return el/la ciudades
     */
    public Map<Ciudad, LinkedList<Ruta>> getCiudades() {
        return this.ciudades;
    }

    /**
     * Metodo recursivo que calcula el numero de rutas posibles entre dos ciudades.
     * @param ciudadOrigen ciudad de origen de las rutas
     * @param ciudadDestino ciudad destino a la que llegaran las rutas
     * @param paradas define el numero de paradas que podra tener la ruta
     * @param paradasExactas si el valor es verdadero, identifica que la ruta debe tener un numero exacto de paradas, caso
     *        contrario la ruta puede tener un numero igual o menor de paradas que el parametro {@code paradas}ç
     * @param contadorParadas
     * @param viajes
     * @return el numero de rutas encontradas definidos por el valor final del parametro viajes
     */
    private Integer obtenerNumeroParadas(Ciudad ciudadOrigen, Ciudad ciudadDestino, Integer paradas, Boolean paradasExactas,
            Integer contadorParadas, Integer viajes) {
        contadorParadas++;
        Logger.getLogger(this.getClass().getName()).log(Level.INFO,
                "Parada No" + contadorParadas + " en la ciudad: " + ciudadOrigen.getNombre());
        if (ciudadOrigen.equals(ciudadDestino) && ((Boolean.FALSE.equals(paradasExactas) && contadorParadas <= paradas)
                || (Boolean.TRUE.equals(paradasExactas) && contadorParadas == paradas))) {
            viajes++;
            Logger.getLogger(this.getClass().getName()).log(Level.INFO, "Viajes compleatados: " + viajes);
            return viajes;
        } else if ((Boolean.FALSE.equals(paradasExactas) && contadorParadas >= paradas)
                || (Boolean.TRUE.equals(paradasExactas) && contadorParadas >= paradas)) {
            throw new RuntimeException("Se ha superado el maximo de paradas sin encontrar el destino");
        }
        for (Ruta ruta : this.ciudades.get(ciudadOrigen)) {
            try {
                viajes = this.obtenerNumeroParadas(ruta.getDestino(), ciudadDestino, paradas, paradasExactas, contadorParadas,
                        viajes);
            } catch (RuntimeException e) {
                continue;
            }
        }
        return viajes;
    }

    /**
     * Metodo recursivo que obtiene la longitud de la ruta mas corta entre dos ciudades.
     * @param ciudadOrigen ciudad de donde parte la ruta
     * @param ciudadDestino ciudad destino de la ruta
     * @param distanciaRecorrida distancia recorrida entre ciudades
     * @return longitud total de la ruta mas corta
     */
    private Integer rutaMasCorta(Ciudad ciudadOrigen, Ciudad ciudadDestino, Integer distanciaRecorrida,
            Integer distanciaMasCorta) {
        Logger.getLogger(this.getClass().getName()).log(Level.INFO,
                "Ciudad Actual: " + ciudadOrigen.getNombre() + ", distancia recorrida: " + distanciaRecorrida);
        if (ciudadOrigen.equals(ciudadDestino)) {
            Logger.getLogger(this.getClass().getName()).log(Level.INFO,
                    "Destino alcanzado, distancia recorrida: " + distanciaRecorrida);
            if (distanciaMasCorta != null) {
                return distanciaRecorrida < distanciaMasCorta ? distanciaRecorrida : distanciaMasCorta;
            }
            return distanciaRecorrida;
        }
        for (Ruta ruta : this.ciudades.get(ciudadOrigen)) {
            if (Boolean.TRUE.equals(ruta.getUtilizada())) {
                continue;
            }
            ruta.setUtilizada(Boolean.TRUE);
            distanciaMasCorta = this.rutaMasCorta(ruta.getDestino(), ciudadDestino, distanciaRecorrida + ruta.getDistancia(),
                    distanciaMasCorta);
        }
        this.resetearRutas(ciudadOrigen);
        return distanciaMasCorta;
    }

    /**
     * Metodo recursivo que calcula un numero de rutas posibles entre dos ciudades siempre y cuando que la distancia sea menor al
     * parametro {@code longitudMaxima}
     * @param ciudadOrigen ciudad de origen de las rutas
     * @param ciudadDestino ciudad destino a la que llegaran las rutas
     * @param distanciaRecorrida distancia recorrida entre ciudades
     * @param maximaDistancia distancia maxima de la ruta
     * @return un numero de rutas posibles entre dos ciudades
     */
    private Integer rutaConMaximoLongitud(Ciudad ciudadOrigen, Ciudad ciudadDestino, Integer distanciaRecorrida,
            Integer maximaDistancia, Integer viajes) {
        Logger.getLogger(this.getClass().getName()).log(Level.INFO,
                "Ciudad Actual: " + ciudadOrigen.getNombre() + ", distancia recorrida: " + distanciaRecorrida);
        if (ciudadOrigen.equals(ciudadDestino)) {
            Logger.getLogger(this.getClass().getName()).log(Level.WARNING,
                    "Destino alcanzado, distancia recorrida: " + distanciaRecorrida);
            viajes++;
        }
        for (Ruta ruta : this.ciudades.get(ciudadOrigen)) {
            if (maximaDistancia <= distanciaRecorrida + ruta.getDistancia()) {
                continue;
            }
            viajes = this.rutaConMaximoLongitud(ruta.getDestino(), ciudadDestino, distanciaRecorrida + ruta.getDistancia(),
                    maximaDistancia, viajes);
        }
        return viajes;
    }

    /**
     * Cambia el valor de visitadas a no visitadas en las rutas de una ciudad.
     * @param ciudadOrigen ciudad de las cuales se quiere actualizar las rutas
     */
    private void resetearRutas(Ciudad ciudadOrigen) {
        for (Ruta ruta : this.ciudades.get(ciudadOrigen)) {
            ruta.setUtilizada(Boolean.FALSE);
        }
    }
}
