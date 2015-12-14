package Modelo;

public class Gestion {
private String codProveedor;
private String codPieza;
private String codProyecto;
private float cantidad;
public Gestion(String codProveedor, String codPieza, String codProyecto, float cantidad) {
	super();
	this.codProveedor = codProveedor;
	this.codPieza = codPieza;
	this.codProyecto = codProyecto;
	this.cantidad = cantidad;
}
public Gestion() {
	// TODO Auto-generated constructor stub
}
public String getCodProveedor() {
	return codProveedor;
}
public void setCodProveedor(String codProveedor) {
	this.codProveedor = codProveedor;
}
public String getCodPieza() {
	return codPieza;
}
public void setCodPieza(String codPieza) {
	this.codPieza = codPieza;
}
public String getCodProyecto() {
	return codProyecto;
}
public void setCodProyecto(String codProyecto) {
	this.codProyecto = codProyecto;
}
public float getCantidad() {
	return cantidad;
}
public void setCantidad(float cantidad) {
	this.cantidad = cantidad;
}


}
