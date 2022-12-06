package ebs.view;

import java.awt.BorderLayout;
import java.awt.Choice;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import ebs.database.Conn;


public class GenerateBill extends JFrame implements ActionListener{

	private static final long serialVersionUID = 1L;
	private JLabel generateBillLabel;
	private JLabel meterLabel;
	private JTextArea generateTA;
	private JButton generateButton;
	private Choice chooseMonth;
	private JPanel generateBillJPanel;
	private String meterString;
	
	public GenerateBill(String meter) {
		this.meterString = meter;
		setSize(500, 900);
		setLocation(750, 100);
		setLayout(new BorderLayout());
		
		getBillPanel();
		getGenerateBill();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	

	private void getBillPanel() {
		generateBillJPanel = new JPanel();
		generateBillLabel = new JLabel("Generate Bill");
		meterLabel = new JLabel(meterString);
		
		generateBillJPanel.add(getChooseMonth());
		generateBillJPanel.add(generateBillLabel);
		generateBillJPanel.add(meterLabel);
		add(generateBillJPanel, "North");
	}

	public Choice getChooseMonth() {
		chooseMonth = new Choice();
		chooseMonth.setBounds(300, 200, 200, 20);
		chooseMonth.add("January");
		chooseMonth.add("February");
		chooseMonth.add("March");
		chooseMonth.add("April");
		chooseMonth.add("May");
		chooseMonth.add("June");
		chooseMonth.add("July");
		chooseMonth.add("August");
		chooseMonth.add("September");
		chooseMonth.add("October");
		chooseMonth.add("November");
		chooseMonth.add("December");	
		
		return chooseMonth;
	}
	
	 public void getGenerateBill(){
		 //TODO add year chosen
		 generateTA = new JTextArea(50,15);
		 generateTA.setText("\n\n\t------- Click on the -------\n\t Generate Bill Button to get\n\tthe bill of the Selected Month\n\n");
		 JScrollPane sp = new JScrollPane(generateTA);
		 generateTA.setFont(new Font("Senserif", Font.ITALIC, 18));
		 add(sp, "Center");
		 
		 generateButton = new JButton("Generate Bill");
		 generateButton.addActionListener(this);
		 add(generateButton, "South");
	 }
	 @Override
	 public void actionPerformed(ActionEvent ae) {
		 try {
			Conn conn = new Conn();
			
			String month = chooseMonth.getSelectedItem();
			generateTA.setText("\tReliance Power Limited\nELECTRICITY BILL FOR THE MONTH OF "+month+" ,2023\n\n\n");
			String query = "SELECT * FROM customer WHERE meter = ?";
			PreparedStatement pstmt = conn.connection.prepareStatement(query);
			pstmt.setString(1, meterString);
			ResultSet rs = pstmt.executeQuery();
			if (rs.next()) {
				generateTA.append("\n   Customer Name:"+rs.getString("name"));
				generateTA.append("\n   Meter Number:  "+rs.getString("meter"));
				generateTA.append("\n   Address:            "+rs.getString("address"));
				generateTA.append("\n   State:                 "+rs.getString("state"));
				generateTA.append("\n   City:                   "+rs.getString("city"));
				generateTA.append("\n   Email:                "+rs.getString("email"));
				generateTA.append("\n   Phone Number: "+rs.getString("phone"));
				generateTA.append("\n---------------------------------------------------------------");
				generateTA.append("\n");
				
			}
			
			query = "SELECT * FROM meter_info WHERE meter_number = ?";
			pstmt = conn.connection.prepareStatement(query);
			pstmt.setString(1, meterString);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				generateTA.append("\n   Meter Location:"+rs.getString("meter_location"));
				generateTA.append("\n   Meter Type:      "+rs.getString("meter_type"));
				generateTA.append("\n   Phase Code:    "+rs.getString("phase_code"));
				generateTA.append("\n   Bill Type:         "+rs.getString("bill_type"));
				generateTA.append("\n   Days:               "+rs.getString("days"));
				generateTA.append("\n");
			}
			
			query = "SELECT * FROM tax";
			pstmt = conn.connection.prepareStatement(query);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				generateTA.append("\n---------------------------------------------------------------");
				generateTA.append("\n\n");
				generateTA.append("\n   Cost Per Unit:            CAD "+rs.getString("cost_per_unit"));
				generateTA.append("\n   Meter Rent:               CAD "+rs.getString("meter_rent"));
				generateTA.append("\n   Service Charge:           CAD "+rs.getString("service_charge"));
				generateTA.append("\n   Service Tax:              CAD "+rs.getString("service_tax"));
				generateTA.append("\n   Swacch Sharat Cess:       CAD "+rs.getString("swacch_bahrat_cess"));
				generateTA.append("\n   Fixed Tax:                CAD "+rs.getString("fixed_tax"));
				generateTA.append("\n");
			}
			
			query = "SELECT * FROM bill Where meter = ? AND month = ?";
			pstmt = conn.connection.prepareStatement(query);
			pstmt.setString(1, meterString);
			pstmt.setString(2, chooseMonth.getSelectedItem());
			rs = pstmt.executeQuery();
			if (rs.next()) {
				generateTA.append("\n   Current Month :\t"+rs.getString("month"));
				generateTA.append("\n   Units Consumed:\t"+rs.getString("units"));
				generateTA.append("\n   Total Charges :\t"+rs.getString("total_bill"));
				generateTA.append("\n---------------------------------------------------------------");
				generateTA.append("\n   TOTAL PAYABLE :\t"+rs.getString("total_bill"));
	
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}

	 }
	public static void main(String[] args) {
		new GenerateBill("").setVisible(true);
	}


	

}
