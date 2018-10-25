/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package trains;

/**
 *
 * @author schavez
 */
public class Ruta {
    
    private Ciudad origen;
    
    private Ciudad destino;
    
    private Integer distancia;
    
    private Boolean utilizada;

    public Ruta(Ciudad origen, Ciudad destino, Integer distancia) {
        this.origen = origen;
        this.destino = destino;
        this.distancia = distancia;
        this.utilizada = Boolean.FALSE;
    }

    public Ciudad getOrigen() {
        return origen;
    }

    public void setOrigen(Ciudad origen) {
        this.origen = origen;
    }

    public Ciudad getDestino() {
        return destino;
    }

    public void setDestino(Ciudad destino) {
        this.destino = destino;
    }

    public Integer getDistancia() {
        return distancia;
    }

    public void setDistancia(Integer distancia) {
        this.distancia = distancia;
    }

    public Boolean getUtilizada() {
        return utilizada;
    }

    public void setUtilizada(Boolean utilizada) {
        this.utilizada = utilizada;
    }
    
    
}
