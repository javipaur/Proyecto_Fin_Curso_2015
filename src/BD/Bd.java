package BD;

import java.awt.Color;
import java.awt.Component;
import java.sql.Connection;
import Vista.*;
import Modelo.*;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.GregorianCalendar;
import javax.naming.CommunicationException;
import javax.swing.DefaultListModel;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SpringLayout.Constraints;
import javax.swing.plaf.BorderUIResource.EtchedBorderUIResource;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;

public class Bd {
	static Statement sentencia;
	static Connection conexion;
	
public static JLabel conexion(JLabel estado) throws ClassNotFoundException, SQLException {
	try {
		conexionBD();		
		estado.setText("Conexion establecida!");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			estado.setForeground(Color.RED);
			estado.setText("No existe la BD Proyecto  o No esta conectado el Servidor");
			return estado;
	}	
	return estado;
}

public static void conexionBD() throws ClassNotFoundException, SQLException {
	Class.forName("com.mysql.jdbc.Driver");
	conexion=DriverManager.getConnection("jdbc:mysql://localhost/proyecto","root","");
	sentencia = conexion.createStatement();
	}

public static void insertarProveedor(Proveedor proveedor,JLabel correcto) throws ClassNotFoundException {
	try {
		conexionBD();
		String sql = "INSERT INTO proveedores VALUES ('" 
					+ proveedor.getCodigo() +"','"
					+ proveedor.getNombre() +"', '" 
					+ proveedor.getApellido() + "', '"
					+ proveedor.getDireccion() +"')";
		//System.out.println(sql);
		int filas = sentencia.executeUpdate(sql);
		correcto.setVisible(true);
		correcto.setText("¡Alta procesada correctamente!");		
		sentencia.close();
		conexion.close();	
	} catch (SQLException e) {
			if (e.getErrorCode() == 1062){
			correcto.setText("¡Numero de proveedor ya existe!");	        
			}else{
        	correcto.setText("¡Algo va mal en BD Proveedores!");
        }
	}
	
}

public static void eliminarProveedor(Proveedor proveedor, JLabel correcto) throws ClassNotFoundException, SQLException {
	try{
		conexionBD();
		int resp = JOptionPane.showConfirmDialog(null, "¿Está seguro?");
		if(resp==0){
			String sql = "delete from proveedores where codigo='"+ proveedor.getCodigo()+"'";
			//System.out.println(sql);
			correcto.setText("Proveedor eliminado");
			correcto.setForeground(Color.RED);
			correcto.setVisible(true);
			int filas = sentencia.executeUpdate(sql);
			//System.out.println("Filas eliminadas: " + filas);
			sentencia.close();
		}
		conexion.close();
	} catch (SQLException e) {
		if (e.getErrorCode() == 1451){
			correcto.setText("¡Tienes que borrar las filas de la tabla gestion de este proveedor!");	        
		}else{
			System.out.println(e.getErrorCode());
		
			correcto.setText("¡Algo va mal en BD Proveedores!");
		}
	
	}
}

public static void modificarProveedor(Proveedor proveedor, JLabel correcto) throws ClassNotFoundException, SQLException {
	conexionBD();
	String sql = "update proveedores "
			+ "set nombre='"+proveedor.getNombre()+
			"',apellidos='"+proveedor.getApellido()+
			"',direccion='"+proveedor.getDireccion()+
			"' where codigo='"+ proveedor.getCodigo()+"'";
	//System.out.println(sql);
	correcto.setText("Proveedor modificado");
	correcto.setForeground(Color.BLUE);
	correcto.setVisible(true);
	int filas = sentencia.executeUpdate(sql);
	//System.out.println("Filas Modificadas: " + filas);
	
	sentencia.close();
	conexion.close();
	
}

public static Proveedor buscarProveedor(Proveedor proveedor) throws ClassNotFoundException, SQLException {
	conexionBD();	
	String sql="SELECT * FROM proyecto.proveedores where codigo = '"+proveedor.getCodigo()+"'";
	///System.out.println(sql);
	ResultSet resul= sentencia.executeQuery(sql);
	if(resul.next()==false){
		proveedor=null;
	
	}else{
	
		proveedor.setCodigo(resul.getString("codigo"));
		proveedor.setNombre(resul.getString("nombre"));
		proveedor.setApellido(resul.getString("apellidos"));
		proveedor.setDireccion(resul.getString("direccion"));
	}
	 //System.out.println(sql+"" + proveedor.getNombre());
		
	 resul.close();
	 sentencia.close();
	 conexion.close();
	
	return proveedor;
	
}

public static void llenarListaProveedores(JTable tabla, DefaultTableModel modelo) throws SQLException, ClassNotFoundException {
	Proveedor proveedor=new Proveedor();
	conexionBD();
	String sql="SELECT * FROM proyecto.proveedores";
	//System.out.println(sql);
	ResultSet resul= sentencia.executeQuery(sql);
	// Bucle para cada resultado en la consulta
	ResultSetMetaData rsMd=resul.getMetaData();
	int numColumnas=rsMd.getColumnCount();
	tabla.setModel(modelo);
	
	for(int i=1;i<=numColumnas;i++){
		modelo.addColumn(rsMd.getColumnLabel(i).toUpperCase());
	}
	
	while (resul.next())
	{
	   Object [] fila = new Object[numColumnas];
	  for (int i=0;i<numColumnas;i++)
	      fila[i] = resul.getObject(i+1); 
	  modelo.addRow(fila);
	}
	
	TableColumnModel columnModel =  tabla.getColumnModel();
	columnModel.getColumn(0).setPreferredWidth(10);
	columnModel.getColumn(1).setPreferredWidth(50);
	columnModel.getColumn(2).setPreferredWidth(100);
	columnModel.getColumn(3).setPreferredWidth(200);

	resul.close();
    sentencia.close();
    conexion.close();
}
	 
public static void llenarBuscaProveedores(JTextField tBuscar, JComboBox cTipo, JTable tabla, DefaultTableModel modelo) throws SQLException, ClassNotFoundException {
	Proveedor proveedor=new Proveedor();
	conexionBD();
	String sql="SELECT * FROM proyecto.proveedores ";
	if(cTipo.getSelectedItem()=="xCodigo"){
		sql="SELECT * FROM proyecto.proveedores where codigo like  '%"+tBuscar.getText()+"%'";
	}else if(cTipo.getSelectedItem()=="xNombre"){
		sql="SELECT * FROM proyecto.proveedores where nombre like  '%"+tBuscar.getText()+"%'";
	}else if(cTipo.getSelectedItem()=="xApellidos"){
		sql="SELECT * FROM proyecto.proveedores where apellidos like  '%"+tBuscar.getText()+"%'";
	}else if(cTipo.getSelectedItem()=="xDireccion"){
		sql="SELECT * FROM proyecto.proveedores where direccion like  '%"+tBuscar.getText()+"%'";
	}
	//System.out.println(sql);
	ResultSet resul= sentencia.executeQuery(sql);
	// Bucle para cada resultado en la consulta
	ResultSetMetaData rsMd=resul.getMetaData();
	int numColumnas=rsMd.getColumnCount();
	tabla.setModel(modelo);
	
	for(int i=1;i<=numColumnas;i++){
		modelo.addColumn(rsMd.getColumnLabel(i).toUpperCase());
	}
	
	while (resul.next())
	{
	   Object [] fila = new Object[numColumnas];
	  for (int i=0;i<numColumnas;i++)
	      fila[i] = resul.getObject(i+1); 
	  modelo.addRow(fila);
	}
	TableColumnModel columnModel =  tabla.getColumnModel();
	columnModel.getColumn(0).setPreferredWidth(10);
	columnModel.getColumn(1).setPreferredWidth(50);
	columnModel.getColumn(2).setPreferredWidth(100);
	columnModel.getColumn(3).setPreferredWidth(200);
	
	resul.close();
    sentencia.close();
    conexion.close();
}

public static void crearBD() throws ClassNotFoundException {
	try {
		conexionBD();
		Statement stmt;

		stmt = conexion.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
				ResultSet.CONCUR_UPDATABLE);
		stmt.executeUpdate("DROP TABLE IF EXISTS gestion;");
		stmt.executeUpdate("DROP TABLE IF EXISTS piezas;");
		stmt.executeUpdate("DROP TABLE IF EXISTS proveedores;");
		stmt.executeUpdate("DROP TABLE IF EXISTS proyectos;");

		stmt.executeUpdate("CREATE TABLE piezas (codigo varchar(6) NOT NULL,nombre varchar(20) NOT NULL,precio float NOT NULL,descripcion varchar(50) DEFAULT NULL,PRIMARY KEY (codigo)) ENGINE=InnoDB DEFAULT CHARSET=utf8;");
		stmt.executeUpdate("CREATE TABLE proveedores (codigo varchar(6) NOT NULL,nombre varchar(20) NOT NULL,apellidos varchar(30) NOT NULL,direccion varchar(40) NOT NULL,PRIMARY KEY (codigo)) ENGINE=InnoDB DEFAULT CHARSET=utf8;");
		stmt.executeUpdate("CREATE TABLE proyectos (codigo varchar(6) NOT NULL,nombre varchar(40) NOT NULL,ciudad varchar(40) DEFAULT NULL,PRIMARY KEY (codigo)) ENGINE=InnoDB DEFAULT CHARSET=utf8;");
		stmt.executeUpdate("CREATE TABLE gestion (codpieza varchar(6) NOT NULL,codproveedor varchar(6) NOT NULL,codproyecto varchar(6) NOT NULL,cantidad float DEFAULT NULL,PRIMARY KEY (codpieza,codproveedor,codproyecto),KEY fk_piezas_has_proveedores_proveedores1_idx (codproveedor),KEY fk_piezas_has_proveedores_piezas_idx (codpieza),KEY fk_piezas_has_proveedores_proyectos1_idx (codproyecto),CONSTRAINT fk_piezas_has_proveedores_piezas FOREIGN KEY (codpieza) REFERENCES piezas (codigo) ON DELETE NO ACTION ON UPDATE CASCADE,CONSTRAINT fk_piezas_has_proveedores_proveedores1 FOREIGN KEY (codproveedor) REFERENCES proveedores (codigo) ON DELETE NO ACTION ON UPDATE CASCADE,CONSTRAINT fk_piezas_has_proveedores_proyectos1 FOREIGN KEY (codproyecto) REFERENCES proyectos (codigo) ON DELETE NO ACTION ON UPDATE CASCADE) ENGINE=InnoDB DEFAULT CHARSET=utf8;");
		
		stmt.executeUpdate("INSERT INTO piezas VALUES ('PI01','Carburador',100.2,'Distribuye la gasolina al motor');");
		stmt.executeUpdate("INSERT INTO piezas VALUES ('PI02','Volante',200.2,'Permite ditigir las ruedas');");
		stmt.executeUpdate("INSERT INTO piezas VALUES ('PI03','Puertas',300.2,'Permite aislar el habitaculo');");
		stmt.executeUpdate("INSERT INTO piezas VALUES ('PI04','Motor',400.2,'Hace que se mueva el vehiculo');");
		stmt.executeUpdate("INSERT INTO piezas VALUES ('PI05','Ruedas',500.2,'Evita el rozamiento');");
		
		stmt.executeUpdate("INSERT INTO proveedores VALUES ('PR01','Juan','Cortes Ruiz','C/Eduardo Dato 12 Vitoria');");
		stmt.executeUpdate("INSERT INTO proveedores VALUES ('PR02','Andres','Peral Santos','C/Ronda de la Estacion 1 Huesca');");		
		stmt.executeUpdate("INSERT INTO proveedores VALUES ('PR03','Federico','Marco Polo','Paseo Independecia 3 Zaragoza');");
		stmt.executeUpdate("INSERT INTO proveedores VALUES ('PR04','Jose Maria','Ruiz Mateos','Paseo Castellana 234 Madrid');");
		stmt.executeUpdate("INSERT INTO proveedores VALUES ('PR05','Josam','Querejeta Maeztu','C/Batan 34 Bilbao');");
		
		stmt.executeUpdate("INSERT INTO proyectos VALUES ('PY01','Prototipo Torpedo','Huesca');");
		stmt.executeUpdate("INSERT INTO proyectos VALUES ('PY02','Autobus Irizar','Ormáiztegui,');");
		stmt.executeUpdate("INSERT INTO proyectos VALUES ('PY03','Coche Electrico','Bilbao');");
		stmt.executeUpdate("INSERT INTO proyectos VALUES ('PY04','Coche Sin carnet','Huesca');");
		stmt.executeUpdate("INSERT INTO proyectos VALUES ('PY05','Formula 1','Barcelona');");
		
		stmt.executeUpdate("INSERT INTO gestion VALUES ('PI01','PR01','PY01',100);");
		stmt.executeUpdate("INSERT INTO gestion VALUES ('PI01','PR01','PY02',200);");
		stmt.executeUpdate("INSERT INTO gestion VALUES ('PI01','PR01','PY03',300);");
		stmt.executeUpdate("INSERT INTO gestion VALUES ('PI01','PR01','PY04',400);");
		
		stmt.executeUpdate("INSERT INTO gestion VALUES ('PI02','PR01','PY01',100);");
		stmt.executeUpdate("INSERT INTO gestion VALUES ('PI02','PR01','PY02',200);");
		stmt.executeUpdate("INSERT INTO gestion VALUES ('PI02','PR01','PY03',300);");
		stmt.executeUpdate("INSERT INTO gestion VALUES ('PI02','PR01','PY04',400);");
		
		stmt.executeUpdate("INSERT INTO gestion VALUES ('PI03','PR01','PY01',100);");
		stmt.executeUpdate("INSERT INTO gestion VALUES ('PI03','PR01','PY02',200);");
		stmt.executeUpdate("INSERT INTO gestion VALUES ('PI03','PR01','PY03',300);");
		stmt.executeUpdate("INSERT INTO gestion VALUES ('PI03','PR01','PY04',400);");
		
		stmt.executeUpdate("INSERT INTO gestion VALUES ('PI04','PR01','PY01',100);");
		stmt.executeUpdate("INSERT INTO gestion VALUES ('PI04','PR01','PY02',200);");
		stmt.executeUpdate("INSERT INTO gestion VALUES ('PI04','PR01','PY03',300);");
		stmt.executeUpdate("INSERT INTO gestion VALUES ('PI04','PR01','PY04',400);");
		
		stmt.executeUpdate("INSERT INTO gestion VALUES ('PI05','PR01','PY01',100);");
		stmt.executeUpdate("INSERT INTO gestion VALUES ('PI05','PR01','PY02',200);");
		stmt.executeUpdate("INSERT INTO gestion VALUES ('PI05','PR01','PY03',300);");
		stmt.executeUpdate("INSERT INTO gestion VALUES ('PI05','PR01','PY04',400);");
		
		
		stmt.executeUpdate("INSERT INTO gestion VALUES ('PI01','PR02','PY01',100);");
		stmt.executeUpdate("INSERT INTO gestion VALUES ('PI01','PR02','PY02',200);");
		stmt.executeUpdate("INSERT INTO gestion VALUES ('PI01','PR02','PY03',300);");
		stmt.executeUpdate("INSERT INTO gestion VALUES ('PI01','PR02','PY04',400);");
		
		stmt.executeUpdate("INSERT INTO gestion VALUES ('PI02','PR02','PY01',100);");
		stmt.executeUpdate("INSERT INTO gestion VALUES ('PI02','PR02','PY02',200);");
		stmt.executeUpdate("INSERT INTO gestion VALUES ('PI02','PR02','PY03',300);");
		stmt.executeUpdate("INSERT INTO gestion VALUES ('PI02','PR02','PY04',400);");
		
		stmt.executeUpdate("INSERT INTO gestion VALUES ('PI03','PR02','PY01',100);");
		stmt.executeUpdate("INSERT INTO gestion VALUES ('PI03','PR02','PY02',200);");
		stmt.executeUpdate("INSERT INTO gestion VALUES ('PI03','PR02','PY03',300);");
		stmt.executeUpdate("INSERT INTO gestion VALUES ('PI03','PR02','PY04',400);");
		
		stmt.executeUpdate("INSERT INTO gestion VALUES ('PI04','PR02','PY01',100);");
		stmt.executeUpdate("INSERT INTO gestion VALUES ('PI04','PR02','PY02',200);");
		stmt.executeUpdate("INSERT INTO gestion VALUES ('PI04','PR02','PY03',300);");
		stmt.executeUpdate("INSERT INTO gestion VALUES ('PI04','PR02','PY04',400);");
		
		stmt.executeUpdate("INSERT INTO gestion VALUES ('PI05','PR02','PY01',100);");
		stmt.executeUpdate("INSERT INTO gestion VALUES ('PI05','PR02','PY02',200);");
		stmt.executeUpdate("INSERT INTO gestion VALUES ('PI05','PR02','PY03',300);");
		stmt.executeUpdate("INSERT INTO gestion VALUES ('PI05','PR02','PY04',400);");
		
		
		stmt.executeUpdate("INSERT INTO gestion VALUES ('PI01','PR03','PY01',100);");
		stmt.executeUpdate("INSERT INTO gestion VALUES ('PI01','PR03','PY02',200);");
		stmt.executeUpdate("INSERT INTO gestion VALUES ('PI01','PR03','PY03',300);");
		stmt.executeUpdate("INSERT INTO gestion VALUES ('PI01','PR03','PY04',400);");
		
		stmt.executeUpdate("INSERT INTO gestion VALUES ('PI02','PR03','PY01',100);");
		stmt.executeUpdate("INSERT INTO gestion VALUES ('PI02','PR03','PY02',200);");
		stmt.executeUpdate("INSERT INTO gestion VALUES ('PI02','PR03','PY03',300);");
		stmt.executeUpdate("INSERT INTO gestion VALUES ('PI02','PR03','PY04',400);");
		
		stmt.executeUpdate("INSERT INTO gestion VALUES ('PI03','PR03','PY01',100);");
		stmt.executeUpdate("INSERT INTO gestion VALUES ('PI03','PR03','PY02',200);");
		stmt.executeUpdate("INSERT INTO gestion VALUES ('PI03','PR03','PY03',300);");
		stmt.executeUpdate("INSERT INTO gestion VALUES ('PI03','PR03','PY04',400);");
		
		stmt.executeUpdate("INSERT INTO gestion VALUES ('PI04','PR03','PY01',100);");
		stmt.executeUpdate("INSERT INTO gestion VALUES ('PI04','PR03','PY02',200);");
		stmt.executeUpdate("INSERT INTO gestion VALUES ('PI04','PR03','PY03',300);");
		stmt.executeUpdate("INSERT INTO gestion VALUES ('PI04','PR03','PY04',400);");
		
		stmt.executeUpdate("INSERT INTO gestion VALUES ('PI05','PR03','PY01',100);");
		stmt.executeUpdate("INSERT INTO gestion VALUES ('PI05','PR03','PY02',200);");
		stmt.executeUpdate("INSERT INTO gestion VALUES ('PI05','PR03','PY03',300);");
		stmt.executeUpdate("INSERT INTO gestion VALUES ('PI05','PR03','PY04',400);");
	
		
		stmt.executeUpdate("INSERT INTO gestion VALUES ('PI01','PR04','PY01',100);");
		stmt.executeUpdate("INSERT INTO gestion VALUES ('PI01','PR04','PY02',200);");
		stmt.executeUpdate("INSERT INTO gestion VALUES ('PI01','PR04','PY03',300);");
		stmt.executeUpdate("INSERT INTO gestion VALUES ('PI01','PR04','PY04',400);");
		
		stmt.executeUpdate("INSERT INTO gestion VALUES ('PI02','PR04','PY01',100);");
		stmt.executeUpdate("INSERT INTO gestion VALUES ('PI02','PR04','PY02',200);");
		stmt.executeUpdate("INSERT INTO gestion VALUES ('PI02','PR04','PY03',300);");
		stmt.executeUpdate("INSERT INTO gestion VALUES ('PI02','PR04','PY04',400);");
		
		stmt.executeUpdate("INSERT INTO gestion VALUES ('PI03','PR04','PY01',100);");
		stmt.executeUpdate("INSERT INTO gestion VALUES ('PI03','PR04','PY02',200);");
		stmt.executeUpdate("INSERT INTO gestion VALUES ('PI03','PR04','PY03',300);");
		stmt.executeUpdate("INSERT INTO gestion VALUES ('PI03','PR04','PY04',400);");
		
		stmt.executeUpdate("INSERT INTO gestion VALUES ('PI04','PR04','PY01',100);");
		stmt.executeUpdate("INSERT INTO gestion VALUES ('PI04','PR04','PY02',200);");
		stmt.executeUpdate("INSERT INTO gestion VALUES ('PI04','PR04','PY03',300);");
		stmt.executeUpdate("INSERT INTO gestion VALUES ('PI04','PR04','PY04',400);");
		
		stmt.executeUpdate("INSERT INTO gestion VALUES ('PI05','PR04','PY01',100);");
		stmt.executeUpdate("INSERT INTO gestion VALUES ('PI05','PR04','PY02',200);");
		stmt.executeUpdate("INSERT INTO gestion VALUES ('PI05','PR04','PY03',300);");
		stmt.executeUpdate("INSERT INTO gestion VALUES ('PI05','PR04','PY04',400);");
		
		
		stmt.executeUpdate("INSERT INTO gestion VALUES ('PI01','PR05','PY01',100);");
		stmt.executeUpdate("INSERT INTO gestion VALUES ('PI01','PR05','PY02',200);");
		stmt.executeUpdate("INSERT INTO gestion VALUES ('PI01','PR05','PY03',300);");
		stmt.executeUpdate("INSERT INTO gestion VALUES ('PI01','PR05','PY04',400);");
		
		stmt.executeUpdate("INSERT INTO gestion VALUES ('PI02','PR05','PY01',100);");
		stmt.executeUpdate("INSERT INTO gestion VALUES ('PI02','PR05','PY02',200);");
		stmt.executeUpdate("INSERT INTO gestion VALUES ('PI02','PR05','PY03',300);");
		stmt.executeUpdate("INSERT INTO gestion VALUES ('PI02','PR05','PY04',400);");
		
		stmt.executeUpdate("INSERT INTO gestion VALUES ('PI03','PR05','PY01',100);");
		stmt.executeUpdate("INSERT INTO gestion VALUES ('PI03','PR05','PY02',200);");
		stmt.executeUpdate("INSERT INTO gestion VALUES ('PI03','PR05','PY03',300);");
		stmt.executeUpdate("INSERT INTO gestion VALUES ('PI03','PR05','PY04',400);");
		
		stmt.executeUpdate("INSERT INTO gestion VALUES ('PI04','PR05','PY01',100);");
		stmt.executeUpdate("INSERT INTO gestion VALUES ('PI04','PR05','PY02',200);");
		stmt.executeUpdate("INSERT INTO gestion VALUES ('PI04','PR05','PY03',300);");
		stmt.executeUpdate("INSERT INTO gestion VALUES ('PI04','PR05','PY04',400);");
		
		stmt.executeUpdate("INSERT INTO gestion VALUES ('PI05','PR05','PY01',100);");
		stmt.executeUpdate("INSERT INTO gestion VALUES ('PI05','PR05','PY02',200);");
		stmt.executeUpdate("INSERT INTO gestion VALUES ('PI05','PR05','PY03',300);");
		stmt.executeUpdate("INSERT INTO gestion VALUES ('PI05','PR05','PY04',400);");
		
		JOptionPane.showMessageDialog(null, "¡Base de datos generada!");	
		
	} catch (SQLException e) {
		System.out.println(e);
	}

}

