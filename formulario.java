package trabalho;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.BorderLayout;
import javax.swing.JButton;
import javax.swing.JTextField;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.io.*;
import java.io.FileInputStream;
import java.io.IOException;
import java.security.MessageDigest;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Scanner;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.*;
import java.awt.Font;
import javax.swing.ImageIcon;
import java.awt.SystemColor;
import java.awt.Toolkit;

public class formulario {

	private JFrame frmDados;
	private static JTextField textField;
	private static JTextField textField_1;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					formulario window = new formulario();
					window.frmDados.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}

		});
	}

	/**
	 * Create the application.
	 */
	public formulario() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmDados = new JFrame();
		frmDados.setTitle("Dados");
		frmDados.setIconImage(Toolkit.getDefaultToolkit().getImage("C:\\Users\\beatr\\OneDrive\\Ambiente de Trabalho\\585.png"));
		frmDados.setBounds(100, 100, 254, 286);
		frmDados.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmDados.getContentPane().setLayout(null);
		frmDados.setLocationRelativeTo(null);
		textField = new JTextField();
		textField.setFont(new Font("Calibri", Font.BOLD, 14));
		textField.setBounds(41, 66, 133, 22);
		frmDados.getContentPane().add(textField);
		textField.setColumns(10);

		JLabel lblNewLabel = new JLabel("Insira o Seu Orçamento:");
		lblNewLabel.setForeground(SystemColor.text);
		lblNewLabel.setFont(new Font("Calibri", Font.BOLD, 13));
		lblNewLabel.setBounds(41, 51, 173, 22);
		frmDados.getContentPane().add(lblNewLabel);

		JButton btnNewButton = new JButton("Guardar");
		btnNewButton.setContentAreaFilled(false);
		btnNewButton.setBorderPainted(false);
		btnNewButton.setFont(new Font("Calibri", Font.BOLD, 13));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				confirmar();
				frmDados.setVisible(false);
			}
		});
		btnNewButton.setBounds(78, 190, 79, 22);
		frmDados.getContentPane().add(btnNewButton);

		JLabel lblMs = new JLabel("Mes:");
		lblMs.setForeground(SystemColor.text);
		lblMs.setFont(new Font("Calibri", Font.BOLD, 13));
		lblMs.setBounds(41, 99, 173, 22);
		frmDados.getContentPane().add(lblMs);

		textField_1 = new JTextField();
		textField_1.setFont(new Font("Calibri", Font.BOLD, 14));
		textField_1.setColumns(10);
		textField_1.setBounds(41, 115, 133, 22);
		frmDados.getContentPane().add(textField_1);

		JLabel lblNewLabel_1 = new JLabel("");
		lblNewLabel_1.setIcon(new ImageIcon("C:\\Users\\beatr\\OneDrive\\Ambiente de Trabalho\\585.png"));
		lblNewLabel_1.setBounds(0, 0, 238, 247);
		frmDados.getContentPane().add(lblNewLabel_1);

	}


	public static void confirmar()
	{


		String mes =textField_1.getText();
		String orcamento = textField.getText();
		Connection conn = null;

		try {

			String dbURL = "jdbc:postgresql://localhost:5432/trabalho5";
			String user = "postgres";
			String pass = "T12345678l";
			conn = DriverManager.getConnection(dbURL, user,pass);
			if (conn != null) {

				String sql1= "INSERT INTO orcamento  VALUES ('"+Login.id_user+"','"+Login.id_user+"','"+mes+"','"+orcamento+"','"+orcamento+"')";
				PreparedStatement statement = conn.prepareStatement(sql1);
				int rowsUpdated = statement.executeUpdate();
				if (rowsUpdated > 0) {
					System.out.print("Orçamento Inserido!");
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

