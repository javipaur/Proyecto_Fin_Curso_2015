package Vista;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import BD.Bd;

import javax.swing.JLabel;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import java.awt.Font;
import javax.swing.JButton;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.awt.event.ActionEvent;

public class Gestion_Global_Estadisticas extends JFrame {

	private JPanel contentPane;
	private JTextField tCodPiezaMax;
	private JTextField tCodPiezaMaxProy;
	private JTextField tNumPiezaMax;
	private JTextField tNumPiezaMaxProy;
	private JTextField tproveedorPiezasNombre;
	private JTextField tproveedorPiezasNumero;
	private JTextField tproyectoNombre;
	private JTextField tproyectoNumero;
	private JTextField tcodigoProveedor;
	private JTextField tnombrepieza;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Gestion_Global_Estadisticas frame = new Gestion_Global_Estadisticas();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 * @throws ClassNotFoundException 
	 */
	public Gestion_Global_Estadisticas() throws ClassNotFoundException {
		setTitle("Gestion estadisticas");
		setBounds(100, 100, 698, 342);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		JLabel lblNewLabel = new JLabel("RESUMENES ESTADISTICOS - PIEZAS PROYECTOS Y PROVEEDORES");
		lblNewLabel.setFont(new Font("Segoe UI", Font.BOLD, 20));
		
		JButton btnPcpsp1 = new JButton("N\u00BA DE PIEZAS Y CANTIDAD DE PIEZAS SUMINISTRADAS EN PROYECTOS");
		btnPcpsp1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					Gestion_Global_Estadisticas_Proyectos ggep=new Gestion_Global_Estadisticas_Proyectos();
					ggep.setVisible(true);
				} catch (ClassNotFoundException | SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
			}
		});
		
		JButton btnPcpsp2 = new JButton("N\u00BA PIEZAS Y CANTIDAD DE PIEZAS SUMINISTRADAS POR PROVEEDOR");
		btnPcpsp2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					Gestion_Global_Estadisticas_Proveedor ggepo=new Gestion_Global_Estadisticas_Proveedor();
					ggepo.setVisible(true);
				} catch (ClassNotFoundException | SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
			}
		});
		
		JLabel lblNewLabel_1 = new JLabel("Pieza de la que se ha suministrado mas cantidad");
		
		tCodPiezaMax = new JTextField();
		tCodPiezaMax.setColumns(10);
		
		JLabel lblNewLabel_2 = new JLabel("Pieza que se ha suministrado a mas proyectos:");
		
		tCodPiezaMaxProy = new JTextField();
		tCodPiezaMaxProy.setColumns(10);
		
		tNumPiezaMax = new JTextField();
		tNumPiezaMax.setColumns(10);
		
		tNumPiezaMaxProy = new JTextField();
		tNumPiezaMaxProy.setColumns(10);
		
		JLabel lblP = new JLabel("Proveedor que ha suministrado mas cantidad de piezas");
		
		tproveedorPiezasNombre = new JTextField();
		tproveedorPiezasNombre.setColumns(10);
		
		tproveedorPiezasNumero = new JTextField();
		tproveedorPiezasNumero.setColumns(10);
		
		JLabel lblNewLabel_3 = new JLabel("Proveedor que ha suministrado a mas proyectos/n poryectos");
		
		tproyectoNombre = new JTextField();
		tproyectoNombre.setColumns(10);
		
		tproyectoNumero = new JTextField();
		tproyectoNumero.setColumns(10);
		
		JLabel lblNewLabel_4 = new JLabel("Proveedor que ha suministrado mas piezas / n\u00BA piezas");
		
		tcodigoProveedor = new JTextField();
		tcodigoProveedor.setColumns(10);
		
		tnombrepieza = new JTextField();
		tnombrepieza.setColumns(10);
		
		Bd.estadistica_max_piezas(tCodPiezaMax,tNumPiezaMax);
		Bd.estadistica_max_pieza_proyectos(tCodPiezaMaxProy,tNumPiezaMaxProy);
		Bd.estadistica_num_pieza_proveedor(tproveedorPiezasNombre,tproveedorPiezasNumero);
		Bd.estadistica_num_pieza_proyectos(tproyectoNombre,tproyectoNumero);
		Bd.estadistica_num_provveedor_suministro_piezas(tcodigoProveedor,tnombrepieza);
		
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(85)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addComponent(btnPcpsp1)
						.addComponent(btnPcpsp2)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(lblNewLabel_1)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(tCodPiezaMax, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(tNumPiezaMax, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(lblNewLabel_2)
							.addGap(18)
							.addComponent(tCodPiezaMaxProy, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(tNumPiezaMaxProy, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING, false)
								.addGroup(gl_contentPane.createSequentialGroup()
									.addComponent(lblNewLabel_4)
									.addPreferredGap(ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
									.addComponent(tcodigoProveedor, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
								.addGroup(gl_contentPane.createSequentialGroup()
									.addComponent(lblP)
									.addPreferredGap(ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
									.addComponent(tproveedorPiezasNombre, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
								.addGroup(Alignment.LEADING, gl_contentPane.createSequentialGroup()
									.addComponent(lblNewLabel_3)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(tproyectoNombre, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
									.addGroup(gl_contentPane.createSequentialGroup()
										.addPreferredGap(ComponentPlacement.RELATED)
										.addComponent(tproyectoNumero, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
									.addGroup(Alignment.TRAILING, gl_contentPane.createSequentialGroup()
										.addPreferredGap(ComponentPlacement.RELATED)
										.addComponent(tproveedorPiezasNumero, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
								.addGroup(Alignment.TRAILING, gl_contentPane.createSequentialGroup()
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(tnombrepieza, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))))
					.addContainerGap())
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblNewLabel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
					.addGap(55))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblNewLabel)
					.addGap(31)
					.addComponent(btnPcpsp1)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNewLabel_1)
						.addComponent(tCodPiezaMax, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(tNumPiezaMax, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNewLabel_2)
						.addComponent(tNumPiezaMaxProy, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(tCodPiezaMaxProy, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(13)
					.addComponent(btnPcpsp2)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblP)
						.addComponent(tproveedorPiezasNumero, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(tproveedorPiezasNombre, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNewLabel_3)
						.addComponent(tproyectoNombre, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(tproyectoNumero, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addComponent(lblNewLabel_4)
						.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
							.addComponent(tnombrepieza, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addComponent(tcodigoProveedor, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
					.addContainerGap(26, Short.MAX_VALUE))
		);
		contentPane.setLayout(gl_contentPane);
	}
}
