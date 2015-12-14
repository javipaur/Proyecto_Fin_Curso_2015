package Vista;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.sql.SQLException;

import javax.swing.DefaultComboBoxModel;
import javax.swing.GroupLayout;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import BD.Bd;

public class Proyectos_Consulta extends JFrame {
	private JTextField tBuscar;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Piezas_Consulta frame = new Piezas_Consulta();
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
	public Proyectos_Consulta() throws ClassNotFoundException, SQLException {
		setTitle("Lista Proyectos");
		setBounds(100, 100, 450, 418);
		JPanel contentPane = new JPanel();
		contentPane.setBorder(null);
		setContentPane(contentPane);
		
		JScrollPane scrollPane = new JScrollPane();
		
		JLabel lblNewLabel = new JLabel("CONSULTA DE PROYECTOS");
		lblNewLabel.setFont(new Font("Segoe UI", Font.BOLD, 20));
		
		JLabel lblTextoABuscar = new JLabel("Texto a buscar: ");
		
		tBuscar = new JTextField();
		tBuscar.setColumns(10);
		
		JComboBox cTipo = new JComboBox();
		cTipo.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				
				if(cTipo.getSelectedItem()!="Todos" & tBuscar.getText().length()==0){
					JOptionPane.showMessageDialog(null, "¡Tienes que introducir el texto a buscar!");	
				}
				GroupLayout gl_contentPane = new GroupLayout(contentPane);
				DefaultTableModel modelo=new DefaultTableModel();
				JTable table=new JTable(modelo);
				
				//System.out.println("Seleccion desplegable "+cTipo.getSelectedItem());
				//System.out.println(tBuscar.getText());
				try {
					Bd.llenarBuscaPiezas(tBuscar,cTipo,table, modelo);					
				} catch (ClassNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				scrollPane.setViewportView(table);
				contentPane.setLayout(gl_contentPane);	
			}
		});
		cTipo.setModel(new DefaultComboBoxModel(new String[] {"Todos", "xCodigo", "xNombre", "xCiudad"}));
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(74)
							.addComponent(lblNewLabel))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addContainerGap()
							.addComponent(lblTextoABuscar)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(tBuscar, GroupLayout.PREFERRED_SIZE, 195, GroupLayout.PREFERRED_SIZE)
							.addGap(18)
							.addComponent(cTipo, GroupLayout.PREFERRED_SIZE, 89, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addContainerGap()
							.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 414, Short.MAX_VALUE)))
					.addContainerGap())
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblNewLabel)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblTextoABuscar)
						.addComponent(tBuscar, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(cTipo, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 271, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
		);
		
		DefaultTableModel modelo=new DefaultTableModel();
		JTable table=new JTable(modelo);
		table.setBorder(null);
		table.setRowSelectionAllowed(false);
		Bd.llenarBuscarProyectos(tBuscar,cTipo,table, modelo);		
	
		scrollPane.setViewportView(table);
		contentPane.setLayout(gl_contentPane);		
	}
}