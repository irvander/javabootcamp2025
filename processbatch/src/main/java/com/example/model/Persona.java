package com.example.model;

public class Persona {
	private long id;
	private String nombre, correo, ip;
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