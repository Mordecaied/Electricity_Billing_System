package ebs.view;

import java.awt.Choice;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import net.proteanit.sql.DbUtils;
import ebs.database.Conn;

public class DepositDetails extends JFrame implements ActionListener{

	private static final long serialVersionUID = 1L;
	
	private JTable depositTable;
	private JLabel sortByMeterNumber;
	private JLabel sortByMonth;
	private Choice byMeterNumberChoice;
	private Choice byMonthChoice;
	private JButton searchButton;
	private JButton printButton;
	private String columnNames[] = {"Meter Number","Month","Units","Total Bill","Status"};
	private String tableGrid[][] = new String[40][8];
	
	public DepositDetails() {
		super("Deposit Details");
		setSize(700, 750);
		setLocation(600, 150);
		setLayout(null);
		getContentPane().setBackground(Color.WHITE);
		
		getSortByMeter();
		getSortByMonth();
		makeTable();
		getSearchBUtton();
		getPrintBUtton();
		
	}
	
	public void getSortByMeter() {
		sortByMeterNumber = new JLabel("Sort By Meter Number");
		sortByMeterNumber.setBounds(20, 20, 150, 20);
		add(sortByMeterNumber);
		
		byMeterNumberChoice = new Choice();
		byMeterNumberChoice.setBounds(180, 20, 150, 20);
		add(byMeterNumberChoice);
	}
	
	public void getSortByMonth() {
		sortByMonth = new JLabel("Sort By Month");
		sortByMonth.setBounds(400, 20, 100, 20);
		add(sortByMonth);
		
		byMonthChoice = new Choice();
		byMonthChoice.setBounds(520, 20, 150, 20);
		byMonthChoice.add("January");
		byMonthChoice.add("February");
		byMonthChoice.add("March");
		byMonthChoice.add("April");
		byMonthChoice.add("May");
		byMonthChoice.add("June");
		byMonthChoice.add("July");
		byMonthChoice.add("August");
		byMonthChoice.add("September");
		byMonthChoice.add("October");
		byMonthChoice.add("November");
		byMonthChoice.add("December");
		add(byMonthChoice);
	}
	
	public void makeTable() {
		depositTable = new JTable(tableGrid, columnNames);
		try {
			Conn conn = new Conn();
			String selectBillStatement = "select * from bill";
			ResultSet rs = conn.statement.executeQuery(selectBillStatement);
			
			depositTable.setModel(DbUtils.resultSetToTableModel(rs));
			
			String selectCustomerStatement = "select * from customer";
			rs = conn.statement.executeQuery(selectCustomerStatement);
			while (rs.next()) {
				byMeterNumberChoice.add(rs.getString("meter"));
				
			}
		} catch (Exception e) {
			e.printStackTrace();
	   }
		
		JScrollPane sp = new JScrollPane(depositTable);
		sp.setBounds(0, 100, 700, 650);
		add(sp);
	}
	
	public void getSearchBUtton() {
		searchButton = new JButton("Search");
		searchButton.setBounds(20, 70, 80, 20);
		searchButton.addActionListener(this);
		add(searchButton);
	}

	public void getPrintBUtton() {
		printButton = new JButton("Search");
		printButton.setBounds(120, 70, 80, 20);
		printButton.addActionListener(this);
		add(printButton);
	}
	
	@Override
	public void actionPerformed(ActionEvent ae) {
		if (ae.getSource() == searchButton) {
			String seardchStatement = "select * from bill where meter = '"+
		                               byMeterNumberChoice.getSelectedItem()+
		                               "' AND month = '"+
		                               byMonthChoice.getSelectedItem()+"'";
			try {
				Conn conn = new Conn();
				ResultSet rs = conn.statement.executeQuery(seardchStatement);
				depositTable.setModel(DbUtils.resultSetToTableModel(rs));
			} catch (Exception e) {}
		}else if (ae.getSource() == printButton) {
			try {
				depositTable.print();
			} catch (Exception e) {}

		}
		
	}
	
	public static void main(String[] args) {
		new DepositDetails().setVisible(true);

	}

	

}
