package Vista;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.sql.SQLException;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import BD.Bd;
import Modelo.Gestion;
import Modelo.Piezas;
import Modelo.Proveedor;
import Modelo.Proyectos;

import javax.swing.JLabel;
import javax.swing.DefaultListModel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JComboBox;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JTextArea;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Gestion_Global_Suministro_Piezas extends JFrame {

	private JPanel contentPane;
	private JTextField tnProyecto;
	private JTextField tnumProveedorSuministro;
	private JTextField tcantidad;
	private JTextField tpcodigo;
	private JTextField tpprecio;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Gestion_Global_Suministro_Piezas frame = new Gestion_Global_Suministro_Piezas();
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
	public Gestion_Global_Suministro_Piezas() throws ClassNotFoundException, SQLException {
		setTitle("Gestion suministros Piezas");
		setBounds(100, 100, 510, 314);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		
		JLabel lblNewLabel = new JLabel("Pieza:");
		
		JComboBox cpieza = new JComboBox();
		Piezas piezas=new Piezas();
		cpieza.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent arg0) {

				piezas.setCodigo(String.valueOf(cpieza.getSelectedItem()));
				try {
					
					DefaultListModel modelo = new DefaultListModel();
					if(cpieza.getSelectedIndex()!=0){
						Bd.buscarPiezas(piezas);
						tpcodigo.setText(piezas.getNombre());
						tpprecio.setText(String.valueOf(piezas.getPrecio()));
						
						Gestion g=new Gestion();
						Bd.piezaSuministradasProyecto(piezas,g,tpcodigo,tnProyecto,tnumProveedorSuministro,tcantidad);
						
					}
					
				} catch (ClassNotFoundException | SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		Piezas pi=new Piezas();
		Bd.DesplegablePieza(pi, cpieza);
		
		JLabel lblNProyecto = new JLabel("N\u00BA Proyectos:");
		
		tnProyecto = new JTextField();
		tnProyecto.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("Numero Proveedores que la suministran: ");
		
		tnumProveedorSuministro = new JTextField();
		tnumProveedorSuministro.setColumns(10);
		
		JLabel lblCantidads = new JLabel("Cantidad Total suministrada: ");
		
		tcantidad = new JTextField();
		tcantidad.setColumns(10);
		
		JButton btnpiezasSuministradas = new JButton("Boton Piezas Suministradas");
		btnpiezasSuministradas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Piezas piezas=new Piezas();
				String piezan=(String) cpieza.getSelectedItem();
				piezas.setCodigo(piezan);
				Gestion_Global_Listado_Piezas_Suministradas glps = null;
				try {
					glps = new Gestion_Global_Listado_Piezas_Suministradas(piezas);
				} catch (ClassNotFoundException | SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				glps.setVisible(true);
			}
		});
		
		tpcodigo = new JTextField();
		tpcodigo.setColumns(10);
		
		tpprecio = new JTextField();
		tpprecio.setColumns(10);
		
		JLabel lblGestionSuministrosPiezas = new JLabel("PIEZAS SUMINISTRADAS A PROYECTOS");
		lblGestionSuministrosPiezas.setFont(new Font("Segoe UI", Font.BOLD, 20));
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(48)
							.addComponent(lblGestionSuministrosPiezas))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_contentPane.createSequentialGroup()
									.addGap(35)
									.addComponent(lblNewLabel, GroupLayout.PREFERRED_SIZE, 42, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(cpieza, GroupLayout.PREFERRED_SIZE, 80, GroupLayout.PREFERRED_SIZE))
								.addGroup(gl_contentPane.createSequentialGroup()
									.addContainerGap()
									.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
										.addComponent(lblNProyecto)
										.addComponent(lblCantidads)
										.addComponent(lblNewLabel_1))))
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_contentPane.createSequentialGroup()
									.addGap(16)
									.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
										.addGroup(gl_contentPane.createSequentialGroup()
											.addGap(6)
											.addComponent(btnpiezasSuministradas))
										.addComponent(tnProyecto, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
										.addComponent(tpcodigo, GroupLayout.DEFAULT_SIZE, 251, Short.MAX_VALUE)
										.addComponent(tpprecio, GroupLayout.DEFAULT_SIZE, 251, Short.MAX_VALUE)))
								.addGroup(gl_contentPane.createSequentialGroup()
									.addGap(18)
									.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
										.addComponent(tcantidad, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
										.addComponent(tnumProveedorSuministro, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))))))
					.addContainerGap())
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addComponent(lblGestionSuministrosPiezas)
					.addPreferredGap(ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNewLabel)
						.addComponent(cpieza, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(tpcodigo, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(tpprecio, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNProyecto)
						.addComponent(tnProyecto, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(52)
							.addComponent(lblCantidads))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(15)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblNewLabel_1)
								.addComponent(tnumProveedorSuministro, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
							.addGap(18)
							.addComponent(tcantidad, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
					.addGap(27)
					.addComponent(btnpiezasSuministradas)
					.addGap(41))
		);
		contentPane.setLayout(gl_contentPane);
	}
}