public static void borrarBD() throws ClassNotFoundException {
	try {
		conexionBD();
		
		Statement stmt;

		stmt = conexion.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
				ResultSet.CONCUR_UPDATABLE);
		stmt.executeUpdate("DROP TABLE IF EXISTS gestion;");
		stmt.executeUpdate("DROP TABLE IF EXISTS piezas;");
		stmt.executeUpdate("DROP TABLE IF EXISTS proveedores;");
		stmt.executeUpdate("DROP TABLE IF EXISTS proyectos;");

		
		JOptionPane.showMessageDialog(null, "¡Base de datos borrada!");	
		
	} catch (SQLException e) {
		System.out.println(e);
	}
	
}

public static void existenTablas() throws ClassNotFoundException, SQLException {
 	try {
	 	//conexionBD();
	 	String sql=	"SELECT * FROM proveedores";
	 	ResultSet resul= sentencia.executeQuery(sql);
 	}catch (SQLException e) {
 		conexion.close();
 		int resp = JOptionPane.showConfirmDialog(null, "¡Tienes que generar las tablas!");
 		crearBD();
 		//JOptionPane.showMessageDialog(null, "¡Tienes que generar las tablas!");	
 	}
	
}

public static Modelo.Piezas buscarPiezas(Piezas piezas) throws ClassNotFoundException, SQLException {
	conexionBD();	
	String sql="SELECT * FROM proyecto.piezas where codigo = '"+piezas.getCodigo()+"'";
	///System.out.println(sql);
	ResultSet resul= sentencia.executeQuery(sql);
	if(resul.next()==false){
		piezas=null;
	}else{
	
		piezas.setCodigo(resul.getString("codigo"));
		piezas.setNombre(resul.getString("nombre"));
		piezas.setPrecio(resul.getFloat("precio"));
		piezas.setDescripcion(resul.getString("descripcion"));
	}
	 //System.out.println(sql+"" + proveedor.getNombre());
		
	 resul.close();
	 sentencia.close();
	 conexion.close();
	
	return piezas;
}

