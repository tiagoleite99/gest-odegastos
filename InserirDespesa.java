package trabalho;

import java.awt.EventQueue;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.LocalTime;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;
import javax.swing.ImageIcon;
import java.awt.Toolkit;

public class InserirDespesa {

	private JFrame frmInserirDespesas;
	private JTextField montante;
	private static JTextField textField_1;
	private JTextField input_montante;
	private static JComboBox procurar_ComboBox2;
	private static String demoroumasfoi;



	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					InserirDespesa window = new InserirDespesa();
					window.frmInserirDespesas.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public InserirDespesa() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmInserirDespesas = new JFrame();
		frmInserirDespesas.setIconImage(Toolkit.getDefaultToolkit().getImage("C:\\Users\beatr\\OneDrive\\Ambiente de Trabalho\\585.png"));
		frmInserirDespesas.setTitle("Inserir Despesas");
		frmInserirDespesas.getContentPane().setFont(new Font("Calibri", Font.PLAIN, 13));
		frmInserirDespesas.setBounds(100, 100, 452, 410);
		frmInserirDespesas.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmInserirDespesas.getContentPane().setLayout(null);
		frmInserirDespesas.setLocationRelativeTo(null);
		JComboBox procurar_ComboBox2 = new JComboBox();
		procurar_ComboBox2.setFont(new Font("Calibri", Font.BOLD, 14));
		procurar_ComboBox2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				demoroumasfoi=String.valueOf(procurar_ComboBox2.getSelectedItem()); 



			}});
		procurar_ComboBox2.setBounds(155, 74, 142, 28);
		frmInserirDespesas.getContentPane().add(procurar_ComboBox2);

		montante = new JTextField();
		montante.setFont(new Font("Calibri", Font.BOLD, 14));
		montante.setBounds(155, 104, 142, 28);
		frmInserirDespesas.getContentPane().add(montante);
		montante.setColumns(10);

		textField_1 = new JTextField();
		textField_1.setFont(new Font("Calibri", Font.BOLD, 14));
		textField_1.setBounds(155, 149, 193, 73);
		frmInserirDespesas.getContentPane().add(textField_1);
		textField_1.setColumns(10);

		JButton btnNewButton = new JButton("Guardar");
		btnNewButton.setContentAreaFilled(false);
		btnNewButton.setBorderPainted(false);
		btnNewButton.setFont(new Font("Calibri", Font.BOLD, 14));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				InserirDespesa(Double.parseDouble(montante.getText()));

			}
		});
		btnNewButton.setBounds(301, 242, 85, 39);
		frmInserirDespesas.getContentPane().add(btnNewButton);

		JButton btnNewButton_1 = new JButton("Voltar");
		btnNewButton_1.setBorderPainted(false);
		btnNewButton_1.setContentAreaFilled(false);
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				index.main(null);
				frmInserirDespesas.dispose();
			}
		});
		btnNewButton_1.setFont(new Font("Calibri", Font.BOLD, 14));
		btnNewButton_1.setBounds(176, 242, 85, 38);
		frmInserirDespesas.getContentPane().add(btnNewButton_1);

		JLabel lblNewLabel_3 = new JLabel("");
		lblNewLabel_3.setIcon(new ImageIcon("C:\\Users\\beatr\\OneDrive\\Ambiente de Trabalho\\585.png"));
		lblNewLabel_3.setBounds(0, 0, 436, 371);
		frmInserirDespesas.getContentPane().add(lblNewLabel_3);


		Connection conn = null;
		try {

			String dbURL = "jdbc:postgresql://localhost:5432/trabalho5";
			String user = "postgres";
			String pass = "T12345678l";
			conn = DriverManager.getConnection(dbURL, user,pass);
			if (conn != null) {



				String sql="Select Nome from categorias ";

				Statement statement= conn.createStatement();
				ResultSet result=statement.executeQuery(sql);

				while(result.next()) {
					String nome = result.getString("Nome");
					procurar_ComboBox2.addItem(nome);



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

	public static void InserirDespesa(double montante) {

		Connection conn = null;
		double disponivel;

		try {
			String dbURL = "jdbc:postgresql://localhost:5432/trabalho5";
			String user = "postgres";
			String pass = "T12345678l";
			conn = DriverManager.getConnection(dbURL, user,pass);
			if (conn != null) {

				String sql="SELECT OrcamentoMensalDisponivel FROM orcamento WHERE ID_user = '"+Login.id_user+"'";
				Statement statement1= conn.createStatement();
				ResultSet result=statement1.executeQuery(sql);

				if(result.next())
				{
					double saldo=result.getDouble(1);

					disponivel= saldo - montante;
					System.out.println(disponivel);

					String sql1 = "UPDATE orcamento SET OrcamentoMensalDisponivel=? WHERE ID_user= '"+Login.id_user+"'";
					PreparedStatement statement = conn.prepareStatement(sql1);
					statement.setDouble(1, disponivel);

					int rowsUpdated = statement.executeUpdate();
					if (rowsUpdated > 0) {
						System.out.println("Despesa abatida no orçamento!");
					}

					LocalTime time = LocalTime.now();
					System.out.println(time);


					LocalDate data = LocalDate.now();
					System.out.println(data);
					String sql2= "INSERT INTO despesas VALUES('"+Login.id_user+"','"+montante+"','"+demoroumasfoi+"','"+data+"','"+time+"','"+textField_1.getText()+"')";
					PreparedStatement statement2 = conn.prepareStatement(sql2);

					int rowsInserted = statement2.executeUpdate();
					if (rowsInserted > 0) {
						System.out.println("Despesa adicionada!");
					}
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
