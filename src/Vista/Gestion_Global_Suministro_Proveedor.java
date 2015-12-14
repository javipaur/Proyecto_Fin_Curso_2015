package Vista;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import BD.Bd;
import Modelo.Gestion;
import Modelo.Proveedor;

import javax.swing.JLabel;
import javax.swing.DefaultListModel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JComboBox;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.awt.event.ActionEvent;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;
import javax.swing.JButton;
import java.awt.Font;

public class Gestion_Global_Suministro_Proveedor extends JFrame {

	private JPanel contentPane;
	private JTextField tproveedor_direccion;
	private JTextField tproveedor_nombre;
	private JTextField tpiezas_suministradas;
	private JTextField tpiezas_suministradas_1;
	private JTextField tproyecto;
	private JTextField tproyecto_1;
	private JLabel lProyecto;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Gestion_Global_Suministro_Proveedor frame = new Gestion_Global_Suministro_Proveedor();
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
	public Gestion_Global_Suministro_Proveedor() throws ClassNotFoundException, SQLException {
		setTitle("Suministros por Proveedores");
		setBounds(100, 100, 520, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		JLabel lblNewLabel = new JLabel("Proveedor:");
		tpiezas_suministradas = new JTextField();
		tpiezas_suministradas.setColumns(10);
		
		JLabel lblProyecto = new JLabel("Proyecto");
		
		tproyecto = new JTextField();
		tproyecto.setColumns(10);
		JComboBox cproveedor = new JComboBox();
		Proveedor proveedor=new Proveedor();
		cproveedor.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent arg0) {
				
				proveedor.setCodigo(String.valueOf(cproveedor.getSelectedItem()));
				try {
					
					DefaultListModel modelo = new DefaultListModel();
					if(cproveedor.getSelectedIndex()!=0){
						Bd.buscarProveedor(proveedor);
						tproveedor_nombre.setText(proveedor.getNombre()+" "+proveedor.getApellido());
						tproveedor_direccion.setText(proveedor.getDireccion());
						Gestion g=new Gestion();
						Bd.piezaSuministradas(proveedor,g, cproveedor, tpiezas_suministradas_1,tproyecto_1);
					}
					
				} catch (ClassNotFoundException | SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		Proveedor p=new Proveedor();
		Bd.DesplegableProveedores(p,cproveedor);
		
		tproveedor_direccion = new JTextField();
		tproveedor_direccion.setColumns(10);
		
		tproveedor_nombre = new JTextField();
		tproveedor_nombre.setColumns(10);
		
		JLabel lblPiezasSuministradoas = new JLabel("Piezas Suministradas:");
		
		tpiezas_suministradas_1 = new JTextField();
		tpiezas_suministradas_1.setColumns(10);
		
		//JLabel lblProyecto;
		JLabel lProyecto = new JLabel("Proyecto:");
		
		tproyecto_1 = new JTextField();
		tproyecto_1.setColumns(10);
		
		JButton tpsuministradas = new JButton("Ver Piezas suministradas");
		tpsuministradas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Gestion_Listado_Por_Proveedor glpp = null;
				try {
					glpp = new Gestion_Listado_Por_Proveedor(proveedor);
				} catch (ClassNotFoundException | SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				glpp.setVisible(true);
			}
		});
		
		JLabel lblPiezasSuministradasPor = new JLabel("PIEZAS SUMINISTRADAS POR UN PROVEEDOR ");
		lblPiezasSuministradasPor.setFont(new Font("Segoe UI", Font.BOLD, 20));
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addContainerGap()
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_contentPane.createSequentialGroup()
									.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
										.addComponent(lblPiezasSuministradoas)
										.addComponent(lProyecto))
									.addPreferredGap(ComponentPlacement.RELATED)
									.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
										.addGroup(gl_contentPane.createSequentialGroup()
											.addComponent(tproyecto_1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
											.addPreferredGap(ComponentPlacement.UNRELATED)
											.addComponent(tpsuministradas, GroupLayout.DEFAULT_SIZE, 229, Short.MAX_VALUE))
										.addComponent(tpiezas_suministradas_1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
								.addGroup(gl_contentPane.createSequentialGroup()
									.addComponent(lblNewLabel, GroupLayout.PREFERRED_SIZE, 87, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(ComponentPlacement.UNRELATED)
									.addComponent(cproveedor, GroupLayout.PREFERRED_SIZE, 82, GroupLayout.PREFERRED_SIZE)
									.addGap(18)
									.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
										.addComponent(tproveedor_direccion, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 235, Short.MAX_VALUE)
										.addComponent(tproveedor_nombre, GroupLayout.DEFAULT_SIZE, 235, Short.MAX_VALUE)))))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(29)
							.addComponent(lblPiezasSuministradasPor)))
					.addContainerGap())
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(Alignment.TRAILING, gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblPiezasSuministradasPor)
					.addGap(22)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(cproveedor, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(tproveedor_nombre, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblNewLabel))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(tproveedor_direccion, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED, 69, Short.MAX_VALUE)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblPiezasSuministradoas)
						.addComponent(tpiezas_suministradas_1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addComponent(lProyecto)
						.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
							.addComponent(tproyecto_1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addComponent(tpsuministradas)))
					.addGap(31))
		);
		contentPane.setLayout(gl_contentPane);
	}
}
