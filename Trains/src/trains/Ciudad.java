/*
 * To change this license header, choose License Headers in Project Properties. To change this template file, choose Tools |
 * Templates and open the template in the editor.
 */
package trains;

/**
 * La entidad ciudad contiene el nombre de una ciudad.
 * @author schavez
 */
public class Ciudad {
    /** Nombre de la ciudad. */
    private String nombre;

    /**
     * Constructor de la clase.
     * @param nombre nombre de la ciudad a crear
     */
    public Ciudad(String nombre) {
        this.nombre = nombre;
    }

    /**
     * Obtiene el valor del atributo nombre.
     * @return valor del atributo nombre
     */
    public String getNombre() {
        return this.nombre;
    }

    /**
     * Asigna un valor al atributo nombre.
     * @param nombre valor a ser asignado
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * {@inheritDoc }
     */
    @Override
    public boolean equals(Object obj) {
        if (obj == null || !(obj instanceof Ciudad)) {
            return false;
        }
        return this.nombre.equals(((Ciudad) obj).getNombre());
    }

    /**
     * {@inheritDoc }
     */
    @Override
    public String toString() {
        return "Ciudad:" + this.nombre;
    }

    /**
     * {@inheritDoc }
     */
    @Override
    public int hashCode() {
        if (this.nombre == null) {
            return 0;
        }
        return this.nombre.hashCode();
    }
}
