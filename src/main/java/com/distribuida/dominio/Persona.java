package com.distribuida.dominio;



public class Persona {

    private Integer id;
    private String cedula;
    private String nombre;
    private String direccion;
    private String correoElectronico;
    
    
	public Persona() {
	
	}

	public Persona(Integer id) {

		this.id = id;
	}



	public Persona(String cedula, String nombre, String direccion, String correoElectronico) {
	
		this.cedula = cedula;
		this.nombre = nombre;
		this.direccion = direccion;
		this.correoElectronico = correoElectronico;
	}



	public Persona(Integer id, String cedula, String nombre, String direccion, String correoElectronico) {
		this.id = id;
		this.cedula = cedula;
		this.nombre = nombre;
		this.direccion = direccion;
		this.correoElectronico = correoElectronico;
	}

	public Integer getId() {
		return id;
	}


	public void setId(Integer id) {
		this.id = id;
	}


	public String getCedula() {
		return cedula;
	}


	public void setCedula(String cedula) {
		this.cedula = cedula;
	}


	public String getNombre() {
		return nombre;
	}


	public void setNombre(String nombre) {
		this.nombre = nombre;
	}


	public String getDireccion() {
		return direccion;
	}


	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}


	public String getCorreoElectronico() {
		return correoElectronico;
	}


	public void setCorreoElectronico(String correoElectronico) {
		this.correoElectronico = correoElectronico;
	}


	@Override
	public String toString() {
		return "Persona [id=" + id + ", cedula=" + cedula + ", nombre=" + nombre + ", direccion=" + direccion
				+ ", correoElectronico=" + correoElectronico + "]";
	}

	
}
