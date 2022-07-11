package trabalho;

import java.awt.BorderLayout;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.JButton;
import javax.swing.border.SoftBevelBorder;
import javax.swing.border.BevelBorder;
import java.awt.Color;
import java.awt.Font;
import javax.swing.UIManager;
import java.awt.Component;
import java.awt.ComponentOrientation;
import java.awt.EventQueue;
import java.awt.Rectangle;
import javax.swing.event.AncestorListener;
import javax.swing.event.AncestorEvent;
import java.awt.Toolkit;

public class consultar {


	/**
	 * Main aplication entry point
	 * @param args
	 * @throws SQLException
	 */
	@SuppressWarnings("deprecation")
	public static void main(String[] args) throws SQLException
	{
		// a MySQL statement
		Statement stmt;
		// a MySQL query
		String query;
		// the results from a MySQL query
		ResultSet rs;

		// 2 dimension array to hold table contents
		// it holds temp values for now
		Object rowData[][] = {{"Row1-Column1", "Row1-Column2", "Row1-Column3","Row1-Column4","Row1-Column5","Row1-Column6"}};
		// array to hold column names
		Object columnNames[] = {"ID_User", "Valor", "Categoria", "Data", "Hora", "Descricao"};

		// create a table model and table based on it
		DefaultTableModel mTableModel = new DefaultTableModel(rowData, columnNames);
		JTable table = new JTable(mTableModel);

		table.addAncestorListener(new AncestorListener() {
			public void ancestorAdded(AncestorEvent arg0) {

			}
			public void ancestorMoved(AncestorEvent arg0) {
			}
			public void ancestorRemoved(AncestorEvent arg0) {
			}
		});
		table.setBounds(new Rectangle(0, 13, 0, 0));
		table.setFillsViewportHeight(true);
		table.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
		table.setBackground(Color.WHITE);

		table.setFont(new Font("Calibri", Font.BOLD, 13));
		Connection conn = null;
		// try and connect to the database
		try {
			String dbURL = "jdbc:postgresql://localhost:5432/trabalho5";
			String user = "postgres";
			String pass = "T12345678l";
			conn = DriverManager.getConnection(dbURL, user,pass);
		} catch (Exception e) {
			System.err.println("Exception: " + e.getMessage());
		}

		// run the desired query
		query = "SELECT * FROM despesas where ID_User='"+Login.id_user+"' ";
		// make a statement with the server
		stmt = conn.createStatement();
		// execute the query and return the result
		rs = stmt.executeQuery(query);

		// create the gui
		JFrame frmDespesasFeitas = new JFrame();
		frmDespesasFeitas.setTitle("Despesas Feitas");
		frmDespesasFeitas.setIconImage(Toolkit.getDefaultToolkit().getImage("C:\\Users\\beatr\\OneDrive\\Ambiente de Trabalho\\585.png"));
		frmDespesasFeitas.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

		JScrollPane scrollPane = new JScrollPane(table);
		scrollPane.addAncestorListener(new AncestorListener() {
			public void ancestorAdded(AncestorEvent arg0) {
			}
			public void ancestorMoved(AncestorEvent arg0) {
			}
			public void ancestorRemoved(AncestorEvent arg0) {
			}
		});
		scrollPane.setFont(new Font("Calibri", Font.BOLD, 18));
		scrollPane.setBackground(Color.WHITE);
		scrollPane.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, null, null, null, null));
		frmDespesasFeitas.getContentPane().add(scrollPane, BorderLayout.CENTER);
		frmDespesasFeitas.setSize(1046, 586);
		frmDespesasFeitas.setVisible(true);
		frmDespesasFeitas.setLocationRelativeTo(null);
		// remove the temp row
		mTableModel.removeRow(0);

		// create a temporary object array to hold the result for each row
		Object[] rows;
		// for each row returned
		while (rs.next()) {
			// add the values to the temporary row
			rows = new Object[]{rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6)};
			// add the temp row to the table
			mTableModel.addRow(rows);
		}
	}
}