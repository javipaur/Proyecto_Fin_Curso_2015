package Modelo;

public class Piezas {
private String codigo;
private String nombre;
private float precio;
private String descripcion;

public Piezas(String codigo, String nombre, float precio, String descripcion) {
	super();
	this.codigo = codigo;
	this.nombre = nombre;
	this.precio = precio;
	this.descripcion = descripcion;
}
public Piezas() {
	// TODO Auto-generated constructor stub
}
public String getCodigo() {
	return codigo;
}
public void setCodigo(String codigo) {
	this.codigo = codigo;
}
public String getNombre() {
	return nombre;
}
public void setNombre(String nombre) {
	this.nombre = nombre;
}
public float getPrecio() {
	return precio;
}
public void setPrecio(float precio) {
	this.precio = precio;
}
public String getDescripcion() {
	return descripcion;
}
public void setDescripcion(String descripcion) {
	this.descripcion = descripcion;
}

}
