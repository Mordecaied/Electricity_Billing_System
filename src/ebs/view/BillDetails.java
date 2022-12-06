package ebs.view;

import java.awt.Color;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import ebs.database.Conn;
import net.proteanit.sql.DbUtils;

public class BillDetails extends JFrame {

	private static final long serialVersionUID = 1L;
	private JTable billTable;
	String x[] = {"Meter Number","Month","Units","Total Bill","Status"};
	String y[][] = new String[40][8];
	int i = 0;
	int j = 0;

	public BillDetails(String meter) {
		super("Bill Details");
		setSize(700, 650);
		setLocation(600, 150);
		setLayout(null);
		getContentPane().setBackground(Color.WHITE);
		
		makeTable(meter);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	private void makeTable(String meter) {
		billTable = new JTable(y,x);
		try {
			Conn conn = new Conn();
			String query  = "SELECT * FROM bill WHERE meter = ?";
			PreparedStatement pstmt = conn.connection.prepareStatement(query);
			pstmt.setString(1, meter);
			ResultSet rs = pstmt.executeQuery();
			billTable.setModel(DbUtils.resultSetToTableModel(rs));
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		JScrollPane sp = new JScrollPane(billTable);
		sp.setBounds(0, 0, 700, 650);
		
		
	}

	public static void main(String[] args) {
		new BillDetails("").setVisible(true);

	}

}
