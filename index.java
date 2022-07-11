package trabalho;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JButton;
import java.awt.BorderLayout;
import javax.swing.JLabel;

import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.awt.event.ActionEvent;
import java.awt.Font;
import javax.swing.ImageIcon;
import java.awt.SystemColor;
import java.awt.Toolkit;
import java.awt.event.HierarchyBoundsAdapter;
import java.awt.event.HierarchyEvent;
import java.awt.Color;
import javax.swing.event.AncestorListener;



import javax.swing.event.AncestorEvent;

public class index extends javax.swing.JFrame {

	private JFrame frmMenu;
	static String nome;
	static Double orcamento_mensal1;
	static Double orcamento_mensal_disponivel1;
	static String nome2;

	/**
	 * Launch the application.
	 */

	public static void main(String[] args) {

		int id = Login.id_user;

		Connection conn = null;

		try {

			String dbURL = "jdbc:postgresql://localhost:5432/trabalho5";
			String user = "postgres";
			String pass = "T12345678l";
			conn = DriverManager.getConnection(dbURL, user,pass);
			if (conn != null) {


				String sql="SELECT Nome FROM login WHERE id = '"+Login.id_user+"'";
				Statement statement= conn.createStatement();
				ResultSet result=statement.executeQuery(sql);

				while (result.next()){

					nome2=result.getString(1);



				}

				String sql2="SELECT OrcamentoMensal FROM orcamento WHERE ID_user = '"+Login.id_user+"'";
				Statement statement2= conn.createStatement();
				ResultSet result2=statement2.executeQuery(sql2);

				while (result2.next()){

					orcamento_mensal1=result2.getDouble(1);



				}

				String sql3="SELECT OrcamentoMensalDisponivel FROM orcamento WHERE ID_user = '"+Login.id_user+"'";
				Statement statement3= conn.createStatement();
				ResultSet result3=statement3.executeQuery(sql3);

				while (result3.next()){

					orcamento_mensal_disponivel1=result3.getDouble(1);



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










		EventQueue.invokeLater(new Runnable() {





			public void run() {

				try {
					index window = new index();
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
	public index() {
		initialize();


	}
	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmMenu = new JFrame();
		frmMenu.setTitle("Menu");
		frmMenu.setIconImage(Toolkit.getDefaultToolkit().getImage("C:\\Users\\beatr\\OneDrive\\Ambiente de Trabalho\\585.png"));
		frmMenu.setBounds(100, 100, 504, 508);
		frmMenu.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmMenu.getContentPane().setLayout(null);
		frmMenu.setLocationRelativeTo(null);
		JButton btnNewButton = new JButton("Nova Despesa");
		btnNewButton.addAncestorListener(new AncestorListener() {
			public void ancestorAdded(AncestorEvent arg0) {
			}
			public void ancestorMoved(AncestorEvent arg0) {
			}
			public void ancestorRemoved(AncestorEvent arg0) {
			}
		});
		btnNewButton.setBorderPainted(false);
		btnNewButton.setContentAreaFilled(false);
		btnNewButton.setFont(new Font("Calibri", Font.BOLD, 14));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frmMenu.setVisible(false);
				InserirDespesa.main(null);
			}
		});
		btnNewButton.setBounds(169, 137, 157, 38);
		frmMenu.getContentPane().add(btnNewButton);
		btnNewButton.setBorderPainted(false);
		btnNewButton.setContentAreaFilled(false);
		btnNewButton.setFont(new Font("Calibri", Font.BOLD, 14));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frmMenu.setVisible(false);

			}
		});

		JButton btnNewButton_1_1 = new JButton("Categorias");
		btnNewButton_1_1.setContentAreaFilled(false);
		btnNewButton_1_1.setBorderPainted(false);
		btnNewButton_1_1.setFont(new Font("Calibri", Font.BOLD, 15));
		btnNewButton_1_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frmMenu.setVisible(false);
				Categorias.main(null);
			}
		});
		btnNewButton_1_1.setBounds(171, 275, 144, 45);
		frmMenu.getContentPane().add(btnNewButton_1_1);

		JButton btnNewButton_1_1_1 = new JButton("Sair");
		btnNewButton_1_1_1.setContentAreaFilled(false);
		btnNewButton_1_1_1.setBorderPainted(false);
		btnNewButton_1_1_1.setFont(new Font("Calibri", Font.BOLD, 14));
		btnNewButton_1_1_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frmMenu.dispose();
			}
		});
		btnNewButton_1_1_1.setBounds(196, 404, 99, 23);
		frmMenu.getContentPane().add(btnNewButton_1_1_1);

		JLabel lblNewLabel = new JLabel("Bem Vindo:");
		lblNewLabel.setForeground(SystemColor.text);
		lblNewLabel.setFont(new Font("Calibri", Font.BOLD, 15));
		lblNewLabel.setBounds(60, 51, 89, 14);
		frmMenu.getContentPane().add(lblNewLabel);

		JLabel nomelabel = new JLabel("");
		nomelabel.setForeground(SystemColor.text);
		nomelabel.setFont(new Font("Calibri", Font.PLAIN, 15));
		nomelabel.setBounds(134, 51, 121, 14);
		frmMenu.getContentPane().add(nomelabel);
		nomelabel.setText(String.valueOf(String.valueOf(nome2)));

		JLabel lblNewLabel_2 = new JLabel("Saldo Disponivel:");
		lblNewLabel_2.setForeground(SystemColor.text);
		lblNewLabel_2.setFont(new Font("Calibri", Font.BOLD, 15));
		lblNewLabel_2.setBounds(60, 79, 115, 14);
		frmMenu.getContentPane().add(lblNewLabel_2);

		JLabel saldod = new JLabel("");
		saldod.setForeground(SystemColor.text);
		saldod.setFont(new Font("Calibri", Font.PLAIN, 15));
		saldod.setBounds(169, 79, 191, 14);
		frmMenu.getContentPane().add(saldod);
		saldod.setText(String.valueOf(String.valueOf(orcamento_mensal_disponivel1))+" €");

		JLabel lblNewLabel_4 = new JLabel("Saldo Mensal:");
		lblNewLabel_4.setForeground(SystemColor.text);
		lblNewLabel_4.setFont(new Font("Calibri", Font.BOLD, 15));
		lblNewLabel_4.setBounds(280, 51, 99, 14);
		frmMenu.getContentPane().add(lblNewLabel_4);

		JLabel saldoM = new JLabel("");
		saldoM.setForeground(SystemColor.text);
		saldoM.setFont(new Font("Calibri", Font.PLAIN, 15));
		saldoM.setBounds(374, 51, 114, 14);
		frmMenu.getContentPane().add(saldoM);
		saldoM.setText(String.valueOf(String.valueOf(orcamento_mensal1))+" €");

		JButton btnNewButton_1 = new JButton("Consultar Despesas");
		btnNewButton_1.addActionListener(new ActionListener() {


			public void actionPerformed(ActionEvent e) {

				try {
					consultar.main(null);
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});





		btnNewButton_1.setContentAreaFilled(false);
		btnNewButton_1.setBorderPainted(false);
		btnNewButton_1.setFont(new Font("Calibri", Font.BOLD, 14));
		btnNewButton_1.setForeground(Color.BLACK);
		btnNewButton_1.setBackground(Color.WHITE);
		btnNewButton_1.setBounds(169, 207, 157, 31);
		frmMenu.getContentPane().add(btnNewButton_1);

		JLabel lblNewLabel_1 = new JLabel("");
		lblNewLabel_1.setBackground(Color.BLACK);
		lblNewLabel_1.setForeground(Color.BLACK);
		lblNewLabel_1.setIcon(new ImageIcon("C:\\Users\\beatr\\OneDrive\\Ambiente de Trabalho\\585.png"));
		lblNewLabel_1.setBounds(0, 0, 488, 469);
		frmMenu.getContentPane().add(lblNewLabel_1);
	}
}
