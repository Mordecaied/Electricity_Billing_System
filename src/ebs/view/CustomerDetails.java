package ebs.view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import ebs.database.Conn;

public class CustomerDetails extends JFrame implements ActionListener {

	private static final long serialVersionUID = 1L;
	
	JTable customerTable;
	JButton printButton;
	String x[] = {"Customr Name","Meter Number","Address","City","State","Email","Phone"};
	String y[][] = new String[40][8];
	int i = 0;
	int j = 0;
	public CustomerDetails() {
		super("Customer Details");
		setSize(1200, 650);
	    setLocation(400, 150);
	    
	    initializeTable();
	    getPrintButton();
	}
	
	public JTable initializeTable() {
		try {
			Conn conn = new Conn();
			String selectStatement = "Select * from customer";
			ResultSet rs = conn.statement.executeQuery(selectStatement);
			 while (rs.next()) {
				y[i][j++] = rs.getString("name");
				y[i][j++] = rs.getString("meter");
				y[i][j++] = rs.getString("address");
				y[i][j++] = rs.getString("city");
				y[i][j++] = rs.getString("state");
				y[i][j++] = rs.getString("email");
				y[i][j++] = rs.getString("phone");	
				i++;
				j=0;
			}
			customerTable = new JTable(y,x);
			 
		} catch (Exception e) {
			e.printStackTrace();
		}
		return customerTable;
	}
	
	public JButton getPrintButton() {
		printButton = new JButton("Print");
		add(printButton, "South");
		JScrollPane sp = new JScrollPane(customerTable);
		add(sp);
		printButton.addActionListener(this);
		return printButton;
}
	
	@Override
	public void actionPerformed(ActionEvent actionEvent) {
		try {
			customerTable.print();
		} catch (Exception e) {
			
		}
	}

	public static void main(String[] args) {
		new CustomerDetails().setVisible(true);

	}
}