public static void eliminarPiezas(Piezas piezas, JLabel correcto) throws ClassNotFoundException {
	try{
		conexionBD();
		int resp = JOptionPane.showConfirmDialog(null, "¿Está seguro?");
		if(resp==0){
			String sql = "delete from piezas where codigo='"+ piezas.getCodigo()+"'";
			//System.out.println(sql);
			correcto.setText("Pieza eliminada");
			correcto.setForeground(Color.RED);
			correcto.setVisible(true);
			int filas = sentencia.executeUpdate(sql);
			//System.out.println("Filas eliminadas: " + filas);
			sentencia.close();
		}
		conexion.close();
	} catch (SQLException e) {
		if (e.getErrorCode() == 1451){
			correcto.setText("¡Tienes que borrar las filas de la tabla gestion de esta pieza!");	        
		}else{
			System.out.println(e.getErrorCode());		
			correcto.setText("¡Algo va mal en Piezas!");
		}
	
	}
	
}

public static void insertarPiezas(Piezas piezas, JLabel correcto) throws ClassNotFoundException {
	try {
		conexionBD();
		String sql = "INSERT INTO piezas VALUES ('" 
					+ piezas.getCodigo() +"','"
					+ piezas.getNombre() +"', '" 
					+ piezas.getPrecio() + "', '"
					+ piezas.getDescripcion() +"')";
		//System.out.println(sql);
		int filas = sentencia.executeUpdate(sql);
		correcto.setVisible(true);
		correcto.setText("¡Alta procesada correctamente!");		
		sentencia.close();
		conexion.close();	
	} catch (SQLException e) {
			if (e.getErrorCode() == 1062){
			correcto.setText("¡Numero de pieza ya existe!");	        
			}else{
        	correcto.setText("¡Algo va mal en Piezas!");
        }
	}
	
}

