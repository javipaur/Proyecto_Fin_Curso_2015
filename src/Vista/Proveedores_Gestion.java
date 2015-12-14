package Vista;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import Modelo.Validaciones;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import BD.Bd;
import Modelo.Proveedor;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.awt.event.ActionEvent;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JMenu;
import java.awt.Color;
import java.awt.Dialog.ModalExclusionType;
import java.awt.event.InputMethodListener;
import java.awt.event.InputMethodEvent;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeEvent;

public class Proveedores_Gestion extends JFrame {

	private JPanel contentPane;
	private JTextField tcodigoProveedor;
	private JTextField tnombre;
	private JTextField tapellido;
	private JTextField tdireccion;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Proveedores_Gestion frame = new Proveedores_Gestion();
					frame.setVisible(true);
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Proveedores_Gestion() {
		setTitle("Gestion de proveedores");
		setBounds(100, 100, 466, 307);
		JLabel correcto = new JLabel("");
		correcto.setVerticalAlignment(SwingConstants.BOTTOM);
		correcto.setVisible(false);
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu mnListado = new JMenu("Listado");
		menuBar.add(mnListado);
		
		JMenuItem mntmListadoproveedores = new JMenuItem("Listado Proveedores");
		mntmListadoproveedores.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Proveedores_Listado lproveedores = null;
				try {
					lproveedores = new Proveedores_Listado();
				} catch (ClassNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				lproveedores.setVisible(true);
			}
		});
		mnListado.add(mntmListadoproveedores);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		JLabel lblNewLabel_1 = new JLabel("Codigo Proveedor:");
		
		JLabel lblNombre = new JLabel("Nombre:");
		
		JLabel lblDireccion = new JLabel("Apellidos:");
		
		JLabel lblDireccion_1 = new JLabel("Direccion:");
		
		tcodigoProveedor = new JTextField();

		tcodigoProveedor.setColumns(10);
		
		tnombre = new JTextField();
		tnombre.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				try {
					//Codigo
					if(tcodigoProveedor.getText().length()==0){
						tcodigoProveedor.requestFocus();
						JOptionPane.showMessageDialog(null, "¡Tienes que introducir un Codigo!");	
					}else{	
						if(tcodigoProveedor.getText().length()>6){
							tcodigoProveedor.setText(tcodigoProveedor.getText().substring(0, 6));
						}
						
					String codigo=tcodigoProveedor.getText().toUpperCase();
					tcodigoProveedor.setText(codigo);

					Proveedor proveedor=new Proveedor();
					proveedor.setCodigo(codigo);
					Proveedor p=Bd.buscarProveedor(proveedor);

					if(p!=null){
					tnombre.setText(p.getNombre());
					tapellido.setText(p.getApellido());
					tdireccion.setText(p.getDireccion());
					}
					}
				} catch (ClassNotFoundException | SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
			}
		});
		tnombre.setColumns(10);
		
		tapellido = new JTextField();
		tapellido.setColumns(10);
		
		tdireccion = new JTextField();
		tdireccion.setColumns(10);
		
		JButton bLimpiar = new JButton("Limpiar");
		bLimpiar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				tcodigoProveedor.setText("");
				tnombre.setText("");
				tapellido.setText("");
				tdireccion.setText("");
			}
		});
		
		JButton bModificar = new JButton("Modificar");
		bModificar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Validaciones.validar_Proveedor(tcodigoProveedor,tnombre,tapellido,tdireccion);
				Proveedor proveedor=new Proveedor();
				proveedor.setCodigo(tcodigoProveedor.getText());
				proveedor.setNombre(tnombre.getText());
				proveedor.setApellido(tapellido.getText());
				proveedor.setDireccion(tdireccion.getText());
				try {
					
					Bd.modificarProveedor(proveedor,correcto);
//					tcodigoProveedor.setText("");
//					tnombre.setText("");
//					tapellido.setText("");
//					tdireccion.setText("");
				} catch (ClassNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			
			}
		});
		
		JButton beliminar = new JButton("Eliminar");
		beliminar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Validaciones.validar_Proveedor(tcodigoProveedor,tnombre,tapellido,tdireccion);
				Proveedor proveedor=new Proveedor();
				proveedor.setCodigo(tcodigoProveedor.getText());
				try {
					Bd.eliminarProveedor(proveedor,correcto);
//					tcodigoProveedor.setText("");
//					tnombre.setText("");
//					tapellido.setText("");
//					tdireccion.setText("");
					
				} catch (ClassNotFoundException | SQLException ex) {
					// TODO Auto-generated catch block
					ex.printStackTrace();
				}
			}
		});
		
		JButton bInsertar = new JButton("Insertar");
		bInsertar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Validaciones.validar_Proveedor(tcodigoProveedor,tnombre,tapellido,tdireccion);
				Proveedor proveedor=new Proveedor();
				proveedor.setCodigo(tcodigoProveedor.getText());
				proveedor.setNombre(tnombre.getText());
				proveedor.setApellido(tapellido.getText());
				proveedor.setDireccion(tdireccion.getText());
				//System.out.println(tnombre.getText());
					try {
						Bd.insertarProveedor(proveedor,correcto);
//						tcodigoProveedor.setText("");
//						tnombre.setText("");
//						tapellido.setText("");
//						tdireccion.setText("");
					} catch (ClassNotFoundException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
			}
		});
		
		
		correcto.setForeground(new Color(0, 128, 0));
		correcto.setBackground(new Color(0, 128, 0));
		correcto.setHorizontalAlignment(SwingConstants.CENTER);
		
		JLabel lblGestinDeProveedores = new JLabel("GESTION DE PROVEEDORES");
		lblGestinDeProveedores.setFont(new Font("Segoe UI", Font.BOLD, 20));
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addComponent(correcto, GroupLayout.PREFERRED_SIZE, 419, GroupLayout.PREFERRED_SIZE)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addComponent(lblNombre)
								.addComponent(lblDireccion)
								.addComponent(lblDireccion_1))
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_contentPane.createSequentialGroup()
									.addGap(18)
									.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
										.addGroup(gl_contentPane.createSequentialGroup()
											.addGap(1)
											.addComponent(bLimpiar)
											.addPreferredGap(ComponentPlacement.RELATED)
											.addComponent(bInsertar)
											.addPreferredGap(ComponentPlacement.RELATED)
											.addComponent(beliminar)
											.addPreferredGap(ComponentPlacement.RELATED)
											.addComponent(bModificar)
											.addPreferredGap(ComponentPlacement.RELATED, 55, Short.MAX_VALUE))
										.addComponent(tdireccion, GroupLayout.DEFAULT_SIZE, 354, Short.MAX_VALUE)
										.addGroup(gl_contentPane.createSequentialGroup()
											.addComponent(tnombre, GroupLayout.PREFERRED_SIZE, 217, GroupLayout.PREFERRED_SIZE)
											.addPreferredGap(ComponentPlacement.RELATED, 137, Short.MAX_VALUE))))
								.addGroup(gl_contentPane.createSequentialGroup()
									.addGap(18)
									.addComponent(tapellido, GroupLayout.PREFERRED_SIZE, 285, GroupLayout.PREFERRED_SIZE))))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(lblNewLabel_1)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(tcodigoProveedor, GroupLayout.PREFERRED_SIZE, 77, GroupLayout.PREFERRED_SIZE)
							.addGap(186)))
					.addGap(120))
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(85)
					.addComponent(lblGestinDeProveedores)
					.addContainerGap(201, Short.MAX_VALUE))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addComponent(lblGestinDeProveedores)
					.addGap(21)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNewLabel_1, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(tcodigoProveedor, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblNombre)
								.addComponent(tnombre, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(32)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblDireccion)
								.addComponent(tapellido, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblDireccion_1)
						.addComponent(tdireccion, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(35)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(bModificar)
						.addComponent(beliminar)
						.addComponent(bInsertar)
						.addComponent(bLimpiar))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(correcto, GroupLayout.PREFERRED_SIZE, 23, GroupLayout.PREFERRED_SIZE)
					.addGap(18))
		);
		contentPane.setLayout(gl_contentPane);
	}
}
