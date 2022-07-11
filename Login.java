package trabalho;

import java.awt.EventQueue;
import java.awt.Window;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

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
import java.awt.Color;
import javax.swing.ImageIcon;
import javax.swing.SwingConstants;
import java.awt.SystemColor;
import java.awt.Toolkit;
import java.awt.Window.Type;
import java.awt.Dimension;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.WindowFocusListener;
import java.awt.event.WindowEvent;
import javax.swing.JPasswordField;
public class Login {
	int tentativas = 3;
	private static JFrame frmAtm;
	private static JTextField in_user;
	private static JPasswordField in_pass;
	public static  int id_user;
	private JButton subbtn; 



	public static void main(String[] args) {


		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Login window = new Login();
					window.frmAtm.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	/*
	 * Create the application.
	 */
	public Login() {
		initialize();

	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {

		frmAtm = new JFrame();

		frmAtm.addWindowFocusListener(new WindowFocusListener() {
			public void windowGainedFocus(WindowEvent arg0) {
			}
			public void windowLostFocus(WindowEvent arg0) {
			}
		});
		frmAtm.addComponentListener(new ComponentAdapter() {


			public void componentHidden(ComponentEvent e) {
			}
		});
		frmAtm.setIconImage(Toolkit.getDefaultToolkit().getImage("C:\\Users\\beatr\\OneDrive\\Ambiente de Trabalho\\585.png"));
		frmAtm.getContentPane().setBackground(Color.WHITE);
		frmAtm.setTitle("Gestão de Gastos");
		frmAtm.setBounds(100, 100, 538, 311);
		frmAtm.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmAtm.getContentPane().setLayout(null);
		frmAtm.setLocationRelativeTo(null);
		subbtn = new JButton("Login");
		subbtn.setBorder(null);
		subbtn.setBackground(SystemColor.text);
		subbtn.setToolTipText("");
		subbtn.setFont(new Font("Calibri", Font.BOLD, 18));
		subbtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				verifica_pass();


			}
		});
		subbtn.setBounds(230, 220, 65, 19);
		frmAtm.getContentPane().add(subbtn);

		in_user = new JTextField();
		in_user.setBorder(null);
		in_user.setCaretColor(Color.BLACK);
		in_user.setFont(new Font("Calibri", Font.BOLD, 14));
		in_user.setBounds(175, 117, 147, 23);
		frmAtm.getContentPane().add(in_user);
		in_user.setColumns(10);

		
		in_pass = new JPasswordField();
		in_pass.setBorder(null);
		in_pass.setFont(new Font("Calibri", Font.BOLD, 14));
		in_pass.setBounds(175, 177, 147, 23);
		frmAtm.getContentPane().add(in_pass);
		in_pass.setColumns(10);

		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon("C:\\\\Users\\\\beatr\\\\OneDrive\\\\Ambiente de Trabalho\\\\585.png"));
		lblNewLabel.setBounds(0, 0, 522, 272);
		frmAtm.getContentPane().add(lblNewLabel);
	}

	public static void verifica_pass()
	{

		String username =in_user.getText();
		String password = in_pass.getText();
		Connection conn = null;
		try {

			String dbURL = "jdbc:postgresql://localhost:5432/trabalho5";
			String user = "postgres";
			String pass = "T12345678l";
			conn = DriverManager.getConnection(dbURL, user,pass);
			if (conn != null) {

				

				String sql="Select username, password,id from login where username='"+username+"'AND password='"+password+"'";

				Statement statement= conn.createStatement();
				ResultSet result=statement.executeQuery(sql);


				if(result.next())
				{
					id_user=result.getInt(3);

					verifica_Orcamento();

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

	public static void verifica_Orcamento()
	{


		Connection conn = null;
		try {

			String dbURL = "jdbc:postgresql://localhost:5432/trabalho5";
			String user = "postgres";
			String pass = "T12345678l";
			conn = DriverManager.getConnection(dbURL, user, pass);
			if (conn != null) {


				String sql="Select OrcamentoMensal from orcamento where ID_User='"+id_user+"'";


				Statement statement= conn.createStatement();
				ResultSet result=statement.executeQuery(sql);




				if(result.next() == true)
				{

					index.main(null);
					frmAtm.hide();
				}else {

					formulario.main(null);
					frmAtm.hide();
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