public static void modificarPiezas(Piezas piezas, JLabel correcto) throws ClassNotFoundException, SQLException {
	conexionBD();
	String sql = "update piezas "
			+ "set nombre='"+piezas.getNombre()+
			"',precio='"+piezas.getPrecio()+
			"',descripcion='"+piezas.getDescripcion()+
			"' where codigo='"+ piezas.getCodigo()+"'";
	//System.out.println(sql);
	correcto.setText("Pieza modificada");
	correcto.setForeground(Color.BLUE);
	correcto.setVisible(true);
	int filas = sentencia.executeUpdate(sql);
	//System.out.println("Filas Modificadas: " + filas);	
	sentencia.close();
	conexion.close();
	
}

public static void llenarListaPiezas(JTable table, DefaultTableModel modelo) throws ClassNotFoundException, SQLException {
	Piezas pieza=new Piezas();
	conexionBD();
	String sql="SELECT * FROM proyecto.piezas";
	//System.out.println(sql);
	ResultSet resul= sentencia.executeQuery(sql);
	// Bucle para cada resultado en la consulta
	ResultSetMetaData rsMd=resul.getMetaData();
	int numColumnas=rsMd.getColumnCount();
	table.setModel(modelo);
	
	for(int i=1;i<=numColumnas;i++){
		modelo.addColumn(rsMd.getColumnLabel(i).toUpperCase());
	}
	
	while (resul.next())
	{
	   Object [] fila = new Object[numColumnas];
	  for (int i=0;i<numColumnas;i++)
	      fila[i] = resul.getObject(i+1); 
	  modelo.addRow(fila);
	}
		
	TableColumnModel columnModel =  table.getColumnModel();
	columnModel.getColumn(0).setPreferredWidth(50);
	columnModel.getColumn(1).setPreferredWidth(100);
	columnModel.getColumn(2).setPreferredWidth(70);
	columnModel.getColumn(3).setPreferredWidth(300);
	
	resul.close();
    sentencia.close();
    conexion.close();
	
}

