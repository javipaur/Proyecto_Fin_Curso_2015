package Vista;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.sql.SQLException;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import BD.Bd;
import Modelo.Piezas;
import Modelo.Proveedor;
import Modelo.Proyectos;
import Modelo.Validaciones;

import javax.swing.JLabel;
import javax.swing.DefaultListModel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import javax.swing.JList;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class Gestion_Global_Piezas_Proveedor_Proyecto extends JFrame {

	private JPanel contentPane;
	private JTextField tcantidad;
	private JTextField tcantidad_1;
	private JTextField tdproveedores;
	private JTextField prove_nombre;
	private JTextField prove_direccion;
	private JTextField tpieza_nombre;
	private JTextField tpiezas_precio;
	private JTextField tpiezas_descripcion;
	private JTextField tproyecto_nombre;
	private JTextField tproyecto_ciudad;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Gestion_Global_Piezas_Proveedor_Proyecto frame = new Gestion_Global_Piezas_Proveedor_Proyecto();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 * @throws SQLException 
	 * @throws ClassNotFoundException 
	 */
	public Gestion_Global_Piezas_Proveedor_Proyecto() throws ClassNotFoundException, SQLException {
		setTitle("Piezas,proveedores y Proyectos");
		setBounds(100, 100, 589, 494);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		JLabel lblNewLabel = new JLabel("RELACIONES PIEZAS-PROVEEDORES-PROYECTOS");
		lblNewLabel.setFont(new Font("Segoe UI", Font.BOLD, 20));
		
		JLabel lblNewLabel_1 = new JLabel("Proveedor:");
		
		JLabel lblPieza = new JLabel("Pieza:");
		
		JLabel lblProyecto = new JLabel("Proyecto:");
		JLabel lblCantidad = new JLabel("Cantidad:");
		JComboBox cproveedor = new JComboBox();
		JTextField prove_nombre = new JTextField();
		prove_nombre.setColumns(10);
		JTextField prove_direccion = new JTextField();
		prove_direccion.setColumns(10);
		
		cproveedor.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent arg0) {
				Proveedor proveedor=new Proveedor();
				proveedor.setCodigo(String.valueOf(cproveedor.getSelectedItem()));
				try {
					
					DefaultListModel modelo = new DefaultListModel();
					if(cproveedor.getSelectedIndex()!=0){
						Bd.buscarProveedor(proveedor);
						prove_nombre.setText(proveedor.getNombre()+" "+proveedor.getApellido());
						prove_direccion.setText(proveedor.getDireccion());
					}
					
				} catch (ClassNotFoundException | SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		JTextField tpieza_nombre = new JTextField();
		tpieza_nombre.setColumns(10);

		JTextField tpiezas_precio = new JTextField();
		tpiezas_precio.setColumns(10);
		
		JTextField tpiezas_descripcion = new JTextField();
		tpiezas_descripcion.setColumns(10);
		
		JTextField tproyecto_nombre = new JTextField();
		tproyecto_nombre.setColumns(10);
		
		JTextField tproyecto_ciudad = new JTextField();
		tproyecto_ciudad.setColumns(10);
		tcantidad = new JTextField();
		tcantidad.setColumns(10);
		DefaultListModel modelo = new DefaultListModel();
		JComboBox cpieza = new JComboBox();
		cpieza.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				Piezas pieza=new Piezas();
				pieza.setCodigo(String.valueOf(cpieza.getSelectedItem()));
				try {
					if(cproveedor.getSelectedIndex()!=0){
						Bd.buscarPiezas(pieza);
						DefaultListModel modelo = new DefaultListModel();
						tpieza_nombre.setText(pieza.getNombre());
						tpiezas_precio.setText(String.valueOf(pieza.getPrecio()));
						tpiezas_descripcion.setText(pieza.getDescripcion());
					}
					
					
				} catch (ClassNotFoundException | SQLException es) {
					// TODO Auto-generated catch block
					es.printStackTrace();
				}
			}
		});
		JComboBox cproyecto = new JComboBox();
		cproyecto.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent arg0) {
				Proyectos p=new Proyectos();
				p.setCodigo(String.valueOf(cproyecto.getSelectedItem()));
				try {
					if(cproveedor.getSelectedIndex()!=0){
						Bd.BuscarProyectos(p);
						DefaultListModel modelo = new DefaultListModel();
						tproyecto_nombre.setText(p.getNombre());
						tproyecto_ciudad.setText(p.getCiudad());
					}
					
					
				} catch (ClassNotFoundException | SQLException ex) {
					// TODO Auto-generated catch block
					ex.printStackTrace();
				}
			}
		});
		
		//JComboBox cproyecto = new JComboBox();
		
		
		
		JTextField tcantidad_1 = new JTextField();
		tcantidad_1.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent evt) {
				char car = evt.getKeyChar();
				if((car<'0' || car>'9')) {
					evt.consume();
				}
			}
		});
		tcantidad_1.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent arg0) {
				try {
				
					Bd.BuscarCantidad(cpieza,cproyecto,cproveedor,tcantidad_1);
				} catch (ClassNotFoundException | SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		tcantidad_1.setColumns(10);
		tcantidad_1.requestFocusInWindow();
		
		Proveedor p=new Proveedor();
		
		Bd.DesplegableProveedores(p,cproveedor);
		Piezas pi=new Piezas();
		Bd.DesplegablePieza(pi, cpieza);
		Proyectos po=new Proyectos();
		Bd.DesplegableProyecto(po,cproyecto);
		
		JLabel correcto = new JLabel("");
		JButton bLimpiar = new JButton("Limpiar");
		bLimpiar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cpieza.setSelectedIndex(0);
				cproyecto.setSelectedIndex(0);
				cproveedor.setSelectedIndex(0);
				
				tcantidad_1.setText(" ");
 				prove_nombre.setText(" ");
 				prove_direccion.setText(" ");
				tproyecto_ciudad.setText(" ");
				tproyecto_nombre.setText(" ");
				tpieza_nombre.setText("");	
				tpiezas_precio.setText("");	
				tpiezas_descripcion.setText("");
				
			}
		});
		
		JButton button_1 = new JButton("Insertar");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					if(!Validaciones.validarGestion(cpieza,cproyecto,cproveedor,tcantidad_1))
					
					Bd.InsertarCantidad(cpieza,cproyecto,cproveedor,tcantidad_1,correcto);
				} catch (ClassNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		
		JButton button_2 = new JButton("Eliminar");
		button_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					if(!Validaciones.validarGestion(cpieza,cproyecto,cproveedor,tcantidad_1))
					Bd.EliminarCantidad(cpieza,cproyecto,cproveedor,tcantidad_1,correcto);
				} catch (ClassNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		
		JButton button_3 = new JButton("Modificar");
		button_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					if(!Validaciones.validarGestion(cpieza,cproyecto,cproveedor,tcantidad_1))
					Bd.ModificarCantidad(cpieza,cproyecto,cproveedor,tcantidad_1,correcto);
				} catch (ClassNotFoundException | SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		
		JButton btnListado = new JButton("Listado");
		btnListado.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Gestion_Listado gl = null;
				try {
					gl = new Gestion_Listado();
				} catch (ClassNotFoundException | SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				gl.setVisible(true);
			}
		});
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_contentPane.createSequentialGroup()
									.addContainerGap()
									.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
										.addComponent(lblNewLabel_1)
										.addComponent(lblPieza)
										.addComponent(lblProyecto))
									.addGap(18)
									.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
										.addComponent(cproyecto, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
										.addComponent(cpieza, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
										.addComponent(cproveedor, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
								.addGroup(gl_contentPane.createSequentialGroup()
									.addContainerGap()
									.addComponent(lblCantidad)))
							.addGap(64)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addComponent(tcantidad_1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING, false)
									.addComponent(tproyecto_nombre, Alignment.LEADING)
									.addComponent(tpiezas_descripcion, Alignment.LEADING)
									.addComponent(tpiezas_precio, Alignment.LEADING)
									.addComponent(tpieza_nombre, Alignment.LEADING)
									.addComponent(prove_direccion)
									.addComponent(prove_nombre, GroupLayout.DEFAULT_SIZE, 225, Short.MAX_VALUE)
									.addComponent(tproyecto_ciudad, Alignment.LEADING))))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(45)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
								.addComponent(lblNewLabel, GroupLayout.PREFERRED_SIZE, 486, GroupLayout.PREFERRED_SIZE)
								.addGroup(gl_contentPane.createSequentialGroup()
									.addComponent(bLimpiar, GroupLayout.PREFERRED_SIZE, 78, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(button_1, GroupLayout.PREFERRED_SIZE, 80, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(button_2, GroupLayout.PREFERRED_SIZE, 81, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(ComponentPlacement.UNRELATED)
									.addComponent(button_3, GroupLayout.PREFERRED_SIZE, 90, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(btnListado, GroupLayout.PREFERRED_SIZE, 81, GroupLayout.PREFERRED_SIZE)
									.addGap(61))))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(33)
							.addComponent(correcto, GroupLayout.PREFERRED_SIZE, 496, GroupLayout.PREFERRED_SIZE)))
					.addContainerGap(19, Short.MAX_VALUE))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblNewLabel)
					.addGap(30)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNewLabel_1)
						.addComponent(cproveedor, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(prove_nombre, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(prove_direccion, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addGap(31)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(cpieza, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblPieza)
						.addComponent(tpieza_nombre, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(tpiezas_precio, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(tpiezas_descripcion, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(cproyecto, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(tproyecto_nombre, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblProyecto))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(tproyecto_ciudad, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addGap(26)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblCantidad)
						.addComponent(tcantidad_1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(29)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(button_2)
						.addComponent(button_1)
						.addComponent(btnListado)
						.addComponent(button_3)
						.addComponent(bLimpiar))
					.addGap(18)
					.addComponent(correcto, GroupLayout.PREFERRED_SIZE, 22, GroupLayout.PREFERRED_SIZE)
					.addGap(152))
		);
		contentPane.setLayout(gl_contentPane);
	}
}
