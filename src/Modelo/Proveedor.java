package Modelo;

public class Proveedor {
private String codigo;
private String nombre;
private String apellido;
private String direccion;

public Proveedor(String codigo, String nombre, String apellido, String direccion) {
	super();
	this.codigo = codigo;
	this.nombre = nombre;
	this.apellido = apellido;
	this.direccion = direccion;
}
public Proveedor() {
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
public String getApellido() {
	return apellido;
}
public void setApellido(String apellido) {
	this.apellido = apellido;
}
public String getDireccion() {
	return direccion;
}
public void setDireccion(String direccion) {
	this.direccion = direccion;
}

}