public static void llenarBuscaPiezas(JTextField tBuscar, JComboBox cTipo, JTable table, DefaultTableModel modelo) throws ClassNotFoundException, SQLException {
	Proveedor proveedor=new Proveedor();
	conexionBD();
	String sql="SELECT * FROM proyecto.piezas ";
	if(cTipo.getSelectedItem()=="xCodigo"){
		sql="SELECT * FROM proyecto.piezas where codigo like  '%"+tBuscar.getText()+"%'";
	}else if(cTipo.getSelectedItem()=="xNombre"){
		sql="SELECT * FROM proyecto.piezas where nombre like  '%"+tBuscar.getText()+"%'";
	}else if(cTipo.getSelectedItem()=="xPrecio"){
		sql="SELECT * FROM proyecto.piezas where cast(precio as CHAR) like '%"+tBuscar.getText()+"%'";
	}else if(cTipo.getSelectedItem()=="xDescripción"){
		sql="SELECT * FROM proyecto.piezas where descripcion like  '%"+tBuscar.getText()+"%'";
	}
	//System.out.println(sql);
	ResultSet resul= sentencia.executeQuery(sql);
	// Bucle para cada resultado en la consulta
	ResultSetMetaData rsMd=resul.getMetaData();
	int numColumnas=rsMd.getColumnCount();
	table.setModel(modelo);
	
	for(int i=1;i<=numColumnas;i++){
		modelo.addColumn(rsMd.getColumnLabel(i).toUpperCase());
	}
	
	while (resul.next())
	{
	  Object [] fila = new Object[numColumnas];
	  for (int i=0;i<numColumnas;i++)
	    fila[i] = resul.getObject(i+1); 	  
	  modelo.addRow(fila);	 
	}
	 TableColumnModel columnModel =  table.getColumnModel();
	 columnModel.getColumn(0).setPreferredWidth(50);
	 columnModel.getColumn(1).setPreferredWidth(100);
	 columnModel.getColumn(2).setPreferredWidth(70);
	 columnModel.getColumn(3).setPreferredWidth(300);	
	
	resul.close();
    sentencia.close();
    conexion.close();
	
}

public static void llenarListaProyectos(JTable table, DefaultTableModel modelo) throws ClassNotFoundException, SQLException {
	Proyectos p=new Proyectos();
	conexionBD();
	String sql="SELECT * FROM proyecto.proyectos";
	//System.out.println(sql);
	ResultSet resul= sentencia.executeQuery(sql);
	// Bucle para cada resultado en la consulta
	ResultSetMetaData rsMd=resul.getMetaData();
	int numColumnas=rsMd.getColumnCount();
	table.setModel(modelo);
	
	for(int i=1;i<=numColumnas;i++){
		modelo.addColumn(rsMd.getColumnLabel(i).toUpperCase());
	}
	
	while (resul.next())
	{
	   Object [] fila = new Object[numColumnas];
	  for (int i=0;i<numColumnas;i++)
	      fila[i] = resul.getObject(i+1); 
	  modelo.addRow(fila);
	}
		
	TableColumnModel columnModel =  table.getColumnModel();
	columnModel.getColumn(0).setPreferredWidth(50);
	columnModel.getColumn(1).setPreferredWidth(150);
	columnModel.getColumn(2).setPreferredWidth(150);
	
	resul.close();
    sentencia.close();
    conexion.close();
	
}

public static void llenarBuscarProyectos(JTextField tBuscar, JComboBox cTipo, JTable table, DefaultTableModel modelo) throws SQLException, ClassNotFoundException {
	Proyectos p=new Proyectos();
	conexionBD();
	String sql="SELECT * FROM proyecto.proyectos ";
	if(cTipo.getSelectedItem()=="xCodigo"){
		sql="SELECT * FROM proyecto.proyectos where codigo like  '%"+tBuscar.getText()+"%'";
	}else if(cTipo.getSelectedItem()=="xNombre"){
		sql="SELECT * FROM proyecto.proyectos where nombre like  '%"+tBuscar.getText()+"%'";
	}else if(cTipo.getSelectedItem()=="xCiudad"){
		sql="SELECT * FROM proyecto.proyectos where ciudad like  '%"+tBuscar.getText()+"%'";
	}
	//System.out.println(sql);
	ResultSet resul= sentencia.executeQuery(sql);
	// Bucle para cada resultado en la consulta
	ResultSetMetaData rsMd=resul.getMetaData();
	int numColumnas=rsMd.getColumnCount();
	table.setModel(modelo);
	
	for(int i=1;i<=numColumnas;i++){
		modelo.addColumn(rsMd.getColumnLabel(i).toUpperCase());
	}
	
	while (resul.next())
	{
	  Object [] fila = new Object[numColumnas];
	  for (int i=0;i<numColumnas;i++)
	    fila[i] = resul.getObject(i+1); 	  
	  modelo.addRow(fila);	 
	}
	 TableColumnModel columnModel =  table.getColumnModel();
	 columnModel.getColumn(0).setPreferredWidth(50);
	 columnModel.getColumn(1).setPreferredWidth(150);
	 columnModel.getColumn(2).setPreferredWidth(150);
	
	
	resul.close();
    sentencia.close();
    conexion.close();
	
}

public static Proyectos BuscarProyectos(Proyectos p) throws ClassNotFoundException, SQLException {
	conexionBD();	
	String sql="SELECT * FROM proyecto.proyectos where codigo = '"+p.getCodigo()+"'";
	///System.out.println(sql);
	ResultSet resul= sentencia.executeQuery(sql);
	if(resul.next()==false){
		p=null;
	}else{
	
		p.setCodigo(resul.getString("codigo"));
		p.setNombre(resul.getString("nombre"));
		p.setCiudad(resul.getString("ciudad"));
	}
	 resul.close();
	 sentencia.close();
	 conexion.close();
	
	return p;
}

public static void modificarProyecto(Proyectos p, JLabel correcto) throws SQLException, ClassNotFoundException {
	conexionBD();
	String sql = "update proyectos "
			+ "set nombre='"+p.getNombre()+
			"',ciudad='"+p.getCiudad()+
			"' where codigo='"+ p.getCodigo()+"'";
	//System.out.println(sql);
	correcto.setText("Proyecto modificado");
	correcto.setForeground(Color.BLUE);
	correcto.setVisible(true);
	int filas = sentencia.executeUpdate(sql);
	//System.out.println("Filas Modificadas: " + filas);	
	sentencia.close();
	conexion.close();
	
}

public static void eliminarProyecto(Proyectos p, JLabel correcto) throws ClassNotFoundException {
	try{
		conexionBD();
		int resp = JOptionPane.showConfirmDialog(null, "¿Está seguro?");
		if(resp==0){
			String sql = "delete from proyectos where codigo='"+ p.getCodigo()+"'";
			//System.out.println(sql);
			correcto.setText("Proyecto eliminado");
			correcto.setForeground(Color.RED);
			correcto.setVisible(true);
			int filas = sentencia.executeUpdate(sql);
			//System.out.println("Filas eliminadas: " + filas);
			sentencia.close();
		}
		conexion.close();
	} catch (SQLException e) {
		if (e.getErrorCode() == 1451){
			correcto.setText("¡Tienes que borrar las filas de la tabla gestion de esta proyectos!");	        
		}else{
			System.out.println(e.getErrorCode());		
			correcto.setText("¡Algo va mal en Proyectos!");
		}
	
	}
	
}

