package trabalho;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JToggleButton;
import javax.swing.table.DefaultTableModel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JPopupMenu;
import java.awt.Component;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JTable;
import java.awt.Color;
import javax.swing.JScrollPane;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.JComboBox;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;
import java.awt.Font;
import java.awt.SystemColor;
import javax.swing.ImageIcon;
import java.awt.Toolkit;


public class Categorias {

	private JFrame frmMenu;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JComboBox procurar_ComboBox;



	/**
	 * 
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {

				try {
					Categorias window = new Categorias();
					window.frmMenu.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Categorias() {

		initialize();


	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {




		frmMenu = new JFrame();
		frmMenu.setTitle("Categoria");
		frmMenu.setIconImage(Toolkit.getDefaultToolkit().getImage("C:\\Users\\beatr\\OneDrive\\Ambiente de Trabalho\\585.png"));
		frmMenu.addWindowListener(new WindowAdapter() {

		});
		frmMenu.setBounds(100, 100, 538, 383);
		frmMenu.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmMenu.getContentPane().setLayout(null);
		frmMenu.setLocationRelativeTo(null);
		JButton btnNewButton = new JButton("Adicionar Categoria");
		btnNewButton.setContentAreaFilled(false);
		btnNewButton.setBorderPainted(false);
		btnNewButton.setAutoscrolls(true);
		btnNewButton.setBackground(SystemColor.text);
		btnNewButton.setFont(new Font("Calibri", Font.BOLD, 14));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				addcategoria.main(null);
				frmMenu.dispose();
			}
		});
		btnNewButton.setBounds(310, 76, 149, 33);
		frmMenu.getContentPane().add(btnNewButton);

		JButton btnNewButton_1 = new JButton("Editar Categoria");
		btnNewButton_1.setBorderPainted(false);
		btnNewButton_1.setContentAreaFilled(false);
		btnNewButton_1.setFont(new Font("Calibri", Font.BOLD, 14));
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				String nome =textField.getText();
				double orcamentototal = Double.parseDouble(textField_1.getText());

				Connection conn = null;
				try {

					String dbURL = "jdbc:postgresql://localhost:5432/trabalho5";
					String user = "postgres";
					String pass = "T12345678l";
					conn = DriverManager.getConnection(dbURL, user,pass);
					if (conn != null) {

						String fetch_row = "update categorias SET Nome='"+nome+"',orcamentototal='"+orcamentototal+"' WHERE Nome= ?";
						PreparedStatement statement = conn.prepareStatement(fetch_row);
						statement.setString(1, (String)procurar_ComboBox.getSelectedItem()); 
						statement.executeQuery();

						Statement stmt = conn.createStatement();
						int rowsUpdated = stmt.executeUpdate(fetch_row);
						if (rowsUpdated > 0) {



							frmMenu.dispose();
						}

					}}catch (SQLException ex) {
						ex.printStackTrace();
					} finally {
						try {
							if (conn != null && !conn.isClosed()) {
								conn.close();
							}
						} catch (SQLException ex) {
							ex.printStackTrace();
						}
					}


			}
		});
		btnNewButton_1.setBounds(310, 132, 149, 40);
		frmMenu.getContentPane().add(btnNewButton_1);

		JButton btnNewButton_1_1 = new JButton("Apagar");
		btnNewButton_1_1.setContentAreaFilled(false);
		btnNewButton_1_1.setBorderPainted(false);
		btnNewButton_1_1.setFont(new Font("Calibri", Font.BOLD, 15));
		btnNewButton_1_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {


				String nome =textField.getText();
				double orcamentototal = Double.parseDouble(textField_1.getText());

				Connection conn = null;
				try {

					String dbURL = "jdbc:postgresql://localhost:5432/trabalho5";
					String user = "postgres";
					String pass = "T12345678l";
					conn = DriverManager.getConnection(dbURL, user,pass);
					if (conn != null) {

						String fetch_row = "delete from categorias WHERE Nome= ?";
						PreparedStatement statement = conn.prepareStatement(fetch_row);
						statement.setString(1, (String)procurar_ComboBox.getSelectedItem()); 
						statement.executeQuery();

						Statement stmt = conn.createStatement();
						int rowsUpdated = stmt.executeUpdate(fetch_row);
						if (rowsUpdated > 0) {
							System.out.println("Apagou com sucesso!");



						}

					}}catch (SQLException ex) {
						ex.printStackTrace();
					} finally {
						try {
							if (conn != null && !conn.isClosed()) {
								conn.close();
							}
						} catch (SQLException ex) {
							ex.printStackTrace();
						}
					}
			}
		});
		btnNewButton_1_1.setBounds(310, 192, 139, 40);
		frmMenu.getContentPane().add(btnNewButton_1_1);

		JButton btnNewButton_1_1_1_1 = new JButton("Voltar");
		btnNewButton_1_1_1_1.setContentAreaFilled(false);
		btnNewButton_1_1_1_1.setBorderPainted(false);
		btnNewButton_1_1_1_1.setFont(new Font("Calibri", Font.BOLD, 15));
		btnNewButton_1_1_1_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frmMenu.setVisible(false);
				index.main(null);
			}
		});
		btnNewButton_1_1_1_1.setBounds(310, 253, 147, 36);
		frmMenu.getContentPane().add(btnNewButton_1_1_1_1);

		JLabel lblNewLabel = new JLabel("Nome:");
		lblNewLabel.setForeground(SystemColor.text);
		lblNewLabel.setFont(new Font("Calibri", Font.BOLD, 14));
		lblNewLabel.setBounds(39, 120, 66, 14);
		frmMenu.getContentPane().add(lblNewLabel);

		JLabel lblOramentoTotal = new JLabel("Orçamento Total:");
		lblOramentoTotal.setForeground(SystemColor.text);
		lblOramentoTotal.setFont(new Font("Calibri", Font.BOLD, 14));
		lblOramentoTotal.setBounds(39, 183, 129, 14);
		frmMenu.getContentPane().add(lblOramentoTotal);

		JLabel lblNewLabel_1_1 = new JLabel("Orçamento Disponivel:");
		lblNewLabel_1_1.setForeground(SystemColor.text);
		lblNewLabel_1_1.setFont(new Font("Calibri", Font.BOLD, 14));
		lblNewLabel_1_1.setBounds(39, 239, 156, 14);
		frmMenu.getContentPane().add(lblNewLabel_1_1);

		textField = new JTextField();
		textField.setFont(new Font("Calibri", Font.BOLD, 13));
		textField.setBounds(39, 132, 168, 26);
		frmMenu.getContentPane().add(textField);
		textField.setColumns(10);

		textField_1 = new JTextField();
		textField_1.setFont(new Font("Calibri", Font.BOLD, 13));
		textField_1.setColumns(10);
		textField_1.setBounds(39, 196, 168, 26);
		frmMenu.getContentPane().add(textField_1);

		textField_2 = new JTextField();
		textField_2.setFont(new Font("Calibri", Font.BOLD, 13));
		textField_2.setEnabled(false);
		textField_2.setColumns(10);
		textField_2.setBounds(39, 253, 168, 26);
		frmMenu.getContentPane().add(textField_2);

		procurar_ComboBox = new JComboBox();
		procurar_ComboBox.setFont(new Font("Calibri", Font.BOLD, 14));



		procurar_ComboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				Connection conn = null;
				try {

					String dbURL = "jdbc:postgresql://localhost:5432/trabalho5";
					String user = "postgres";
					String pass = "T12345678l";
					conn = DriverManager.getConnection(dbURL, user,pass);
					if (conn != null) {



						String fetch_row="Select Nome,orcamentototal,orcamentodisponivel from categorias where Nome=?";

						PreparedStatement statement = conn.prepareStatement(fetch_row);
						statement.setString(1, (String)procurar_ComboBox.getSelectedItem()); 
						ResultSet set=statement.executeQuery();


						while(set.next()) {
							String nome2 = set.getString("Nome");
							textField.setText(String.valueOf(String.valueOf(nome2)));
							String nome3 = set.getString("orcamentototal");
							textField_1.setText(String.valueOf(String.valueOf(nome3)));
							String nome4 = set.getString("orcamentodisponivel");
							textField_2.setText(String.valueOf(String.valueOf(nome4)));

						}

					}}catch (SQLException ex) {
						ex.printStackTrace();
					} finally {
						try {
							if (conn != null && !conn.isClosed()) {
								conn.close();
							}
						} catch (SQLException ex) {
							ex.printStackTrace();
						}
					}




			}
		});
		procurar_ComboBox.setBounds(39, 78, 168, 26);
		frmMenu.getContentPane().add(procurar_ComboBox);

		JLabel lblNewLabel_2 = new JLabel("Procurar:");
		lblNewLabel_2.setForeground(SystemColor.text);
		lblNewLabel_2.setFont(new Font("Calibri", Font.BOLD, 14));
		lblNewLabel_2.setBounds(39, 58, 66, 14);
		frmMenu.getContentPane().add(lblNewLabel_2);

		JLabel lblNewLabel_3 = new JLabel("");
		lblNewLabel_3.setIcon(new ImageIcon("C:\\Users\\beatr\\OneDrive\\Ambiente de Trabalho\\585.png"));
		lblNewLabel_3.setBounds(0, 0, 522, 344);
		frmMenu.getContentPane().add(lblNewLabel_3);


		JLabel lblNewLabel_1 = new JLabel("Procurar:");
		lblNewLabel_1.setBounds(73, 41, 46, 14);


		Connection conn = null;
		try {

			String dbURL = "jdbc:postgresql://localhost:5432/trabalho5";
			String user = "postgres";
			String pass = "T12345678l";
			conn = DriverManager.getConnection(dbURL, user,pass);
			if (conn != null) {



				String sql="Select Nome from categorias where ID_User='"+Login.id_user+"'";

				Statement statement= conn.createStatement();
				ResultSet result=statement.executeQuery(sql);

				while(result.next()) {
					String nome = result.getString("Nome");
					procurar_ComboBox.addItem(nome);


				} 

			}}catch (SQLException ex) {
				ex.printStackTrace();
			} finally {
				try {
					if (conn != null && !conn.isClosed()) {
						conn.close();
					}
				} catch (SQLException ex) {
					ex.printStackTrace();
				}
			}


	}



}
