/*
 * To change this license header, choose License Headers in Project Properties. To change this template file, choose Tools |
 * Templates and open the template in the editor.
 */
package trains;

/**
 * @author schavez
 */
public class Ruta {
    /** Ciudad de origen de esta ruta. */
    private Ciudad origen;
    /** Ciudad de destino de esta ruta. */
    private Ciudad destino;
    /** Distancia o longitud de la ruta. */
    private Integer distancia;
    /** Indica si la ruta ha sido utilizada. */
    private Boolean utilizada;

    /**
     * Crea una nueva instancia de la clase Ruta.
     * @param origen valor a ser asignado al atributo origen
     * @param destino valor a ser asignado al atributo destino
     * @param distancia valor a ser asignado al atributo distancia
     */
    public Ruta(Ciudad origen, Ciudad destino, Integer distancia) {
        this.origen = origen;
        this.destino = destino;
        this.distancia = distancia;
        this.utilizada = Boolean.FALSE;
    }

    /**
     * Obtiene el atributo de clase: "origen"
     * @return el/la origen
     */
    public Ciudad getOrigen() {
        return this.origen;
    }

    /**
     * Asigna valor al atributo de clase: "origen"
     * @param origen el/la origen para asignar el valor
     */
    public void setOrigen(Ciudad origen) {
        this.origen = origen;
    }

    /**
     * Obtiene el atributo de clase: "destino"
     * @return el/la destino
     */
    public Ciudad getDestino() {
        return this.destino;
    }

    /**
     * Asigna valor al atributo de clase: "destino"
     * @param destino el/la destino para asignar el valor
     */
    public void setDestino(Ciudad destino) {
        this.destino = destino;
    }

    /**
     * Obtiene el atributo de clase: "distancia"
     * @return el/la distancia
     */
    public Integer getDistancia() {
        return this.distancia;
    }

    /**
     * Asigna valor al atributo de clase: "distancia"
     * @param distancia el/la distancia para asignar el valor
     */
    public void setDistancia(Integer distancia) {
        this.distancia = distancia;
    }

    /**
     * Obtiene el atributo de clase: "utilizada"
     * @return el/la utilizada
     */
    public Boolean getUtilizada() {
        return this.utilizada;
    }

    /**
     * Asigna valor al atributo de clase: "utilizada"
     * @param utilizada el/la utilizada para asignar el valor
     */
    public void setUtilizada(Boolean utilizada) {
        this.utilizada = utilizada;
    }
}