public static void insertarProyecto(Proyectos p, JLabel correcto) throws ClassNotFoundException {
	try {
		conexionBD();
		String sql = "INSERT INTO proyectos VALUES ('" 
					+ p.getCodigo() +"','"
					+ p.getNombre() +"', '" 
					+ p.getCiudad() +"')";
		//System.out.println(sql);
		int filas = sentencia.executeUpdate(sql);
		correcto.setVisible(true);
		correcto.setText("¡Alta procesada correctamente!");		
		sentencia.close();
		conexion.close();	
	} catch (SQLException e) {
			if (e.getErrorCode() == 1062){
			correcto.setText("¡Numero de proyecto ya existe!");	        
			}else{
        	correcto.setText("¡Algo va mal en Piezas!");
        }
	}
	
	
}

public static Proveedor DesplegableProveedores(Proveedor p, JComboBox cproveedor) throws ClassNotFoundException, SQLException {
	conexionBD();	
	String sql="SELECT * FROM proyecto.proveedores";
	///System.out.println(sql);
	ResultSet resul= sentencia.executeQuery(sql);
	cproveedor.addItem ("Elige código");
	while (resul.next())
	{
	// Comenzamos a rellenar el combobox a partir de la consulta
	cproveedor.addItem (resul.getObject("codigo"));
	}
	 resul.close();
	 sentencia.close();
	 conexion.close();
	
	return p;
	
}

public static Piezas DesplegablePieza(Piezas pi, JComboBox cproveedor) throws ClassNotFoundException, SQLException {
	conexionBD();	
	String sql="SELECT * FROM proyecto.piezas";
	///System.out.println(sql);
	ResultSet resul= sentencia.executeQuery(sql);
	cproveedor.addItem ("Elige código");
	while (resul.next())
	{
	// Comenzamos a rellenar el combobox a partir de la consulta
	cproveedor.addItem (resul.getObject("codigo"));
	}
	 resul.close();
	 sentencia.close();
	 conexion.close();
	
	return pi;
	
}

public static Proyectos DesplegableProyecto(Proyectos po, JComboBox cproyecto) throws ClassNotFoundException, SQLException {
	conexionBD();	
	String sql="SELECT * FROM proyecto.proyectos";
	///System.out.println(sql);
	ResultSet resul= sentencia.executeQuery(sql);
	cproyecto.addItem ("Elige código");
	while (resul.next())
	{
	// Comenzamos a rellenar el combobox a partir de la consulta
	cproyecto.addItem (resul.getObject("codigo"));
	}
	
		
	
	 resul.close();
	 sentencia.close();
	 conexion.close();
	
	return po;
	
}

public static void BuscarCantidad(JComboBox cpieza, JComboBox cproyecto, JComboBox cproveedor, JTextField tcantidad_1) throws ClassNotFoundException, SQLException {
	conexionBD();
	String sql="SELECT cantidad FROM proyecto.gestion where codpieza='"+cpieza.getSelectedItem()+"'and codproveedor='"+cproveedor.getSelectedItem()+"' and codproyecto='"+cproyecto.getSelectedItem()+"'";
	//System.out.println(sql);
	ResultSet resul= sentencia.executeQuery(sql);
	while (resul.next())
	{
		String cantidad=resul.getString("cantidad");
	// Comenzamos a rellenar el combobox a partir de la consulta
		tcantidad_1.setText(cantidad);
	//resul.getObject("cantidad")
	}
	
		
	
	 resul.close();
	 sentencia.close();
	 conexion.close();
	
}

public static void ModificarCantidad(JComboBox cpieza, JComboBox cproyecto, JComboBox cproveedor,
		JTextField tcantidad_1, JLabel correcto) throws ClassNotFoundException, SQLException {
	
	conexionBD();
	String sql = "update proyecto.gestion SET cantidad ='"+tcantidad_1.getText()+
			"'WHERE codpieza ='" +cpieza.getSelectedItem()+"'"	
			+ " AND codproveedor = '"+cproveedor.getSelectedItem()+"' AND codproyecto = '"+cproyecto.getSelectedItem()+"'";
	//System.out.println(sql);
	correcto.setText("Relación modificada");
	correcto.setForeground(Color.BLUE);
	correcto.setVisible(true);
	int filas = sentencia.executeUpdate(sql);
	//System.out.println("Filas Modificadas: " + filas);	
	sentencia.close();
	conexion.close();
	
}

public static void EliminarCantidad(JComboBox cpieza, JComboBox cproyecto, JComboBox cproveedor, JTextField tcantidad_1,
		JLabel correcto) throws ClassNotFoundException {
	try{
		conexionBD();
		int resp = JOptionPane.showConfirmDialog(null, "¿Está seguro?");
		if(resp==0){
			String sql = "delete from proyecto.gestion where codpieza='"+ cpieza.getSelectedItem()+"'";
			//System.out.println(sql);
			correcto.setText("Relación eliminada");
			correcto.setForeground(Color.RED);
			correcto.setVisible(true);
			int filas = sentencia.executeUpdate(sql);
			//System.out.println("Filas eliminadas: " + filas);
			sentencia.close();
		}
		conexion.close();
	} catch (SQLException e) {
		if (e.getErrorCode() == 1451){
			correcto.setText("¡Tienes que borrar las filas de la tabla gestion de esta Gestion!");	        
		}else{
			System.out.println(e.getErrorCode());		
			correcto.setText("¡Algo va mal en Gestion!");
		}
	
}

}

public static void InsertarCantidad(JComboBox cpieza, JComboBox cproyecto, JComboBox cproveedor, JTextField tcantidad_1,
		JLabel correcto) throws ClassNotFoundException {
	try {
		conexionBD();
		String sql = "INSERT INTO proyecto.gestion VALUES ('" 
					+ cpieza.getSelectedItem() +"','"
					+ cproveedor.getSelectedItem() +"', '"
					+ cproyecto.getSelectedItem() +"',"
					+ tcantidad_1.getText()+");";
		//System.out.println(sql);
		int filas = sentencia.executeUpdate(sql);
		correcto.setVisible(true);
		correcto.setForeground(Color.GREEN);
		correcto.setText("¡Alta procesada correctamente!");		
		sentencia.close();
		conexion.close();	
	} catch (SQLException e) {
			if (e.getErrorCode() == 1062){
			correcto.setText("¡La relación ya existe!");	        
			}else{
        	correcto.setText("¡Algo va mal en Gestion!");
        }
	}
}

public static void llenarListaRelaciones(JTable table, DefaultTableModel modelo) throws ClassNotFoundException, SQLException {
	
	conexionBD();
	String sql="SELECT * FROM proyecto.gestion";
	ResultSet resul= sentencia.executeQuery(sql);
	ResultSetMetaData rsMd=resul.getMetaData();
	int numColumnas=rsMd.getColumnCount();
	table.setModel(modelo);
	
	for(int i=1;i<=numColumnas;i++){
		modelo.addColumn(rsMd.getColumnLabel(i).toUpperCase());
	}
	
	while (resul.next())
	{
	   Object [] fila = new Object[numColumnas];
	  for (int i=0;i<numColumnas;i++)
	      fila[i] = resul.getObject(i+1); 
	  modelo.addRow(fila);
	}
		
	TableColumnModel columnModel =  table.getColumnModel();
	columnModel.getColumn(0).setPreferredWidth(50);
	columnModel.getColumn(1).setPreferredWidth(50);
	columnModel.getColumn(2).setPreferredWidth(50);
	columnModel.getColumn(3).setPreferredWidth(50);
	
	resul.close();
    sentencia.close();
    conexion.close();
	
	
}

