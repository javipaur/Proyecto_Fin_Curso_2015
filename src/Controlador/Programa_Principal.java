package Controlador;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.GroupLayout;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.SwingConstants;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.border.EmptyBorder;

import BD.Bd;
import Vista.*;
import Vista.Gestion_Global_Estadisticas;
import Vista.Gestion_Global_Piezas_Proveedor_Proyecto;
import Vista.Gestion_Global_Suministro_Piezas;
import Vista.Gestion_Global_Suministro_Proveedor;
import Vista.Piezas_Consulta;
import Vista.Piezas_Gestion;
import Vista.Proveedores_Consulta;
import Vista.Proveedores_Gestion;
import Vista.Proyecto_Gestion;
import Vista.Proyectos_Listado;


public class Programa_Principal extends JFrame {

	private JPanel contentPane;
	private final Action action = new SwingAction();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Programa_Principal v = new Programa_Principal();
					
					v.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		
		
	}

	/**
	 * Create the frame.
	 * @throws ClassNotFoundException 
	 * @throws SQLException 
	 */
	public Programa_Principal() throws ClassNotFoundException, SQLException {
		setResizable(false);
		setTitle("Gestor de Proyectos");
		setBounds(100, 100, 751, 354);
		
		
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu mnBaseDeDatos = new JMenu("Base de Datos");
		menuBar.add(mnBaseDeDatos);
		
		JMenuItem mntmCrearBd = new JMenuItem("Crear BD");
		mntmCrearBd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					Bd.crearBD();
				} catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		mnBaseDeDatos.add(mntmCrearBd);
		
		JMenuItem mntmBorrarBd = new JMenuItem("Borrar BD");
		mntmBorrarBd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					Bd.borrarBD();
				} catch (ClassNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		mnBaseDeDatos.add(mntmBorrarBd);
		
		JMenuItem mntmSalir = new JMenuItem("Salir");
		mntmSalir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		mnBaseDeDatos.add(mntmSalir);
		
		JMenu mPorveedores = new JMenu("Proveedores");
		menuBar.add(mPorveedores);
		
		JMenuItem mGestionProveedores = new JMenuItem("Gestion de Proveedores");
		mGestionProveedores.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Proveedores_Gestion gproveedor=new Proveedores_Gestion();
				gproveedor.setVisible(true);
				
			}
		});
		mPorveedores.add(mGestionProveedores);
		
		JMenuItem mConsultaDeProveedores = new JMenuItem("Consulta de Proveedores");
		mConsultaDeProveedores.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Proveedores_Consulta pc = null;
				try {
					pc = new Proveedores_Consulta();
				} catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				pc.setVisible(true);
			}
		});
		mPorveedores.add(mConsultaDeProveedores);
		
		JMenu mnJefeProyecto = new JMenu("Piezas");
		menuBar.add(mnJefeProyecto);
		
		JMenuItem mntmAlta_1 = new JMenuItem("Gestion de Piezas");
		mntmAlta_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Piezas_Gestion gpiezas=new Piezas_Gestion();
				gpiezas.setVisible(true);
			}
		});
		mnJefeProyecto.add(mntmAlta_1);
		
		JMenuItem mntmBaja_1 = new JMenuItem("Consulta de Piezas");
		mntmBaja_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Piezas_Consulta cpiezas = null;
				try {
					cpiezas = new Piezas_Consulta();
				} catch (ClassNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				cpiezas.setVisible(true);
			}
		});
		mnJefeProyecto.add(mntmBaja_1);
		
		JMenu menu = new JMenu("Proyectos");
		menuBar.add(menu);
		
		JMenuItem mntmGestionProyectos = new JMenuItem("Gestion Proyectos");
		mntmGestionProyectos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Proyecto_Gestion pg=new Proyecto_Gestion();
				pg.setVisible(true);
			}
		});
		menu.add(mntmGestionProyectos);
		
		JMenuItem mlistadoP = new JMenuItem("Listado Proyectos");
		mlistadoP.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Proyectos_Listado plst = null;
				try {
					plst = new Proyectos_Listado();
				} catch (ClassNotFoundException | SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				plst.setVisible(true);
			}
		});
		
		menu.add(mlistadoP);
		
		JMenu mnListado = new JMenu("Gestion Global");
		menuBar.add(mnListado);
		
		JMenuItem mntmPiezasproveedoresYproyectos = new JMenuItem("Piezas,Proveedores y Proyectos");
		mntmPiezasproveedoresYproyectos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Gestion_Global_Piezas_Proveedor_Proyecto ggppp = null;
				try {
					ggppp = new Gestion_Global_Piezas_Proveedor_Proyecto();
				} catch (ClassNotFoundException | SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				ggppp.setVisible(true);
			}
		});
		mnListado.add(mntmPiezasproveedoresYproyectos);
		
		
		
		
		JMenuItem mntmListadoProyectos = new JMenuItem("Suministros por Piezas");
		mntmListadoProyectos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Gestion_Global_Suministro_Piezas gsp1 = null;
				try {
					gsp1 = new Gestion_Global_Suministro_Piezas();
				} catch (ClassNotFoundException | SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				gsp1.setVisible(true);
			}
		});
		
		JMenuItem mntmSuministrosPorProveedor = new JMenuItem("Suministros por Proveedor");
		mntmSuministrosPorProveedor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Gestion_Global_Suministro_Proveedor gsp2 = null;
				try {
					gsp2 = new Gestion_Global_Suministro_Proveedor();
				} catch (ClassNotFoundException | SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				gsp2.setVisible(true);
			}
		});
		mnListado.add(mntmSuministrosPorProveedor);
		mnListado.add(mntmListadoProyectos);
		
		JMenuItem mntmEstadisticas = new JMenuItem("Estadisticas");
		mntmEstadisticas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Gestion_Global_Estadisticas ge = null;
				try {
					ge = new Gestion_Global_Estadisticas();
				} catch (ClassNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				ge.setVisible(true);
			}
		});
		mnListado.add(mntmEstadisticas);
		
		JMenu mnAyuda = new JMenu("Ayuda");
		menuBar.add(mnAyuda);
		
		JMenuItem mntmSobreMi = new JMenuItem("Sobre Mi");
		mntmSobreMi.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Ayuda ayuda=new Ayuda();
				ayuda.setVisible(true);
			}
		});
		mnAyuda.add(mntmSobreMi);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		JLabel titulo = new JLabel("");
		titulo.setIcon(new ImageIcon(Programa_Principal.class.getResource("/img/logo1.jpg")));
		
		
		
		
		titulo.setHorizontalAlignment(SwingConstants.CENTER);
		titulo.setFont(new Font("Simplified Arabic", Font.BOLD, 42));
		
		JLabel lblEstadoBaseDe = new JLabel("Estado Base de Datos");
		
		JLabel estado = new JLabel("");
		estado.setForeground(new Color(0, 128, 0));
		Bd.conexion(estado);

		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addContainerGap()
							.addComponent(titulo, GroupLayout.PREFERRED_SIZE, 716, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(lblEstadoBaseDe)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(estado, GroupLayout.DEFAULT_SIZE, 614, Short.MAX_VALUE)))
					.addContainerGap())
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addComponent(titulo, GroupLayout.PREFERRED_SIZE, 246, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED, 24, Short.MAX_VALUE)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
						.addComponent(estado, GroupLayout.DEFAULT_SIZE, 14, Short.MAX_VALUE)
						.addComponent(lblEstadoBaseDe)))
		);
		contentPane.setLayout(gl_contentPane);
		
	}
	private static void addPopup(Component component, final JPopupMenu popup) {
		component.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				if (e.isPopupTrigger()) {
					showMenu(e);
				}
			}
			public void mouseReleased(MouseEvent e) {
				if (e.isPopupTrigger()) {
					showMenu(e);
				}
			}
			private void showMenu(MouseEvent e) {
				popup.show(e.getComponent(), e.getX(), e.getY());
			}
		});
	}
	private class SwingAction extends AbstractAction {
		public SwingAction() {
			putValue(NAME, "SwingAction");
			putValue(SHORT_DESCRIPTION, "Some short description");
		}
		public void actionPerformed(ActionEvent e) {
		}
	}
}
