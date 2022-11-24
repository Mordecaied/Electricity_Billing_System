package ebs.view;

import java.awt.BorderLayout;
import java.awt.Choice;
import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.sql.ResultSet;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import ebs.database.Conn;

public class CalculateBill extends JFrame implements ActionListener{

	private static final long serialVersionUID = 1L;
	
	private JPanel calculateBillPanel;
	
	private JLabel calculateElectricityBillJLabel;
	private JLabel meterNo;
	private JLabel names;
	private JLabel address;
	private JLabel unitsConsumed;
	private JLabel month;
	private JLabel nameFromDB;
	private JLabel addressFromDB;
	private JLabel imageLabel;
	
	private JTextField unitsTF;
	
	private Choice chooseMeter;
	private Choice chooseMonth;
	
	private JButton submitButton;
	private JButton cancelButton;
	

	
	public CalculateBill() {
		getContentPane().setBackground(Color.WHITE);
		setSize(750, 500);
		setLocation(550, 220);
		setLayout(new BorderLayout(30,30));
		getCalculateBillPanel();
		getCalculateBillImage();
		
	    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	private JPanel getCalculateBillPanel() {
		calculateBillPanel = new JPanel();
		calculateBillPanel.setLayout(null);
		calculateBillPanel.setBackground(new Color(173, 216, 230));
		
		getCalculateElectricityBill();
		getMeterNo();
		getCusName();
		getAddress();
		getUnitsConsumed();
		getChooseMonths();
		getChooseMeter();
		getSubmitButton();
		getCancelButton();
		
		add(calculateBillPanel, "Center");
		return calculateBillPanel;
	}

	private void getCalculateElectricityBill() {
		calculateElectricityBillJLabel = new JLabel("Calculate Electricity Bill", SwingConstants.CENTER);
		calculateElectricityBillJLabel.setBounds(30, 10, 400, 30);
		calculateElectricityBillJLabel.setFont(new Font("Senserif", Font.PLAIN, 26));
		calculateElectricityBillJLabel.setHorizontalAlignment(JLabel.CENTER);
		
		calculateBillPanel.add(calculateElectricityBillJLabel);
	}

	private void getMeterNo() {
		meterNo = new JLabel("Meter no", SwingConstants.CENTER);
		meterNo.setBounds(60, 70, 100, 30);
		meterNo.setOpaque(true);
		meterNo.setBorder(BorderFactory.createLineBorder(Color.YELLOW, 5));
		meterNo.setBackground(Color.WHITE);
		
		calculateBillPanel.add(meterNo);
	}

	private void getCusName() {
		names = new JLabel("Name", SwingConstants.CENTER);
		names.setBounds(60, 120, 100, 30);
		names.setOpaque(true);
		names.setBorder(BorderFactory.createLineBorder(Color.YELLOW, 5));
		names.setBackground(Color.WHITE);
		
		calculateBillPanel.add(names);	
	}

	private void getAddress() {
		address = new JLabel("Address", SwingConstants.CENTER);
		address.setBounds(60, 170, 100, 30);
		address.setOpaque(true);
		address.setBorder(BorderFactory.createLineBorder(Color.YELLOW, 5));
		address.setBackground(Color.WHITE);
		
		calculateBillPanel.add(address);	
	}

	private void getUnitsConsumed() {
		unitsConsumed = new JLabel("Units Consumed", SwingConstants.CENTER);
		unitsConsumed.setBounds(60, 220, 100, 30);
		unitsConsumed.setOpaque(true);
		unitsConsumed.setBorder(BorderFactory.createLineBorder(Color.YELLOW, 5));
		unitsConsumed.setBackground(Color.WHITE);
        
		unitsTF = new JTextField();
		unitsTF.setBounds(200, 220, 180, 20);
		
		calculateBillPanel.add(unitsConsumed);
	}

	private void getChooseMonths() {
		month = new JLabel("Month", SwingConstants.CENTER);
		month.setBounds(60, 270, 100, 30);
		month.setOpaque(true);
		month.setBorder(BorderFactory.createLineBorder(Color.YELLOW, 5));
		month.setBackground(Color.WHITE);
	
		
		calculateBillPanel.add(month);
	}
	
	private void getChooseMeter() {
		chooseMeter = new Choice();
		chooseMeter.setBounds(200, 70, 180, 20);

		try {
			Conn conn = new Conn();
			ResultSet rs = conn.statement.executeQuery("select * from customer");
			while (rs.next()) {
				chooseMeter.add(rs.getString("meter"));
			}
		}catch (Exception e) {}
		
		chooseMeter.addItemListener(new ItemListener() {
			@Override
			public void itemStateChanged(ItemEvent e) {
				try {
					Conn conn = new Conn();
					ResultSet rs = conn.statement.executeQuery("select * from customer where meter = '"+chooseMeter.getSelectedItem()+"'");
					while (rs.next()) {
						nameFromDB.setText(rs.getString("name"));
						addressFromDB.setText(rs.getString("address"));
					}
				} catch (Exception e2) {}
			}
		});
		
		calculateBillPanel.add(chooseMeter);
	}
	
	
	public void chooseMonth() {
		chooseMonth = new Choice();
		chooseMonth.setBounds(200, 270, 180, 20);
		
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
		
	}
	
	public JButton getSubmitButton() {
		submitButton = new JButton("Submit");
		submitButton.setBounds(120, 390, 100, 25);
		submitButton.setBackground(Color.BLACK);
		submitButton.setForeground(Color.WHITE);
		submitButton.addActionListener(this);
		
		calculateBillPanel.add(submitButton);
		return submitButton;	
	}
	
	public JButton getCancelButton() {
		cancelButton = new JButton("Cancel");
		cancelButton.setBounds(250, 390, 100, 25);
		cancelButton.setBackground(Color.BLACK);
		cancelButton.setForeground(Color.WHITE);
		cancelButton.addActionListener(this);
		
		calculateBillPanel.add(cancelButton);
		return cancelButton;	
	}
	
	public void getCalculateBillImage() {
		ImageIcon userIcon = new ImageIcon(ClassLoader.getSystemResource("Icons/login.png"));
		Image userImage = userIcon.getImage().getScaledInstance(180, 270, Image.SCALE_SMOOTH);
		ImageIcon scaledImageIcon = new ImageIcon(userImage);
		imageLabel = new JLabel(scaledImageIcon);
		
		add(imageLabel, "West");
	}
	
	@Override
	public void actionPerformed(ActionEvent ae) {
		if (ae.getSource() == submitButton) {
			String meter_no = chooseMeter.getSelectedItem();
			String units = unitsTF.getText();
			String month = chooseMonth.getSelectedItem();
			
			int unitsConsumed = Integer.parseInt(units);
			
			int totalBill = 0;
			try {
				Conn conn = new Conn();
				ResultSet rs = conn.statement.executeQuery("select * from tax");
				while (rs.next()) {
					totalBill = unitsConsumed * Integer.parseInt(rs.getString("cost_per_unit"));//120 * 9
					totalBill += Integer.parseInt(rs.getString("meter_rent"));
					totalBill += Integer.parseInt(rs.getString("service_charge"));
					totalBill += Integer.parseInt(rs.getString("service_tax"));
					totalBill += Integer.parseInt(rs.getString("swacch_bharat"));
					totalBill += Integer.parseInt(rs.getString("fixed_tax"));
				}
			} catch (Exception e) {}
			
				String q = "insert into bill values('"+meter_no+"','"+units+"','"+totalBill+"', 'Not Paid')";
			try {
				Conn conn2 = new Conn();
				conn2.statement.executeQuery(q);
				JOptionPane.showMessageDialog(null,  "Customer Bill Udated Succesfully");
				this.setVisible(false);
			} catch (Exception e2) {
				e2.printStackTrace();
			}
			}else if (ae.getSource() == cancelButton) {
				this.setVisible(false);
			}
		}
	
	public static void main(String[] args) {
		new CalculateBill().setVisible(true);

	}

	
}