public static void piezaSuministradas(Proveedor proveedor, Gestion g, JComboBox cproveedor, JTextField tpiezas_suministradas, JTextField tproyecto) throws ClassNotFoundException {
	try {
		conexionBD();
	
	String sql="SELECT count(distinct codproyecto) nproyectos,"
			+ "sum(cantidad)  suma_cantidad "
			+ "FROM proyecto.gestion "
			+ "where codproveedor='"+proveedor.getCodigo()+"';";
	//System.out.println(sql);
	ResultSet resul= sentencia.executeQuery(sql);
	resul.first();
	//System.out.println(resul.getString("pss"));
		
		//System.out.println(resul.getString("pss"));
		tpiezas_suministradas.setText(resul.getString("suma_cantidad"));
		tproyecto.setText(resul.getString("nproyectos"));
		
		 resul.close();
		 sentencia.close();
		 conexion.close();
	} catch (SQLException e) {
		System.out.println(e);
		e.printStackTrace();
	}	
	
	
}

public static void llenarListaSuministrosProveedor(JTable table, DefaultTableModel modelo, Proveedor proveedor) throws ClassNotFoundException, SQLException {
	Piezas pieza=new Piezas();
	conexionBD();
	String sql="select y.codigo, y.nombre, y.precio, z.codigo,z.nombre,x.cantidad  from gestion as x,piezas as y, proyectos as z where codproveedor = '"+proveedor.getCodigo()+"' and  x.codproyecto = z.codigo and  x.codpieza = y.codigo group by codpieza, codproyecto";
	//System.out.println(sql);
	ResultSet resul= sentencia.executeQuery(sql);
	// Bucle para cada resultado en la consulta
	ResultSetMetaData rsMd=resul.getMetaData();
	int numColumnas=rsMd.getColumnCount();
	table.setModel(modelo);
	
	for(int i=1;i<=numColumnas;i++){
		modelo.addColumn(rsMd.getColumnLabel(i).toUpperCase());
	}
	
	while (resul.next())
	{
	   Object [] fila = new Object[numColumnas];
	  for (int i=0;i<numColumnas;i++)
	      fila[i] = resul.getObject(i+1); 
	  modelo.addRow(fila);
	}
		
	TableColumnModel columnModel =  table.getColumnModel();
	columnModel.getColumn(0).setPreferredWidth(50);
	columnModel.getColumn(1).setPreferredWidth(100);
	columnModel.getColumn(2).setPreferredWidth(70);
	columnModel.getColumn(3).setPreferredWidth(50);
	columnModel.getColumn(4).setPreferredWidth(150);
	columnModel.getColumn(5).setPreferredWidth(50);
	
	resul.close();
    sentencia.close();
    conexion.close();
	
}

public static void piezaSuministradasProyecto(Piezas piezas, Gestion g, JTextField tpcodigo, JTextField tnProyecto, JTextField tnumProveedorSuministro, JTextField tcantidad) throws ClassNotFoundException {
	try {
		conexionBD();
		
		String sql = "select count(distinct codproyecto) proyectos,"
				+ " count(distinct codproveedor) proveedores,"
				+ "sum(cantidad) piezas"
				+ "		from gestion"				
				+ "		where codpieza = '"+piezas.getCodigo()+"'";
	
	
	//System.out.println(sql);
	ResultSet resul= sentencia.executeQuery(sql);
	resul.first();
		tnProyecto.setText(resul.getString("proyectos"));
		tnumProveedorSuministro.setText(resul.getString("proveedores"));
		tcantidad.setText(resul.getString("piezas"));
		 resul.close();
		 sentencia.close();
		 conexion.close();
	} catch (SQLException e) {
		System.out.println(e);
		e.printStackTrace();
	}	
	
	
}

public static void numeroProyecto(Piezas piezas, JTextField tnProyecto, JTextField tnumProveedorSuministro,
		JTextField tcantidad) throws ClassNotFoundException {
	try {
		conexionBD();
	
	String sql="select z.codigo,"
			+ " z.nombre, z.ciudad,"
			+ " x.codpieza,"
			+ " count(distinct(x.codpieza)),"
			+ " max(x.cantidad),"
			+ " count(distinct(x.codproveedor) pro) "
			+ " from gestion as x,"
			+ " piezas as y,"
			+ " proyectos as z"			
			+ " where  x.codproyecto= z.codigo";
	//System.out.println(sql);
	ResultSet resul= sentencia.executeQuery(sql);
	resul.first();

		tnProyecto.setText(resul.getString("codigo"));		
		tcantidad.setText(resul.getString("cantidadtotal"));
		
		 resul.close();
		 sentencia.close();
		 conexion.close();
	} catch (SQLException e) {
		System.out.println(e);
		e.printStackTrace();
	}	
	
}

public static void ListaPiezasSuministradas(JTable table, DefaultTableModel modelo, Piezas piezas) throws ClassNotFoundException, SQLException {

	Piezas pieza=new Piezas();
	conexionBD();
	String sql="select z.codigo,"
			+ " z.nombre,"
			+ " y.codigo,"
			+ " y.nombre,"
			+ "x.cantidad "
			+ "from gestion as x,"
			+ "proveedores as y,"
			+ " proyectos as z "
			+ "where codpieza = '"+piezas.getCodigo()+"'"
			+ " and  x.codproyecto = z.codigo"
			+ " and  x.codproveedor = y.codigo "
			+ "group by  codproyecto,codproveedor";
	//System.out.println(sql);
	ResultSet resul= sentencia.executeQuery(sql);
	// Bucle para cada resultado en la consulta
	ResultSetMetaData rsMd=resul.getMetaData();
	int numColumnas=rsMd.getColumnCount();
	table.setModel(modelo);
	
	for(int i=1;i<=numColumnas;i++){
		modelo.addColumn(rsMd.getColumnLabel(i).toUpperCase());
	}
	
	while (resul.next())
	{
	   Object [] fila = new Object[numColumnas];
	  for (int i=0;i<numColumnas;i++)
	      fila[i] = resul.getObject(i+1); 
	  modelo.addRow(fila);
	}
		
	TableColumnModel columnModel =  table.getColumnModel();
	columnModel.getColumn(0).setPreferredWidth(50);
	columnModel.getColumn(1).setPreferredWidth(100);
	columnModel.getColumn(2).setPreferredWidth(70);
	columnModel.getColumn(3).setPreferredWidth(50);
	columnModel.getColumn(4).setPreferredWidth(150);

	
	resul.close();
    sentencia.close();
    conexion.close();
}

public static void estadistica_max_piezas(JTextField tCodPiezaMax, JTextField tNumPiezaMax) throws ClassNotFoundException {
	try {
		conexionBD();
	
	String sql="select codpieza as codigo,"
			+ "max(cantidad) as cantidadtotal"
			+ " from gestion "
			+ " group by codpieza"
			+ " order by 2 desc";
			
	//System.out.println(sql);
	ResultSet resul= sentencia.executeQuery(sql);
	resul.first();

		tCodPiezaMax.setText(resul.getString("codigo"));		
		tNumPiezaMax.setText(resul.getString("cantidadtotal"));
		
		 resul.close();
		 sentencia.close();
		 conexion.close();
	} catch (SQLException e) {
		System.out.println(e);
		e.printStackTrace();
	}	
	
}

