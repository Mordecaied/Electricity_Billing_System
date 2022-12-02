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

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;

import ebs.database.Conn;

public class SignUp extends JFrame implements ActionListener {

	private static final long serialVersionUID = 1L;

	JPanel accountPanel;

	JLabel userNameLabel;
	JLabel nameLabel;
	JLabel passwordLabel;
	JLabel createAccountAsJLabel;
	JLabel meterNumberJLabel;
	JLabel signUpuserIconlabel;

	JTextField usernameField;
	JTextField nameField;
	JTextField passwordField;
	JTextField meterNumberField;

	Choice userTypeChoice;

	JButton createButton;
	JButton backButton;


	SignUp(){
		setBounds(600, 250, 700, 400);
		setUpSignupPage();

	}

	void setUpSignupPage() {
		setUpAccountPanel();
		CreateSignUpPageIcon();
		setUpButtons();
	}
	void setUpAccountPanel() {
		createAccountPannel();
		createUserNameField();
		createNameField();
		createPasswordField();
		createUserTypeChoice();
		createMeterNumberField();	
	}

	void createAccountPannel() {
		accountPanel = new JPanel();
		accountPanel.setBounds(30, 30, 650, 300);
		accountPanel.setLayout(null);
		accountPanel.setBackground(Color.WHITE);
		accountPanel.setForeground(new Color(34, 139, 34));
		accountPanel.setBorder(new TitledBorder(new LineBorder(new Color(152,0,204), 2),
				"Create-Account", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(152,0,204)));
		add(accountPanel);
	}
	//UserName
	void createUserNameField() {
		userNameLabel = new JLabel("Username");
		userNameLabel.setForeground(Color.DARK_GRAY);
		userNameLabel.setFont(new Font("Tahoma", Font.BOLD, 14));
		userNameLabel.setBounds(100, 50, 100, 20);
		accountPanel.add(userNameLabel);

		usernameField = new JTextField();
		usernameField.setBounds(260, 50, 150, 20);
		accountPanel.add(usernameField);
	}

	//Name
	void createNameField() {
		nameLabel = new JLabel("Name");
		nameLabel.setForeground(Color.DARK_GRAY);
		nameLabel.setFont(new Font("Tahoma", Font.BOLD, 14));
		nameLabel.setBounds(100, 90, 100, 20);
		accountPanel.add(nameLabel);

		nameField = new JTextField();
		nameField.setBounds(260, 90, 150, 20);
		accountPanel.add(nameField);
	}

	//Password
	void createPasswordField() {
		passwordLabel = new JLabel("Password");
		passwordLabel.setForeground(Color.DARK_GRAY);
		passwordLabel.setFont(new Font("Tahoma", Font.BOLD, 14));
		passwordLabel.setBounds(100, 130, 100, 20);
		accountPanel.add(passwordLabel);

		passwordField = new JTextField();
		passwordField.setBounds(260, 130, 150, 20);
		accountPanel.add(passwordField);
	}
	//UserType
	void createUserTypeChoice() {
		createAccountAsJLabel = new JLabel("Create Account As");
		createAccountAsJLabel.setForeground(Color.DARK_GRAY);
		createAccountAsJLabel.setFont(new Font("Tahoma", Font.BOLD, 14));
		createAccountAsJLabel.setBounds(100, 170, 140, 20);
		accountPanel.add(createAccountAsJLabel);

		userTypeChoice = new Choice();
		userTypeChoice.add("Admin");
		userTypeChoice.add("Customer");
		userTypeChoice.setBounds(260, 170, 150, 20);
		accountPanel.add(userTypeChoice);

		userTypeChoice.addItemListener(new ItemListener() {
			@Override
			public void itemStateChanged(ItemEvent actionEvent) {
				String user = userTypeChoice.getSelectedItem();
				if (user.equals("Customer")) {
					meterNumberField.setVisible(true);
					meterNumberJLabel.setVisible(true);
				} else {
					meterNumberField.setVisible(false);
					meterNumberJLabel.setVisible(false);
				}

			}
		});
	}
	//MeterNumber
	void createMeterNumberField() {
		meterNumberJLabel = new JLabel("Meter Number");
		meterNumberJLabel.setForeground(Color.DARK_GRAY);
		meterNumberJLabel.setFont(new Font("Tahoma", Font.BOLD, 14));
		meterNumberJLabel.setBounds(100, 210, 140, 20);
		meterNumberJLabel.setVisible(false);
		accountPanel.add(meterNumberJLabel);

		meterNumberField = new JTextField();
		meterNumberField.setBounds(260, 210, 150, 20);
		meterNumberField.setVisible(false);
		accountPanel.add(meterNumberField);
	}
	//Buttons
	void setUpButtons(){
		buttonCreate();
		buttonBack();
	}
	//Create button
	void buttonCreate() {
		createButton = new JButton("Create");
		createButton.setBackground(new Color(152,0,204));
		createButton.setForeground(Color.WHITE);
		createButton.setBounds(140, 240, 120, 30);
		createButton.addActionListener(this);
		accountPanel.add(createButton);	

	}
	//Back Button
	void buttonBack() {
		backButton = new JButton("Back");
		backButton.setBackground(new Color(152,0,204));
		backButton.setForeground(Color.WHITE);
		backButton.setBounds(270, 240, 120, 30);
		backButton.addActionListener(this);
		accountPanel.add(backButton);	
	}
	//Signup Icon
	void CreateSignUpPageIcon(){
		ImageIcon signupUserImageIcon = new ImageIcon(ClassLoader.getSystemResource("Icons/signupUser.png"));
		Image signupUserImage = signupUserImageIcon.getImage().getScaledInstance(200, 200, Image.SCALE_SMOOTH);
		ImageIcon sigUpUserBigIcon = new ImageIcon(signupUserImage);
		signUpuserIconlabel = new JLabel(sigUpUserBigIcon);
		signUpuserIconlabel.setBounds(450, 30, 200, 200);
		accountPanel.add(signUpuserIconlabel);
	}

	@Override
	public void actionPerformed(ActionEvent actioEvent) {
		if (actioEvent.getSource() == createButton) {
			String username = usernameField.getText();
			String name = nameField.getText();
			String password = passwordField.getText();
			String userType = userTypeChoice.getSelectedItem();
			String meter = meterNumberField.getText();

			try {
				Conn conn = new Conn();
				String query = null;
				PreparedStatement pstmt;
				if(userType == "Admin") {
					query = "INSERT INTO login VALUES(?,?,?,?,?)";
					pstmt = conn.connection.prepareStatement(query);
				    pstmt.setString(1, meter);
				    pstmt.setString(2, username);
				    pstmt.setString(3, name);
				    pstmt.setString(4, password);
				    pstmt.setString(5, userType);
				    
				}else {
					query = "UPDATE login SET username = ?, name = ?, password =?, userType = ? "
							+"WHERE meter_no = ?";	
					pstmt = conn.connection.prepareStatement(query);
				    pstmt.setString(1, username);
				    pstmt.setString(2, name);
				    pstmt.setString(3, password);
				    pstmt.setString(4, userType);
				    pstmt.setString(5, meterNumberField.getText());
				}
				pstmt.executeUpdate();
				JOptionPane.showMessageDialog(null, "Account Created Successfully");
				this.setVisible(false);
				new Login().setVisible(true);	
			} catch (Exception e) {

			}
		} else if (actioEvent.getSource() == backButton) {
			this.setVisible(false);
			new Login().setVisible(true);
		}
	}
	
	public static void main(String[] args) {
		new SignUp().setVisible(true);
	}
}
