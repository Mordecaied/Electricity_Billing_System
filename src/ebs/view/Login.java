package ebs.view;

import java.awt.BorderLayout;
import java.awt.Choice;
import java.awt.Color;
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
import javax.swing.JPasswordField;	
import javax.swing.JTextField;


import ebs.database.Conn;

public class Login extends JFrame implements ActionListener {

	private static final long serialVersionUID = 1L;
	JLabel userNameL;
	JLabel passwordL;
	JLabel loggingInAsL;
	JLabel userIconL;

	JTextField userNameTF;
	JPasswordField passwordPF;
	Choice userTypeChoice;

	JButton loginButton;
	JButton cancelButton;
	JButton signupButton;

	ImageIcon loginIcon;
	ImageIcon cancelIcon;
	ImageIcon signupIcon;
	ImageIcon secondIcon;

	Login(){
		super("Login Page");
		setLayout(null);
		getContentPane().setBackground(Color.white);
		
		setUpLoggingPage();
		
		setLayout(new BorderLayout());
		setSize(640, 300);
		setLocation(600, 300);
		setVisible(true);
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	void setUpLoggingPage() {
		createUserNameField();
		createPasswordField();
		createLoginField();	
		setUpButtons();
		createLoginPageIcon();		
	}
       //User Name
	void createUserNameField() {
		//Create userName Label
		userNameL = new JLabel("Username");
		userNameL.setBounds(300, 20, 120, 20);
		add(userNameL);	
		//Create UserName Field
		userNameTF = new JTextField(15);
		userNameTF.setBounds(400, 20, 150, 20);
		add(userNameTF);
	}
       //Password
	void createPasswordField() {
		//Create password Label
		passwordL = new JLabel("Password");
		passwordL.setBounds(300, 60, 100, 20);
		add(passwordL);
		//Create Password field
		passwordPF = new JPasswordField(15);
		passwordPF.setBounds(400, 60, 150, 20);
		add(passwordPF);
	}
        //Login
	void createLoginField() {
		//create Login label
		loggingInAsL = new JLabel("Logging in as");
		loggingInAsL.setBounds(300, 100, 100, 20);
		add(loggingInAsL);
		//Add choice radio button
		userTypeChoice = new Choice();
		userTypeChoice.setBounds(400, 100, 150, 20);
		userTypeChoice.add("Admin");
		userTypeChoice.add("Customer");
		add(userTypeChoice);		
	}
	
	void setUpButtons() {
		buttonLogin();
		buttonCancel();
		buttonSignUp();
	}

	//Create Login Button
	void buttonLogin() {
		ImageIcon userIcon = new ImageIcon(ClassLoader.getSystemResource("Icons/login.png"));
		Image userImage = userIcon.getImage().getScaledInstance(16, 16, Image.SCALE_SMOOTH);
		loginButton = new JButton("Login", new ImageIcon(userImage));
		loginButton.setBackground(new Color(152,0,204));
		loginButton.setForeground(Color.WHITE);
		loginButton.setBounds(300, 160, 120, 25);
		add(loginButton);
		loginButton.addActionListener(this);
	}

	//Create cancel Button
	void buttonCancel() {
		ImageIcon cancelIcon = new ImageIcon(ClassLoader.getSystemResource("Icons/cancel.png"));
		Image cancelImage = cancelIcon.getImage().getScaledInstance(16, 16, Image.SCALE_SMOOTH);
		cancelButton = new JButton("Cancel", new ImageIcon(cancelImage));
		cancelButton.setBackground(new Color(152,0,204));
		cancelButton.setForeground(Color.WHITE);
		cancelButton.setBounds(430, 160, 120, 25);
		add(cancelButton);
		cancelButton.addActionListener(this);
	}
	
	//Create signUp Button
	void buttonSignUp() {
		ImageIcon signupIcon = new ImageIcon(ClassLoader.getSystemResource("Icons/signup.png"));
		Image signupImage = signupIcon.getImage().getScaledInstance(16, 16, Image.SCALE_SMOOTH);
		signupButton = new JButton("Signup", new ImageIcon(signupImage));
		signupButton.setBackground(new Color(152,0,204));
		signupButton.setForeground(Color.WHITE);
		signupButton.setBounds(360, 200, 130, 25);
		add(signupButton);
		signupButton.addActionListener(this);
	}
	//Create Login page big Icon
	void createLoginPageIcon() {
		ImageIcon loginUserIcon = new ImageIcon(ClassLoader.getSystemResource("Icons/loginUser.png"));
		Image loginUserImage = loginUserIcon.getImage().getScaledInstance(250, 250, Image.SCALE_SMOOTH);
		ImageIcon loginUserBigIcon = new ImageIcon(loginUserImage);
		userIconL = new JLabel(loginUserBigIcon);
        userIconL.setBounds(0, 0, 250, 250);
        add(userIconL);
	}
	
	@Override
	public void actionPerformed(ActionEvent actionEvent) {
		//Connect to DB and search for matching username and password
		if (actionEvent.getSource() == loginButton) {
			try {
				String username = userNameTF.getText();
				String password = String.valueOf(passwordPF.getPassword());
				String userType = userTypeChoice.getSelectedItem();
				
				Conn conn = new Conn();
				String query = "SELECT * from login WHERE username = ? AND password = ? AND user = ?";
				PreparedStatement pstmt = conn.connection.prepareStatement(query);
				pstmt.setString(1, username);
				pstmt.setString(2, password);
				pstmt.setString(3, userType);
				ResultSet rs = pstmt.executeQuery();
				if (rs.next()) {
					String meter = rs.getString("meter_no");
					new MainPage(meter, userType).setVisible(true);
					this.setVisible(false);
					
				} else {
					JOptionPane.showMessageDialog(null, "Username/password can't be found. \nPlease try again or use the signup button to create an account");
					userNameTF.setText("");
					passwordPF.setText("");
				}	
			} catch (Exception e) {
				e.printStackTrace();
				System.out.println("Error: "+ e);
			}
		
		}else if (actionEvent.getSource() == cancelButton) {
			this.setVisible(false);
			
		//Signup if account doen't exist
		}else if (actionEvent.getSource() == signupButton) {
			this.setVisible(false);
			new SignUp().setVisible(true);
		}
		
	}

	public static void main(String[] args) {
		new Login().setVisible(true);

	}
}