public static void estadistica_max_pieza_proyectos(JTextField tCodPiezaMaxProy, JTextField tNumPiezaMaxProy) throws ClassNotFoundException {
	try {
		conexionBD();
	
	String sql="select codpieza as codigo,"
			+ "count(distinct(codproyecto)) as cantidadtotal"
			+ " from gestion"
			+ " group by codpieza "
			+ " order by 2 desc, 1 asc limit 1 ";	
	//System.out.println(sql);
	ResultSet resul= sentencia.executeQuery(sql);
	resul.first();

		tCodPiezaMaxProy.setText(resul.getString("codigo"));		
		tNumPiezaMaxProy.setText(resul.getString("cantidadtotal"));
		
		 resul.close();
		 sentencia.close();
		 conexion.close();
	} catch (SQLException e) {
		System.out.println(e);
		e.printStackTrace();
	}	
	
}

public static void llenarEstadisticaProyectos(JTable table, DefaultTableModel modelo) throws ClassNotFoundException, SQLException {

	Piezas pieza=new Piezas();
	conexionBD();
	
	String sql="select z.codigo,"
			+ " z.nombre,"
			+ " z.ciudad,"
			+ " x.codpieza,"
			+ " count(distinct(x.codpieza)) as numPiezas,"
			+ " max(x.cantidad) as cantidad, "
			+ " count(distinct(x.codproveedor)) as numProveedores "
			+ "from gestion as x,"
			+ "proyectos as z,"
			+ "piezas as  y "
			+ "where x.codproyecto = z.codigo "
			+ "group by codproyecto";
	

	//System.out.println(sql);
	ResultSet resul= sentencia.executeQuery(sql);
	// Bucle para cada resultado en la consulta
	ResultSetMetaData rsMd=resul.getMetaData();
	int numColumnas=rsMd.getColumnCount();
	table.setModel(modelo);
	
	for(int i=1;i<=numColumnas;i++){
		modelo.addColumn(rsMd.getColumnLabel(i).toUpperCase());
	}
	
	while (resul.next())
	{
	   Object [] fila = new Object[numColumnas];
	  for (int i=0;i<numColumnas;i++)
	      fila[i] = resul.getObject(i+1); 
	  modelo.addRow(fila);
	}
		
	TableColumnModel columnModel =  table.getColumnModel();
	columnModel.getColumn(0).setPreferredWidth(50);
	columnModel.getColumn(1).setPreferredWidth(100);
	columnModel.getColumn(2).setPreferredWidth(150);
	columnModel.getColumn(3).setPreferredWidth(50);
	columnModel.getColumn(4).setPreferredWidth(50);
	columnModel.getColumn(5).setPreferredWidth(100);

	
	resul.close();
    sentencia.close();
    conexion.close();
	
}

public static void llenarEstadisticaProveedores(JTable table, DefaultTableModel modelo) throws ClassNotFoundException, SQLException {

	Piezas pieza=new Piezas();
	conexionBD();
	String sql="select w.codigo,"
 			+ " w.nombre,"
 			+ " w.apellidos,"
 			+ " count(distinct(x.codpieza)) as numPiezas,"
 			+ "max(x.cantidad) as maxCantidad, "
 			+ " count(distinct(x.codproyecto)) as numProyectos "
 			+ "from gestion as x,"
 			+ "proveedores as w,"
 			+ " proyectos as z "
 			+ "where x.codproveedor = w.codigo "
 			+ "group by codproveedor";

	//System.out.println(sql);
	ResultSet resul= sentencia.executeQuery(sql);
	// Bucle para cada resultado en la consulta
	ResultSetMetaData rsMd=resul.getMetaData();
	int numColumnas=rsMd.getColumnCount();
	table.setModel(modelo);
	
	for(int i=1;i<=numColumnas;i++){
		modelo.addColumn(rsMd.getColumnLabel(i).toUpperCase());
	}
	
	while (resul.next())
	{
	   Object [] fila = new Object[numColumnas];
	  for (int i=0;i<numColumnas;i++)
	      fila[i] = resul.getObject(i+1); 
	  modelo.addRow(fila);
	}
		
	TableColumnModel columnModel =  table.getColumnModel();
	columnModel.getColumn(0).setPreferredWidth(50);
	columnModel.getColumn(1).setPreferredWidth(100);
	columnModel.getColumn(2).setPreferredWidth(270);
	columnModel.getColumn(3).setPreferredWidth(50);
	columnModel.getColumn(4).setPreferredWidth(50);
	columnModel.getColumn(5).setPreferredWidth(50);
	

	
	resul.close();
    sentencia.close();
    conexion.close();
	
}

public static void estadistica_num_pieza_proveedor(JTextField tproveedorPiezasNombre,
		JTextField tproveedorPiezasNumero) throws ClassNotFoundException {
	
	try {
		conexionBD();
	
	String sql="select codproveedor as codigo,"
			+ "max(cantidad) as cantidadtotal"
			+ " from gestion "
			+ " group by codproveedor "
			+ " order by 2 desc, 1 asc";	
	//System.out.println(sql);
	ResultSet resul= sentencia.executeQuery(sql);
	resul.first();

		tproveedorPiezasNombre.setText(resul.getString("codigo"));		
		tproveedorPiezasNumero.setText(resul.getString("cantidadtotal"));
		
		 resul.close();
		 sentencia.close();
		 conexion.close();
	} catch (SQLException e) {
		System.out.println(e);
		e.printStackTrace();
	}	
	
}

public static void estadistica_num_pieza_proyectos(JTextField tproyectoNombre, JTextField tproyectoNumero) throws ClassNotFoundException {
	try {
		conexionBD();
	
	String sql="select distinct(codproveedor) as codigo,count(distinct(codproyecto)) as cantidadtotal from gestion group by codproveedor order by 2 desc,1 asc limit 1";
	//System.out.println(sql);
	ResultSet resul= sentencia.executeQuery(sql);
	resul.first();

		tproyectoNombre.setText(resul.getString("codigo"));		
		tproyectoNumero.setText(resul.getString("cantidadtotal"));
		
		 resul.close();
		 sentencia.close();
		 conexion.close();
	} catch (SQLException e) {
		System.out.println(e);
		e.printStackTrace();
	}	
	
}

public static void estadistica_num_provveedor_suministro_piezas(JTextField tcodigoProveedor, JTextField tnombrepieza) throws ClassNotFoundException {
	try {
		conexionBD();
		String sql="select distinct(codproveedor) as codigo,"
				+ "(count(distinct(codpieza))) as cantidadtotal"
				+ " from gestion "
				+ "	group by codproveedor "
				+ "	order by 2 desc, 1 asc limit 1";
	//String sql="select z.codigo,z.nombre,z.ciudad,x.codpieza, count(distinct(x.codpieza)) as codigo , max(x.cantidad), count(distinct(x.codproveedor)) as cantidadtotal from gestion as x,piezas as y,proyectos as z where x.codproyecto=z.codigo group by codproyecto";
	//System.out.println(sql);
	ResultSet resul= sentencia.executeQuery(sql);
	resul.first();

		tcodigoProveedor.setText(resul.getString("codigo"));		
		tnombrepieza.setText(resul.getString("cantidadtotal"));
		
		 resul.close();
		 sentencia.close();
		 conexion.close();
	} catch (SQLException e) {
		System.out.println(e);
		e.printStackTrace();
	}	
	
}




}