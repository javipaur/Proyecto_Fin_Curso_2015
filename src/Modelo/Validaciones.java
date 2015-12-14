package Modelo;

import java.sql.SQLException;

import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import BD.Bd;

public class Validaciones {
	
	
public static void validar_Proveedor(JTextField tcodigoProveedor, JTextField tnombre, JTextField tapellido, JTextField tdireccion){

	if(tcodigoProveedor.getText().length()>6){
		tcodigoProveedor.setText(tcodigoProveedor.getText().substring(0, 5));
	}
	String codigo=tcodigoProveedor.getText().toUpperCase();
	tcodigoProveedor.setText(codigo);
	//Nombre
	if(tnombre.getText().length()>20){
			tnombre.setText(tnombre.getText().substring(0, 20));
	}
	//Apellidos
	if(tapellido.getText().length()>30){
			tapellido.setText(tapellido.getText().substring(0, 30));
	}
	//Direccion
	if(tdireccion.getText().length()>40){
			tdireccion.setText(tdireccion.getText().substring(0, 40));
	}
		
}

public static void validar_Piezas(JTextField tcodigoPieza, JTextField tnombre, JTextField tprecio,
		JTextField tdescripcion) {
	if(tcodigoPieza.getText().length()>6){
		tcodigoPieza.setText(tcodigoPieza.getText().substring(0, 5));
	}
	String codigo=tcodigoPieza.getText().toUpperCase();
	tcodigoPieza.setText(codigo);
	//Nombre
	if(tnombre.getText().length()>20){
			tnombre.setText(tnombre.getText().substring(0, 20));
	}
	//Precio
	if(tprecio.getText().length()>10){
			tprecio.setText(tprecio.getText().substring(0, 10));
	}
	//Descripcion
	if(tdescripcion.getText().length()>50){
		tdescripcion.setText(tdescripcion.getText().substring(0, 50));
	}
	
}

public static void validar_Proyectos(JTextField tcodigoProyecto, JTextField tnombre, JTextField tciudad) {
	if(tcodigoProyecto.getText().length()>6){
		tcodigoProyecto.setText(tcodigoProyecto.getText().substring(0, 5));
	}
	String codigo=tcodigoProyecto.getText().toUpperCase();
	tcodigoProyecto.setText(codigo);
	//Nombre
	if(tnombre.getText().length()>40){
			tnombre.setText(tnombre.getText().substring(0, 40));
	}
	//Ciudad
	if(tciudad.getText().length()>40){
		tciudad.setText(tciudad.getText().substring(0, 40));
	}
	
}

public static boolean validarGestion(JComboBox cpieza, JComboBox cproyecto, JComboBox cproveedor, JTextField tcantidad_1) {
	//System.out.println("pasa "+cproveedor.getSelectedIndex());
	boolean error=false;
	if(cproveedor.getSelectedIndex()==0){
		JOptionPane.showMessageDialog(null, "¡Tienes que seleccionar una proveedor!");	
		error=true;
	}
	if(cpieza.getSelectedIndex()==0){
		JOptionPane.showMessageDialog(null, "¡Tienes que seleccionar una pieza!");	
		error=true;
	}
	if(cproyecto.getSelectedIndex()==0){
		JOptionPane.showMessageDialog(null, "¡Tienes que seleccionar un proyecto!");	
		error=true;
	}
	if(tcantidad_1.getText().length()==0){
		JOptionPane.showMessageDialog(null, "¡Teclea algo en cantidad!");	
		error=true;
	}
	return error;
	
}


}
