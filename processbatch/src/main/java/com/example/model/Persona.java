package com.example.model;

public class Persona {
	private long id;
	private String nombre, correo, ip;
	
	public Persona() {}
	
	public Persona(long id, String nombre, String correo, String ip) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.correo = correo;
		this.ip = ip;
	}
	public long getId() {
		return id;
	}
	public String getNombre() {
		return nombre;
	}
	public String getCorreo() {
		return correo;
	}
	public String getIp() {
		return ip;
	}
	public void setId(long id) {
		this.id = id;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public void setCorreo(String correo) {
		this.correo = correo;
	}
	public void setIp(String ip) {
		this.ip = ip;
	}
	@Override
	public String toString() {
		return "Persona [id=" + id + ", nombre=" + nombre + ", correo=" + correo + ", ip=" + ip + "]";
	}
	
	
}