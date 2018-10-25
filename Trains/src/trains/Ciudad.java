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
public class Ciudad {
    
    private String nombre;

    public Ciudad(String nombre) {
        this.nombre = nombre;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

	@Override
	public boolean equals(Object obj) {
		if(obj == null || !(obj instanceof Ciudad)) {
			return false;
		}
		return this.nombre.equals(((Ciudad) obj).getNombre());
	}

	@Override
	public String toString() {
		return "Ciudad:"+this.nombre;
	}
    
	@Override
	public int hashCode() {
		if(this.nombre == null) return 0;
		return this.nombre.hashCode();
	}
}
