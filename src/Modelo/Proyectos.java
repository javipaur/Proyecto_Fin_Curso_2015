package Modelo;

public class Proyectos {
private String codigo;
private String nombre;
private String ciudad;

public Proyectos(String codigo, String nombre, String ciudad) {
	super();
	this.codigo = codigo;
	this.nombre = nombre;
	this.ciudad = ciudad;
}
public Proyectos() {
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
public String getCiudad() {
	return ciudad;
}
public void setCiudad(String ciudad) {
	this.ciudad = ciudad;
}

}
