package ebs.view;

import java.awt.Choice;
import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import ebs.database.Conn;

public class PayBill extends JFrame implements ActionListener {

	private static final long serialVersionUID = 1L;

    private JLabel title;
	private JLabel meterNo;
	private JLabel names;
	private JLabel month;
	private JLabel units;
	private JLabel totalBill;
	private JLabel status;
	private JLabel imageLabel;
	
	private JLabel meterNoLF;
	private JLabel namesLF;
	private JLabel unitsLF;
	private JLabel totalBillLF;
	private JLabel statusLF;

	private Choice chooseMonth;
	
	private JButton payButton;
	private JButton backButton;
	
	private String meter;
		
	public PayBill(String meter) {
		this.meter = meter;
		setLayout(null);
		setBounds(550, 200, 900, 600);
		getContentPane().setBackground(Color.WHITE);
		
		getElectricityBillTitle();
		getMeterNo();
		getCusName();
		getUnits();
		getTotalBill();
		getStatus();
		getChooseMonth();
		getBill();
        getPayButton();
        getBackButton();
        getPayBillImage();
			
	    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	private void getElectricityBillTitle() {
		title = new JLabel("Electricity Bill");
		title.setFont(new Font("Tahoma", Font.BOLD , 24));
		title.setBounds(120, 5, 400, 30);
		add(title);
	}

	private void getMeterNo() {
		meterNo = new JLabel("Meter no");
		meterNo.setBounds(35, 80, 200, 20);
		add(meterNo);
		
		meterNoLF = new JLabel(meter);
		meterNoLF.setBounds(300, 80, 200, 20);
		add(meterNoLF);
	}

	private void getCusName() {
		names = new JLabel("Name");
		names.setBounds(35, 140, 200, 20);
		add(names);	
		
		namesLF = new JLabel();
		namesLF.setBounds(300, 140, 200, 20);
		add(namesLF);
	}
	
	private void getUnits() {
		units = new JLabel("Units");
		units.setBounds(35, 260, 200, 20);
        add(units);
        
		unitsLF = new JLabel();
		unitsLF.setBounds(300, 260, 200, 20);
		add(unitsLF);	
	}
	
	private void getTotalBill() {
		totalBill = new JLabel("Total Bill");
		totalBill.setBounds(35, 320, 200, 20);
        add(totalBill);
        
        totalBillLF = new JLabel();
        totalBillLF.setBounds(300, 320, 200, 20);
		add(totalBillLF);	
	}
	
	private void getStatus() {
		status = new JLabel("Status");
		status.setBounds(35, 380, 200, 20);
        add(status);
        
		statusLF = new JLabel();
		statusLF.setBounds(300, 380, 200, 20);
		statusLF.setBackground(Color.RED);
		add(statusLF);	
	}
	
	public void getChooseMonth() {
		month = new JLabel("Month");
		month.setBounds(300, 140, 200, 20);
		add(month);
		
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
		add(chooseMonth);	
	}
	
	public void getBill() {
		
		try {
			Conn conn = new Conn();
			String query = "SELECET * FROM customer where meter = ? ";
			PreparedStatement pstmt = conn.connection.prepareStatement(query);
			pstmt.setString(1, meter);
			ResultSet rs = pstmt.executeQuery();
			
			while (rs.next()) {
				meterNoLF.setText(rs.getString("meter"));
				namesLF.setText(rs.getString("name"));
			}
			String query2 = "SELECET * FROM bill where meter = ? AND month = ?";
			rs = pstmt.executeQuery(query2);
			pstmt.setString(1, meter);
			pstmt.setString(2, chooseMonth.getSelectedItem());
			while (rs.next()) {
				unitsLF.setText(rs.getString("units"));
				totalBillLF.setText(rs.getString("total_bill"));
				statusLF.setText(rs.getString("status"));
			}
		} catch (Exception e) {}
		
		chooseMonth.addItemListener(new ItemListener() {
			
			@Override
			public void itemStateChanged(ItemEvent ie) {
				try {
					Conn conn = new Conn();
					String query = "SELECT * FROM bill WHERE meter = ? and month = ?";
					PreparedStatement pstmt = conn.connection.prepareStatement(query);
					pstmt.setString(1, meter);
					pstmt.setString(2, chooseMonth.getSelectedItem());
					ResultSet rs = pstmt.executeQuery();
					while (rs.next()) {
						unitsLF.setText(rs.getString("units"));
						totalBillLF.setText(rs.getString("total_bill"));
						statusLF.setText(rs.getString("status"));
					}
				} catch (Exception e) {}
				
			}
		});
	}
	
	public JButton getPayButton() {
		payButton = new JButton("Pay");
		payButton.setBounds(100, 460, 100, 25);
		payButton.setBackground(Color.BLACK);
		payButton.setForeground(Color.WHITE);
		payButton.addActionListener(this);
		
		add(payButton);
		return payButton;	
	}
	
	public JButton getBackButton() {
		backButton = new JButton("Back");
		backButton.setBounds(230, 460, 100, 25);
		backButton.setBackground(Color.BLACK);
		backButton.setForeground(Color.WHITE);
		backButton.addActionListener(this);
		
		add(backButton);
		return backButton;	
	}
	
	public void getPayBillImage() {
		ImageIcon userIcon = new ImageIcon(ClassLoader.getSystemResource("Icons/signupUser.png"));
		Image userImage = userIcon.getImage().getScaledInstance(300, 300, Image.SCALE_SMOOTH);
		ImageIcon scaledImageIcon = new ImageIcon(userImage);
		imageLabel = new JLabel(scaledImageIcon);
		imageLabel.setBounds(550, 120, 300, 300);
		add(imageLabel);
	}
	
	@Override
	public void actionPerformed(ActionEvent ae) {
		if (ae.getSource() == payButton) {
			try {
				Conn conn = new Conn();
				String query = "UPDATE bil staus = 'Paid' WHERE meter = ? AND month = ?";
				PreparedStatement pstmt = conn.connection.prepareStatement(query);
				pstmt.setString(1, meter);
				pstmt.setString(2, chooseMonth.getSelectedItem());
				pstmt.executeUpdate();
			} catch (Exception e) {}
				this.setVisible(false);
				new Paytm(meter).setVisible(true);	
		}else if (ae.getSource() == backButton) {
			this.setVisible(false);
			
		}
	}

	public static void main(String[] args) {
		new PayBill("").setVisible(true);
	}

}
