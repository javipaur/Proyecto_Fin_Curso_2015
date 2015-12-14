package Vista;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.sql.SQLException;

import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.border.EmptyBorder;

import BD.Bd;
import Modelo.Piezas;
import Modelo.Proyectos;
import Modelo.Validaciones;

public class Proyecto_Gestion extends JFrame {


	private JPanel contentPane;
	private JTextField tcodigoProyecto;
	private JTextField tnombre;
	private JTextField tciudad;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Piezas_Gestion frame = new Piezas_Gestion();
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
	public Proyecto_Gestion() {
		setTitle("Gestion de Proyectos");
		setBounds(100, 100, 480, 307);
		JLabel correcto = new JLabel("");
		correcto.setVerticalAlignment(SwingConstants.BOTTOM);
		correcto.setVisible(false);
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu mnListado = new JMenu("Listado");
		menuBar.add(mnListado);
		
		JMenuItem mntmListadoPiezases = new JMenuItem("Listado Proyectos");
		mntmListadoPiezases.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Proyectos_Listado lPiezases = null;
				try {
					lPiezases = new Proyectos_Listado();
				} catch (ClassNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				lPiezases.setVisible(true);
			}
		});
		mnListado.add(mntmListadoPiezases);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		JLabel lblNewLabel_1 = new JLabel("Codigo:");
		
		JLabel lblNombre = new JLabel("Nombre:");
		
		JLabel lblDireccion_1 = new JLabel("Ciudad:");
		
		tcodigoProyecto = new JTextField();

		tcodigoProyecto.setColumns(10);
		
		tnombre = new JTextField();
		tnombre.addFocusListener(new FocusAdapter() {
			
			public void focusGained(FocusEvent e) {
				//Codigo
				if(tcodigoProyecto.getText().length()==0){
					tcodigoProyecto.requestFocus();
					JOptionPane.showMessageDialog(null, "¡Tienes que introducir un Codigo!");	
				}else{	
					if(tcodigoProyecto.getText().length()>6){
						tcodigoProyecto.setText(tcodigoProyecto.getText().substring(0, 6));
					}
					
					String codigo=tcodigoProyecto.getText().toUpperCase();
					tcodigoProyecto.setText(codigo);
	
					Proyectos proyecto=new Proyectos();
					proyecto.setCodigo(codigo);
					Proyectos p = null;
					try {
						p = Bd.BuscarProyectos(proyecto);
					} catch (ClassNotFoundException | SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
	
					if(p!=null){
						tnombre.setText(p.getNombre());
						tciudad.setText(p.getCiudad());
					}
				}
				
			}
		});
		tnombre.setColumns(10);
		
		tciudad = new JTextField();
		tciudad.setColumns(10);
		
		JButton bLimpiar = new JButton("Limpiar");
		bLimpiar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				tcodigoProyecto.setText("");
				tnombre.setText("");
				tciudad.setText("");
			}
		});
		
		JButton bModificar = new JButton("Modificar");
		bModificar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Validaciones.validar_Proyectos(tcodigoProyecto,tnombre,tciudad);
				Proyectos p=new Proyectos();
				p.setCodigo(tcodigoProyecto.getText());
				p.setNombre(tnombre.getText());
				p.setCiudad(tciudad.getText());
				try {
					Bd.modificarProyecto(p,correcto);
				} catch (ClassNotFoundException | SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
}
		});
		
		JButton beliminar = new JButton("Eliminar");
		beliminar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Validaciones.validar_Proyectos(tcodigoProyecto, tnombre, tciudad);
				Proyectos p=new Proyectos();
				p.setCodigo(tcodigoProyecto.getText());
				try {
					Bd.eliminarProyecto(p,correcto);
				} catch (ClassNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		
		JButton bInsertar = new JButton("Insertar");
		bInsertar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Validaciones.validar_Proyectos(tcodigoProyecto, tnombre, tciudad);
				Proyectos p=new Proyectos();
				p.setCodigo(tcodigoProyecto.getText());
				p.setNombre(tnombre.getText());
				p.setCiudad(tciudad.getText());
				try {
					Bd.insertarProyecto(p,correcto);
				} catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}	
			}
		});
		
		
		correcto.setForeground(new Color(0, 128, 0));
		correcto.setBackground(new Color(0, 128, 0));
		correcto.setHorizontalAlignment(SwingConstants.CENTER);
		
		JLabel lblGestinDePiezases = new JLabel("GESTION DE PROYECTOS");
		lblGestinDePiezases.setFont(new Font("Segoe UI", Font.BOLD, 20));
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
								.addComponent(lblDireccion_1)
								.addComponent(lblNewLabel_1))
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
											.addPreferredGap(ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
										.addComponent(tciudad, GroupLayout.DEFAULT_SIZE, 360, Short.MAX_VALUE)
										.addComponent(tnombre, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 360, Short.MAX_VALUE)))
								.addGroup(gl_contentPane.createSequentialGroup()
									.addGap(18)
									.addComponent(tcodigoProyecto, GroupLayout.PREFERRED_SIZE, 77, GroupLayout.PREFERRED_SIZE)))))
					.addGap(120))
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(138)
					.addComponent(lblGestinDePiezases)
					.addContainerGap(175, Short.MAX_VALUE))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addComponent(lblGestinDePiezases)
					.addGap(21)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNewLabel_1, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(tcodigoProyecto, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNombre)
						.addComponent(tnombre, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblDireccion_1)
						.addComponent(tciudad, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
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
