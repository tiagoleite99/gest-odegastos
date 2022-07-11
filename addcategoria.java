package trabalho;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.awt.event.ActionEvent;
import java.awt.Font;
import javax.swing.ImageIcon;
import java.awt.SystemColor;
import java.awt.Toolkit;

public class addcategoria {

	private JFrame frmAdicionarNovaCategoria;
	private static JTextField textField;
	private static JTextField textField_1;

	/**
	 * Launch the application.
	 * 
	 */

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					addcategoria window = new addcategoria();
					window.frmAdicionarNovaCategoria.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public addcategoria() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmAdicionarNovaCategoria = new JFrame();
		frmAdicionarNovaCategoria.setTitle("Adicionar nova categoria");
		frmAdicionarNovaCategoria.setIconImage(Toolkit.getDefaultToolkit().getImage("C:\\Users\\beatr\\OneDrive\\Ambiente de Trabalho\\585.png"));
		frmAdicionarNovaCategoria.setBounds(100, 100, 492, 264);
		frmAdicionarNovaCategoria.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmAdicionarNovaCategoria.getContentPane().setLayout(null);

		JLabel lblNewLabel = new JLabel("Orçamento Total:");
		lblNewLabel.setForeground(SystemColor.text);
		lblNewLabel.setFont(new Font("Calibri", Font.BOLD, 14));
		lblNewLabel.setBounds(93, 93, 113, 14);
		frmAdicionarNovaCategoria.getContentPane().add(lblNewLabel);
		frmAdicionarNovaCategoria.setLocationRelativeTo(null);
		JLabel lblNewLabel_1 = new JLabel("Nome:");
		lblNewLabel_1.setForeground(SystemColor.text);
		lblNewLabel_1.setFont(new Font("Calibri", Font.BOLD, 14));
		lblNewLabel_1.setBounds(93, 45, 46, 14);
		frmAdicionarNovaCategoria.getContentPane().add(lblNewLabel_1);

		textField = new JTextField();
		textField.setFont(new Font("Calibri", Font.PLAIN, 12));
		textField.setBounds(91, 59, 187, 26);
		frmAdicionarNovaCategoria.getContentPane().add(textField);
		textField.setColumns(10);

		textField_1 = new JTextField();
		textField_1.setFont(new Font("Calibri", Font.PLAIN, 12));
		textField_1.setBounds(93, 109, 177, 29);
		frmAdicionarNovaCategoria.getContentPane().add(textField_1);
		textField_1.setColumns(10);

		JButton btnNewButton = new JButton("Criar");
		btnNewButton.setContentAreaFilled(false);
		btnNewButton.setBorderPainted(false);
		btnNewButton.setFont(new Font("Calibri", Font.BOLD, 15));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				confirmar();
				frmAdicionarNovaCategoria.dispose();



			}
		});
		btnNewButton.setBounds(270, 162, 86, 26);
		frmAdicionarNovaCategoria.getContentPane().add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("");
		btnNewButton_1.setBorderPainted(false);
		btnNewButton_1.setContentAreaFilled(false);
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				frmAdicionarNovaCategoria.dispose();
			}
		});
		btnNewButton_1.setIcon(new ImageIcon("C:\\Users\\beatr\\OneDrive\\Ambiente de Trabalho\\585.png"));
		btnNewButton_1.setBounds(171, 162, 54, 26);
		frmAdicionarNovaCategoria.getContentPane().add(btnNewButton_1);
		
				
				
				JLabel lblNewLabel_2 = new JLabel("");
				lblNewLabel_2.setIcon(new ImageIcon("C:\\Users\\beatr\\OneDrive\\Ambiente de Trabalho\\585.png"));
				lblNewLabel_2.setBounds(0, 0, 476, 225);
				frmAdicionarNovaCategoria.getContentPane().add(lblNewLabel_2);
	}


	public static void confirmar()
	{


		String nome =textField.getText();
		String valor = textField_1.getText();
		Connection conn = null;

		try {

			String dbURL = "jdbc:postgresql://localhost:5432/trabalho5";
			String user = "postgres";
			String pass = "T12345678l";
			conn = DriverManager.getConnection(dbURL, user,pass);
			if (conn != null) {

				String sql1= "INSERT INTO categorias  VALUES ('"+nome+"','"+valor+"','"+valor+"','"+Login.id_user+"')";
				PreparedStatement statement = conn.prepareStatement(sql1);
				int rowsUpdated = statement.executeUpdate();
				if (rowsUpdated > 0) {
					System.out.print("Categoria Inserida!");
					index.main(null);
				}
			}	 
		} catch (SQLException ex) {
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
