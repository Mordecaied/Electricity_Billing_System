package ebs.view;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import ebs.database.Conn;

public class UpdateInformation extends JFrame implements ActionListener{

	private static final long serialVersionUID = 1L;
	
	private JLabel titleL;
	private JLabel nameL;
	private JLabel meterL;
	private JLabel customerNameL;
	private JLabel meterNumberL;
	private JLabel addressL;
	private JLabel cityL;
	private JLabel stateL;
	private JLabel emailL;
	private JLabel phoneL;
	private JLabel imageLabel;
	
	private JTextField addresssTF;
	private JTextField cityTF;
	private JTextField stateTF;
	private JTextField emailTF;
	private JTextField phoneTF;
	
	private JButton updateButton;
	private JButton backButton;
	
	private String meter;

	public UpdateInformation(String meter) {
		this.meter = meter;

		setBounds(500, 220, 1050, 450);
		setLayout(null);
		getContentPane().setBackground(Color.WHITE);

		setUpUpdateInformation();

		
	}

	private void setUpUpdateInformation() {
		titleL = new JLabel("UPDATE CUSTOMER INFORMATION");
		titleL.setBounds(110, 0, 400, 30);
		titleL.setFont(new Font("Tahoma", Font.PLAIN, 20));
		add(titleL);
		
		getUpdateImage();
		getCustomerName();
		getMeterNumber();
		getAddress();
		getCity();
		getCusState();
		getEmail();
		getPhone();
		getUpdateButton();
		getBackButton();
		getCustomerDetails();
	}

	private void getUpdateImage() {
		ImageIcon userIcon = new ImageIcon(ClassLoader.getSystemResource("Icons/loginUser.png"));
		Image userImage = userIcon.getImage().getScaledInstance(400, 300, Image.SCALE_SMOOTH);
		ImageIcon scaledImageIcon = new ImageIcon(userImage);
		imageLabel = new JLabel(scaledImageIcon);
		imageLabel.setBounds(550, 50, 400, 300);
		add(imageLabel);	
	}

	private void getCustomerName() {
		nameL = new JLabel("Name");
		nameL.setBounds(30, 70, 100, 20);
		add(nameL);
		customerNameL = new JLabel();
		customerNameL.setBounds(230, 70, 200, 20);
		add(customerNameL);
	}

	private void getMeterNumber() {
		meterL = new JLabel("Meter Number");
		meterL.setBounds(30, 110, 100, 20);
		add(meterL);
		meterNumberL = new JLabel();
		meterNumberL.setBounds(230, 110, 200, 20);
		add(meterNumberL);
	}

	private void getAddress() {
		addressL = new JLabel("Address");
		addressL.setBounds(30, 150, 100, 20);
		add(addressL);
		addresssTF = new JTextField();
		addresssTF.setBounds(230, 150, 200, 20);
		add(addresssTF);
	}

	private void getCity() {
		cityL = new JLabel("City");
		cityL.setBounds(30, 190, 100, 20);
		add(cityL);
		cityTF = new JTextField();
		cityTF.setBounds(230, 190, 200, 20);
		add(cityTF);
	}

	private void getCusState() {
		stateL = new JLabel("State");
		stateL.setBounds(30, 230, 100, 20);
		add(stateL);
		stateTF = new JTextField();
		stateTF.setBounds(230, 230, 200, 20);
		add(stateTF);
	}

	private void getEmail() {
		emailL = new JLabel("Email");
		emailL.setBounds(30, 270, 100, 20);
		add(emailL);
		emailTF  = new JTextField();
		emailTF.setBounds(230, 270, 200, 20);
		add(emailTF);
	}

	private void getPhone() {
		phoneL = new JLabel("Phone");
		phoneL.setBounds(30, 310, 100, 20);
		add(phoneL);
		phoneTF = new JTextField();
		phoneTF.setBounds(230, 310, 200, 20);
		add(phoneTF);
	}

	private void getUpdateButton() {
		updateButton = new JButton("Update");
		updateButton.setBackground(Color.BLACK);
		updateButton.setForeground(Color.WHITE);
		updateButton.setBounds(70, 360, 100, 25);
		updateButton.addActionListener(this);
		add(updateButton);
	}

	private void getBackButton() {
		backButton = new JButton("Back");
		backButton.setBackground(Color.BLACK);
		backButton.setForeground(Color.WHITE);
		backButton.setBounds(230, 360, 100, 25);
		backButton.addActionListener(this);
		add(backButton);
	}
	
	public void getCustomerDetails() {
		String query = null;
		PreparedStatement pstmt = null;
		
		try {
			Conn conn = new Conn();
			query =  "SELECT * FROM customer WHERE meter = ?";
			pstmt = conn.connection.prepareStatement(query);
			pstmt.setString(1, meter);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				customerNameL.setText(rs.getString(1));
				meterNumberL.setText(rs.getString(2));
				addresssTF.setText(rs.getString(3));
				cityTF.setText(rs.getString(4));
				stateTF.setText(rs.getString(5));
				emailTF.setText(rs.getString(6));
				phoneTF.setText(rs.getString(7));
				
			}
		} catch (Exception e) {}
	}

	@Override
	public void actionPerformed(ActionEvent ae) {
		String query = null;
		PreparedStatement pstmt = null;
		
		if (ae.getSource() == updateButton) {
//			String name = customerNameL.getText();
//			String meter = meterNumberL.getText();
			String address = addresssTF.getText();
			String city = cityTF.getText();
			String state = stateTF.getText();
			String email = emailTF.getText();
			String phone = phoneTF.getText();

			try {
				Conn conn = new Conn();
				query =  "UPDATE customer SET address = ?, city = ?, state = ?, email = ?, phone = ?";
				pstmt = conn.connection.prepareStatement(query);
				pstmt.setString(1, address);
				pstmt.setString(2, city);
				pstmt.setString(3, state);
				pstmt.setString(4, email);
				pstmt.setString(5, phone);
				pstmt.executeUpdate();

				JOptionPane.showMessageDialog(null, "Details updated succesfully");
				this.setVisible(true);
				
			} catch (Exception e) {}
		}else if (ae.getSource() == backButton) {
			this.setVisible(false);
		}
	}

	public static void main(String[] args) {
		new UpdateInformation("").setVisible(true);

	}


}
