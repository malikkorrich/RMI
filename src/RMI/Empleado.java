package RMI;

import java.io.Serializable;

public class Empleado implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int id;
	private String nombre;
	private String apellidos;
	private int edad;
	private String cargo;
	
	public Empleado(int id,String nombre, String apellidos, int edad, String cargo) {
		super();
		
		
		this.id=id;
		this.nombre = nombre;
		this.apellidos = apellidos;
		this.edad = edad;
		this.cargo = cargo;
	}

	@Override
	public String toString() {
		return "Empleado [id=" + id + ", nombre=" + nombre + ", apellidos=" + apellidos + ", edad=" + edad + ", cargo="
				+ cargo + "]";
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellidos() {
		return apellidos;
	}

	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}

	public int getEdad() {
		return edad;
	}

	public void setEdad(int edad) {
		this.edad = edad;
	}

	public String getCargo() {
		return cargo;
	}

	public void setCargo(String cargo) {
		this.cargo = cargo;
	}
	
	
	
	
	
	
	
}
