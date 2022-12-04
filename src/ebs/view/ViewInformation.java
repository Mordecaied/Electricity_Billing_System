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
import ebs.database.Conn;

public class ViewInformation extends JFrame implements ActionListener {

	private static final long serialVersionUID = 1L;

	private JLabel titleL;
	private JLabel nameL;
	private JLabel meterL;
	private JLabel addressL;
	private JLabel cityL;
	private JLabel stateL;
	private JLabel emailL;
	private JLabel phoneL;
	private JLabel imageLabel;

	private JLabel customerNameTL;
	private JLabel meterNumberTL;
	private JLabel addressTL;
	private JLabel cityTL;
	private JLabel stateTL;
	private JLabel emailTL;
	private JLabel phoneTL;

	private JButton backButton;

	private String meter;

	public ViewInformation(String meter) {
		setBounds(600, 250, 850, 650);
		getContentPane().setBackground(Color.WHITE);
		setLayout(null);

		setUpCustomerInformation();	
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	private void setUpCustomerInformation() {
		titleL = new JLabel("VIEW CUSTOMER INFORMATION");
		titleL.setBounds(250, 0, 500, 40);
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
		getBackButton();
		getCustomerDetails();
	}

	private void getUpdateImage() {
		ImageIcon userIcon = new ImageIcon(ClassLoader.getSystemResource("Icons/loginUser.png"));
		Image userImage = userIcon.getImage().getScaledInstance(600, 300, Image.SCALE_SMOOTH);
		ImageIcon scaledImageIcon = new ImageIcon(userImage);
		imageLabel = new JLabel(scaledImageIcon);
		imageLabel.setBounds(20, 350, 600, 300);
		add(imageLabel);	
	}

	private void getCustomerName() {
		nameL = new JLabel("Name");
		nameL.setBounds(70, 80, 100, 20);
		add(nameL);
		customerNameTL = new JLabel();
		customerNameTL.setBounds(250, 80, 100, 20);
		add(customerNameTL);
	}

	private void getMeterNumber() {
		meterL = new JLabel("Meter Number");
		meterL.setBounds(70, 140, 100, 20);
		add(meterL);
		meterNumberTL = new JLabel();
		meterNumberTL.setBounds(250, 140, 100, 20);
		add(meterNumberTL);
	}

	private void getAddress() {
		addressL = new JLabel("Address");
		addressL.setBounds(70, 200, 100, 20);
		add(addressL);
		addressTL = new JLabel();
		addressTL.setBounds(250, 200, 100, 20);
		add(addressTL);
	}

	private void getCity() {
		cityL = new JLabel("City");
		cityL.setBounds(70, 260, 100, 20);
		add(cityL);
		cityTL = new JLabel();
		cityTL.setBounds(250, 260, 200, 20);
		add(cityTL);
	}

	private void getCusState() {
		stateL = new JLabel("State");
		stateL.setBounds(500, 80, 100, 20);
		add(stateL);
		stateTL = new JLabel();
		stateTL.setBounds(650, 80, 100, 20);
		add(stateTL);
	}

	private void getEmail() {
		emailL = new JLabel("Email");
		emailL.setBounds(500, 140, 100, 20);
		add(emailL);
		emailTL  = new JLabel();
		emailTL.setBounds(650, 140, 100, 20);
		add(emailTL);
	}

	private void getPhone() {
		phoneL = new JLabel("Phone");
		phoneL.setBounds(500, 200, 100, 20);
		add(phoneL);
		phoneTL = new JLabel();
		phoneTL.setBounds(650, 200, 100, 20);
		add(phoneTL);
	}

	private void getBackButton() {
		backButton = new JButton("Back");
		backButton.setBackground(Color.BLACK);
		backButton.setForeground(Color.WHITE);
		backButton.setBounds(350, 340, 100, 25);
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
				customerNameTL.setText(rs.getString(1));
				meterNumberTL.setText(rs.getString(2));
				addressTL.setText(rs.getString(3));
				cityTL.setText(rs.getString(4));
				stateTL.setText(rs.getString(5));
				emailTL.setText(rs.getString(6));
				phoneTL.setText(rs.getString(7));
			}
		} catch (Exception e) {}
	}


	@Override
	public void actionPerformed(ActionEvent e) {
		this.setVisible(false);
	}

	public static void main(String[] args) {
		new ViewInformation("").setVisible(true);

	}
}